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
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.plugins.Plugin;


public class ColorChooser extends Plugin {

    private int checkedTheme = 0;

    public ColorChooser(CodeEditor editor) {
        super(editor);
        name = "colorchooser";
        description = "This plugin is very simple and allow you to pick up a theme and apply to editor";
    }

    public void showChooser() {
        ArrayList<Extension> extensions = new ArrayList<>();
        for(Extension e : editor.plugins.extensions) {
            if ( e.isEnabled() &&
                    e instanceof ColorPlugin) {
                extensions.add(e);
            }
        }
        String[] names = new String[extensions.size()];
        for(int i = 0; i < extensions.size(); i++) {
            names[i] = extensions.get(i).name;
        }
        Context ctx = editor.getContext();
        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);
        adb.setTitle("Color theme chooser");
        adb.setSingleChoiceItems(names,checkedTheme, ((dialog, which) ->
        {
            checkedTheme = which;
            ColorPlugin cp = (ColorPlugin) extensions.get(checkedTheme);
            cp.apply();
            dialog.dismiss();
        }));
        adb.setNegativeButton("Cancel",null);
        adb.show();
    }
}
