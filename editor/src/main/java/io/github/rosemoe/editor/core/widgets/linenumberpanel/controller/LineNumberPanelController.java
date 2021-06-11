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
package io.github.rosemoe.editor.core.widgets.linenumberpanel.controller;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.TypedValue;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.widgets.Widget;
import io.github.rosemoe.editor.core.widgets.linenumberpanel.extension.LineNumberPanelEvent;
import io.github.rosemoe.editor.core.widgets.linenumberpanel.model.LineNumberPanelModel;
import io.github.rosemoe.editor.core.widgets.linenumberpanel.view.LineNumberPanelView;

import static io.github.rosemoe.editor.core.widgets.linenumberpanel.model.LineNumberPanelModel.*;

/**
 * Class responsible for displaying a line number panel on the editor.
 *
 */
public class LineNumberPanelController extends Widget {

    public LineNumberPanelModel model = new LineNumberPanelModel();
    public final LineNumberPanelView  view;


    private float mDividerWidth;
    private float mDividerMargin;

    public LineNumberPanelController(CodeEditor editor) {
        super(editor);
        subscribe(LineNumberPanelEvent.class);
        view = new LineNumberPanelView(editor);
        mDividerWidth  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, Resources.getSystem().getDisplayMetrics());
        mDividerMargin = mDividerWidth;
        name        = "linenumberpanel";
        description = "This widget is responsible from displaying the linenumber panel";
    }

    @Override
    public void handleEventDispatch(Event e, String subtype) {
        LineNumberPanelEvent uie = (LineNumberPanelEvent) e;
        switch(subtype) {
            case LineNumberPanelEvent.CHANGE_ALIGN:
                Integer align = (Integer) uie.getArg(0);
                if ( align == null ) {
                    Logger.v("No arguments given to change align");
                }
                setLineNumberAlign(align);
                break;
            case LineNumberPanelEvent.DIVIDER:
                String prop = (String) uie.getArg(0);
                if( prop != null ) {
                    if ( prop.equals("width") ) {
                        Float width = (Float) uie.getArg(1);
                        setDividerWidth(width);
                    } else if ( prop.equals("margin") ) {
                        Float margin = (Float) uie.getArg(1);
                        setDividerMargin(margin);
                    }
                }
        }
    }

    @Override
    public void setEnabled(boolean state) {
        super.setEnabled(state);
        editor.invalidate();
    }
    public float measureLineNumber(int lineCount) {
        if (!isEnabled()) {
            return 0f;
        }
        int count = 0;
        while (lineCount > 0) {
            count++;
            lineCount /= 10;
        }
        final String[] charSet = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        float single = 0f;
        for (String ch : charSet) {
            single = Math.max(single, view.lineNumberPaint.measureText(ch));
        }
        return single * count;
    }

    /**
     * Draw single line number
     */
    public void drawLineNumber(Canvas canvas, int line, int row, float offsetX, float width, int color) {
        if (width + offsetX <= 0) {
            return;
        }
        int count = model.computeAndGetText(line);
        view.drawLineNumber(canvas,row,offsetX,width,count,color,model.computedText,getDividerMargin(),getViewLineNumber());
    }
    /**
     * Get the width of line number region
     *
     * @return width of line number region
     */

    /**
     * Draw line number background
     *
     * @param canvas  Canvas to draw
     * @param offsetX Start x of line number region
     * @param width   Width of line number region
     * @param color   Color of line number background
     */
    public void drawLineNumberBackground(Canvas canvas, float offsetX, float width, int color) {
        float right = offsetX + width;
        if (right < 0) {
            return;
        }
        float left = Math.max(0f, offsetX);
        RectF mRect = new RectF();
        mRect.bottom = editor.getHeight();
        mRect.top = 0;
        int offY = editor.getOffsetY();
        if (offY < 0) {
            mRect.bottom = mRect.bottom - offY;
            mRect.top = mRect.top - offY;
        }
        mRect.left = left;
        mRect.right = right;
        editor.drawColor(canvas, color, mRect);
    }
    /**
     * @return Typeface of line number
     */
    public Typeface getTypefaceLineNumber() {
        return view.lineNumberPaint.getTypeface();
    }

    /**
     * Set line number's typeface
     *
     * @param typefaceLineNumber New typeface
     */
    public void setTypefaceLineNumber(Typeface typefaceLineNumber) {
        if (typefaceLineNumber == null) {
            typefaceLineNumber = Typeface.MONOSPACE;
        }
        view.lineNumberPaint.setTypeface(typefaceLineNumber);
        view.mLineNumberMetrics = view.lineNumberPaint.getFontMetricsInt();
        editor.invalidate();
    }

    /**
     * Get align state from the model.
     * @return Line number align
     */
    private Paint.Align getViewLineNumber() {
        switch (model.mLineNumberAlign) {
            case ALIGN_LEFT:
                return Paint.Align.LEFT;
            case ALIGN_CENTER:
                return Paint.Align.CENTER;
            case ALIGN_RIGHT:
                return Paint.Align.RIGHT;
        }
        model.mLineNumberAlign = ALIGN_DEFAULT;
        return getViewLineNumber();
    }
    /**
     * Set line number align
     *
     * @param align Align for line number
     */
    public void setLineNumberAlign(int align) {
        model.mLineNumberAlign = align;
        view.setTextAlign(getViewLineNumber());
        editor.invalidate();
    }

    /**
     * @return Margin of divider line
     */
    public float getDividerMargin() {
        return mDividerMargin;
    }

    /**
     * Set divider line's left and right margin
     *
     * @param dividerMargin Margin for divider line
     */
    public void setDividerMargin(float dividerMargin) {
        if (dividerMargin < 0) {
            throw new IllegalArgumentException("margin can not be under zero");
        }
        this.mDividerMargin = dividerMargin;
        editor.invalidate();
    }

    /**
     * @return Width of divider line
     */
    public float getDividerWidth() {
        return mDividerWidth;
    }

    /**
     * Set divider line's width
     *
     * @param dividerWidth Width of divider line
     */
    public void setDividerWidth(float dividerWidth) {
        if (dividerWidth < 0) {
            throw new IllegalArgumentException("width can not be under zero");
        }
        this.mDividerWidth = dividerWidth;
        editor.invalidate();
    }

}
