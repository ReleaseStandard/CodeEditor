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
package io.github.rosemoe.editor.core.widgets.searcher.view;

import android.app.ProgressDialog;
import android.widget.Toast;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController;
import io.github.rosemoe.editor.core.CodeEditor;

public class SearcherView {
    public CodeEditor editor;
    public void showSearchDialog(String searchText, String newText) {
        final ProgressDialog progressDialog = ProgressDialog.show(editor.getContext(), "Replacing", "Editor is now replacing texts, please wait", true, false);
        new Thread() {
            @Override
            public void run() {
                String text = null;
                Exception ex = null;
                try {
                    text = editor.getText().toString().replace(searchText, newText);
                } catch (Exception e) {
                    e.printStackTrace();
                    ex = e;
                }
                final Exception ex2 = ex;
                final String text2 = text;
                editor.post(() -> {
                    if (text2 == null) {
                        Toast.makeText(editor.getContext(), String.valueOf(ex2), Toast.LENGTH_SHORT).show();
                    } else {
                        int line = editor.getCursor().getLeftLine();
                        int column = editor.getCursor().getLeftColumn();
                        editor.getText().replace(0, 0, editor.getLineCount() - 1, editor.getText().getColumnCount(editor.getLineCount() - 1), text2);
                        editor.setSelectionAround(line, column);
                        editor.invalidate();
                    }
                    progressDialog.cancel();
                });
            }

        }.start();
    }
    public void gotoNext(final String searchText,boolean tip) {
        ContentMapController text = editor.getText();
        CursorController cursor = text.getCursor();
        int line = cursor.getRightLine();
        int column = cursor.getRightColumn();
        for (int i = line; i < text.getLineCount(); i++) {
            int idx = column >= text.getColumnCount(i) ? -1 : text.getLine(i).indexOf(searchText, column);
            if (idx != -1) {
                editor.setSelectionRegion(i, idx, i, idx + searchText.length());
                return;
            }
            column = 0;
        }
        if (tip) {
            Toast.makeText(editor.getContext(), "Not found in this direction", Toast.LENGTH_SHORT).show();
            editor.jumpToLine(0);
        }
    }
    public void gotoLast(String searchText) {
        ContentMapController text = editor.getText();
        CursorController cursor = text.getCursor();
        int line = cursor.getLeftLine();
        int column = cursor.getLeftColumn();
        for (int i = line; i >= 0; i--) {
            int idx = column - 1 < 0 ? -1 : text.getLine(i).lastIndexOf(searchText, column - 1);
            if (idx != -1) {
                editor.setSelectionRegion(i, idx, i, idx + searchText.length());
                return;
            }
            column = i - 1 >= 0 ? text.getColumnCount(i - 1) : 0;
        }
        Toast.makeText(editor.getContext(), "Not found in this direction", Toast.LENGTH_SHORT).show();
    }
}
