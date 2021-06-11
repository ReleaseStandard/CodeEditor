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
package io.github.rosemoe.editor.plugins.color;

import java.util.HashMap;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.CodeEditor;

public class ColorPluginEclipse extends ColorPlugin {

    public ColorPluginEclipse(CodeEditor editor) {
        super(editor);
        name = "Eclipse theme";
        description = "picked from Eclipse IDE for Java Developers Version 2019-12 (4.14.0)";
    }

    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base00, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_base1, 0xff3f7f5f);
            put(R.styleable.CodeEditor_widget_color_base2, 0xffe8f2fe);
            put(R.styleable.CodeEditor_widget_color_base3, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xff7f0074);
            put(R.styleable.CodeEditor_widget_color_accent4, 0xffb8633e);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xff2a00ff);
            put(R.styleable.CodeEditor_widget_color_comment, 0xff3f7f5f);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xff787878);
            put(R.styleable.CodeEditor_widget_color_linedivider,0xffd8d8d8);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanelText,0xffd8d8d8);
            put(R.styleable.CodeEditor_widget_color_selectedTextBackground, 0xff3399ff);
            put(R.styleable.CodeEditor_widget_color_matchedTextBackground, 0xffd4d4d4);
            put(R.styleable.CodeEditor_widget_color_currentLine, 0xffe8f2fe);
            put(R.styleable.CodeEditor_widget_color_selectionInsert, 0xff03ebeb);
            put(R.styleable.CodeEditor_widget_color_selectionHandle, 0xff03ebeb);
            put(R.styleable.CodeEditor_widget_color_blockLine, 0xffd8d8d8);
            put(R.styleable.CodeEditor_widget_color_blockLineCurrent, 0);
        }};
    }
}
