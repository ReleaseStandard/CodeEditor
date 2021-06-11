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
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.extension.ColorSchemeEvent;
import io.github.rosemoe.editor.core.widgets.linenumberpanel.extension.LineNumberPanelEvent;
import io.github.rosemoe.editor.core.widgets.loopback.codeanalysis.LoopbackEvent;
import io.github.rosemoe.editor.core.widgets.userinput.extension.UserInputEvent;
import io.github.rosemoe.editor.core.widgets.widgetmanager.extension.WidgetManagerEvent;


/**
 * You may implement handleEventDispatch, handleEventEmit
 * then when using the plugin : .dispatch() and .emit()
 * @author ReleaseStandard
 */
public abstract class Plugin extends Extension {

    /**
     * Below defined constantes are for convenience only.
     * As a good citizen, you should not use them.
     * Because some languages may not have secondary keyword, or literals.
     * Or accent colors may be used for a totally different purpose.
     */
    public final static int BASE03            = R.styleable.CodeEditor_widget_color_base03;
    public final static int BASE02            = R.styleable.CodeEditor_widget_color_base02;
    public final static int BASE01            = R.styleable.CodeEditor_widget_color_base01;
    public final static int BASE00            = R.styleable.CodeEditor_widget_color_base00;
    public final static int BASE0            = R.styleable.CodeEditor_widget_color_base0;
    public final static int BASE1            = R.styleable.CodeEditor_widget_color_base1;
    public final static int BASE2            = R.styleable.CodeEditor_widget_color_base2;
    public final static int BASE3            = R.styleable.CodeEditor_widget_color_base3;
    public final static int ACCENT1          = R.styleable.CodeEditor_widget_color_accent1;
    public final static int ACCENT2          = R.styleable.CodeEditor_widget_color_accent2;
    public final static int ACCENT3          = R.styleable.CodeEditor_widget_color_accent3;
    public final static int ACCENT4          = R.styleable.CodeEditor_widget_color_accent4;
    public final static int ACCENT5          = R.styleable.CodeEditor_widget_color_accent5;
    public final static int ACCENT6          = R.styleable.CodeEditor_widget_color_accent6;
    public final static int ACCENT7          = R.styleable.CodeEditor_widget_color_accent7;
    public final static int ACCENT8          = R.styleable.CodeEditor_widget_color_accent8;
    public final static int KEYWORD           = ACCENT1;
    public final static int SECONDARY_KEYWORD = ACCENT2;
    public final static int UNDERLINE         = ACCENT3;
    public final static int ID_VARIABLE       = ACCENT4;
    public final static int ID_CLASS          = ACCENT5;
    public final static int ID_FUNCT          = ACCENT6;
    public final static int LITERAL           = ACCENT7;
    public final static int PUNCT             = ACCENT8;
    public final static Class E_LOOPBACK      = LoopbackEvent.class;
    public final static Class E_USERINPUT     = UserInputEvent.class;
    public final static Class E_COLOR         = ColorSchemeEvent.class;
    public final static Class E_LINENUMBER    = LineNumberPanelEvent.class;
    public final static Class E_WMANAGER      = WidgetManagerEvent.class;
    /**
     * Override this method to execute action when a given event is dispatched.
     * @param e
     */
    @Override
    protected void handleEventDispatch(Event e, String subtype) {

    }

    @Override
    protected void handleEventEmit(Event e) { editor.widgets.dispatch(e); }

    public Plugin(CodeEditor editor) {
        super(editor);
    }
}
