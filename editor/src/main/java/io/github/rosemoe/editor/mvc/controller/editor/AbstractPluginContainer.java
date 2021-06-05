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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import io.github.rosemoe.editor.mvc.controller.editor.AbstractPlugin;
import io.github.rosemoe.editor.mvc.controller.editor.events.Event;

/**
 * This is class is a generic plugin container.
 */
public class AbstractPluginContainer extends AbstractPlugin {

    public PriorityQueue<AbstractPlugin> plugins = new PriorityQueue<AbstractPlugin>();

    @Override
    public boolean issubscribed(int type) {
        return true;
    }
    @Override
    protected void handleEventDispatch(Event e, int type) {
        for (Iterator<AbstractPlugin> it = plugins.iterator(); it.hasNext(); ) {
            AbstractPlugin plugin = it.next();
            plugin.dispatch(e);
        }
    }
    @Override
    protected void handleEventEmit(Event e) {
        // Depends on the type of the event, the plugin
        throw new RuntimeException("You cannot call emit() on an AbstractPlugin contains");
    }

    /**
     * Add an abstract plugin in the container poll.
     * Widget or Plugins
     * @param plugin
     */
    public void put(AbstractPlugin plugin) {
        plugins.add(plugin);
    }
}
