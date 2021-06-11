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

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.widgets.completion.controller.CompletionItemController;
import io.github.rosemoe.editor.core.widgets.completion.controller.CompletionAdapter;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * Adapter to display results
 *
 * @author Rose
 */
@SuppressWarnings("CanBeFinal")
public
class DefaultCompletionItemAdapter extends CompletionAdapter {

    public final CodeEditor editor;
    public View view;
    public DefaultCompletionItemAdapter(CodeEditor editor) {
        super();
        this.editor = editor;
    }
    @Override
    public int getItemHeight() {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, Resources.getSystem().getDisplayMetrics());
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent, boolean isCurrentCursorPosition) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.default_completion_result_item, parent, false);
        }
        CompletionItemController item = getItem(pos);
        item.setContent((TextView) view.findViewById(R.id.result_item_label));

        view.setTag(pos);
        Logger.debug("Get view");
        if (isCurrentCursorPosition) {
            view.setBackgroundColor(editor.getColorScheme().getAutoCompleteItemCurrentPosition());
        } else {
            view.setBackgroundColor(editor.getColorScheme().getAutoCompleteItem());
        }
        ImageView iv = (ImageView) view.findViewById(R.id.result_item_image);
        iv.setImageDrawable(item.view.icon);
        this.view = view;
        return view;
    }

}
