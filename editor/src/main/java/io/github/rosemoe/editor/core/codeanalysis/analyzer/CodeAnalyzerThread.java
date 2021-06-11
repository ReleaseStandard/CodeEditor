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
package io.github.rosemoe.editor.core.codeanalysis.analyzer;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.codeanalysis.results.Callback;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.codeanalysis.CodeAnalyzerResultColor;

/**
 * AnalyzeThread to control
 */
public class CodeAnalyzerThread extends Thread {

    public final Object lock;
    private volatile boolean waiting = false;
    private ContentMapController content;
    public long mOpStartTime;
    CodeAnalyzer codeAnalyzer;
    public Callback mCallback;
    /**
     * Create a new thread
     * @param content The ContentMapController to analyze
     */
    public CodeAnalyzerThread(ContentMapController content, CodeAnalyzer codeAnalyzer) {
        this.lock = new Object();
        this.content = content;
        this.codeAnalyzer = codeAnalyzer;
    }

    /**
     * Run analysis and update the view according to new values.
     */
    @Override
    public void run() {
        try {
            do {
                Delegate d = new Delegate();
                mOpStartTime = System.currentTimeMillis();
                do {
                    waiting = false;
                    StringBuilder c = content.toStringBuilder();
                    codeAnalyzer.lockBuild();
                    codeAnalyzer.analyze(c, d);
                    if (waiting) {
                        codeAnalyzer.clearInBuild();
                    }
                    codeAnalyzer.unlockBuild();
                } while (waiting);

                codeAnalyzer.updateView();

                codeAnalyzer.lockBuild();
                CodeAnalyzerResultColor colorsResult = (CodeAnalyzerResultColor) codeAnalyzer.getResultInBuild("color");
                if ( colorsResult != null ) {
                    colorsResult.map.addNormalIfNull();
                }
                codeAnalyzer.unlockBuild();

                try {
                    if (mCallback != null) {
                        mCallback.onAnalyzeDone(codeAnalyzer);
                    }
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }

                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    Logger.v("Analyze daemon is being interrupted -> Exit");
                    handleError();
                    break;
                }
            } while (true);
        } catch (Exception ex) {
            Logger.v("Analyze daemon got exception -> Exit",ex);
            handleError();
        }
    }

    public void handleError() {
        codeAnalyzer.lockBuild();
        codeAnalyzer.clearInBuild();
        codeAnalyzer.unlockBuild();
    }

    /**
     * New content has been sent
     * Notify us to restart
     *
     * @param content New source
     */
    public synchronized void restartWith(ContentMapController content) {
        Logger.v("Restarting analysis thread with some content");
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
    private static int sThreadId = 0;
    private synchronized static int nextThreadId() {
        sThreadId++;
        return sThreadId;
    }
    public static CodeAnalyzerThread newInstance(ContentMapController origin, CodeAnalyzer codeAnalyzer) {
        CodeAnalyzerThread thread;
        thread = new CodeAnalyzerThread(origin, codeAnalyzer);
        thread.setName("TextAnalyzeDaemon-" + nextThreadId());
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}