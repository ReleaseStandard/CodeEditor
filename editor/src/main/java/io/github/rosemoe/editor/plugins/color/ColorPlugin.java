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

import io.github.rosemoe.editor.plugins.Plugin;
import io.github.rosemoe.editor.widget.CodeEditor;

public abstract class ColorPlugin extends Plugin {
    CodeEditor editor;
    boolean invert = false;
    public abstract void apply();
    public static ColorPlugin DEFAULT(CodeEditor editor) { return new ColorPluginSolarized(editor); }
    public ColorPlugin(CodeEditor editor) {
        this.editor = editor;
    }
    public ColorPlugin(CodeEditor editor, boolean invert) {
        this.editor = editor;
        this.invert = invert;
    }
}
