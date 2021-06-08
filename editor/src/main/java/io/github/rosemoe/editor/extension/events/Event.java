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
package io.github.rosemoe.editor.extension.events;

import java.util.ArrayList;

import io.github.rosemoe.editor.util.Logger;

/**
 * Basically any type of events.
 *
 * @author Release Standard
 */
public abstract class Event implements Comparable {

    @Override
    public int compareTo(Object o) {
        if ( o instanceof Event ) {
            Event e = (Event) o;
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
    public boolean stopHorizontalPropagation = false;
    public boolean stopVerticalPropagation   = false;
    public final static String TYPE_NONE = "none";
    public String type = TYPE_NONE;
    /**
     * Type of event, eg event type from EventInput, event type from the layout
     * @return
     */
    public String getType() { return type; }
    /**
     * We define a subtype, eg if event type if UserInput, we can user the scrollby subtype.
     */
    public String subtype = TYPE_NONE;
    public String getSubType() { return subtype; }
    /**
     * Stop propagation of this event at the current propagation state.
     */
    public void stopHorizontalPropagation() {
        stopHorizontalPropagation = true;
    }
    /**
     * Stop propagation of this event before entry in lower priority events.
     */
    public void stopVerticalPropagation() {
        stopVerticalPropagation = true;
    }

    public ArrayList<Object> args = new ArrayList<>();

    public Event(String type, String subtype) {
        this.type = type;
        this.subtype = subtype;
    }
    public Event() {
    }
    public Event(String subtype, Object ...args) {
        this.subtype = subtype;
        putArgs(args);
    }
    /**
     * Get argument associated with a given index.
     * @param index index of argument to get.
     * @return
     */
    public Object getArg(int index) {
        try {
            return args.get(index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Put a given arg in the event.
     * @param arg
     */
    public void putArg(Object arg) { args.add(arg); }
    public void putArgs(Object ...args) {
        for(Object arg : args) {
            putArg(arg);
        }
    }
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        Logger.debug(offset + "type=",type,",subtype=",subtype);
    }
}
