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

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import io.github.rosemoe.editor.mvc.controller.widgets.colorAnalyzer.analysis.spans.SpanLineController;
import io.github.rosemoe.editor.mvc.controller.widgets.contentAnalyzer.ContentMapController;
import io.github.rosemoe.editor.core.util.Logger;

/**
 * This could be :
 *  a color analysis (display color (spans at screen)),
 *  a content analysis (display text at screen).
 *  a spellcheck result (display misspelled words at screen).
 */
public abstract class CodeAnalyzer {

    /**
     * This switch is setup by the analyzer to prevent the view from painting non displayed regions.
     * Set suppress switch for editor
     * What is 'suppress switch' ?:
     * Suppress switch is a switch size for code block line drawing
     * and for the process to find out which code block the cursor is in.
     * Because the code blocks are not saved by the order of both start line and
     * end line,we are unable to know exactly when we should stop the process.
     * So without a suppress switch,it will cost a large of time to search code
     * blocks.So I added this switch.
     * A suppress switch is the code block count in the first layer code block
     * (as well as its sub code blocks).
     * If you are unsure,do not set it.
     * The default value if Integer.MAX_VALUE
     *
     * @param suppressSwitch Suppress switch
     */
    public int mSuppressSwitch = Integer.MAX_VALUE;

    public ReentrantLock resultsLock = new ReentrantLock();
    public HashMap<String, CodeAnalyzerResult> results = new HashMap<>();
    public ReentrantLock inProcessResultsLock = new ReentrantLock();
    public HashMap<String, CodeAnalyzerResult> inProcessResults = new HashMap<>();


