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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans;

import java.util.TreeMap;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.model.analysis.spans.SpanLineModel;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.view.analysis.spans.SpanLineView;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.ColorSchemeController;

/**
 * The class handle one line in the text editor.
 *
 * @author Release Standard
 */
public class SpanLineController {

    public SpanLineModel model = new SpanLineModel();
    public SpanLineView  view  = new SpanLineView();

    /**
     * Column index 0..n-1, SpanController
     */
    public TreeMap<Integer, SpanController> line;
    public SpanLineController() {
        line = new TreeMap<>();
    }

    /**
     * Add a new span to the spanline
     * @param col column index 0..n-1
     * @param span
     */
    public void add(int col, SpanController span) {
        line.put(col,span);
    }
    public void add(SpanController span) {
        add(span.model.column,span);
    }
    public void add(SpanLineController line) {
        for(SpanController span : line.line.values()){
            add(span);
        }
    }

    /**
     * Get the size of the span line.
     * @return
     */
    public int size() {
        return line.size();
    }

    /**
     * Get the SpanController to the index i in the SpanLineController.
     * @param i
     * @return
     */
    public SpanController get(int i) {
        return line.get(i);
    }

    /**
     * Test if the span line is empty (contains no SpanController).
     * @return
     */
    public boolean isEmpty() {
        return line.size()==0;
    }

    /**
     * Remove a span from the span line.
     * @param i line index 0..n-1
     * @return
     */
    public SpanController remove(int i) {
        return line.remove(i);
    }
    /**
     * Remove a span from the span line.
     * @param span the span to remove, span.column 0..n-1
     * @return
     */
    public SpanController remove(SpanController span) {
        return remove(span.model.column);
    }
    /**
     * Clear the span line.
     */
    public void clear() {
        line.clear();
    }
    /**
     * Dump the current state of the SpanLineController.
     */
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        Logger.debug(offset + "span in the line="+size());
    }

    /**
     * Split the line at given column index.
     * @param col index 0..n-1
     * @return newly created SpanLineController with the column index updated.
     */
    public SpanLineController[] split(int col) {
        SpanLineController[] parts = new SpanLineController[2];
        parts[0]=new SpanLineController();
        parts[1]=new SpanLineController();
        int columnIndex = 0;
        for(SpanController span : line.values()) {
            if ( span.model.column < col ) {
                parts[0].add(span);
            } else {
                int length = span.model.column - col;
                span.setColumn(columnIndex);
                columnIndex += length;
                parts[1].add(span);
            }
        }
        return parts;
    }
    /**
     * This function merge two SpanLines together and returns the merged span line.
     * @param one
     * @param two
     * @return
     */
    public static SpanLineController merge(SpanLineController one, SpanLineController two) {
        int index = 0;
        if ( one.size() > 0) {
            SpanController lastSpan = one.line.lastEntry().getValue();
            index = lastSpan.model.column;
        }
        int lastCol = 0;
        for(SpanController span : two.line.values()) {
            lastCol = span.model.column - lastCol;
            index += lastCol;
            span.setColumn(index);
            one.add(index,span);
        }
        return one;
    }

    /**
     * Insert content into the SpanLineController at specified position.
     * @param span the span to insert
     * @param col index 0..n-1
     * @param sz size 0..n
     */
    public void insertContent(SpanController span, int col, int sz) {
        for(SpanController s : line.values()) {
            if ( s.model.column >= col ) {
                line.remove(s.model.column);
                s.setColumn(s.model.column+sz);
                line.put(s.model.column,span);
            }
        }
        span.setColumn(col);
        line.put(col,span);
    }

    /**
     * Remove spans on affected region by the deletion.
     * @param col index 0..n-1
     * @param sz size 0..n
     */
    public void removeContent(int col,int sz) {
        for(SpanController span : line.values()) {
            if ( span.model.column < col) {

            } else {
                if ( span.model.column < col+sz) {
                    remove(span);
                } else {
                    span.setColumn(span.model.column-sz);
                }
            }
        }
    }
    /**
     * Empty spanline.
     * @return returns an empty spanline
     */
    public static SpanLineController EMPTY() {
        SpanLineController line = new SpanLineController();
        line.add(SpanController.obtain(0, ColorSchemeController.DEFAULT_TEXT_COLOR()));
        return line;
    }

    /**
     * This function is used to avoid concurrent exception when working with Collections.
     * @return
     */
    public SpanController[] concurrentSafeGetValues() {
        SpanController[] spans = null;
        while (spans == null ) {
            try {
                spans = line.values().toArray(new SpanController[size()]);
            } catch (java.util.ConcurrentModificationException e) {
                Logger.debug("This error is harmless if not repeat to much");
                e.printStackTrace();
                spans=null;
            }
        }
        return spans;
    }
}
