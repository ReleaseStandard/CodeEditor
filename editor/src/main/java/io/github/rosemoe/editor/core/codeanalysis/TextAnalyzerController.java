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
package io.github.rosemoe.editor.core.codeanalysis;

import java.util.List;

import io.github.rosemoe.editor.mvc.controller.content.CodeAnalyzerResultContent;
import io.github.rosemoe.editor.mvc.controller.content.ContentMapController;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.core.codeanalysis.results.Callback;
import io.github.rosemoe.editor.mvc.controller.widgets.colorAnalyzer.analysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.mvc.controller.widgets.colorAnalyzer.analysis.spans.SpanMapController;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;

/**
 * This is a manager of analyzing text
 * It inject data into the TextAnalyzerView
 * @author Rose
 */
public class TextAnalyzerController {


    /**
     * Debug:Start time
     */
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
        mCodeAnalyzer = codeAnalyzer0;
        if( getSpanMap() != null ) {
            getSpanMap().addNormalIfNull();
        }
    }

    public SpanMapController getSpanMap() {
        CodeAnalyzerResultColor color = (CodeAnalyzerResultColor)mCodeAnalyzer.getResultListener("color");
        return color == null ? null : color.map;
    }

    public List<BlockLineModel> getContent() {
        CodeAnalyzerResultContent content = (CodeAnalyzerResultContent)mCodeAnalyzer.getResultListener("content");
        return content == null ? null : content.mBlocks;
    }

    /**
     * Set callback of analysis
     *
     * @param cb New callback
     */
    public void setCallback(Callback cb) {
        if ( mCodeAnalyzer.isRunning() ) {
            mCodeAnalyzer.mThread.mCallback = cb;
        }
    }

    /**
     * Stop the text analyzer
     */
    public void shutdown() {
        mCodeAnalyzer.shutdown();
    }

    /**
     * Called from painting process to recycle outdated objects for reusing
     */
    public void notifyRecycle() {
        mCodeAnalyzer.recycle();
    }

    /**
     * Analyze the given text
     *
     * @param origin The source text
     */
    public synchronized void analyze(ContentMapController origin) {
        mCodeAnalyzer.start(origin);
    }

}

