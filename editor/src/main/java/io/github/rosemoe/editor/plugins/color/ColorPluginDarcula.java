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

public class ColorPluginDarcula extends ColorPlugin {

    public ColorPluginDarcula(CodeEditor editor) {
        super(editor);
        name        = "Darcula theme";
        description = "picked from Android Studio, Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)";
    }

    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base00, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_base1, 0xff606366);
            put(R.styleable.CodeEditor_widget_color_base2, 0xff323232);
            put(R.styleable.CodeEditor_widget_color_base3, 0xff2b2b2b);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xffcc7832);
            put(R.styleable.CodeEditor_widget_color_accent4, 0xFF9876aa);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xff6a8759);
            put(R.styleable.CodeEditor_widget_color_comment, 0xff808080);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xff2b2b2b);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xff313335);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xff606366);
            put(R.styleable.CodeEditor_widget_color_linedivider, 0xff606366);
            put(R.styleable.CodeEditor_widget_color_scrollbarThumb, 0xffa6a6a6);
            put(R.styleable.CodeEditor_widget_color_scrollbarThumbPressed, 0xff565656);
            put(R.styleable.CodeEditor_widget_color_selectedTextBackground, 0xff3676b8);
            put(R.styleable.CodeEditor_widget_color_matchedTextBackground, 0xff32593d);
            put(R.styleable.CodeEditor_widget_color_currentLine, 0xff323232);
            put(R.styleable.CodeEditor_widget_color_selectionInsert, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_selectionHandle, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_blockLine, 0xff575757);
            put(R.styleable.CodeEditor_widget_color_blockLineCurrent, 0xdd575757);
            put(R.styleable.CodeEditor_widget_color_nonPrintableChar, 0xff6a8759);
        }};
    }

}
