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

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.extension.Extension;
import io.github.rosemoe.editor.core.extension.ExtensionChooser;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.plugins.Plugin;


/**
 * Will show all color plugin that are enabled, and allow user to pick up one.
 */
public class ColorChooser extends ExtensionChooser {


    public ColorChooser(CodeEditor editor) {
        super(editor);
        popup_title = "Color theme chooser";
        addFilter(ColorPlugin.class);
    }

    @Override
    public void handleExtensionChoosed(Extension e ) {
        ColorPlugin cp = (ColorPlugin) e;
        cp.apply();
    }
}
