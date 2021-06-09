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

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.mvc.controller.widgets.linenumberpanel.LineNumberPanelController;
import io.github.rosemoe.editor.mvc.controller.widgets.linenumberpanel.LineNumberPanelEvent;
import io.github.rosemoe.editor.mvc.model.widget.linenumberpanel.LineNumberPanelModel;

import static io.github.rosemoe.editor.mvc.controller.widgets.userinput.UserInputEvent.*;

public class TestPlugin extends DebugPlugin {

    public TestPlugin(CodeEditor editor) {
        super(editor);
        name = "test";
        description = "Test plugin";
    }

    @Override
    protected void handleEventDispatch(Event e, String subtype) {
        if ( e.getType() == E_USERINPUT ) {
            if ( subtype.equals(ONDOUBLETAP) ) {
                LineNumberPanelEvent lnpe = new LineNumberPanelEvent(LineNumberPanelEvent.CHANGE_ALIGN);
                lnpe.putArgs(LineNumberPanelModel.ALIGN_CENTER);
                emit(lnpe);
            }
        }
    }
}
