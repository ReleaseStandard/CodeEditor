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
import io.github.rosemoe.editor.plugins.Plugin;
import io.github.rosemoe.editor.widget.CodeEditor;

public abstract class ColorPlugin extends Plugin {
    CodeEditor editor;
    boolean invert = false;
    public abstract void apply();

    /**
     * Below defined constantes are for convenience only.
     * As a good citizen, you should not use them.
     * Because some languages may not have secondary keyword, or literals.
     * Or accent colors may be used for a totally different purpose.
     */
    public final static int KEYWORD           = R.styleable.CodeEditor_widget_color_accent1;
    public final static int SECONDARY_KEYWORD = R.styleable.CodeEditor_widget_color_accent2;
    public final static int UNDERLINE         = R.styleable.CodeEditor_widget_color_accent3;
    public final static int ID_VARIABLE       = R.styleable.CodeEditor_widget_color_accent4;
    public final static int ID_CLASS          = R.styleable.CodeEditor_widget_color_accent5;
    public final static int ID_FUNCT          = R.styleable.CodeEditor_widget_color_accent6;
    public final static int LITERAL           = R.styleable.CodeEditor_widget_color_accent7;
    public final static int PUNCT             = R.styleable.CodeEditor_widget_color_accent8;


    public static ColorPlugin DEFAULT(CodeEditor editor) { return new ColorPluginSolarized(editor); }
    public ColorPlugin(CodeEditor editor) {
        this.editor = editor;
    }
    public ColorPlugin(CodeEditor editor, boolean invert) {
        this.editor = editor;
        this.invert = invert;
    }
}
