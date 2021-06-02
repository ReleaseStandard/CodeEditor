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
package io.github.rosemoe.editor.mvc.controller.widget;

import android.app.ProgressDialog;
import android.widget.Toast;

import io.github.rosemoe.editor.mvc.model.widget.SearcherModel;
import io.github.rosemoe.editor.mvc.controller.content.ContentController;
import io.github.rosemoe.editor.mvc.view.widget.SearcherView;
import io.github.rosemoe.editor.widget.CodeEditor;

/**
 * Search text in editor
 *
 * @author Rose
 */
@SuppressWarnings("deprecated")
public class SearcherController {

    public SearcherModel model = new SearcherModel();
    public SearcherView view   = new SearcherView();

    public SearcherController(CodeEditor editor) {
        view.editor = editor;
    }

    private void checkState() {
        if (model.searchText == null) {
            throw new IllegalStateException("search text has not been set");
        }
    }

    public void search(String text) {
        if (text != null && text.length() == 0) {
            text = null;
        }
        model.searchText = text;
        view.editor.postInvalidate();
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean replaceThis(String newText) {
        checkState();
        ContentController text = view.editor.getText();
        CursorController cursor = text.getCursor();
        if (cursor.isSelected()) {
            String selectedText = text.subContent(cursor.getLeftLine(), cursor.getLeftColumn(), cursor.getRightLine(), cursor.getRightColumn()).toString();
            if (selectedText.equals(model.searchText)) {
                cursor.onCommitText(newText);
                view.editor.hideAutoCompleteWindow();
                gotoNext(false);
                return true;
            }
        }
        gotoNext(false);
        return false;
    }

    public void replaceAll(final String newText) {
        checkState();
        final String searchText = model.searchText;
        view.showSearchDialog(searchText,newText);
    }

    public void gotoNext() {
        gotoNext(true);
    }

    private void gotoNext(boolean tip) {
        checkState();
        ContentController text = view.editor.getText();
        CursorController cursor = text.getCursor();
        int line = cursor.getRightLine();
        int column = cursor.getRightColumn();
        for (int i = line; i < text.getLineCount(); i++) {
            int idx = column >= text.getColumnCount(i) ? -1 : text.getLine(i).indexOf(model.searchText, column);
            if (idx != -1) {
                view.editor.setSelectionRegion(i, idx, i, idx + model.searchText.length());
                return;
            }
            column = 0;
        }
        if (tip) {
            Toast.makeText(view.editor.getContext(), "Not found in this direction", Toast.LENGTH_SHORT).show();
            view.editor.jumpToLine(0);
        }
    }

    public void gotoLast() {
        checkState();
        ContentController text = view.editor.getText();
        CursorController cursor = text.getCursor();
        int line = cursor.getLeftLine();
        int column = cursor.getLeftColumn();
        for (int i = line; i >= 0; i--) {
            int idx = column - 1 < 0 ? -1 : text.getLine(i).lastIndexOf(model.searchText, column - 1);
            if (idx != -1) {
                view.editor.setSelectionRegion(i, idx, i, idx + model.searchText.length());
                return;
            }
            column = i - 1 >= 0 ? text.getColumnCount(i - 1) : 0;
        }
        Toast.makeText(view.editor.getContext(), "Not found in this direction", Toast.LENGTH_SHORT).show();
    }

    public void stopSearch() {
        search(null);
    }

}
