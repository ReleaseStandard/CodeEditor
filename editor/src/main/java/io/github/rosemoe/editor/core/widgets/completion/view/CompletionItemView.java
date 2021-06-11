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

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import io.github.rosemoe.editor.R;

public class CompletionItemView {
    /**
     * Icon for displaying in adapter
     */
    public Drawable icon;

    /**
     * Set the content of a given CompletionItem.
     * @param root  root view of the completion item.
     * @param label label of the given complete item.
     * @param desc  description of the given complete item.
     */
    public void setContent(View root, String label, String desc) {
        TextView tv = (TextView) root.findViewById(R.id.result_item_label);
        if ( tv != null ) {
            tv.setText(label);
        }
        tv = (TextView) root.findViewById(R.id.result_item_desc);
        if ( tv != null ) {
            tv.setText(desc);
        }
    }
}
