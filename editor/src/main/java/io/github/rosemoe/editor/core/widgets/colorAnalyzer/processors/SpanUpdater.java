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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.processors;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanMapController;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanLineController;
import io.github.rosemoe.editor.core.util.Logger;

/**
 * Process text change events.
 * Update spans on text change event
 *
 * @author Rose
 */
public class SpanUpdater {

    /**
     * Called when user delete a newline, select and cut on multiple lines.
     * @param map
     * @param startLine
     * @param startColumn
     * @param endLine
     * @param endColumn
     */
    public static void shiftSpansOnMultiLineDelete(SpanMapController map, int startLine, int startColumn, int endLine, int endColumn) {
        Logger.debug("startLine=",startLine,",startColumn=",startColumn,",endLine=",endLine,",endColumn=",endColumn);
        map.cutLines(startLine,startColumn,endLine,endColumn);
    }

    /**
     * Called when user delete characters on a single line.
     * @param map
     * @param line
     * @param startCol
     * @param endCol
     */
    public static void shiftSpansOnSingleLineDelete(SpanMapController map, int line, int startCol, int endCol) {
        Logger.debug("line=",line,",startCol=",startCol,",endCol=",endCol);
        SpanLineController spanLine = map.get(line);
        spanLine.removeContent(startCol,endCol-startCol);
    }

    /**
     * Called when user insert on a single line : eg newline on its input.
     * @param map the map to work on.
     * @param line index 0..n-1 the line to modify.
     * @param startCol index 0..n-1 the start column of modification.
     * @param endCol index 0..n-1 the end column of modification.
     */
    public static void shiftSpansOnSingleLineInsert(SpanMapController map, int line, int startCol, int endCol) {
        Logger.debug("line=",line,",startCol=",startCol,",endCol=",endCol);
        map.insertContent(line,startCol,endCol-startCol);
    }

    /**
     * Called when user insert on multiple lines : eg newline, copy paste into.
     * @param map SpanMapController to update.
     * @param startLine start of insert line index.
     * @param startColumn start of insert column index.
     * @param endLine end of insert line index.
     * @param endColumn end of insert column index.
     */
    public static void shiftSpansOnMultiLineInsert(SpanMapController map, int startLine, int startColumn, int endLine, int endColumn) {

        Logger.debug("startLine=",startLine,",startColumn=",startColumn,",endLine=",endLine,",endColumn=",endColumn);
        int cutSize = endLine-startLine;
        map.splitLine(startLine,startColumn,cutSize);
    }

}
