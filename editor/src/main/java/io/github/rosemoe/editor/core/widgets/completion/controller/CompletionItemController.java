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
package io.github.rosemoe.editor.core.widgets.completion.controller;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Comparator;

import io.github.rosemoe.editor.core.widgets.completion.model.CompletionItemModel;
import io.github.rosemoe.editor.core.widgets.completion.view.CompletionItemView;

/**
 * The class used to save auto complete result items
 *
 * @author Rose
 */
@SuppressWarnings("CanBeFinal")
public class CompletionItemController {

    public final static Comparator<CompletionItemController> COMPARATOR_BY_NAME = (p1, p2) -> p1.model.label.compareTo(p2.model.label);

    public CompletionItemModel model = new CompletionItemModel();
    public CompletionItemView  view = new CompletionItemView();

    public CompletionItemController(String str, String desc) {
        this(str, desc, (Drawable) null);
    }

    public CompletionItemController(String label, String commit, String desc) {
        this(label, commit, desc, null);
    }

    public CompletionItemController(String label, String desc, Drawable icon) {
        this(label, label, desc, icon);
    }

    public CompletionItemController(String label, String commit, String desc, Drawable icon) {
        model.label = label;
        model.commit = commit;
        model.desc = desc;
        view.icon = icon;
        model.cursorOffset = commit.length();
    }

    public CompletionItemController shiftCount(int shiftCount) {
        return cursorOffset(model.commit.length() - shiftCount);
    }

    public CompletionItemController cursorOffset(int offset) {
        model.cursorOffset(offset);
        return this;
    }

    public void setContent(View root) {
        view.setContent(root,model.label,model.desc);
    }

}

