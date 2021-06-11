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

/**
 * This theme is useful for debugging.
 *
 * @author Release Standard
 */
public class ColorPluginDebug extends ColorPlugin {

    public ColorPluginDebug(CodeEditor editor) {
        super(editor);
        name = "Debug theme";
        description = "Take care it can hurt your eyes";
    }

    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base03, 0xFFFF0000);
            put(R.styleable.CodeEditor_widget_color_base02, 0xFF00FF00);
            put(R.styleable.CodeEditor_widget_color_base01, 0xFF0000FF);
            put(R.styleable.CodeEditor_widget_color_base00, 0xFFFFFF00);
            put(R.styleable.CodeEditor_widget_color_base0, 0xFF00FFFF);
            put(R.styleable.CodeEditor_widget_color_base1, 0xFFFF00FF);
            put(R.styleable.CodeEditor_widget_color_base2, 0xFFFFFFFF);
            put(R.styleable.CodeEditor_widget_color_base3, 0xFF000000);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xFFaaFF00);
            put(R.styleable.CodeEditor_widget_color_accent2, 0xFFaa0011);
            put(R.styleable.CodeEditor_widget_color_accent3, 0xFFFFaa00);
            put(R.styleable.CodeEditor_widget_color_accent4, 0xFF0aFFaF);
            put(R.styleable.CodeEditor_widget_color_accent5, 0xFF6c71c4);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xFF268bd2);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xFF2aa198);
            put(R.styleable.CodeEditor_widget_color_accent8, 0xFF859900);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xFFFF0000);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xFF00FF00);
            put(R.styleable.CodeEditor_widget_color_currentLine, 0xFFFF0000);
            put(R.styleable.CodeEditor_widget_color_textSelected,0xFF00FF00);
            put(R.styleable.CodeEditor_widget_color_selectedTextBackground, 0xFFFF0000);
        }};
    }
}
