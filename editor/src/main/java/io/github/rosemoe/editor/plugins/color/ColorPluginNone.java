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

import io.github.rosemoe.editor.core.CodeEditor;

/**
 * This theme does not show any color to the screen.
 *
 * @author Release Standard
 */
public class ColorPluginNone extends ColorPlugin {

    public ColorPluginNone(CodeEditor editor) {
        super(editor);
        name = "None theme";
        description = "Text will be displayed with no colors";
    }

    @Nullable
    @Override
    public HashMap<Integer, Integer> getColors() {
        return null;
    }


}
