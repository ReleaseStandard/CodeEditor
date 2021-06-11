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

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.extension.ColorSchemeEvent;
import io.github.rosemoe.editor.plugins.Plugin;
import io.github.rosemoe.editor.core.CodeEditor;

public abstract class ColorPlugin extends Plugin {
    boolean invert = false;

    public static ColorPlugin DEFAULT(CodeEditor editor) { return new ColorPluginSolarized(editor); }
    public ColorPlugin(CodeEditor editor) {
        super(editor);
    }
    public ColorPlugin(CodeEditor editor, boolean invert) {
        super(editor);
        this.invert = invert;
    }
    public void init() {
        name = "colorplugin";
        description = "plugin that will change editor's colors";
    }


    public void apply() {
        HashMap<Integer,Integer> colors = getColors();
        if ( colors == null ) {
            colors = new HashMap<>();
        }
        emit(new ColorSchemeEvent(ColorSchemeEvent.UPDATE_THEME,colors));
    }

    /**
     * Define you color scheme here.
     * @return
     */
    @Nullable
    public abstract HashMap<Integer,Integer> getColors();

}
