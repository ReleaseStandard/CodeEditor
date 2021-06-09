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
package io.github.rosemoe.editor.plugins.base;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.plugins.Plugin;

/**
 * This simple plugin allow you to send message to  widgets.
 * Then you simply use :
 *  Emitter e = new Emitter(editor);
 *  e.emit(new Event());
 *  eg.
 *    e.emit(new WidgetManagerEvent(TOGGLE));
 *
 */
public class Emitter extends Plugin {

    @Override
    public boolean issubscribed(Class type) {
        return false;
    }

    public Emitter(CodeEditor editor) {
        super(editor);
        name = "emitter";
        description = "simply allow calls to emit() by others plugins";
    }
}
