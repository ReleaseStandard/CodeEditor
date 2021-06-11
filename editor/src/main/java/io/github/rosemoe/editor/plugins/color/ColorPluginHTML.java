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

public class ColorPluginHTML extends ColorPlugin {

    public ColorPluginHTML(CodeEditor editor) {
        super(editor);
        name = "HTML theme";
        description = "ColorScheme for HTML Language for editor";
    }

    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base00, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_base1, 0xffbdbdbd);
            put(R.styleable.CodeEditor_widget_color_base2, 0xff464646);
            put(R.styleable.CodeEditor_widget_color_base3, 0xff212121);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xff4fc3f7);
            put(R.styleable.CodeEditor_widget_color_accent5, 0xfff0be4b);
            put(R.styleable.CodeEditor_widget_color_accent6,0xff333333);
            put(R.styleable.CodeEditor_widget_color_accent7,0xff008080);
            put(R.styleable.CodeEditor_widget_color_comment, 0xffbdbdbd);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xff212121);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xff1e1e1e);
            put(R.styleable.CodeEditor_widget_color_linedivider, 0xff2b9eaf);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanelText, 0xff2b9eaf);
            put(R.styleable.CodeEditor_widget_color_currentLine, 0xff464646);
            put(R.styleable.CodeEditor_widget_color_nonPrintableChar, 0xffdddddd);
            put(R.styleable.CodeEditor_widget_color_selectionInsert, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_selectionHandle, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xff2b9eaf);
        }};
    }
}
