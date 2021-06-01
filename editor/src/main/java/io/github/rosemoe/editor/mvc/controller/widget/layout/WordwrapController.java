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
package io.github.rosemoe.editor.mvc.controller.widget.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import io.github.rosemoe.editor.mvc.controller.RowController;
import io.github.rosemoe.editor.mvc.model.widget.layout.WordwrapModel;
import io.github.rosemoe.editor.mvc.controller.content.ContentController;
import io.github.rosemoe.editor.mvc.controller.content.ContentLineController;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.RowIterator;

/**
 * Wordwrap layout for editor
 * <p>
 * This layout will not let character displayed outside the editor's width
 * <p>
 * However, using this can be power-costing because we will have to recreate this layout in various
 * conditions, such as when the line number increases and its width grows or when the text size has changed
 *
 * @author Rose
 */
public class WordwrapController extends AbstractLayout {


    public final WordwrapModel model;

    public WordwrapController(CodeEditor editor, ContentController text) {
        super(editor, text);
        model = new WordwrapModel() {
            @Override
            public int measureText(int line, int startColumn, int column) {
                return (int) WordwrapController.this.measureText(text.getLine(line), startColumn, column);
            }

            @Override
            public int orderedFindCharIndex(float xOffset, int line, int startColumn, int endColumn) {
                return (int) WordwrapController.this.orderedFindCharIndex(xOffset, text.getLine(line), startColumn, endColumn)[0];

            }
            @Override
            public void breakLine(int line, List<Integer> breakpoints) {
                ContentLineController sequence = text.getLine(line);
                float currentWidth = 0;
                for (int i = 0; i < sequence.length(); i++) {
                    char ch = sequence.charAt(i);
                    float single = fontCache.measureChar(ch, shadowPaint);
                    if (ch == '\t') {
                        single = fontCache.measureChar(' ', shadowPaint) * editor.getTabWidth();
                    }
                    if (currentWidth + single > model.width) {
                        int lastCommit = breakpoints.size() != 0 ? breakpoints.get(breakpoints.size() - 1) : 0;
                        if (i == lastCommit) {
                            i++;
                            continue;
                        }
                        breakpoints.add(i);
                        currentWidth = 0;
                        i--;
                    } else {
                        currentWidth += single;
                    }
                }
                if (breakpoints.size() != 0 && breakpoints.get(breakpoints.size() - 1) == sequence.length()) {
                    breakpoints.remove(breakpoints.size() - 1);
                }
            }
        };
        model.rowTable = new ArrayList<>();
        model.width = editor.getWidth() - (int) editor.measureTextRegionOffset() - (int) editor.getDpUnit() * 5;
        breakAllLines();
    }

    private void breakAllLines() {
        List<Integer> breakpoints = new ArrayList<>();
        for (int i = 0; i < text.getLineCount(); i++) {
            model.breakLine(i, breakpoints);
            for (int j = -1; j < breakpoints.size(); j++) {
                int start = j == -1 ? 0 : breakpoints.get(j);
                int end = j + 1 < breakpoints.size() ? breakpoints.get(j + 1) : text.getColumnCount(i);
                model.rowTable.add(new WordwrapModel.RowRegion(i, start, end));
            }
            breakpoints.clear();
        }
    }

    @Override
    public void beforeReplace(ContentController content) {
        // Intentionally empty
    }

    @Override
    public void afterInsert(ContentController content, int startLine, int startColumn, int endLine, int endColumn, CharSequence insertedContent) {
        // Update line numbers
        int delta = endLine - startLine;
        if (delta != 0) {
            for (int row = model.findRow(startLine + 1); row < model.rowTable.size(); row++) {
                model.rowTable.get(row).line += delta;
            }
        }
        // Re-break
        model.breakLines(startLine, endLine,text);
    }

    @Override
    public void afterDelete(ContentController content, int startLine, int startColumn, int endLine, int endColumn, CharSequence deletedContent) {
        int delta = endLine - startLine;
        if (delta != 0) {
            int startRow = model.findRow(startLine);
            while (startRow < model.rowTable.size()) {
                int line = model.rowTable.get(startRow).line;
                if (line >= startLine && line <= endLine) {
                    model.rowTable.remove(startRow);
                } else {
                    break;
                }
            }
            for (int row = model.findRow(endLine + 1); row < model.rowTable.size(); row++) {
                model.rowTable.get(row).line -= delta;
            }
        }
        model.breakLines(startLine, startLine,text);
    }

    @Override
    public void onRemove(ContentController content, ContentLineController line) {

    }

    @Override
    public void destroyLayout() {
        super.destroyLayout();
        model.clear();
    }

    @Override
    public int getLineNumberForRow(int row) {
        return model.getLineNumberForRow(row);
    }

    @Override
    public RowIterator obtainRowIterator(int initialRow) {
        return new WordwrapLayoutRowItr(initialRow);
    }

    @Override
    public int getLayoutWidth() {
        return 0;
    }

    @Override
    public int getLayoutHeight() {
        return model.rowTable.size() * editor.getRowHeight();
    }

    @Override
    public long getCharPositionForLayoutOffset(float xOffset, float yOffset) {
        return model.getCharPositionForLayoutOffset(xOffset,yOffset,editor.getRowHeight());
    }

    @Override
    public float[] getCharLayoutOffset(int line, int column, float[] dest) {
        int rowHeight = editor.getRowHeight();
        return model.getCharLayoutOffset(line,column,dest,rowHeight);
    }

    class WordwrapLayoutRowItr implements RowIterator {

        final RowController row;
        int currentRow;

        WordwrapLayoutRowItr(int initialRow) {
            currentRow = initialRow;
            row = new RowController();
        }

        @Override
        public RowController next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            WordwrapModel.RowRegion currentRegion = model.rowTable.get(currentRow);
            WordwrapModel.RowRegion previousRegion = model.rowTable.get(currentRow - 1);
            row.initFromRegion(currentRegion,previousRegion,currentRow);
            currentRow++;
            return row;
        }

        @Override
        public boolean hasNext() {
            return currentRow >= 0 && currentRow < model.rowTable.size();
        }

    }

}