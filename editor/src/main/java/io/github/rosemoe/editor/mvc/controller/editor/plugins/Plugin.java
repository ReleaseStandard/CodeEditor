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
package io.github.rosemoe.editor.mvc.controller.editor.plugins;

import io.github.rosemoe.editor.mvc.controller.editor.AbstractPlugin;
import io.github.rosemoe.editor.mvc.controller.editor.events.Event;


/**
 * You may implement handleEventDispatch, handleEventEmit
 * then when using the plugin : .dispatch() and .emit()
 * @author ReleaseStandard
 */
public class Plugin extends AbstractPlugin implements Comparable {
    @Override
    public int compareTo(Object o) {
        if ( o instanceof Plugin) {
            Plugin e = (Plugin) o;
            return priorityRing.compareTo(e.priorityRing);
        }
        return 0;
    }

    /**
     * Event priority declaration : WARNING they should be putted from low to high priority.
     */
    public enum PRIORITY {
        LOW,
        STD,
        HIGH
    }
    public PRIORITY priorityRing = PRIORITY.STD;

    /**
     * Override this method to execute action when a given event is dispatched.
     * @param e
     * @param type
     */
    @Override
    protected void handleEventDispatch(Event e, int type) {

    }

    @Override
    protected void handleEventEmit(Event e) {

    }
}
