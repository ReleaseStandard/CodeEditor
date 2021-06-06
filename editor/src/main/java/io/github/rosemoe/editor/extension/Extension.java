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
package io.github.rosemoe.editor.extension;

import java.util.HashMap;

import io.github.rosemoe.editor.extension.events.Event;
import io.github.rosemoe.editor.extension.events.EventDestination;
import io.github.rosemoe.editor.extension.events.EventQueue;
import io.github.rosemoe.editor.extension.events.EventSource;
import io.github.rosemoe.editor.plugins.Plugin;
import io.github.rosemoe.editor.util.Logger;


/**
 * With this generic class you can add widgets and plugins to the editor.
 * Adding widget will make the life easier to plugins developpers.
 *
 * The fact to separate Plugin Widget allow use to control by  priority access to resources given to plugins.
 * So you can think the widget as a low level Plugin.
 * And the plugin as an higher level plugin.
 *
 */
public abstract class Extension implements EventSource, EventDestination, Comparable  {
    /**
     * Plugin priority declaration : WARNING they should be putted from low to high priority.
     * that is plugin can be given a priority.
     */
    public enum PRIORITY {
        LOW,
        STD,
        HIGH
    }
    public PRIORITY priorityRing = PRIORITY.STD;
    private HashMap<String, Boolean> subscribedEventTypes = new HashMap<>();

    /**
     * Name of the extension.
     */
    public  String name       = "default";
    /**
     * Extension's description.
     */
    public String description = "default description";
    /**
     * Image for the extension.
     */
    public Object image       = "plugin image";

    @Override
    public int compareTo(Object o) {
        if ( o instanceof Extension) {
            Extension p = (Extension) o;
            return priorityRing.compareTo(p.priorityRing);
        }
        return 0;
    }

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
    public boolean isDisabled() { return ! enabled; }

    /**
     * Override this method of the widget to provide action in the widget on event emission.
     * @param e
     */
    protected void handleEventEmit(Event e) { }
    @Override
    public void emit(Event e) {
        if ( isDisabled() ) {
            Logger.debug("Cannot emit as this extension is disabled !");
            return;
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                handleEventEmit(e);
            }
        }.start();
    }


    private EventQueue dispatchQueue = new EventQueue() {
        @Override
        public void handlePolling(Event e) {
            handleEventDispatch(e,e.getType(),e.getSubType());
        }
    };
    @Override
    public void dispatch(Event e) {
        if ( isDisabled() ) {
            Logger.debug("Cannot emit as this extension is disabled !");
            return;
        }
        if( issubscribed(e.getType()) ) {
            Logger.debug("Inserting event in the pq");
            e.dump("      ");
            dispatchQueue.add(e);
        }
    }

    @Override
    public void subscribe(String type) {
        subscribedEventTypes.put(type,true);
    }

    @Override
    public void unsubscribe(String type) {
        subscribedEventTypes.put(type,false);
    }

    @Override
    public boolean issubscribed(String type) {
        return subscribedEventTypes.containsKey(type);
    }

    /**
     * Override this method to execute action when a given event is dispatched.
     * @param e
     * @param type
     */
    protected void handleEventDispatch(Event e, String type, String subtype) { }
}
