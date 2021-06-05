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
package io.github.rosemoe.editor.mvc.controller.widgets.userinput;

import android.content.Context;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.OverScroller;

import io.github.rosemoe.editor.mvc.controller.widgets.Widget;
import io.github.rosemoe.editor.extension.events.Event;
import io.github.rosemoe.editor.mvc.model.widget.userinput.UserInputModel;
import io.github.rosemoe.editor.mvc.view.widget.userinput.UserInputView;
import io.github.rosemoe.editor.util.IntPair;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.TextActionPopupWindow;

import static io.github.rosemoe.editor.mvc.controller.widgets.loopback.LoopbackEvent.TYPE_LOOPBACK;
import static io.github.rosemoe.editor.mvc.controller.widgets.userinput.UserInputEvent.*;
import static io.github.rosemoe.editor.mvc.model.widget.userinput.UserInputModel.*;
import static io.github.rosemoe.editor.mvc.model.widget.userinput.UserInputModel.isSameSign;

/**
 * Handles touch events of editor
 * This is an event source for our mvc.
 * Each widget could an event source or an event destination.
 *
 *
 * @author Rose
 */
@SuppressWarnings("CanBeFinal")
public final class UserInputController extends Widget {

    public UserInputModel model = new UserInputModel();
    public final UserInputView  view;

    public final static int HIDE_DELAY = 3000;
    private final static int SELECTION_HANDLE_RESIZE_DELAY = 10;
    public final static int HIDE_DELAY_HANDLE = 5000;
    public static final long INTERACTION_END_DELAY = 100;

    private SelectionHandle insert = null, left = null, right = null;
    private float downY = 0;
    private float downX = 0;
    private int touchedHandleType = -1;

