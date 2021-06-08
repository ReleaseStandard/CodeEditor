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
package io.github.rosemoe.editor.core.codeanalysis.results;

import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzer;

/**
 * Callback for text analyzing
 *
 * @author Rose
 */
public interface Callback {

    /**
     * Called when analyze result is available
     * Count of calling this method is not always equal to the count you call
     *
     * @param analyzer Host TextAnalyzerView
     */
    void onAnalyzeDone(CodeAnalyzer analyzer);

}