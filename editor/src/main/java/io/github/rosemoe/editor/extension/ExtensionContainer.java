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

import java.util.Iterator;
import java.util.PriorityQueue;

import io.github.rosemoe.editor.extension.events.Event;

/**
 * This is class is a container for extension with a priority queue.
 */
public class ExtensionContainer extends Extension {

    public PriorityQueue<Extension> plugins = new PriorityQueue<Extension>();

    @Override
    public boolean issubscribed(String type) {
        return true;
    }

    /**
     * Inside an extension container, a dispatch() call cause an event dispatching on all plugins of the container.
     * With priority based processing (priority of extensions) as the event priority is alrdy handled by Extension.
     * - first we check : priority of event
     * - then we check  : priority of extension
     * @param e
     * @param type
     */
    @Override
    protected void handleEventDispatch(Event e, String type, String subtype) {
        for (Iterator<Extension> it = plugins.iterator(); it.hasNext(); ) {
            Extension plugin = it.next();
            plugin.dispatch(e);
        }
    }
    @Override
    protected void handleEventEmit(Event e) {
        // Depends on the type of the event, the plugin
        throw new RuntimeException("You cannot call emit() on an Extension contains");
    }

    /**
     * Add an extension in the container.
     * @param extension extension to add
     */
    public void put(Extension extension) {
        plugins.add(extension);
    }
}
