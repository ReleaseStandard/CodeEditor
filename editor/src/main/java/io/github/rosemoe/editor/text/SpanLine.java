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
package io.github.rosemoe.editor.text;

import java.util.TreeMap;

import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * The class handle one line in the text editor.
 */
public class SpanLine {
    /**
     * Column index 0..n-1, Span
     */
    public TreeMap<Integer, Span> line;
    public SpanLine() {
        line = new TreeMap<>();
    }

    /**
     * Add a new span to the spanline
     * @param col column index 0..n-1
     * @param span
     */
    public void add(int col,Span span) {
        line.put(col,span);
    }
    public void add(Span span) {
        add(span.column,span);
    }

    /**
     * Get the size of the span line.
     * @return
     */
    public int size() {
        return line.size();
    }

    /**
     * Get the Span to the index i in the SpanLine.
     * @param i
     * @return
     */
    public Span get(int i) {
        return line.get(i);
    }

    /**
     * Test if the span line is empty (contains no Span).
     * @return
     */
    public boolean isEmpty() {
        return line.size()==0;
    }

    /**
     * Remove a span from the span line.
     * @param i
     * @return
     */
    public Span remove(int i) {
        return line.remove(i);
    }
    public Span remove(Span span) {
        return line.remove(span.column);
    }
    /**
     * Clear the span line.
     */
    public void clear() {
        line.clear();
    }
    /**
     * Dump the current state of the SpanLine.
     */
    public void dump() {
        Logger.debug("line number=",line.values().size());
    }

    public static SpanLine EMPTY() {
        SpanLine line = new SpanLine();
        line.add(Span.obtain(0, EditorColorScheme.TEXT_NORMAL));
        return line;
    }
}
