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
package io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.rosemoe.editor.mvc.controller.content.ContentMapController;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.TextAnalyzerController;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.results.Callback;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.util.Logger;

/**
 * This could be :
 *  a color analysis (display color (spans at screen)),
 *  a content analysis (display text at screen).
 *  a spellcheck result (display misspelled words at screen).
 */
public abstract class CodeAnalyzer {

    public HashMap<String, CodeAnalyzerResult> results = new HashMap<>();
    public HashMap<String, CodeAnalyzerResult> inProcessResults = new HashMap<>();

    protected abstract void analyze(CharSequence content, CodeAnalyzerThread.Delegate delegate);

    /**
     * Dispatch (partial) results of an analysis on result objects, to show the result of processing, use updateView()
     * (No matter what type is expected by results)
     */
    public void dispatchResultPart(Object ...args) {
        for(CodeAnalyzerResult result : inProcessResults.values()) {
            result.dispatchResult(args);
        }
    }

    /**
     * This add a result listener on the code analyzer.
     * @param listener
     */
    public void addResultListener(String name, CodeAnalyzerResult listener) {
        results.put(name,listener);
    }

    /**
     * Launch a clear for all result listener inside this analyzer.
     */
    public void clear() {
        clearBuilded();
        clearInBuild();
    }
    /**
     * Clear what have been done in the analyzer (view).
     */
    public void clearBuilded() {
        for(CodeAnalyzerResult result : results.values()) {
            result.clear();
        }
    }
    /**
     * Clear what is being done in the analyzer.
     */
    public void clearInBuild() {
        for(CodeAnalyzerResult inProcessResult : inProcessResults.values()) {
            inProcessResult.clear();
        }
    }

    /**
     * Get the result listener for a given name.
     * @param name
     * @return
     */
    public CodeAnalyzerResult getResultListener(String name) {
        Logger.debug("name=",name);
        return results.get(name);
    }

    /**
     * Recycle the content of all analysis result.
     */
    public void recycle() {
        for(CodeAnalyzerResult result : results.values()) {
            result.recycler.recycle();
        }
    }

    /**
     * This call will put inProcessResults to results and create an
     * empty inProcessResults.
     */
    public void updateView() {
        for(Map.Entry<String, CodeAnalyzerResult> e : results.entrySet()) {
            CodeAnalyzerResult result = e.getValue();
            String key = e.getKey();
            result.recycler.putToDigest(result);
            CodeAnalyzerResult newResult = inProcessResults.get(key);
            results.put(key, newResult);
            result.clear();
            inProcessResults.put(key, result);
        }
    }

    private Callback mCallback;


    /**
     * Start an analysis thread of a given text/code.
     */
    public CodeAnalyzerThread mThread;
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
     * Dump analyzer content.
     */
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
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
    }
}

