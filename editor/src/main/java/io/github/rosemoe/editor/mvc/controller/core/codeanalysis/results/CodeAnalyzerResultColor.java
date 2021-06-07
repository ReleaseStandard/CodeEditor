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
package io.github.rosemoe.editor.mvc.controller.core.codeanalysis.results;

import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.TokenEmitterResult;
import io.github.rosemoe.editor.mvc.controller.widgets.color.spans.SpanController;
import io.github.rosemoe.editor.mvc.controller.widgets.color.spans.SpanMapController;
import io.github.rosemoe.editor.util.Logger;

public class CodeAnalyzerResultColor extends TokenEmitterResult {

    SpanMapController map = new SpanMapController();

    @Override
    public void putResult(Object... args) {
        if ( args.length < 0 ) {

        }
        if ( args.length >= 3 ) {
            addIfNeeded(args[0],args[1],args[2]);
        }
    }
    /**
     * Add a new span if required (color is different from last)
     *  @param spanLine Line
     * @param column   Column
     * @param color  Type
     */
    private void addIfNeeded(Object spanLine, Object column, Object color) {
        Logger.debug("spanLine=",spanLine,",column=",column,",color=",color);
        add((Integer)spanLine, SpanController.obtain((Integer)column, (Integer)color));
    }
    /**
     * Add a span directly
     * Note: the line should always >= the line of span last committed
     * if two spans are on the same line, you must add them in order by their column
     *
     * @param spanLine The line position of span
     * @param span     The span
     */
    private void add(int spanLine, SpanController span) {
        map.getAddIfNeeded(spanLine).add(span);
    }
}

