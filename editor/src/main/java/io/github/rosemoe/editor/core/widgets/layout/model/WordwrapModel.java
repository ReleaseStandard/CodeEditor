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
package io.github.rosemoe.editor.core.widgets.layout.model;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.util.IntPair;

public class WordwrapModel {

    public int width = 0;
    public List<RowRegion> rowTable;

    public static class RowRegion {

        public final int startColumn;
        public final int endColumn;
        public int line;

        public RowRegion(int line, int start, int end) {
            this.line = line;
            startColumn = start;
            endColumn = end;
        }

    }
    public int findRow(int line) {
        int index = 0;
        while (index < rowTable.size()) {
            if (rowTable.get(index).line < line) {
                index++;
            } else {
                if (rowTable.get(index).line > line) {
                    index--;
                }
                break;
            }
        }
        return index;
    }

    public void breakLines(int startLine, int endLine, ContentMapController text) {
        int insertPosition = 0;
        while (insertPosition < rowTable.size()) {
            if (rowTable.get(insertPosition).line < startLine) {
                insertPosition++;
            } else {
                break;
            }
        }
        while (insertPosition < rowTable.size()) {
            int line = rowTable.get(insertPosition).line;
            if (line >= startLine && line <= endLine) {
                rowTable.remove(insertPosition);
            } else {
                break;
            }
        }
        List<Integer> breakpoints = new ArrayList<>();
        for (int i = startLine; i <= endLine; i++) {
            breakLine(i, breakpoints);
            for (int j = -1; j < breakpoints.size(); j++) {
                int start = j == -1 ? 0 : breakpoints.get(j);
                int end = j + 1 < breakpoints.size() ? breakpoints.get(j + 1) : text.getColumnCount(i);
                rowTable.add(insertPosition++, new RowRegion(i, start, end));
            }
            breakpoints.clear();
        }
    }

    public void clear() {
        rowTable.clear();
    }
    public int getLineNumberForRow(int row) {
        return row >= rowTable.size() ? rowTable.get(rowTable.size() - 1).line : rowTable.get(row).line;
    }
    public long getCharPositionForLayoutOffset(float xOffset, float yOffset, int rowHeight) {
        int row = (int) (yOffset / rowHeight);
        row = Math.max(0, Math.min(row, rowTable.size() - 1));
        RowRegion region = rowTable.get(row);
        int column = orderedFindCharIndex(xOffset,region.line,region.startColumn,region.endColumn);
        return IntPair.pack(region.line, column);
    }

    public float[] getCharLayoutOffset(int line, int column, float[] dest,int rowHeight) {
        if (dest == null || dest.length < 2) {
            dest = new float[2];
        }
        int row = findRow(line);
        if (row < rowTable.size()) {
            RowRegion region = rowTable.get(row);
            if (region.line != line) {
                dest[0] = dest[1] = 0;
                return dest;
            }
            while (region.startColumn < column && row + 1 < rowTable.size()) {
                row++;
                region = rowTable.get(row);
                if (region.line != line) {
                    row--;
                    region = rowTable.get(row);
                    break;
                }
            }
            dest[0] = rowHeight * (row + 1);
            dest[1] = measureText(region.line,region.startColumn,column);

        } else {
            dest[0] = dest[1] = 0;
        }
        return dest;
    }

    /**
     * Overrided by the controller (because we need data from the view).
     */
    public int measureText(int line, int startColumn, int column) {
        return 0;
    }
    /**
     * Overrided by the controller (because we need data from the view).
     */
    public int orderedFindCharIndex(float xOffset, int line, int startColumn, int endColumn) {
        return 0;
    }
    /**
     * Overrided by the controller (because we need data from the view).
     */
    public void breakLine(int line, List<Integer> breakpoints) {
    }
}
