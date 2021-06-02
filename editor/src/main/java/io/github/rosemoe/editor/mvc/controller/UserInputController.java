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
package io.github.rosemoe.editor.mvc.controller;

import android.graphics.RectF;
import android.view.MotionEvent;

import io.github.rosemoe.editor.mvc.model.UserInputModel;
import io.github.rosemoe.editor.mvc.view.UserInputView;
import io.github.rosemoe.editor.util.IntPair;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.TextActionPopupWindow;

import static io.github.rosemoe.editor.mvc.model.UserInputModel.*;
import static io.github.rosemoe.editor.mvc.model.UserInputModel.isSameSign;

/**
 * Handles touch events of editor
 * This is an event source for our mvc.
 * @author Rose
 */
@SuppressWarnings("CanBeFinal")
public final class UserInputController {

    public UserInputModel model = new UserInputModel();
    public final UserInputView  view;

    public final static int HIDE_DELAY = 3000;
    private final static int SELECTION_HANDLE_RESIZE_DELAY = 10;
    public final static int HIDE_DELAY_HANDLE = 5000;
    public static final long INTERACTION_END_DELAY = 100;



    private SelectionHandle insert = null, left = null, right = null;
    private float downY = 0;
    private float downX = 0;
    private int mTouchedHandleType = -1;



    /**
     * Create a event handler for the given editor
     *
     * @param editor Host editor
     */
    public UserInputController(CodeEditor editor) {
        view = new UserInputView(editor) {
            @Override
            public long refreshLastScroll() {
                model.mLastSetSelection = System.currentTimeMillis();
                return model.mLastSetSelection;
            }
            @Override
            public long refreshLastSetSelection() {
                model.mLastScroll = System.currentTimeMillis();
                return model.mLastScroll;
            }
            @Override
            public long refreshLastInteraction() {
                model.mLastInteraction = System.currentTimeMillis();
                return model.mLastInteraction;
            }

            @Override
            public boolean handleOnScale() {
                model.isScaling = true;
                return true;
            }

            @Override
            public boolean handleOnScaleEnd() {
                model.isScaling = false;
                return false;
            }
        };
    }

    /**
     * Hide the insert handle at once
     */
    public void hideInsertHandle() {
        if (!shouldDrawInsertHandle()) {
            return;
        }
        model.mLastSetSelection = 0;
        view.mEditor.invalidate();
    }

    /**
     * Whether the vertical scroll bar is touched
     *
     * @return Whether touched
     */
    public boolean holdVerticalScrollBar() {
        return model.mHoldingScrollbarVertical;
    }

    /**
     * Whether the horizontal scroll bar is touched
     *
     * @return Whether touched
     */
    public boolean holdHorizontalScrollBar() {
        return model.mHoldingScrollbarHorizontal;
    }

    /**
     * Whether insert handle is touched
     *
     * @return Whether touched
     */
    public boolean holdInsertHandle() {
        return model.mHoldingInsertHandle;
    }

    /**
     * Whether the editor should draw insert handler
     *
     * @return Whether to draw
     */
    public boolean shouldDrawInsertHandle() {
        return (System.currentTimeMillis() - model.mLastSetSelection < HIDE_DELAY || model.mHoldingInsertHandle) && view.checkActionWindow();
    }



    /**
     * Notify the editor later to resize touched selection handle to normal size
     */
    public void notifyTouchedSelectionHandlerLater() {
        model.mLastTouchedSelectionHandle = System.currentTimeMillis();
        class InvalidateNotifier implements Runnable {

            @Override
            public void run() {
                if (System.currentTimeMillis() - model.mLastTouchedSelectionHandle >= SELECTION_HANDLE_RESIZE_DELAY) {
                    view.mEditor.invalidate();
                    view.mEditor.onEndTextSelect();
                }
            }
        }
        view.mEditor.postDelayed(new InvalidateNotifier(), SELECTION_HANDLE_RESIZE_DELAY);
    }



