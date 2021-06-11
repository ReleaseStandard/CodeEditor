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

import androidx.annotation.Nullable;

import java.util.HashMap;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.CodeEditor;

public class ColorPluginVS2019 extends ColorPlugin {

    public ColorPluginVS2019(CodeEditor editor) {
        super(editor);
        name = "VS2019 theme";
        description = "picked from Visual Studio 2019, Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)";
    }

    @Nullable
    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base00, 0xffdcdcdc);
            put(R.styleable.CodeEditor_widget_color_base1, 0xff57a64a);
            put(R.styleable.CodeEditor_widget_color_base2, 0xff3676b8);
            put(R.styleable.CodeEditor_widget_color_base3, 0xff1e1e1e);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xff569cd6);
            put(R.styleable.CodeEditor_widget_color_accent5, 0xff4ec9b0);
            put(R.styleable.CodeEditor_widget_color_accent4, 0xffdcdcaa);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xffdcdcdc);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xffd69d85);
            put(R.styleable.CodeEditor_widget_color_comment, 0xff57a64a);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xff1e1e1e);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xffdcdcdc);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xff1e1e1e);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanelText, 0xff2b9eaf);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xff2b9eaf);
            put(R.styleable.CodeEditor_widget_color_linedivider, 0xff2b9eaf);
            put(R.styleable.CodeEditor_widget_color_scrollbarThumb, 0xff3e3e42);
            put(R.styleable.CodeEditor_widget_color_scrollbarThumbPressed, 0xff9e9e9e);
            put(R.styleable.CodeEditor_widget_color_selectedTextBackground, 0xff3676b8);
            put(R.styleable.CodeEditor_widget_color_matchedTextBackground, 0xff653306);
            put(R.styleable.CodeEditor_widget_color_currentLine, 0xff464646);
            put(R.styleable.CodeEditor_widget_color_selectionInsert, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_selectionHandle, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_blockLine, 0xff717171);
            put(R.styleable.CodeEditor_widget_color_blockLineCurrent, 0);
            put(R.styleable.CodeEditor_widget_color_nonPrintableChar, 0xffdddddd);
        }};
    }
}
