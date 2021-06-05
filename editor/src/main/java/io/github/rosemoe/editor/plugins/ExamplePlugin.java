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

import io.github.rosemoe.editor.extension.events.Event;
import io.github.rosemoe.editor.util.Logger;

public class ExamplePlugin extends Plugin {
    /**
     * To receive all type of events
     * @param type
     * @return
     */
    @Override
    public boolean issubscribed(String type) {
        return true;
    }
    @Override
    protected void handleEventDispatch(Event e, String type, String subtype) {
        Logger.debug("Event e, type=",type," has been received");
    }
}
