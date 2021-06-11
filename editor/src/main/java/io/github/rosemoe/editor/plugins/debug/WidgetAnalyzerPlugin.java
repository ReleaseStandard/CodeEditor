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

import java.util.HashMap;

import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.core.widgets.userinput.extension.UserInputEvent;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * WARNING : it is disabled by default, you have to set Logger.DEBUG=true in the Logger.java file.
 * 
 * 
 * This plugin analyze the event system.
 *
 */
public class WidgetAnalyzerPlugin extends DebugPlugin {

    public WidgetAnalyzerPlugin(CodeEditor editor) {
        super(editor);
        name = "widget analyzer";
        description = "widget analyzer plugin, make stats on widgets";
    }

    private HashMap<Class,Integer> eventCount = new HashMap<>();

    @Override
    protected void handleEventDispatch(Event e, String subtype) {
        Integer c = eventCount.get(e.getType());
        if ( c == null ) {
            c = 0;
        } else {
            c++;
        }
        eventCount.put(e.getType(),c);
        if (e.getType() == E_USERINPUT) {
            if (e.getSubType().equals(UserInputEvent.ONDOUBLETAP)) {
                print();
            }
        }
    }

    public int getEventCount() {
        int c = 0;
        for(Integer i : eventCount.values()) {
            c += i;
        }
        return c;
    }
    public void print() {
        Logger.v("Widgets have emitted ", getEventCount() , " events");
        for(Class key : eventCount.keySet()) {
            Logger.v("    widget ",key," has emitted ",eventCount.get(key)," events");
        }
    }
}