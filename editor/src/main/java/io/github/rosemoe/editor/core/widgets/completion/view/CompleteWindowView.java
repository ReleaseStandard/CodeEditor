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
package io.github.rosemoe.editor.core.widgets.completion.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.ColorSchemeController;
import io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController;
import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.EditorBasePopupWindow;
import io.github.rosemoe.editor.core.widgets.completion.controller.CompletionAdapter;

public class CompleteWindowView extends EditorBasePopupWindow {
    public CompletionAdapter mAdapter;
    public ListView mListView = null;
    public TextView mTip = null;
    public GradientDrawable mBg = null;
    private final CodeEditor mEditor;

    public CompleteWindowView(CodeEditor editor) {
        super(editor);
        mEditor = editor;
        Context ctx = editor.getContext();
        mAdapter = new DefaultCompletionItemAdapter(editor);
        RelativeLayout layout = new RelativeLayout(ctx);
        mListView = new ListView(ctx);
        layout.addView(mListView, new LinearLayout.LayoutParams(-1, -1));
        mTip = new TextView(ctx);
        mTip.setText("Refreshing...");
        mTip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        mTip.setBackgroundColor(editor.getColorScheme().getCompletionPanelBackground());
        mTip.setTextColor(editor.getColorScheme().getTextNormal());
        layout.addView(mTip);
        ((RelativeLayout.LayoutParams) mTip.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        setContentView(layout);
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(1);
        layout.setBackgroundDrawable(gd);
        mBg = gd;
        mListView.setDividerHeight(0);
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                select(position);
            } catch (Exception e) {
                Toast.makeText(ctx, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void applyColorScheme(ColorSchemeController theme) {
        mBg.setStroke(1, theme.getCompletionPanelCorner());
        mBg.setColor(theme.getCompletionPanelBackground());
    }
    public void setAdapter(CompletionAdapter adapter) {
        mAdapter = adapter;
        if (adapter == null) {
            mAdapter = new DefaultCompletionItemAdapter(mEditor);
        }
    }

    @Override
    public void show() {
        handleShow();
        super.show();
    }

    /**
     * Select the given position
     *
     * @param pos Index of auto complete item
     */
    public void select(int pos) {
        CursorController cursor = mEditor.getCursor();
        handleCursorSelect(cursor,pos);
        mEditor.postHideCompletionWindow();
    }

    public void handleShow() { }
    public void handleCursorSelect(CursorController cursor, int pos) { }
}
