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
package io.github.rosemoe.editor.mvc.controller.widgets.widgetmanager;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.mvc.controller.widgets.Widget;

/**
 * This widget disable any other, based on the Event defined with each.
 */
public class WidgetManager extends Widget {
    public CodeEditor editor;
    public WidgetManager(CodeEditor editor) {
        subscribe(WidgetManagerEvent.TYPE_WIDGET_MGR);
        this.editor = editor;
    }

    @Override
    protected void handleEventDispatch(Event e, String type, String subtype) {
        WidgetManagerEvent wme = (WidgetManagerEvent) e;
        switch (subtype) {
            case WidgetManagerEvent.TYPE_WIDGET_MGR:
                String wname = (String) wme.getArg(0);
                Boolean state = (Boolean) wme.getArg(1);
                Widget w = (Widget) editor.widgets.get(wname);
                w.setEnabled(state);
                break;
        }
    }
}
