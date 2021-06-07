/*
 *   Copyright 2020-2021 Rosemoe
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package io.github.rosemoe.editor.mvc.controller.core.codeanalysis;

import android.util.Log;

import java.util.List;

import io.github.rosemoe.editor.mvc.controller.content.ContentMapController;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.SpanMapController;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.mvc.model.util.BlockLineManager;
import io.github.rosemoe.editor.processor.spanmap.Recycler;

/**
 * This is a manager of analyzing text
 * It inject data into the TextAnalyzerView
 * @author Rose
 */
public class TextAnalyzerController {

    private static int sThreadId = 0;
    private final ResultRecycler recycler = new ResultRecycler();
    private final Object mLock = new Object();
    /**
     * Debug:Start time
     */
    public long mOpStartTime;
    private TextAnalyzerView currentResult;
    private Callback mCallback;
    private AnalyzeThread mThread;
    public CodeAnalyzer mCodeAnalyzer;
    /**
     * Create a new manager for the given codeAnalyzer
     *
     * @param codeAnalyzer0 Target codeAnalyzer
     */
    public TextAnalyzerController(CodeAnalyzer codeAnalyzer0) {
        if (codeAnalyzer0 == null) {
            throw new IllegalArgumentException();
        }
        currentResult = new TextAnalyzerView();
        //TODO:currentResult.addNormalIfNull();
        mCodeAnalyzer = codeAnalyzer0;
    }

    private synchronized static int nextThreadId() {
        sThreadId++;
        return sThreadId;
    }

    /**
     * Set callback of analysis
     *
     * @param cb New callback
     */
    public void setCallback(Callback cb) {
        mCallback = cb;
    }

    /**
     * Stop the text analyzer
     */
    public void shutdown() {
        final AnalyzeThread thread = mThread;
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            mThread = null;
        }
    }

    /**
     * Called from painting process to recycle outdated objects for reusing
     */
    public void notifyRecycle() {
        recycler.recycle();
    }

    /**
     * Analyze the given text
     *
     * @param origin The source text
     */
    public synchronized void analyze(ContentMapController origin) {
        AnalyzeThread thread = this.mThread;
        if (thread == null || !thread.isAlive()) {
            Log.d("TextAnalyzerView", "Starting a new thread for analyzing");
            thread = this.mThread = new AnalyzeThread(mLock, mCodeAnalyzer, origin);
            thread.setName("TextAnalyzeDaemon-" + nextThreadId());
            thread.setDaemon(true);
            thread.start();
        } else {
            thread.restartWith(origin);
            synchronized (mLock) {
                mLock.notify();
            }
        }
    }

    /**
     * Get analysis result
     *
     * @return Result of analysis
     */
    public TextAnalyzerView getResult() {
        return currentResult;
    }

    /**
     * Callback for text analyzing
     *
     * @author Rose
     */
    public interface Callback {

        /**
         * Called when analyze result is available
         * Count of calling this method is not always equal to the count you call {@link TextAnalyzerController#analyze(ContentMapController)}
         *
         * @param analyzer Host TextAnalyzerView
         */
        void onAnalyzeDone(TextAnalyzerController analyzer);

    }

    /**
     * Container for objects that is going to be recycled
     *
     * @author Rose
     */
    static class ResultRecycler {

        SpanMapController spanMap;
        List<BlockLineModel> blockLines;

        /**
         * Process objects currently in the recycler.
         */
        void recycle() {
            BlockLineManager.recycle(blockLines);
            Recycler.getInstance().recycle(spanMap);
            spanMap = null;
            blockLines = null;
        }

        /**
         * Put an analysis result to digestion by the recycler.
         * @param result
         */
        void putToDigest(TextAnalyzerView result) {
            spanMap = result.spanMap;
            blockLines = result.mBlocks;
        }

    }

    /**
     * AnalyzeThread to control
     */
    public class AnalyzeThread extends Thread {

        private final CodeAnalyzer codeAnalyzer;
        private final Object lock;
        private volatile boolean waiting = false;
        private ContentMapController content;

        /**
         * Create a new thread
         *  @param a       The CodeAnalyzerController to call
         * @param content The ContentMapController to analyze
         */
        public AnalyzeThread(Object lock, CodeAnalyzer a, ContentMapController content) {
            this.lock = lock;
            codeAnalyzer = a;
            this.content = content;
        }

        /**
         * Run analysis and update the view according to new values.
         */
        @Override
        public void run() {
            try {
                do {
                    TextAnalyzerView newResult = new TextAnalyzerView();
                    Delegate d = new Delegate();
                    mOpStartTime = System.currentTimeMillis();
                    do {
                        waiting = false;
                        StringBuilder c = content.toStringBuilder();
                        codeAnalyzer.analyze(c, d);
                        if (waiting) {
                            newResult.clear();
                        }
                    } while (waiting);

                    recycler.putToDigest(currentResult);
                    currentResult = newResult;
                    //TODO:newResult.addNormalIfNull();
                    try {
                        if (mCallback != null) {
                            mCallback.onAnalyzeDone(TextAnalyzerController.this);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    try {
                        synchronized (lock) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        Log.d("AnalyzeThread", "Analyze daemon is being interrupted -> Exit");
                        break;
                    }
                } while (true);
            } catch (Exception ex) {
                Log.i("AnalyzeThread", "Analyze daemon got exception -> Exit", ex);
            }
        }

        /**
         * New content has been sent
         * Notify us to restart
         *
         * @param content New source
         */
        public synchronized void restartWith(ContentMapController content) {
            waiting = true;
            this.content = content;
        }

        /**
         * A delegate for token stream loop
         * To make it stop in time
         */
        public class Delegate {

            /**
             * Whether new input is set
             * If it returns true,you should stop your tokenizing at once
             *
             * @return Whether re-analyze required
             */
            public boolean shouldAnalyze() {
                return !waiting;
            }

        }

    }
}

