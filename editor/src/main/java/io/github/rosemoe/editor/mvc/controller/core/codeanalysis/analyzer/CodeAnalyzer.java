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

import java.util.ArrayList;

import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.TextAnalyzerController;

/**
 * This could be :
 *  a color analysis (display color (spans at screen)),
 *  a content analysis (display text at screen).
 *  a spellcheck result (display misspelled words at screen).
 */
public abstract class CodeAnalyzer {

    public ArrayList<CodeAnalyzerResult> results = new ArrayList<>();

    public abstract void analyze(CharSequence content, TextAnalyzerController.AnalyzeThread.Delegate delegate);

    /**
     * Dispatch (partial) results of an analysis on results objects.
     * (No matter what type is expected by results)
     */
    public void dispatchResult(Object ...args) {
        for(CodeAnalyzerResult result : results) {
            result.putResult(args);
        }
    }

    /**
     * This add a result listener on the code analyzer.
     * @param listener
     */
    public void addResultListener(CodeAnalyzerResult listener) {
        results.add(listener);
    }
}

