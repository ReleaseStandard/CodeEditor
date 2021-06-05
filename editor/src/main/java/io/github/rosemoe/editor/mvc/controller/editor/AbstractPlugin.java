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
package io.github.rosemoe.editor.mvc.controller.editor;

import io.github.rosemoe.editor.mvc.controller.editor.events.Event;
import io.github.rosemoe.editor.mvc.controller.editor.events.EventDestination;
import io.github.rosemoe.editor.mvc.controller.editor.events.EventQueue;
import io.github.rosemoe.editor.mvc.controller.editor.events.EventSource;


/**
 * This class is used both for the widget system and for the plugin system.
 * The only difference between both is that widget provide some base implementation and computation before
 * passing events to plugins, also widgets ensure coherent accesses to CodeEditor accross all plugins.
 *
 * But you can perfectly start a plugin from scratch just with the CodeEditor Object.
 *
 * For example : to extends the color scheme you can extends the WIDGET class, make span computation.
 * and display spans to the screen.
 * Or you can extends as PLUGIN and provide just colors that you want to change.
 *
 * The fact to separate Plugin Widget allow use to control by  priority access to resources given to plugin.
 * So you can think the widget as a low level Plugin.
 * And the plugin as an higher level plugin.
 *
 */
public class AbstractPlugin implements EventSource, EventDestination  {
    public boolean enabled = true;
    /**
     * Is the plugin enabled
     * @param state
     */
    public void setEnabled(boolean state) {
        enabled = state;
    }
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Override this method of the widget to provide action in the widget on event emission.
     * @param e
     */
    protected void handleEventEmit(Event e) { }
    @Override
    public void emit(Event e) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                handleEventEmit(e);
            }
        }.start();
    }


    EventQueue dispatchQueue = new EventQueue() {
        @Override
        public void handlePolling(Event e) {
            handleEventDispatch(e,e.getType());
        }
    };
    @Override
    public void dispatch(Event e) {
        if( issubscribed(e.getType()) ) {
            dispatchQueue.add(e);
        }
    }

    @Override
    public void subscribe(int type) {
        subscribedEventTypes.put(type,true);
    }

    @Override
    public void unsubscribe(int type) {
        subscribedEventTypes.put(type,false);
    }

    @Override
    public boolean issubscribed(int type) {
        return subscribedEventTypes.containsKey(type);
    }

    /**
     * Override this method to execute action when a given event is dispatched.
     * @param e
     * @param type
     */
    protected void handleEventDispatch(Event e, int type) {

    }
}
