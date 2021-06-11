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
package io.github.rosemoe.editor.core.widgets.contextaction.view;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.EditorBasePopupWindow;

public class ContextActionView extends EditorBasePopupWindow implements View.OnClickListener, CodeEditor.EditorTextActionPresenter {

    private final CodeEditor mEditor;
    public Button mPasteBtn;
    public Button mCopyBtn;
    public Button mCutBtn;
    public View mRootView;
    public ContextActionView(CodeEditor editor) {
        super(editor);
        this.mEditor = editor;
        // Since popup window does provide decor view, we have to pass null to this method
        @SuppressLint("InflateParams")
        View root = LayoutInflater.from(editor.getContext()).inflate(R.layout.text_compose_panel, null);
        Button selectAll = root.findViewById(R.id.panel_btn_select_all);
        Button cut = root.findViewById(R.id.panel_btn_cut);
        Button copy = root.findViewById(R.id.panel_btn_copy);
        mPasteBtn = root.findViewById(R.id.panel_btn_paste);
        mCopyBtn = copy;
        mCutBtn = cut;
        selectAll.setOnClickListener(this);
        cut.setOnClickListener(this);
        copy.setOnClickListener(this);
        mPasteBtn.setOnClickListener(this);
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(5);
        gd.setColor(0xffffffff);
        root.setBackground(gd);
        setContentView(root);
        mRootView = root;
    }
    public void updateBtnState(CodeEditor editor) {
        mPasteBtn.setEnabled(editor.hasClip());
        mCopyBtn.setVisibility(editor.getCursor().isSelected() ? View.VISIBLE : View.GONE);
        mCutBtn.setVisibility(editor.getCursor().isSelected() ? View.VISIBLE : View.GONE);
        mRootView.measure(View.MeasureSpec.makeMeasureSpec(1000000, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(100000, View.MeasureSpec.AT_MOST));
    }

    @Override
    public void onBeginTextSelect() {
        float dpUnit = mEditor.getDpUnit();
        int maxWidth = (int) (dpUnit * 230);
        setHeight((int) (dpUnit * 60));
        handleOnBeginTextSelect(maxWidth);
        setWidth(maxWidth);
    }

    @Override
    public boolean onExit() {
        boolean result = isShowing();
        hide();
        return result;
    }
    public void click(CodeEditor editor,View p1) {
        int id = p1.getId();
        if (id == R.id.panel_btn_select_all) {
            editor.selectAll();
        } else if (id == R.id.panel_btn_cut) {
            editor.copyText();
            if (editor.getCursor().isSelected()) {
                editor.getCursor().onDeleteKeyPressed();
            }
        } else if (id == R.id.panel_btn_paste) {
            editor.pasteText();
            editor.setSelection(editor.getCursor().getRightLine(), editor.getCursor().getRightColumn());
        } else if (id == R.id.panel_btn_copy) {
            editor.copyText();
            editor.setSelection(editor.getCursor().getRightLine(), editor.getCursor().getRightColumn());
        }
    }


    @Override
    public void onSelectedTextClicked(MotionEvent event) {
        if (isShowing()) {
            hide();
        } else {
            int first = mEditor.getFirstVisibleRow();
            int last = mEditor.getLastVisibleRow();
            int left = mEditor.getCursor().getLeftLine();
            int right = mEditor.getCursor().getRightLine();
            int toLineBottom;
            if (right <= first) {
                toLineBottom = first;
            } else if (right > last) {
                if (left <= first) {
                    toLineBottom = (first + last) / 2;
                } else if (left >= last) {
                    toLineBottom = last - 2;
                } else {
                    if (left + 3 >= last) {
                        toLineBottom = left - 2;
                    } else {
                        toLineBottom = left + 1;
                    }
                }
            } else {
                if (left <= first) {
                    if (right + 3 >= last) {
                        toLineBottom = right - 2;
                    } else {
                        toLineBottom = right + 1;
                    }
                } else {
                    if (left + 5 >= right) {
                        toLineBottom = right + 1;
                    } else {
                        toLineBottom = (left + right) / 2;
                    }
                }
            }
            toLineBottom = Math.max(0, toLineBottom);
            int panelY = mEditor.getRowBottom(toLineBottom) - mEditor.getOffsetY();
            float handleLeftX = mEditor.getOffset(left, mEditor.getCursor().getLeftColumn());
            float handleRightX = mEditor.getOffset(right, mEditor.getCursor().getRightColumn());
            int panelX = (int) ((handleLeftX + handleRightX) / 2f);
            setExtendedX(panelX);
            setExtendedY(panelY);
            show();
        }
    }


    /**
     * Update the state of paste button
     */
    private void updateBtnState() {
        updateBtnState(mEditor);
        handleUpdateBtnState();
    }

    @Override
    public void onUpdate() {
        hide();
    }

    @Override
    public void onUpdate(int updateReason) {
        hide();
    }

    @Override
    public void show() {
        updateBtnState();
        setElevation(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, Resources.getSystem().getDisplayMetrics()));
        super.show();
    }
    @Override
    public void onClick(View p1) {
        click(mEditor,p1);
        hide();
    }
    @Override
    public void onTextSelectionEnd() {

    }
    @Override
    public boolean shouldShowCursor() {
        return !isShowing();
    }

    public void handleOnBeginTextSelect(int maxWidth) {}
    public void handleUpdateBtnState() {}
}
