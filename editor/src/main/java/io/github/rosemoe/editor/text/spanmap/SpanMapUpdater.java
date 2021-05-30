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
package io.github.rosemoe.editor.text.spanmap;

import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * Update spans on text change event
 *
 * @author Rose
 */
public class SpanMapUpdater {

    public static void shiftSpansOnMultiLineDelete(SpanMap map, int startLine, int startColumn, int endLine, int endColumn) {
        Logger.debug("startLine=",startLine,",startColumn=",startColumn,",endLine=",endLine,",endColumn=",endColumn);
        int lineCount = endLine - startLine - 1;
        // Remove unrelated lines
        while (lineCount > 0) {
            map.remove(startLine + 1);
            lineCount--;
        }
        // Clean up start line
        SpanLine startLineSpans = map.get(startLine);
        int index = startLineSpans.size() - 1;
        while (index > 0) {
            if (startLineSpans.get(index).column >= startColumn) {
                startLineSpans.remove(index).recycle();
                index--;
            } else {
                break;
            }
        }
        // Shift end line
        SpanLine endLineSpans = map.get(startLine + 1);
        while (endLineSpans.size() > 1) {
            Span first = endLineSpans.get(0);
            if (first.column >= endColumn) {
                break;
            } else {
                int spanEnd = endLineSpans.get(1).column;
                if (spanEnd <= endColumn) {
                    endLineSpans.remove(first);
                    first.recycle();
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < endLineSpans.size(); i++) {
            Span span = endLineSpans.get(i);
            if (span.column < endColumn) {
                span.column = 0;
            } else {
                span.column -= endColumn;
            }
        }
    }

    public static void shiftSpansOnSingleLineDelete(SpanMap map, int line, int startCol, int endCol) {
        Logger.debug("line=",line,",startCol=",startCol,",endCol=",endCol);
        if (map == null || map.isEmpty()) {
            return;
        }
        SpanLine spanList = map.get(line);
        int startIndex = findSpanIndexFor(spanList, 0, startCol);
        if (startIndex == -1) {
            //No span is to be updated
            return;
        }
        int endIndex = findSpanIndexFor(spanList, startIndex, endCol);
        if (endIndex == -1) {
            endIndex = spanList.size();
        }
        // Remove spans inside delete text
        int removeCount = endIndex - startIndex;
        for (int i = 0; i < removeCount; i++) {
            spanList.remove(startIndex).recycle();
        }
        // Shift spans
        int delta = endCol - startCol;
        while (startIndex < spanList.size()) {
            spanList.get(startIndex).column -= delta;
            startIndex++;
        }
        // Ensure there is span
        if (spanList.isEmpty() || spanList.get(0).column != 0) {
            spanList.add(0, Span.obtain(0, EditorColorScheme.TEXT_NORMAL));
        }
        // Remove spans with length 0
        for (int i = 0; i + 1 < spanList.size(); i++) {
            if (spanList.get(i).column >= spanList.get(i + 1).column) {
                spanList.remove(i).recycle();
                i--;
            }
        }
    }

    /**
     * Called when user insert on a single line : eg newline on its input.
     * @param map the map to work on.
     * @param line index 0..n-1 the line to modify.
     * @param startCol index 0..n-1 the start column of modification.
     * @param endCol index 0..n-1 the end column of modification.
     */
    public static void shiftSpansOnSingleLineInsert(SpanMap map, int line, int startCol, int endCol) {
        Logger.debug("line=",line,",startCol=",startCol,",endCol=",endCol);
        map.insertContent(line,startCol,endCol-startCol);
    }

    /**
     * Called when user insert on multiple lines : eg newline, copy paste into.
     * @param map SpanMap to update.
     * @param startLine start of insert line index.
     * @param startColumn start of insert column index.
     * @param endLine end of insert line index.
     * @param endColumn end of insert column index.
     */
    public static void shiftSpansOnMultiLineInsert(SpanMap map, int startLine, int startColumn, int endLine, int endColumn) {

        Logger.debug("startLine=",startLine,",startColumn=",startColumn,",endLine=",endLine,",endColumn=",endColumn);
        int cutSize = endLine-startLine;
        map.cutDown(startLine,startColumn,cutSize);
    }

    private static int findSpanIndexFor(SpanLine spans, int initialPosition, int targetCol) {

        for (int i = initialPosition; i < spans.size(); i++) {
            if (spans.get(i).column >= targetCol) {
                return i;
            }
        }
        return -1;
    }

}
