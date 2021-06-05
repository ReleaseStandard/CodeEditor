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
package io.github.rosemoe.editor.mvc.controller.editor.events;

import java.util.ArrayList;

/**
 * Event class, it is a generic class that can handle any type of event.
 * it can represent a keypress, language related action, scaling event scrolling event.
 * Events will be proceeded from top priority to lower priority.
 * Events will be propagated from top prio to lower prio.
 * An event could be from the programmer to or from event source to programmer(plugin)
 *
 * @author Release Standard
 */
public class Event implements Comparable {

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
    public final static int TYPE_NONE = 0;
    public int type = TYPE_NONE;
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

    /**
     * Type of event (subtype).
     * @return
     */
    public int getType() { return type; }


    public ArrayList<Object> args = new ArrayList<>();

    /**
     * Get argument associated with a given index.
     * @param index index of argument to get.
     * @return
     */
    public Object getArg(int index) { return args.get(index); }
    /**
     * Put a given arg in the event.
     * @param arg
     */
    public void putArg(Object arg) { args.add(arg); }
}