    /**
     * Create a event handler for the given editor
     *
     * @param editor Host editor
     */
    public UserInputController(CodeEditor editor, Context ctx) {
        super();
        subscribe(TYPE_USERINPUT);
        view = new UserInputView(editor,ctx) {
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
            @Override public boolean handleOnScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY, int endX, int endY) {
                emit(ONSCROLL,e1,e2,distanceX,distanceY,endX,endY);
                return super.handleOnScroll(e1,e2,distanceX,distanceY,endX,endY);
            }
            @Override public boolean handleOnSingleTapUp(int line, int column) {
                emit(SINGLETAPUP,line,column);
                return super.handleOnSingleTapUp(line,column);
            }
            @Override public void handleOnLongPress(MotionEvent e,int startLine,int startColumn, int endLine, int endColumn) {
                emit(LONGPRESS,e,startLine,startColumn,endLine,endColumn);
                super.handleOnLongPress(e,startLine,startColumn,endLine,endColumn);
            }
            @Override public boolean handleOnFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
                emit(ONFLING,e1,e2,velocityX,velocityY);
                return super.handleOnFling(e1,e2,velocityX,velocityY);
            }
            @Override
            public boolean handleOnScale(int firstVisibleRow, float top, int height, float newY) {
                model.isScaling = true;
                emit(ONSCALE,firstVisibleRow,top,height,newY);
                return super.handleOnScale(firstVisibleRow,top,height,newY);
            }
            @Override
            public boolean handleOnScaleBegin(ScaleGestureDetector detector) {
                emit(ONSCALEBEGIN,detector);
                return super.handleOnScaleBegin(detector);
            }
            @Override
            public boolean handleOnScaleEnd(ScaleGestureDetector detector) {
                model.isScaling = false;
                emit(ONSCALEEND,detector);
                return super.handleOnScaleEnd(detector);
            }
            @Override
            public boolean handleOnDown(MotionEvent e) {
                emit(ONDOWN,e);
                return super.handleOnDown(e);
            }
            @Override
            public void handleOnShowPress(MotionEvent e) {
                emit(ONSHOWPRESS,e);
                super.handleOnShowPress(e);
            }
            @Override
            public boolean handleOnSingleTapConfirmed(MotionEvent e) {
                emit(ONSINGLETAPCONFIRMED,e);
                return super.handleOnSingleTapConfirmed(e);
            }
            @Override
            public boolean handleOnDoubleTap(MotionEvent e){
                emit(ONDOUBLETAP,e);
                return super.handleOnDoubleTap(e);
            }
            @Override
            public boolean handleOnDoubleTapEvent(MotionEvent e) {
                emit(ONDOUBLETAPEVENT,e);
                return super.handleOnDoubleTapEvent(e);
            }
        };
        setOverScrollEnabled(true);
    }

    /**
     * Hide the insert handle at once
     */
    public void hideInsertHandle() {
        if (!shouldDrawInsertHandle()) {
            return;
        }
        model.mLastSetSelection = 0;
        view.editor.invalidate();
    }

    /**
     * Whether the vertical scroll bar is touched
     *
     * @return Whether touched
     */
    public boolean holdVerticalScrollBar() {
        return model.holdingScrollbarVertical;
    }

    /**
     * Whether the horizontal scroll bar is touched
     *
     * @return Whether touched
     */
    public boolean holdHorizontalScrollBar() {
        return model.holdingScrollbarHorizontal;
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
     * @see #setOverScrollEnabled(boolean)
     */
    public boolean isOverScrollEnabled() {
        return model.mOverScrollEnabled;
    }

    /**
     * Whether over scroll is permitted.
     * When over scroll is enabled, the user will be able to scroll out of displaying
     * bounds if the user scroll fast enough.
     * This is implemented by {@link OverScroller#fling(int, int, int, int, int, int, int, int, int, int)}
     */
    public void setOverScrollEnabled(boolean overScrollEnabled) {
        model.mOverScrollEnabled = overScrollEnabled;
    }

    /**
     * Whether display vertical scroll bar when scrolling
     *
     * @param enabled Enabled / disabled
     */
    public void setScrollBarEnabled(boolean enabled) {
        model.mVerticalScrollBarEnabled = model.mHorizontalScrollBarEnabled = enabled;
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
                    view.editor.invalidate();
                    view.editor.onEndTextSelect();
                }
            }
        }
        view.editor.postDelayed(new InvalidateNotifier(), SELECTION_HANDLE_RESIZE_DELAY);
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
        Logger.debug(e.getAction(),": down=",MotionEvent.ACTION_DOWN,",move=",MotionEvent.ACTION_MOVE,",up=",MotionEvent.ACTION_UP,",cancel=",MotionEvent.ACTION_CANCEL);
        if (model.edgeFieldSize == 0) {
            model.edgeFieldSize = view.editor.getDpUnit() * 25;
        }
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                model.holdingScrollbarVertical = model.holdingScrollbarHorizontal = false;
                RectF rect = view.getVerticalScrollBarRect();
                if (rect.contains(e.getX(), e.getY())) {
                    model.holdingScrollbarVertical = true;
                    downY = e.getY();
                    view.editor.hideAutoCompleteWindow();
                }
                rect = view.getHorizontalScrollBarRect();
                if (rect.contains(e.getX(), e.getY())) {
                    model.holdingScrollbarHorizontal = true;
                    downX = e.getX();
                    view.editor.hideAutoCompleteWindow();
                }
                if (model.holdingScrollbarVertical && model.holdingScrollbarHorizontal) {
                    model.holdingScrollbarHorizontal = false;
                }
                if (model.holdingScrollbarVertical || model.holdingScrollbarHorizontal) {
                    view.editor.invalidate();
                }
                if (shouldDrawInsertHandle() && view.editor.getInsertHandleRect().contains(e.getX(), e.getY())) {
                    model.mHoldingInsertHandle = true;
                    downY = e.getY();
                    downX = e.getX();

                    insert = new SelectionHandle(SelectionHandle.BOTH);
                }
                boolean left = view.editor.getLeftHandleRect().contains(e.getX(), e.getY());
                boolean right = view.editor.getRightHandleRect().contains(e.getX(), e.getY());
                if (left || right) {
                    if (left) {
                        model.selectionHandleType = SelectionHandle.LEFT;
                        touchedHandleType = SelectionHandle.LEFT;
                    } else {
                        model.selectionHandleType = SelectionHandle.RIGHT;
                        touchedHandleType = SelectionHandle.RIGHT;
                    }
                    downY = e.getY();
                    downX = e.getX();

                    this.left = new SelectionHandle(SelectionHandle.LEFT);
                    this.right = new SelectionHandle(SelectionHandle.RIGHT);

                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (model.holdingScrollbarVertical) {
                    float movedDis = e.getY() - downY;
                    downY = e.getY();
                    float all = view.editor.mLayout.getLayoutHeight() + view.editor.getHeight() / 2f;
                    float dy = movedDis / view.editor.getHeight() * all;
                    view.scrollBy(0, dy);
                    return true;
                }
                if (model.holdingScrollbarHorizontal) {
                    float movedDis = e.getX() - downX;
                    downX = e.getX();
                    float all = view.editor.getScrollMaxX() + view.editor.getWidth();
                    float dx = movedDis / view.editor.getWidth() * all;
                    view.scrollBy(dx, 0);
                    return true;
                }
                return handleSelectionChange(e);
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (model.holdingScrollbarVertical) {
                    model.holdingScrollbarVertical = false;
                    view.editor.invalidate();
                    model.mLastScroll = System.currentTimeMillis();
                    view.notifyScrolled();
                }
                if (model.holdingScrollbarHorizontal) {
                    model.holdingScrollbarHorizontal = false;
                    view.editor.invalidate();
                    model.mLastScroll = System.currentTimeMillis();
                    view.notifyScrolled();
                }
                if (model.mHoldingInsertHandle) {
                    model.mHoldingInsertHandle = false;
                    view.editor.invalidate();
                    view.notifyLater();
                }
                model.selectionHandleType = -1;

                // check touch event is related to text selection or not
                if (touchedHandleType > -1) {
                    touchedHandleType = -1;
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
        int flag = model.computeEdgeFlags(e.getX(), e.getY(), view.editor.getWidth(), view.editor.getHeight());
        int initialDelta = (int) (8 * view.editor.getDpUnit());
        if (flag != 0 && view.mEdgeFlags == 0) {
            view.mEdgeFlags = flag;
            view.mThumb = MotionEvent.obtain(e);
            view.editor.post(new UserInputController.EdgeScrollRunnable(initialDelta));
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
        return touchedHandleType;
    }

    /**
     * Emit an event on the attached CodeEditor.
     * Destination of the event may vary.
     */
    private void emit(String subtype,Object ...args) {
        UserInputEvent e = new UserInputEvent();
        e.putArgs(args);
        e.subtype = subtype;
        emit(e);
    }


    @Override
    public void handleEventEmit(Event e) {
        super.handleEventEmit(e);
        Logger.debug("Dispatching event to plugins !");
        view.editor.plugins.dispatch(e);
    }

    @Override
    public void handleEventDispatch(Event e, String type, String subtype) {
        Logger.debug("TYPE_LOOPBACK=",issubscribed(TYPE_LOOPBACK),",TYPE_USERINPUT=",issubscribed(UserInputEvent.TYPE_USERINPUT));
        UserInputEvent uie = (UserInputEvent) e;
        switch(subtype) {
        }
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
            float targetY = view.mScroller.getCurrY() + e.getY() - view.editor.getInsertHandleRect().height() * 4 / 3;
            int line = IntPair.getFirst(view.editor.getPointPosition(0, targetY));
            if (line >= 0 && line < view.editor.getLineCount()) {
                int column = IntPair.getSecond(view.editor.getPointPosition(targetX, targetY));
                int lastLine = type == RIGHT ? view.editor.getCursor().getRightLine() : view.editor.getCursor().getLeftLine();
                int lastColumn = type == RIGHT ? view.editor.getCursor().getLeftColumn() : view.editor.getCursor().getLeftColumn();
                int anotherLine = type != RIGHT ? view.editor.getCursor().getRightLine() : view.editor.getCursor().getLeftLine();
                int anotherColumn = type != RIGHT ? view.editor.getCursor().getRightColumn() : view.editor.getCursor().getLeftColumn();

                if (line != lastLine || column != lastColumn) {
                    switch (type) {
                        case BOTH:
                            view.editor.cancelAnimation();
                            view.editor.setSelection(line, column, false);
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
                                view.editor.setSelectionRegion(line, column, anotherLine, anotherColumn, false);
                            } else {
                                view.editor.setSelectionRegion(anotherLine, anotherColumn, line, column, false);
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
                                view.editor.setSelectionRegion(anotherLine, anotherColumn, line, column, false);
                            } else {
                                view.editor.setSelectionRegion(line, column, anotherLine, anotherColumn, false);
                            }
                            break;
                    }
                }
            }

            if (view.editor.getTextActionPresenter() instanceof TextActionPopupWindow) {
                view.editor.getTextActionPresenter().onUpdate(TextActionPopupWindow.DRAG);
            } else {
                view.editor.getTextActionPresenter().onUpdate();
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
                    line = view.editor.getCursor().getLeftLine();
                } else {
                    line = view.editor.getCursor().getRightLine();
                }
                int column = view.editor.getText().getColumnCount(line);
                // Do not scroll too far from text region of this line
                float maxOffset = view.editor.measureTextRegionOffset() + view.editor.mLayout.getCharLayoutOffset(line, column)[1] - view.editor.getWidth() * 0.85f;
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
                view.editor.postDelayed(this, 10);
            }
        }
    }
}

