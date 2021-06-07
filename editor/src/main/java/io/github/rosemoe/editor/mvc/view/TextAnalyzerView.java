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
package io.github.rosemoe.editor.mvc.view;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.mvc.controller.content.CodeAnalyzerResultContent;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.SpanMapController;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.processors.SpanRecycler;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;
import io.github.rosemoe.editor.mvc.model.util.BlockLineManager;

/**
 * Display the result of analysis.
 * Update spans in response to the analysis.
 */
public class TextAnalyzerView {

    public Object mExtra;
    protected int mSuppressSwitch = Integer.MAX_VALUE;
    CodeAnalyzer analyzer;

    /**
     * Create a new result
     */
    public TextAnalyzerView(CodeAnalyzer analyzer) {
        this.analyzer = analyzer;
    }


    public SpanMapController getSpanMap() {
        return ((CodeAnalyzerResultColor)analyzer.getResultListener("color")).map;
    }

    public List<BlockLineModel> getContent() {
        return ((CodeAnalyzerResultContent)analyzer.getResultListener("content")).mBlocks;
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
     * Returns suppress switch
     *
     * @return suppress switch
     * @see TextAnalyzerView#setSuppressSwitch(int)
     */
    public int getSuppressSwitch() {
        return mSuppressSwitch;
    }

    /**
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
    public void setSuppressSwitch(int suppressSwitch) {
        mSuppressSwitch = suppressSwitch;
    }

    /**
     * Reset the anaylysis result
     */
    public void clear() {
        analyzer.clear();
        mSuppressSwitch = Integer.MAX_VALUE;
        mExtra = null;
    }
}
