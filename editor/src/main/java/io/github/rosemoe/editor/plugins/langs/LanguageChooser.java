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
package io.github.rosemoe.editor.plugins.langs;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.extension.Extension;
import io.github.rosemoe.editor.core.extension.ExtensionChooser;
import io.github.rosemoe.editor.core.langs.LanguagePlugin;
import io.github.rosemoe.editor.plugins.color.ColorPlugin;


/**
 * Allow user to select languages from which that are enabled.
 */
public class LanguageChooser extends ExtensionChooser {
    public LanguageChooser(CodeEditor editor) {
            super(editor);
            popup_title = "Language chooser";
            addFilter(LanguagePlugin.class);
        }

    @Override
    public void handleExtensionChoosed(Extension e ) {
        LanguagePlugin lp = (LanguagePlugin)e;
        editor.setEditorLanguage(lp);
    }
}
