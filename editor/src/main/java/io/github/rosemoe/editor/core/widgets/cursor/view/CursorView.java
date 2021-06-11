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
package io.github.rosemoe.editor.core.widgets.cursor.view;

import android.graphics.Canvas;
import android.graphics.RectF;

import io.github.rosemoe.editor.core.CodeEditor;

public class CursorView {
    public CursorView() {
    }
    /**
     * Class for saving state for cursor
     */
    public static class CursorPaintAction {

        /**
         * RowModel position
         */
        final int row;

        /**
         * Center x offset
         */
        final float centerX;

        /**
         * Handle rectangle
         */
        final RectF outRect;

        /**
         * Draw as insert cursor
         */
        final boolean insert;

        int handleType = -1;

        public CursorPaintAction(int row, float centerX, RectF outRect, boolean insert) {
            this.row = row;
            this.centerX = centerX;
            this.outRect = outRect;
            this.insert = insert;
        }

        public CursorPaintAction(int row, float centerX, RectF outRect, boolean insert, int handleType) {
            this.row = row;
            this.centerX = centerX;
            this.outRect = outRect;
            this.insert = insert;
            this.handleType = handleType;
        }


        /**
         * Execute painting on the given editor and canvas
         */
        public void exec(Canvas canvas, CodeEditor editor) {
            editor.drawCursor(canvas, centerX, row, outRect, insert, handleType);
        }

    }
}
