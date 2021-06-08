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

import java.util.List;

import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.SpanMapController;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.spans.processors.SpanRecycler;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;
import io.github.rosemoe.editor.mvc.model.util.BlockLineManager;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;

/**
 * Container for objects that are going to be recycled
 *
 * @author Rose
 */
public class CodeAnalyzerResultRecycler {

        /**
         * Process objects currently in the recycler.
         */
        public void recycle() {

        }

        /**
         * Put an analysis result to digestion by the recycler.
         * @param result
         */
        public void putToDigest(CodeAnalyzerResult result) {

        }
}