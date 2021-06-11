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
package io.github.rosemoe.editor.core.widgets.searcher.controller;

import io.github.rosemoe.editor.core.widgets.Widget;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController;
import io.github.rosemoe.editor.core.widgets.searcher.model.SearcherModel;
import io.github.rosemoe.editor.core.widgets.searcher.view.SearcherView;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * Search text in editor
 *
 * @author Rose
 */
@SuppressWarnings("deprecated")
public class SearcherController extends Widget {

    public SearcherModel model = new SearcherModel();
    public SearcherView view   = new SearcherView();

    public SearcherController(CodeEditor editor) {
        super(editor);
        // TODO : define an event
        view.editor = editor;
    }

    private boolean checkState() {
        return model.checkState();
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
        if ( ! checkState() ) { return false; }
        ContentMapController text = view.editor.getText();
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
        if( ! checkState() ) { return; }
        final String searchText = model.searchText;
        view.showSearchDialog(searchText,newText);
    }

    public void gotoNext() {
        gotoNext(true);
    }

    private void gotoNext(boolean tip) {
        if ( ! checkState() ) { return ;}
        view.gotoNext(model.searchText,tip);
    }

    public void gotoLast() {
        if ( ! checkState() ) { return ;}
        view.gotoLast(model.searchText);
    }

    public void stopSearch() {
        search(null);
    }

}
