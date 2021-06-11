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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.codeanalysis;

import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzerResult;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.tokenemitter.TokenEmitterResult;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.ColorSchemeController;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanController;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanMapController;
import io.github.rosemoe.editor.core.util.Logger;

/**
 * This class provide Interface that every language code analyzer will input into.
 */
public class CodeAnalyzerResultColor extends TokenEmitterResult {

    /**
     * A color result must have a theme attached to it.
     */
    public ColorSchemeController theme = null;
    public SpanMapController map = new SpanMapController();

    public CodeAnalyzerResultColor() {
        recycler = new ColorAnalyzerResultRecycler();
        map.addNormalIfNull();
    }
    @Override
    public void dispatchResult(Object... args) {
        if ( args.length < 0 ) {
            map.addNormalIfNull();
        }
        if ( args.length >= 3 ) {
            if ( args[2] instanceof String ) {
                addFromColorName(args[0],args[1],args[2]);
            } else {
                addIfNeeded(args[0], args[1], args[2]);
            }
        }
    }

    @Override
    public boolean isReady() {
        return map != null && theme != null;
    }
    @Override
    public void clear() {
        if ( map != null ) {
            map.clear();
        }
    }

    @Override
    public CodeAnalyzerResult clone() {
        return new CodeAnalyzerResultColor();
    }

    /**
     * Add a new span if required (color is different from last)
     *  @param spanLine Line
     * @param column   Column
     * @param color  Type
     */
    private void addIfNeeded(Object spanLine, Object column, Object color) {
        Logger.debug("Add a new span into the line : spanLine=",spanLine,",column=",column,",color=",color);
        add((Integer)spanLine, SpanController.obtain((Integer)column, (Integer)color));
    }
    private void addFromColorName(Object spanLine, Object column, Object colorName) {
        String colName = (String) colorName;
        Integer color = theme.getColor((String) colorName);
        Logger.debug("colName=",colName,",color=",color);
        addIfNeeded(spanLine,column,color);
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
        if ( theme == null ) {
            Logger.debug("WARNING : the color result has no theme attached so you will get no colors.");
            return;
        }
        map.getAddIfNeeded(spanLine).add(span);
    }

    public void determine(int line) {
        map.appendLines(line);
    }

}

