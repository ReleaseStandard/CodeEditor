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

public class ColorPluginGithub extends ColorPlugin {

    public ColorPluginGithub(CodeEditor editor) {
        super(editor);
        name = "GitHub theme";
        description = "picked from Notepad++ v7.8.1, Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)";
    }

    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base00, 0xff24292e);
            put(R.styleable.CodeEditor_widget_color_base1, 0xff6a737d);
            put(R.styleable.CodeEditor_widget_color_base2, 0xffbec0c1);
            put(R.styleable.CodeEditor_widget_color_base3, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xffde3a49);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xff24292e);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xff032f62);
            put(R.styleable.CodeEditor_widget_color_accent8, 0xff005cc5);
            put(R.styleable.CodeEditor_widget_color_comment, 0xff6a737d);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xff24292e);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xffbec0c1);
            put(R.styleable.CodeEditor_widget_color_selectionInsert, 0xffc7edcc);
            put(R.styleable.CodeEditor_widget_color_selectionHandle, 0xffc7edcc);
        }};
    }
}
