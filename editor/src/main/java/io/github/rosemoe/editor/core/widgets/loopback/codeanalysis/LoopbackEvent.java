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
package io.github.rosemoe.editor.core.widgets.loopback.codeanalysis;

import io.github.rosemoe.editor.core.extension.events.Event;

/**
 * This widget is important because it allows communication between plugins.
 */
public class LoopbackEvent extends Event {
    // secondary keywords
    // same // public final static String PLUGINS_BROADCAST = "loopback";
    // action
    public final static String PLUGINS_BROADCAST = "loopback";
    public LoopbackEvent(String subtype, Object ...args) {
        super(subtype,args);
    }
}
