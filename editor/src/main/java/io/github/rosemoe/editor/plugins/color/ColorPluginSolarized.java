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

/**
 * https://ethanschoonover.com/solarized/
 *
 * @author Release Standard
 */
public class ColorPluginSolarized extends ColorPlugin {

    public ColorPluginSolarized(CodeEditor editor) {
        super(editor);
        name = "Solarized theme";
        description = "https://ethanschoonover.com/solarized/";
    }

    @Nullable
    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base03, 0xFF002b36);
            put(R.styleable.CodeEditor_widget_color_base02, 0xFF073642);
            put(R.styleable.CodeEditor_widget_color_base01, 0xFF586e75);
            put(R.styleable.CodeEditor_widget_color_base00, 0xFF657b83);
            put(R.styleable.CodeEditor_widget_color_base0, 0xFF839496);
            put(R.styleable.CodeEditor_widget_color_base1, 0xFF93a1a1);
            put(R.styleable.CodeEditor_widget_color_base2, 0xFFeee8d5);
            put(R.styleable.CodeEditor_widget_color_base3, 0xFFfdf6e3);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xFFb58900);
            put(R.styleable.CodeEditor_widget_color_accent2, 0xFFcb4b16);
            put(R.styleable.CodeEditor_widget_color_accent3, 0xFFdc322f);
            put(R.styleable.CodeEditor_widget_color_accent4, 0xFFd33682);
            put(R.styleable.CodeEditor_widget_color_accent5, 0xFF6c71c4);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xFF268bd2);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xFF2aa198);
            put(R.styleable.CodeEditor_widget_color_accent8, 0xFF859900);
            put(R.styleable.CodeEditor_widget_color_comment, 0xff3f7f5f);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xff787878);
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
