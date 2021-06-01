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
package io.github.rosemoe.editor.mvc.controller.spans;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.github.rosemoe.editor.mvc.model.spans.SpanModel;
import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * The span model
 *
 * @author Rose
 */
public class SpanController {

    private static final BlockingQueue<SpanController> cacheQueue = new ArrayBlockingQueue<>(8192 * 2);
    public SpanModel sm = new SpanModel();

    public static SpanController EMPTY() {
        return obtain(0, EditorColorScheme.DEFAULT().getTextNormal());
    }
    /**
     * Create a new span
     *
     * @param column  Start column of span
     * @param color Type of span
     * @see SpanController#obtain(int, int)
     */
    private SpanController(int column, int color) {
        sm.column = column;
        sm.color = color;
    }

    public static SpanController obtain(int column, int color) {
        SpanController span = cacheQueue.poll();
        if (span == null) {
            return new SpanController(column, color);
        } else {
            span.sm.column = column;
            span.sm.color = color;
            return span;
        }
    }

    public static void recycleAll(SpanController[] spans) {
        for (SpanController span : spans) {
            if (!span.recycle()) {
                return;
            }
        }
    }

    /**
     * Set a underline for this region
     * Zero for no underline
     *
     * @param color Color for this underline (not color id of {@link EditorColorScheme})
     * @return Self
     */
    public SpanController setUnderlineColor(int color) {
        sm.underlineColor = color;
        return this;
    }

    /**
     * Get span start column
     *
     * @return Start column
     */
    public int getColumn() {
        return sm.column;
    }

    /**
     * Set column of this span
     */
    public SpanController setColumn(int column) {
        sm.column = column;
        return this;
    }

    /**
     * Make a copy of this span
     */
    public SpanController copy() {
        SpanController copy = obtain(sm.column, sm.color);
        copy.setUnderlineColor(sm.underlineColor);
        return copy;
    }

    public boolean recycle() {
        sm.recycle();
        return cacheQueue.offer(this);
    }

}
