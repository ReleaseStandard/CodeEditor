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
package io.github.rosemoe.editor.core;

import java.util.List;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.analysis.CodeAnalyzerResultContent;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.codeanalysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanMapController;
import io.github.rosemoe.editor.core.BlockLineModel;

/**
 * Display the result of analysis.
 * Update spans in response to the analysis.
 */
public class TextAnalyzerView {

    CodeAnalyzer analyzer;

    /**
     * Create a new result
     */
    public TextAnalyzerView(CodeAnalyzer analyzer) {
        this.analyzer = analyzer;
    }


    public SpanMapController getSpanMap() {
        return ((CodeAnalyzerResultColor)analyzer.getResult("color")).map;
    }

    public List<BlockLineModel> getContent() {
        return ((CodeAnalyzerResultContent)analyzer.getResult("content")).mBlocks;
    }

    /**
     * Get list of code blocks
     *
     * @return code blocks
     */
    public List<BlockLineModel> getBlocks() {
        return getContent();
    }

    /**
     * Reset the anaylysis result
     */
    public void clear() {
        analyzer.clear();
        //mExtra = null; TODO
    }
}
