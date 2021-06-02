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

import android.app.ProgressDialog;
import android.widget.Toast;

import io.github.rosemoe.editor.widget.CodeEditor;

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
}
