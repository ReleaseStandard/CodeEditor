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
package io.github.rosemoe.editor.plugins.debug;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.extension.ColorSchemeEvent;
import io.github.rosemoe.editor.core.widgets.loopback.codeanalysis.LoopbackEvent;
import io.github.rosemoe.editor.core.widgets.userinput.extension.UserInputEvent;
import io.github.rosemoe.editor.core.widgets.widgetmanager.extension.WidgetManagerEvent;
import io.github.rosemoe.editor.plugins.color.ColorPluginDarcula;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * WARNING : it is disabled by default, you have to set Logger.DEBUG=true in the Logger.java file.
 *
 * This plugin is very simple.
 * It will change various colors of the interface in response to touch events.
 * eg: scaling editor cause background turn into green.
 * eg  triple tap cause background to red.
 *
 * @author Release Standard.
 */
public class ExamplePlugin extends DebugPlugin {

    long currentTime = 0;
    int taps = 0;

    public ExamplePlugin(CodeEditor editor) {
        super(editor);
        name = "example plugin";
        description = "this plugin perform bunch of actions on various user input (ex: scroll, scale, doubletap)";
    }

    private void incOrReset() {
        long newTime = System.currentTimeMillis();
        long diff = newTime - currentTime;
        if ( diff > 2000 ) {
            currentTime = newTime;
            taps = 1;
        } else {
            taps ++;
        }
        if ( taps > 3 ) {
            Logger.debug("Tripletap detected, sending a loopback event");
            LoopbackEvent e = new LoopbackEvent(LoopbackEvent.PLUGINS_BROADCAST,true);
            emit(e);
            ColorSchemeEvent cse = new ColorSchemeEvent(ColorSchemeEvent.UPDATE_COLOR, R.styleable.CodeEditor_widget_color_wholeBackground,0xFFFF0000);
            emit(cse);
            taps = 0;
        }
    }
    @Override
    protected void handleEventDispatch(Event e, String subtype) {
        Logger.debug("Event e, subtype=",subtype," has been received");
        if (e.getType() == E_USERINPUT) {
            switch (subtype) {
                case UserInputEvent.ONDOUBLETAP: {
                    WidgetManagerEvent wme = new WidgetManagerEvent(WidgetManagerEvent.GUI);
                    emit(wme);
                    incOrReset();
                    incOrReset();
                    break;
                }
                case UserInputEvent.SINGLETAPUP: {
                    incOrReset();
                    ColorSchemeEvent cse = new ColorSchemeEvent(ColorSchemeEvent.UPDATE_COLOR, R.styleable.CodeEditor_widget_color_wholeBackground,0xFF0000FF);
                    emit(cse);
                    break;
                }
                case UserInputEvent.ONSCALEBEGIN: {
                    Logger.v("Multiple tap detected, sending background color change");
                    ColorSchemeEvent cse = new ColorSchemeEvent(ColorSchemeEvent.UPDATE_COLOR, R.styleable.CodeEditor_widget_color_wholeBackground,0xFF00FF00);
                    emit(cse);
                    break;
                }
                case UserInputEvent.LONGPRESS:
                    // emit event under the hood
                    new ColorPluginDarcula(editor).apply();
                    break;
                case UserInputEvent.ONSCALEEND: {
                    Logger.debug("On scale end");
                    WidgetManagerEvent wme = new WidgetManagerEvent(WidgetManagerEvent.TOGGLE);
                    wme.putArg("linenumberpanel");
                    emit(wme);
                    break;
                }
            }
        }
        if (e.getType() == E_LOOPBACK) {
            if (subtype.equals(LoopbackEvent.PLUGINS_BROADCAST)) {
                Logger.v("Response from the widget received");
            }
        }

    }


}