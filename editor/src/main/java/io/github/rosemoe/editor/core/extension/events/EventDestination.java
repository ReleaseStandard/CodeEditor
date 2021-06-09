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
package io.github.rosemoe.editor.core.extension.events;

/**
 * Interface for event destination.
 */
public interface EventDestination {
    /**
     * Dispatch the event e to the given destination.
     * @param e
     */
    void dispatch(Event e);

    /**
     * Subscribe to this type of events
     * @param type
     */
    void subscribe(Class type);
    /**
     * Unsubscribe to this type of events.
     * @param type event type to unsubscribe.
     */
    void unsubscribe(Class type);
    /**
     * Test if current event destination is subscribed to event.
     * @param type
     * @return
     */
    boolean issubscribed(Class type);
}
