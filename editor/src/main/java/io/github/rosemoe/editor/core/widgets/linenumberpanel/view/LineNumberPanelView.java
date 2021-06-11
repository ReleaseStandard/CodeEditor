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
package io.github.rosemoe.editor.core.widgets.linenumberpanel.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import io.github.rosemoe.editor.core.CodeEditor;

public class LineNumberPanelView {
    public Paint lineNumberPaint = new Paint();
    public Paint.FontMetricsInt mLineNumberMetrics;
    final CodeEditor editor;

    public LineNumberPanelView(CodeEditor editor) {
        lineNumberPaint.setAntiAlias(true);
        lineNumberPaint.setTypeface(Typeface.MONOSPACE);
        this.editor = editor;
    }
    public void setTextAlign(Paint.Align align) {
        if (lineNumberPaint.getTextAlign() != align) {
            lineNumberPaint.setTextAlign(align);
        }
    }
    public void drawLineNumber(Canvas canvas, int row, float offsetX, float width, int count, int color, char[]computedText, float mDividerMargin, Paint.Align mLineNumberAlign) {
        setTextAlign(mLineNumberAlign);
        lineNumberPaint.setColor(color);
        // Line number center align to text center

        float y = (editor.getRowBottom(row) + editor.getRowTop(row)) / 2f - (mLineNumberMetrics.descent - mLineNumberMetrics.ascent) / 2f - mLineNumberMetrics.ascent - editor.getOffsetY();

        switch (mLineNumberAlign) {
            case LEFT:
                canvas.drawText(computedText, 0, count, offsetX, y, lineNumberPaint);
                break;
            case RIGHT:
                canvas.drawText(computedText, 0, count, offsetX + width, y, lineNumberPaint);
                break;
            case CENTER:
                canvas.drawText(computedText, 0, count, offsetX + (width + mDividerMargin) / 2f, y, lineNumberPaint);
        }
    }
}
