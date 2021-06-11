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
package io.github.rosemoe.editor.core.widgets.layout.controller;

import java.util.NoSuchElementException;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentLineController;
import io.github.rosemoe.editor.core.util.BinaryHeap;
import io.github.rosemoe.editor.core.util.IntPair;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * Layout implementation of editor
 * This layout is never broke unless there is actually a newline character
 *
 * @author Rose
 */
public class LineBreakLayout extends AbstractLayout {

    private BinaryHeap widthMaintainer;

    public LineBreakLayout(CodeEditor editor, ContentMapController text) {
        super(editor, text);
        measureAllLines();
    }

    private void measureAllLines() {
        if (text == null) {
            return;
        }
        widthMaintainer = new BinaryHeap();
        widthMaintainer.ensureCapacity(text.getLineCount());
        for (int i = 0; i < text.getLineCount(); i++) {
            ContentLineController line = text.getLine(i);
            int width = (int) measureText(line, 0, line.length());
            line.setWidth(width);
            line.setId(widthMaintainer.push(width));
        }
    }

    private void measureLines(int startLine, int endLine) {
        if (text == null) {
            return;
        }
        while (startLine <= endLine && startLine < text.getLineCount()) {
            ContentLineController line = text.getLine(startLine);
            int width = (int) measureText(line, 0, line.length());
            if (line.getId() != -1) {
                if (line.getWidth() == width) {
                    startLine++;
                    continue;
                }
                widthMaintainer.update(line.getId(), width);
                startLine++;
                continue;
            }
            line.setId(widthMaintainer.push(width));
            line.setWidth(width);
            startLine++;
        }
    }

    @Override
    public RowIterator obtainRowIterator(int initialRow) {
        return new LineBreakLayoutRowItr(initialRow);
    }

    @Override
    public void beforeReplace(ContentMapController content) {
        // Intentionally empty
    }

    @Override
    public void afterInsert(ContentMapController content, int startLine, int startColumn, int endLine, int endColumn, CharSequence insertedContent) {
        measureLines(startLine, endLine);
    }

    @Override
    public void afterDelete(ContentMapController content, int startLine, int startColumn, int endLine, int endColumn, CharSequence deletedContent) {
        measureLines(startLine, startLine);
    }

    @Override
    public void onRemove(ContentMapController content, ContentLineController line) {
        widthMaintainer.remove(line.getId());
    }

    @Override
    public void destroyLayout() {
        super.destroyLayout();
        widthMaintainer = null;
    }

    @Override
    public int getLineNumberForRow(int row) {
        return Math.max(0, Math.min(row, text.getLineCount() - 1));
    }

    @Override
    public int getLayoutWidth() {
        return widthMaintainer.top();
    }

    @Override
    public int getLayoutHeight() {
        return text.getLineCount() * editor.getRowHeight();
    }

    @Override
    public long getCharPositionForLayoutOffset(float xOffset, float yOffset) {
        int lineCount = text.getLineCount();
        int line = Math.min(lineCount - 1, Math.max((int) (yOffset / editor.getRowHeight()), 0));
        ContentLineController str = text.getLine(line);
        float[] res = orderedFindCharIndex(xOffset, str);
        return IntPair.pack(line, (int) res[0]);
    }

    @Override
    public float[] getCharLayoutOffset(int line, int column, float[] dest) {
        if (dest == null || dest.length < 2) {
            dest = new float[2];
        }
        CharSequence sequence = text.getLine(line);
        dest[0] = editor.getRowHeight() * (line + 1);
        dest[1] = measureText(sequence, 0, column);
        return dest;
    }

    class LineBreakLayoutRowItr implements RowIterator {

        private final RowController row;
        private int currentRow;

        LineBreakLayoutRowItr(int initialRow) {
            currentRow = initialRow;
            row = new RowController();
            row.model.isLeadingRow = true;
            row.model.startColumn = 0;
        }

        @Override
        public RowController next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            row.model.lineIndex = currentRow;
            row.model.endColumn = text.getColumnCount(currentRow++);
            return row;
        }

        @Override
        public boolean hasNext() {
            return currentRow >= 0 && currentRow < text.getLineCount();
        }

    }

}
