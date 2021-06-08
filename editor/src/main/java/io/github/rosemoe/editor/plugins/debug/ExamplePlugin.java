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
import io.github.rosemoe.editor.extension.events.Event;
import io.github.rosemoe.editor.mvc.controller.widgets.color.ColorSchemeEvent;
import io.github.rosemoe.editor.mvc.controller.widgets.loopback.LoopbackEvent;
import io.github.rosemoe.editor.mvc.controller.widgets.userinput.UserInputEvent;
import io.github.rosemoe.editor.plugins.Plugin;
import io.github.rosemoe.editor.plugins.color.ColorPluginDarcula;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.CodeEditor;

/**
 * WARNING : it is disabled by default, you have to set Logger.DEBUG=true in the Logger.java file.
 *
 * This plugin is very simple.
 * It will change various colors of the interface in response to touch events.
 * eg: scaling editor cause background turn into green.
 *
 * @author Release Standard.
 */
public class ExamplePlugin extends DebugPlugin {

    long currentTime = 0;
    int taps = 0;

    public ExamplePlugin(CodeEditor editor) {
        super(editor);
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
        if ( taps >= 3 ) {
            Logger.debug("Tripletap detected, sending a loopback event");
            LoopbackEvent e = new LoopbackEvent(LoopbackEvent.PLUGINS_BROADCAST,true);
            editor.widgets.dispatch(e);
            taps = 0;
        }
    }
    @Override
    protected void handleEventDispatch(Event e, String type, String subtype) {
        Logger.debug("Event e, type=",type," has been received");
        if ( e.getType() == UserInputEvent.TYPE_USERINPUT ) {
            if ( e.getSubType() == UserInputEvent.ONDOUBLETAP ) {
                incOrReset();
                incOrReset();
            }
            if ( e.getSubType() == UserInputEvent.SINGLETAPUP ) {
                incOrReset();
            }
            if ( e.getSubType() == UserInputEvent.ONSCALEBEGIN ) {
                Logger.v("Multiple tap detected, sending background color change");
                ColorSchemeEvent cse = new ColorSchemeEvent(ColorSchemeEvent.UPDATE_COLOR, R.styleable.CodeEditor_widget_color_wholeBackground,0xFF00FF00);
                editor.widgets.dispatch(cse);
            }
            if ( e.getSubType() == UserInputEvent.ONSCROLL ) {
                new ColorPluginDarcula(editor);
                //editor.widgets.dispatch(new ColorSchemeEvent(ColorSchemeEvent.UPDATE_COLOR, R.styleable.CodeEditor_widget_color_wholeBackground, ));
            }
        }
        if ( e.getType() == LoopbackEvent.TYPE_LOOPBACK ) {
            if ( e.getSubType() == LoopbackEvent.PLUGINS_BROADCAST ) {
                Logger.v("Response from the widget received");
            }
        }

    }


}