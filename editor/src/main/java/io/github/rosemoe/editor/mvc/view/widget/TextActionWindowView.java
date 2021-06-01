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
package io.github.rosemoe.editor.mvc.view.widget;

import android.view.View;
import android.widget.Button;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.widget.CodeEditor;

public class TextActionWindowView {
    public Button mPasteBtn;
    public Button mCopyBtn;
    public Button mCutBtn;
    public View mRootView;
    public void updateBtnState(CodeEditor editor) {
        mPasteBtn.setEnabled(editor.hasClip());
        mCopyBtn.setVisibility(editor.getCursor().isSelected() ? View.VISIBLE : View.GONE);
        mCutBtn.setVisibility(editor.getCursor().isSelected() ? View.VISIBLE : View.GONE);
        mRootView.measure(View.MeasureSpec.makeMeasureSpec(1000000, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(100000, View.MeasureSpec.AT_MOST));
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
}