    /**
     * Called by editor
     * Whether this class is handling motions by user
     *
     * @return Whether handling
     */
    public boolean handlingMotions() {
        return holdHorizontalScrollBar() || holdVerticalScrollBar() || holdInsertHandle() || model.selectionHandleType != -1;
    }

    /**
     * Handle events apart from detectors
     *
     * @param e The event editor received
     * @return Whether this touch event is handled by this class
     */
    public boolean onTouchEvent(MotionEvent e) {
        if (model.edgeFieldSize == 0) {
            model.edgeFieldSize = view.mEditor.getDpUnit() * 25;
        }
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                model.mHoldingScrollbarVertical = model.mHoldingScrollbarHorizontal = false;
                RectF rect = view.mEditor.getVerticalScrollBarRect();
                if (rect.contains(e.getX(), e.getY())) {
                    model.mHoldingScrollbarVertical = true;
                    downY = e.getY();
                    view.mEditor.hideAutoCompleteWindow();
                }
                rect = view.mEditor.getHorizontalScrollBarRect();
                if (rect.contains(e.getX(), e.getY())) {
                    model.mHoldingScrollbarHorizontal = true;
                    downX = e.getX();
                    view.mEditor.hideAutoCompleteWindow();
                }
                if (model.mHoldingScrollbarVertical && model.mHoldingScrollbarHorizontal) {
                    model.mHoldingScrollbarHorizontal = false;
                }
                if (model.mHoldingScrollbarVertical || model.mHoldingScrollbarHorizontal) {
                    view.mEditor.invalidate();
                }
                if (shouldDrawInsertHandle() && view.mEditor.getInsertHandleRect().contains(e.getX(), e.getY())) {
                    model.mHoldingInsertHandle = true;
                    downY = e.getY();
                    downX = e.getX();

                    insert = new SelectionHandle(SelectionHandle.BOTH);
                }
                boolean left = view.mEditor.getLeftHandleRect().contains(e.getX(), e.getY());
                boolean right = view.mEditor.getRightHandleRect().contains(e.getX(), e.getY());
                if (left || right) {
                    if (left) {
                        model.selectionHandleType = SelectionHandle.LEFT;
                        mTouchedHandleType = SelectionHandle.LEFT;
                    } else {
                        model.selectionHandleType = SelectionHandle.RIGHT;
                        mTouchedHandleType = SelectionHandle.RIGHT;
                    }
                    downY = e.getY();
                    downX = e.getX();

                    this.left = new SelectionHandle(SelectionHandle.LEFT);
                    this.right = new SelectionHandle(SelectionHandle.RIGHT);

                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (model.mHoldingScrollbarVertical) {
                    float movedDis = e.getY() - downY;
                    downY = e.getY();
                    float all = view.mEditor.mLayout.getLayoutHeight() + view.mEditor.getHeight() / 2f;
                    float dy = movedDis / view.mEditor.getHeight() * all;
                    view.scrollBy(0, dy);
                    return true;
                }
                if (model.mHoldingScrollbarHorizontal) {
                    float movedDis = e.getX() - downX;
                    downX = e.getX();
                    float all = view.mEditor.getScrollMaxX() + view.mEditor.getWidth();
                    float dx = movedDis / view.mEditor.getWidth() * all;
                    view.scrollBy(dx, 0);
                    return true;
                }
                return handleSelectionChange(e);
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (model.mHoldingScrollbarVertical) {
                    model.mHoldingScrollbarVertical = false;
                    view.mEditor.invalidate();
                    model.mLastScroll = System.currentTimeMillis();
                    view.notifyScrolled();
                }
                if (model.mHoldingScrollbarHorizontal) {
                    model.mHoldingScrollbarHorizontal = false;
                    view.mEditor.invalidate();
                    model.mLastScroll = System.currentTimeMillis();
                    view.notifyScrolled();
                }
                if (model.mHoldingInsertHandle) {
                    model.mHoldingInsertHandle = false;
                    view.mEditor.invalidate();
                    view.notifyLater();
                }
                model.selectionHandleType = -1;

                // check touch event is related to text selection or not
                if (mTouchedHandleType > -1) {
                    mTouchedHandleType = -1;
                    notifyTouchedSelectionHandlerLater();
                }
                stopEdgeScroll();
                break;
        }
        return false;
    }

