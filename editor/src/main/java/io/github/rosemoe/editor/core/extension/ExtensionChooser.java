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
package io.github.rosemoe.editor.core.extension;

import android.app.AlertDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.plugins.Plugin;
import io.github.rosemoe.editor.plugins.color.ColorChooser;
import io.github.rosemoe.editor.plugins.color.ColorPlugin;


/**
 * Extension Chooser is a simple plugin that allow you to perform an action when user select plugin in a subset of.
 */
public class ExtensionChooser extends Extension {

    protected String popup_title = "Extension chooser";
    protected String popup_cancel = "Cancel";
    private int checkedTheme = 0;
    public ArrayList<Class> filter = new ArrayList<>();

    public ExtensionChooser(CodeEditor editor) {
        super(editor);
    }

    public ExtensionChooser addFilter(Class c) {
        this.filter.add(c);
        return this;
    }
    public void showChooser() {
        ArrayList<Extension> extensions = new ArrayList<>();
        if( filter.size() == 0 ) {
            filter.add(Plugin.class);
        }
        for(Class c : filter) {
            for (Extension e : editor.plugins.extensions) {
                if (e.isEnabled() &&
                        c.isAssignableFrom(e.getClass())) {
                    extensions.add(e);
                }
            }
        }
        String[] names = new String[extensions.size()];
        for(int i = 0; i < extensions.size(); i++) {
            names[i] = extensions.get(i).name;
        }
        Context ctx = editor.getContext();
        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);
        adb.setTitle(popup_title);
        adb.setSingleChoiceItems(names,checkedTheme, ((dialog, which) ->
        {
            checkedTheme = which;
            handleExtensionChoosed(extensions.get(checkedTheme));
            dialog.dismiss();
        }));
        adb.setNegativeButton(popup_cancel,null);
        adb.show();
    }
    public void handleExtensionChoosed(Extension e) {

    }

}
