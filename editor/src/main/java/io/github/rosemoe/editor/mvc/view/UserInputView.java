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
package io.github.rosemoe.editor.mvc.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.OverScroller;

import io.github.rosemoe.editor.mvc.controller.widget.ContextActionController;
import io.github.rosemoe.editor.mvc.model.UserInputModel;
import io.github.rosemoe.editor.util.IntPair;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.TextActionPopupWindow;
import io.github.rosemoe.editor.widget.TextComposeBasePopup;

import static io.github.rosemoe.editor.mvc.controller.UserInputController.*;

public class UserInputView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener {
    public CodeEditor editor;
    public final OverScroller mScroller;
    public float maxSize;
    public float minSize;
    public MotionEvent mThumb;
    public int mEdgeFlags;
    public GestureDetector mBasicDetector;
    public ScaleGestureDetector mScaleDetector;

    public UserInputView(CodeEditor editor, Context ctx) {
        this.editor = editor;
        mScroller = new OverScroller(editor.getContext());
        maxSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 32, Resources.getSystem().getDisplayMetrics());
        minSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 6, Resources.getSystem().getDisplayMetrics());
        mBasicDetector = new GestureDetector(ctx, this);
        mBasicDetector.setOnDoubleTapListener(this);
        mScaleDetector = new ScaleGestureDetector(ctx, this);
    }

    /**
     * Handles the selected text click event
     *
     * @param e      the MotionEvent
     * @param line   line number index
     * @param column column index in line
     */
    private void handleSelectedTextClick(MotionEvent e, int line, int column) {
        if (editor.getTextActionPresenter() instanceof ContextActionController) {
            char text = editor.getText().charAt(line, column);
            if (UserInputModel.isWhitespace(text) || ((ContextActionController) editor.getTextActionPresenter()).view.isShowing())
                editor.setSelection(line, column);
            else editor.getTextActionPresenter().onSelectedTextClicked(e);
        } else {
            editor.getTextActionPresenter().onSelectedTextClicked(e);
        }
    }

    private void handleLongPressForModifiedTextAction(MotionEvent e) {
        if (editor.getCursor().isSelected() || e.getPointerCount() != 1) {
            return;
        }
        long res = editor.getPointPositionOnScreen(e.getX(), e.getY());
        int line = IntPair.getFirst(res);
        int column = IntPair.getSecond(res);
        //Find word edges
        int startLine = line, endLine = line;
        int startColumn = column;
        while (startColumn > 0 && UserInputModel.isIdentifierPart(editor.getText().charAt(line, startColumn - 1))) {
            startColumn--;
        }
        int maxColumn = editor.getText().getColumnCount(line);
        int endColumn = column;
        while (endColumn < maxColumn && UserInputModel.isIdentifierPart(editor.getText().charAt(line, endColumn))) {
            endColumn++;
        }
        if (startLine == endLine && startColumn == endColumn) {
            editor.showTextActionPopup();
        } else {
            editor.setSelectionRegion(startLine, startColumn, endLine, endColumn);
        }
    }

    public boolean handleOnSingleTapUp() { return false; }
    public void handleOnLongPress() { }
    public boolean handleOnScroll() { return false; }
    public boolean handleOnFling() { return false; }
    public boolean handleOnScale() { return false; }
    public boolean handleOnScaleBegin() { return false; }
    public boolean handleOnScaleEnd() { return false; }
    public boolean handleOnDown() { return false; }
    public void handleOnShowPress() { }
    public boolean handleOnSingleTapConfirmed() { return false; }
    public boolean handleOnDoubleTap(){ return false; }
    public boolean handleOnDoubleTapEvent() { return false; }
    public long refreshLastSetSelection() { return System.currentTimeMillis(); }
    public long refreshLastScroll() { return System.currentTimeMillis(); }
    public long refreshLastInteraction() { return System.currentTimeMillis(); }


    /**
     * Those are used only in the function below.
     */
    int preciousX = 0;
    int preciousY = 0;

    public void notifyGestureInteractionEnd(int type) {
        long mLastInteraction = refreshLastInteraction();
        class InvalidateNotifier implements Runnable {
            @Override
            public void run() {
                if (type == TextComposeBasePopup.SCROLL) {
                    int x = mScroller.getCurrX();
                    int y = mScroller.getCurrY();
                    if (x - preciousX == 0 && y - preciousY == 0) {
                        editor.invalidate();
                        editor.onEndGestureInteraction();
                        preciousX = 0;
                        preciousY = 0;
                        return;
                    }
                    preciousX = x;
                    preciousY = y;
                    editor.postDelayed(this, INTERACTION_END_DELAY);
                } else if (System.currentTimeMillis() - mLastInteraction >= INTERACTION_END_DELAY) {
                    editor.invalidate();
                    editor.onEndGestureInteraction();
                }
            }

        }
        editor.postDelayed(new InvalidateNotifier(), INTERACTION_END_DELAY);
    }
    /**
     * Get scroller for editor
     *
     * @return Scroller using
     */
    public OverScroller getScroller() {
        return mScroller;
    }

    /**
     * Reset scroll state
     */
    public void reset() {
        mScroller.startScroll(0, 0, 0, 0, 0);
    }
    /**
     * Notify the editor later to hide insert handle
     */
    public void notifyLater() {
        long mLastSetSelection = refreshLastScroll();
        class InvalidateNotifier implements Runnable {
            @Override
            public void run() {
                if (System.currentTimeMillis() - mLastSetSelection >= HIDE_DELAY) {
                    editor.invalidate();
                }
            }

        }
        editor.postDelayed(new InvalidateNotifier(), HIDE_DELAY);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        editor.showSoftInput();
        mScroller.forceFinished(true);
        long res = editor.getPointPositionOnScreen(e.getX(), e.getY());
        int line = IntPair.getFirst(res);
        int column = IntPair.getSecond(res);
        if (editor.getCursor().isSelected() && editor.getCursor().isInSelectedRegion(line, column) && !editor.isOverMaxY(e.getY())) {
            handleSelectedTextClick(e, line, column);
        } else {
            notifyLater();
            int oldLine = editor.getCursor().getLeftLine();
            int oldColumn = editor.getCursor().getLeftColumn();
            if (line == oldLine && column == oldColumn) {
                if (editor.mTextActionPresenter instanceof ContextActionController) {
                    ContextActionController contextAction = (ContextActionController) editor.mTextActionPresenter;
                    contextAction.handleTap(e);
                }
            } else {
                editor.setSelection(line, column);
                editor.hideAutoCompleteWindow();
            }
        }
        editor.performClick();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (editor.mTextActionPresenter instanceof TextActionPopupWindow) {
            handleLongPressForModifiedTextAction(e);
            return;
        }
        if (editor.getCursor().isSelected() || e.getPointerCount() != 1) {
            return;
        }
        long res = editor.getPointPositionOnScreen(e.getX(), e.getY());
        int line = IntPair.getFirst(res);
        int column = IntPair.getSecond(res);
        //Find word edges
        int startLine = line, endLine = line;
        int startColumn = column;
        while (startColumn > 0 && UserInputModel.isIdentifierPart(editor.getText().charAt(line, startColumn - 1))) {
            startColumn--;
        }
        int maxColumn = editor.getText().getColumnCount(line);
        int endColumn = column;
        while (endColumn < maxColumn && UserInputModel.isIdentifierPart(editor.getText().charAt(line, endColumn))) {
            endColumn++;
        }
        if (startColumn == endColumn) {
            if (startColumn > 0) {
                startColumn--;
            } else if (endColumn < maxColumn) {
                endColumn++;
            } else {
                if (line > 0) {
                    int lastColumn = editor.getText().getColumnCount(line - 1);
                    startLine = line - 1;
                    startColumn = lastColumn;
                } else if (line < editor.getLineCount() - 1) {
                    endLine = line + 1;
                    endColumn = 0;
                }
            }
        }
        editor.setSelectionRegion(startLine, startColumn, endLine, endColumn);
    }

    public boolean topOrBottom; //true for bottom
    public boolean leftOrRight; //true for right

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (editor.getTextActionPresenter() instanceof TextActionPopupWindow) {
            editor.getTextActionPresenter().onUpdate(TextActionPopupWindow.SCROLL);
        } else {
            editor.getTextActionPresenter().onUpdate();
        }
        int endX = mScroller.getCurrX() + (int) distanceX;
        int endY = mScroller.getCurrY() + (int) distanceY;
        endX = Math.max(endX, 0);
        endY = Math.max(endY, 0);
        endY = Math.min(endY, editor.getScrollMaxY());
        endX = Math.min(endX, editor.getScrollMaxX());
        boolean notifyY = true;
        boolean notifyX = true;
        if (!editor.getVerticalEdgeEffect().isFinished() && !editor.getVerticalEdgeEffect().isRecede()) {
            endY = mScroller.getCurrY();
            float displacement = Math.max(0, Math.min(1, e2.getX() / editor.getWidth()));
            editor.getVerticalEdgeEffect().onPull((topOrBottom ? distanceY : -distanceY) / editor.getMeasuredHeight(), !topOrBottom ? displacement : 1 - displacement);
            notifyY = false;
        }
        if (!editor.getHorizontalEdgeEffect().isFinished() && !editor.getHorizontalEdgeEffect().isRecede()) {
            endX = mScroller.getCurrX();
            float displacement = Math.max(0, Math.min(1, e2.getY() / editor.getHeight()));
            editor.getHorizontalEdgeEffect().onPull((leftOrRight ? distanceX : -distanceX) / editor.getMeasuredWidth(), !leftOrRight ? 1 - displacement : displacement);
            notifyX = false;
        }
        mScroller.startScroll(mScroller.getCurrX(),
                mScroller.getCurrY(),
                endX - mScroller.getCurrX(),
                endY - mScroller.getCurrY(), 0);
        final float minOverPull = 0;
        if (notifyY && mScroller.getCurrY() + distanceY <= -minOverPull) {
            editor.getVerticalEdgeEffect().onPull(-distanceY / editor.getMeasuredHeight(), Math.max(0, Math.min(1, e2.getX() / editor.getWidth())));
            topOrBottom = false;
        }
        if (notifyY && mScroller.getCurrY() + distanceY >= editor.getScrollMaxY() + minOverPull) {
            editor.getVerticalEdgeEffect().onPull(distanceY / editor.getMeasuredHeight(), Math.max(0, Math.min(1, e2.getX() / editor.getWidth())));
            topOrBottom = true;
        }
        if (notifyX && mScroller.getCurrX() + distanceX <= -minOverPull) {
            editor.getHorizontalEdgeEffect().onPull(-distanceX / editor.getMeasuredWidth(), Math.max(0, Math.min(1, e2.getY() / editor.getHeight())));
            leftOrRight = false;
        }
        if (notifyX && mScroller.getCurrX() + distanceX >= editor.getScrollMaxX() + minOverPull) {
            editor.getHorizontalEdgeEffect().onPull(distanceX / editor.getMeasuredWidth(), Math.max(0, Math.min(1, e2.getY() / editor.getHeight())));
            leftOrRight = true;
        }
        editor.invalidate();
        return true;
    }
    /**
     * Check whether the text action window is shown
     */
    public boolean checkActionWindow() {
        CodeEditor.EditorTextActionPresenter presenter = editor.mTextActionPresenter;
        if (presenter instanceof ContextActionController) {
            return !((ContextActionController) presenter).view.isShowing();
        }
        return true;
    }
    public void scrollBy(float distanceX, float distanceY) {
        if (editor.getTextActionPresenter() != null) {
            if (editor.getTextActionPresenter() instanceof TextActionPopupWindow) {
                editor.getTextActionPresenter().onUpdate(TextActionPopupWindow.SCROLL);
            } else {
                editor.getTextActionPresenter().onUpdate();
            }
        }
        editor.hideAutoCompleteWindow();
        int endX = mScroller.getCurrX() + (int) distanceX;
        int endY = mScroller.getCurrY() + (int) distanceY;
        endX = Math.max(endX, 0);
        endY = Math.max(endY, 0);
        endY = Math.min(endY, editor.getScrollMaxY());
        endX = Math.min(endX, editor.getScrollMaxX());
        mScroller.startScroll(mScroller.getCurrX(),
                mScroller.getCurrY(),
                endX - mScroller.getCurrX(),
                endY - mScroller.getCurrY(), 0);
        editor.invalidate();
    }
    /**
     * Notify the editor later to hide scroll bars
     */
    public void notifyScrolled() {
        long mLastScroll = refreshLastSetSelection();
        class ScrollNotifier implements Runnable {

            @Override
            public void run() {
                if (System.currentTimeMillis() - mLastScroll >= HIDE_DELAY_HANDLE) {
                    editor.invalidate();
                }
            }

        }
        editor.postDelayed(new ScrollNotifier(), HIDE_DELAY_HANDLE);
    }



    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (editor.isDrag()) {
            return false;
        }
        // If we do not finish it here, it can produce a high speed and cause the final scroll range to be broken, even a NaN for velocity
        mScroller.forceFinished(true);
        mScroller.fling(mScroller.getCurrX(),
                mScroller.getCurrY(),
                (int) -velocityX,
                (int) -velocityY,
                0,
                editor.getScrollMaxX(),
                0,
                editor.getScrollMaxY(),
                editor.isOverScrollEnabled() && !editor.isWordwrap() ? (int) (20 * editor.getDpUnit()) : 0,
                editor.isOverScrollEnabled() ? (int) (20 * editor.getDpUnit()) : 0);
        editor.invalidate();
        float minVe = editor.getDpUnit() * 2000;
        if (Math.abs(velocityX) >= minVe || Math.abs(velocityY) >= minVe) {
            notifyScrolled();
            editor.hideAutoCompleteWindow();
        }
        if (Math.abs(velocityX) >= minVe / 2f) {
            editor.getHorizontalEdgeEffect().finish();
        }
        if (Math.abs(velocityY) >= minVe) {
            editor.getVerticalEdgeEffect().finish();
        }
        return false;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Logger.debug("onScale");
        if (editor.isScalable()) {
            Logger.debug("editor is scalable");
            float newSize = editor.getTextSizePx() * detector.getScaleFactor();
            if (newSize < minSize || newSize > maxSize) {
                return false;
            }
            int firstVisible = editor.getFirstVisibleRow();
            float top = mScroller.getCurrY() - firstVisible * editor.getRowHeight();
            int height = editor.getRowHeight();
            editor.setTextSizePxDirect(newSize);
            editor.invalidate();
            float newY = firstVisible * editor.getRowHeight() + top * editor.getRowHeight() / height;
            mScroller.startScroll(mScroller.getCurrX(), (int) newY, 0, 0, 0);
            return handleOnScale();
        }
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        mScroller.forceFinished(true);
        return editor.isScalable() && handleOnScaleBegin();
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        editor.createLayout();
        editor.invalidate();
        handleOnScaleEnd();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return editor.isEnabled() && handleOnDown();
    }

    @Override
    public void onShowPress(MotionEvent e) {
        handleOnShowPress();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return handleOnSingleTapConfirmed();
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        onLongPress(e);
        return handleOnDoubleTap();
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return handleOnDoubleTapEvent();
    }

}