    private boolean handleSelectionChange(MotionEvent e) {
        if (model.mHoldingInsertHandle) {
            insert.applyPosition(e);
            scrollIfThumbReachesEdge(e);
            return true;
        }
        switch (model.selectionHandleType) {
            case SelectionHandle.LEFT:
                this.left.applyPosition(e);
                scrollIfThumbReachesEdge(e);
                return true;
            case SelectionHandle.RIGHT:
                this.right.applyPosition(e);
                scrollIfThumbReachesEdge(e);
                return true;
        }
        return false;
    }

    private void handleSelectionChange2(MotionEvent e) {
        if (model.mHoldingInsertHandle) {
            insert.applyPosition(e);
        } else {
            switch (model.selectionHandleType) {
                case SelectionHandle.LEFT:
                    this.left.applyPosition(e);
                    break;
                case SelectionHandle.RIGHT:
                    this.right.applyPosition(e);
                    break;
            }
        }
    }
    private void scrollIfThumbReachesEdge(MotionEvent e) {
        int flag = model.computeEdgeFlags(e.getX(), e.getY(), view.mEditor.getWidth(), view.mEditor.getHeight());
        int initialDelta = (int) (8 * view.mEditor.getDpUnit());
        if (flag != 0 && view.mEdgeFlags == 0) {
            view.mEdgeFlags = flag;
            view.mThumb = MotionEvent.obtain(e);
            view.mEditor.post(new UserInputController.EdgeScrollRunnable(initialDelta));
        } else if (flag == 0) {
            stopEdgeScroll();
        } else {
            view.mEdgeFlags = flag;
            view.mThumb = MotionEvent.obtain(e);
        }
    }

    private void stopEdgeScroll() {
        view.mEdgeFlags = 0;
    }






    public int getTouchedHandleType() {
        return mTouchedHandleType;
    }



    /**
     * This is a helper for EventHandler to control handles
     */
    @SuppressWarnings("CanBeFinal")
    public
    class SelectionHandle {

        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int BOTH = 2;

        public int type;

        /**
         * Create a handle
         *
         * @param type Type :left,right,both
         */
        public SelectionHandle(int type) {
            this.type = type;
        }

        /**
         * Handle the event
         *
         * @param e Event sent by EventHandler
         */
        public void applyPosition(MotionEvent e) {
            float targetX = view.mScroller.getCurrX() + e.getX();
            float targetY = view.mScroller.getCurrY() + e.getY() - view.mEditor.getInsertHandleRect().height() * 4 / 3;
            int line = IntPair.getFirst(view.mEditor.getPointPosition(0, targetY));
            if (line >= 0 && line < view.mEditor.getLineCount()) {
                int column = IntPair.getSecond(view.mEditor.getPointPosition(targetX, targetY));
                int lastLine = type == RIGHT ? view.mEditor.getCursor().getRightLine() : view.mEditor.getCursor().getLeftLine();
                int lastColumn = type == RIGHT ? view.mEditor.getCursor().getLeftColumn() : view.mEditor.getCursor().getLeftColumn();
                int anotherLine = type != RIGHT ? view.mEditor.getCursor().getRightLine() : view.mEditor.getCursor().getLeftLine();
                int anotherColumn = type != RIGHT ? view.mEditor.getCursor().getRightColumn() : view.mEditor.getCursor().getLeftColumn();

                if (line != lastLine || column != lastColumn) {
                    switch (type) {
                        case BOTH:
                            view.mEditor.cancelAnimation();
                            view.mEditor.setSelection(line, column, false);
                            break;
                        case RIGHT:
                            if (anotherLine > line || (anotherLine == line && anotherColumn > column)) {
                                //Swap type
                                UserInputController.this.model.selectionHandleType = LEFT;
                                this.type = LEFT;
                                left.type = RIGHT;
                                SelectionHandle tmp = right;
                                right = left;
                                left = tmp;
                                view.mEditor.setSelectionRegion(line, column, anotherLine, anotherColumn, false);
                            } else {
                                view.mEditor.setSelectionRegion(anotherLine, anotherColumn, line, column, false);
                            }
                            break;
                        case LEFT:
                            if (anotherLine < line || (anotherLine == line && anotherColumn < column)) {
                                //Swap type
                                UserInputController.this.model.selectionHandleType = RIGHT;
                                this.type = RIGHT;
                                right.type = LEFT;
                                SelectionHandle tmp = right;
                                right = left;
                                left = tmp;
                                view.mEditor.setSelectionRegion(anotherLine, anotherColumn, line, column, false);
                            } else {
                                view.mEditor.setSelectionRegion(line, column, anotherLine, anotherColumn, false);
                            }
                            break;
                    }
                }
            }

            if (view.mEditor.getTextActionPresenter() instanceof TextActionPopupWindow) {
                view.mEditor.getTextActionPresenter().onUpdate(TextActionPopupWindow.DRAG);
            } else {
                view.mEditor.getTextActionPresenter().onUpdate();
            }
        }

    }

