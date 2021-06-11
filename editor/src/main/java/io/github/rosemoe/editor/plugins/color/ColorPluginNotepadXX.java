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

public class ColorPluginNotepadXX extends ColorPlugin {

    public ColorPluginNotepadXX(CodeEditor editor) {
        super(editor);
        name        = "NotepadXX theme";
        description = "picked from Notepad++ v7.8.1, Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)";
    }

    @Nullable
    @Override
    public HashMap<Integer, Integer> getColors() {
        return new HashMap<Integer, Integer>() {{
            put(R.styleable.CodeEditor_widget_color_base00, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_base1, 0xff008000);
            put(R.styleable.CodeEditor_widget_color_base2, 0xffe4e4e4);
            put(R.styleable.CodeEditor_widget_color_base3, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_accent1, 0xff8000ff);
            put(R.styleable.CodeEditor_widget_color_accent6, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_accent7, 0xff808080);
            put(R.styleable.CodeEditor_widget_color_accent8, 0xff0000ff);
            put(R.styleable.CodeEditor_widget_color_comment, 0xff008000);
            put(R.styleable.CodeEditor_widget_color_wholeBackground, 0xffffffff);
            put(R.styleable.CodeEditor_widget_color_textNormal, 0xff000000);
            put(R.styleable.CodeEditor_widget_color_lineNumberBackground, 0xffe4e4e4);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanel, 0xff808080);
            put(R.styleable.CodeEditor_widget_color_lineNumberPanelText, 0xffc0c0c0);
            put(R.styleable.CodeEditor_widget_color_linedivider, 0xffc0c0c0);
            put(R.styleable.CodeEditor_widget_color_selectedTextBackground, 0xff75d975);
            put(R.styleable.CodeEditor_widget_color_matchedTextBackground, 0xffc0c0c0);
            put(R.styleable.CodeEditor_widget_color_currentLine, 0xffe8e8ff);
            put(R.styleable.CodeEditor_widget_color_selectionInsert, 0xff8000ff);
            put(R.styleable.CodeEditor_widget_color_selectionHandle, 0xff8000ff);
            put(R.styleable.CodeEditor_widget_color_blockLine, 0xffc0c0c0);
            put(R.styleable.CodeEditor_widget_color_blockLineCurrent, 0);
        }};
    }
}
