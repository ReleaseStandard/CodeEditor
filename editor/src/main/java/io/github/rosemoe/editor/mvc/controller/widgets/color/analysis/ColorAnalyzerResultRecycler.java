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
package io.github.rosemoe.editor.mvc.controller.widgets.color.analysis;

import java.util.List;

import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzerResult;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzerResultRecycler;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.SpanMapController;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.processors.SpanRecycler;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;
import io.github.rosemoe.editor.mvc.model.util.BlockLineManager;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.util.Logger;

public class ColorAnalyzerResultRecycler extends CodeAnalyzerResultRecycler {
    SpanMapController spanMap;

    /**
     * Process objects currently in the recycler.
     */
    public void recycle() {
        SpanRecycler.getInstance().recycle(spanMap);
        spanMap = null;
    }

    /**
     * Put an analysis result to digestion by the recycler.
     * @param result
     */
    @Override
    public void putToDigest(CodeAnalyzerResult result) {
        if ( ! (result instanceof CodeAnalyzerResultColor)) {
            Logger.debug("It don't digest that sorry");
            return;
        }
        CodeAnalyzerResultColor caro = (CodeAnalyzerResultColor) result;
        spanMap = caro.map;
    }
}
