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
package io.github.rosemoe.editor.core.widgets.contextaction.controller;

import android.view.MotionEvent;

import io.github.rosemoe.editor.core.widgets.Widget;
import io.github.rosemoe.editor.core.widgets.contextaction.model.ContextActionModel;
import io.github.rosemoe.editor.core.widgets.contextaction.view.ContextActionView;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * This will show when selecting text
 * (context action)
 *
 * @author Rose
 */
public class ContextActionController extends Widget {

    public ContextActionModel model = new ContextActionModel();
    public final ContextActionView view;

    /**
     * Create a panel for the given editor
     *
     * @param editor Target editor
     */
    public ContextActionController(CodeEditor editor) {
        super(editor);
        name = "contextaction";
        description = "This widget manage context action";
        view = new ContextActionView(editor) {
            @Override
            public void handleOnBeginTextSelect(int maxWidth) {
                model.maxWidth = maxWidth;
            }

            @Override
            public void handleUpdateBtnState() {
                setWidth(Math.min(mRootView.getMeasuredWidth(), model.maxWidth));
            }
        };
    }

    public void handleTap(MotionEvent e) {
        if (view.isShowing()) {
            view.hide();
        } else {
            view.onBeginTextSelect();
            view.onSelectedTextClicked(e);
        }
    }
}