    /**
     * Runnable for controlling auto-scrolling when thumb reaches the edges of editor
     */
    private class EdgeScrollRunnable implements Runnable {
        private final static int MAX_FACTOR = 25;
        private final static float INCREASE_FACTOR = 1.06f;

        private int initialDelta;
        private int deltaHorizontal;
        private int deltaVertical;
        private int lastDx, lastDy;
        private int factorX, factorY;

        public EdgeScrollRunnable(int initDelta) {
            initialDelta = deltaHorizontal = deltaVertical = initDelta;
        }

        @Override
        public void run() {
            int dx = (((view.mEdgeFlags & LEFT_EDGE) != 0) ? -deltaHorizontal : 0) + (((view.mEdgeFlags & RIGHT_EDGE) != 0) ? deltaHorizontal : 0);
            int dy = (((view.mEdgeFlags & TOP_EDGE) != 0) ? -deltaVertical : 0) + (((view.mEdgeFlags & BOTTOM_EDGE) != 0) ? deltaVertical : 0);
            if (dx > 0) {
                // Check whether there is content at right
                int line;
                if (model.mHoldingInsertHandle || model.selectionHandleType == SelectionHandle.LEFT) {
                    line = view.mEditor.getCursor().getLeftLine();
                } else {
                    line = view.mEditor.getCursor().getRightLine();
                }
                int column = view.mEditor.getText().getColumnCount(line);
                // Do not scroll too far from text region of this line
                float maxOffset = view.mEditor.measureTextRegionOffset() + view.mEditor.mLayout.getCharLayoutOffset(line, column)[1] - view.mEditor.getWidth() * 0.85f;
                if (view.mScroller.getCurrX() > maxOffset) {
                    dx = 0;
                }
            }
            view.scrollBy(dx, dy);

            // Speed up if we are scrolling in the direction
            if (isSameSign(dx, lastDx)) {
                if (factorX < MAX_FACTOR) {
                    factorX++;
                    deltaHorizontal *= INCREASE_FACTOR;
                }
            } else {
                // Recover initial speed because direction changed
                deltaHorizontal = initialDelta;
                factorX = 0;
            }
            if (isSameSign(dy, lastDy)) {
                if (factorY < MAX_FACTOR) {
                    factorY++;
                    deltaVertical *= INCREASE_FACTOR;
                }
            } else {
                deltaVertical = initialDelta;
                factorY = 0;
            }
            lastDx = dx;
            lastDy = dy;

            // Update selection
            handleSelectionChange2(view.mThumb);

            // Post for animation
            if (view.mEdgeFlags != 0) {
                view.mEditor.postDelayed(this, 10);
            }
        }
    }
}