    public CodeAnalyzer() {
        if (Logger.DEBUG) {
            new Thread() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            int a = resultsLock.getHoldCount();
                            int b = inProcessResultsLock.getHoldCount();
                            Thread.sleep(10000);
                            if ( a == resultsLock.getHoldCount()
                            ) {
                                Logger.v("WARNING you probably have a deadlock on in builded resutls (view)");
                            }
                            if ( b == inProcessResultsLock.getHoldCount()
                            ) {
                                Logger.v("WARNING you probably have a deadlock on in building resutls (processing)");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
    /**
     * Method responsible from building results in inProcessResults HashMap.
     * Each implementation of CodeAnalyzer is free to provide as much CodeAnalyzerResult as it want or none.
     * eg : CodeAnalyzerResultColor, CodeAnalyzerResultSpellCheck, CodeAnalyzerResultContent.
     *      CodeAnalyzerResultUnderlineAbcWords, CodeAnalyzerResultSyntaxeChecking.
     *
     * @param content Content to analyze
     * @param delegate
     */
    protected abstract void analyze(CharSequence content, CodeAnalyzerThread.Delegate delegate);

    /**
     * Dispatch (partial) results of an analysis on result objects, to show the result of processing, use updateView()
     * (No matter what type is expected by results)
     */
    public void dispatchResultPart(Object ...args) {
        inProcessResultsLock.lock();
        for(CodeAnalyzerResult result : inProcessResults.values()) {
            if ( result != null ) {
                result.dispatchResult(args);
            } else {
                Logger.debug("Cannot given results to the object since it is null");
            }
        }
        inProcessResultsLock.unlock();
    }

    /**
     * This add a result listener on the code analyzer.
     * @param listener
     */
    public void addResultListener(String name, CodeAnalyzerResult listener) {
        lockView();
        lockBuild();

        Logger.v("name=",name,",listener=",listener);
        results.put(name,listener);
        inProcessResults.put(name, listener.clone());

        unlockBuild();
        unlockView();
    }

    /**
     * Launch a clear for all result listener inside this analyzer.
     */
    public void clear() {
        mSuppressSwitch = Integer.MAX_VALUE;
        clearBuilded();
        clearInBuild();
    }
    /**
     * Clear what have been done in the analyzer (view).
     */
    public void clearBuilded() {
        for(CodeAnalyzerResult result : results.values()) {
            if ( result != null ) {
                result.clear();
            }
        }
    }
    /**
     * Clear what is being done in the analyzer.
     */
    public void clearInBuild() {
        for(CodeAnalyzerResult inProcessResult : inProcessResults.values()) {
            if ( inProcessResult != null ) {
                inProcessResult.clear();
            }
        }
    }

    /**
     * Get the result listener, in mean in build results
     *
     * @param name
     * @return
     */
    public CodeAnalyzerResult getResultInBuild(String name) {
        return inProcessResults.get(name);
    }

    /**
     * caller is responsible from removing the lock.
     * @param name
     * @return
     */
    public CodeAnalyzerResult getResult(String name) {
        return results.get(name);
    }

    /**
     * Recycle the content of all analysis result.
     */
    public void recycle() {
        Logger.debug("recycle results");
        /*for(CodeAnalyzerResult result : results.values()) {
            if ( result != null ) {
                result.recycle();
            }
        }*/
        //for(CodeAnalyzerResult result : concurrentSafeGetValues(results)) {
            //
        //}
    }

    /**
     * Lock modifications done to the view.
     */
    public void lockView() {
        lock(resultsLock);
    }
    /**
     * Allow again modifications to the view.
     */
    public void unlockView() {
        unlock(resultsLock);
    }
    /**
     * lock results being builded.
     */
    public void lockBuild() {
        lock(inProcessResultsLock);
    }
    /**
     * Unlock results beeing builded.
     */
    public void unlockBuild() {
        unlock(inProcessResultsLock);
    }
    private void unlock(ReentrantLock lock) {
        if ( lock != null &&
                lock.isHeldByCurrentThread() &&
                lock.isLocked() ) {
            lock.unlock();
        }
    }
    private void lock(ReentrantLock lock) {
        lock.lock();
    }
    /**
     * This call will put inProcessResults to results and create an
     * empty inProcessResults.
     */
    public void updateView() {
        lockView();
        lockBuild();
        Logger.debug("Update the view");
        for(Map.Entry<String, CodeAnalyzerResult> e : results.entrySet()) {
            CodeAnalyzerResult result = e.getValue();
            String key = e.getKey();
            CodeAnalyzerResult newResult = inProcessResults.get(key);
            results.put(key, newResult);
            if ( result != null ) {
                result.recycler.putToDigest(result);
                result.clear();
            }
            inProcessResults.put(key, result);
        }
        unlockView();
        unlockBuild();
    }

    /**
     * Start an analysis thread of a given text/code.
     */
    public CodeAnalyzerThread mThread = null;
    public synchronized void start(ContentMapController origin) {
        CodeAnalyzerThread thread = this.mThread;
        if (thread == null || !thread.isAlive()) {
            thread = this.mThread = CodeAnalyzerThread.newInstance(origin, this);
        } else {
            thread.restartWith(origin);
            synchronized (thread.lock) {
                thread.lock.notify();
            }
        }
    }

    /**
     * Stopping analysis thread.
     */
    public void shutdown() {
        final CodeAnalyzerThread thread = mThread;
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            mThread = null;
        }
    }

    /**
     * Is analyzer's thread running ?
     * @return
     */
    public boolean isRunning() {
        return mThread != null;
    }

    /**
     * Dump analyzer content.
     */
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        lockView();
        Logger.debug(offset,"CodeAnalyzer:");
        Logger.debug(offset,"  results = " + results.size());
        for(Map.Entry<String, CodeAnalyzerResult> entry : results.entrySet()) {
            Logger.debug(offset, entry.getKey());
            if ( entry.getValue() != null ) {
                entry.getValue().dump(offset);
            } else {
                Logger.debug("entry getValue is null");
            }
        }
        Logger.debug(offset,"  inProcessResults = " + inProcessResults.size());
        for(Map.Entry<String, CodeAnalyzerResult> entry : results.entrySet()) {
            Logger.debug(offset, entry.getKey());
            if ( entry.getValue() != null ) {
                entry.getValue().dump(offset);
            } else {
                Logger.debug("entry getValue is null");
            }
        }
        unlockView();
    }

}

