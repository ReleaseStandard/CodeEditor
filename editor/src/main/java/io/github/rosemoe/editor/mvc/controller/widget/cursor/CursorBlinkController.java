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
package io.github.rosemoe.editor.mvc.controller.widget.cursor;

import io.github.rosemoe.editor.mvc.model.widget.cursor.CursorBlinkModel;
import io.github.rosemoe.editor.mvc.view.widget.cursor.CursorBlinkView;
import io.github.rosemoe.editor.widget.CodeEditor;

/**
 * This class is used to control cursor visibility
 *
 * @author Rose
 */
public final class CursorBlinkController implements Runnable {

    public final CursorBlinkModel model;
    public final CursorBlinkView view;

    public CursorBlinkController(CodeEditor editor, int period) {
        model = new CursorBlinkModel(period);
        view  = new CursorBlinkView(editor);
    }

    public void onSelectionChanged() {
        model.onSelectionChanged();
    }
    public void setPeriod(int period) {
        model.setPeriod(period);
    }

    public boolean isSelectionVisible() {
        model.buffer = view.editor.mLayout.getCharLayoutOffset(view.editor.getCursor().getLeftLine(), view.editor.getCursor().getLeftColumn(), model.buffer);
        return (model.buffer[0] >= view.editor.getOffsetY() && model.buffer[0] - view.editor.getRowHeight() <= view.editor.getOffsetY() + view.editor.getHeight()
                && model.buffer[1] >= view.editor.getOffsetX() && model.buffer[1] - 100f/* larger than a single character */ <= view.editor.getOffsetX() + view.editor.getWidth());
    }

    @Override
    public void run() {
        if (model.valid && model.period > 0) {
            if (System.currentTimeMillis() - model.lastSelectionModificationTime >= model.period * 2) {
                model.visibility = !model.visibility;
                if (!view.editor.getCursor().isSelected() && isSelectionVisible()) {
                    view.editor.invalidate();
                }
            }
            view.editor.postDelayed(this, model.period);
        } else {
            model.visibility = true;
        }
    }

}
