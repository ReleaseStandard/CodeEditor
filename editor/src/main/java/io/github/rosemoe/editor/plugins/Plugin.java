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
package io.github.rosemoe.editor.plugins;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.extension.Extension;
import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.core.CodeEditor;


/**
 * You may implement handleEventDispatch, handleEventEmit
 * then when using the plugin : .dispatch() and .emit()
 * @author ReleaseStandard
 */
public abstract class Plugin extends Extension {

    protected CodeEditor editor;
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

    /**
     * Override this method to execute action when a given event is dispatched.
     * @param e
     * @param type
     */
    @Override
    protected void handleEventDispatch(Event e, String type, String subtype) {

    }

    @Override protected void handleEventEmit(Event e) {
        editor.widgets.dispatch(e);
    }
    public Plugin(CodeEditor editor) {
        this.editor = editor;
    }
}
