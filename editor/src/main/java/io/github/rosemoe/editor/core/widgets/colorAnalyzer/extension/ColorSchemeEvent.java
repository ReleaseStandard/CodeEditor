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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.extension;

import io.github.rosemoe.editor.core.extension.events.Event;

/**
 * This widget is important because it allows communication between plugins.
 */
public class ColorSchemeEvent extends Event {
    // secondary keywords
    // action
    public final static String UPDATE_COLOR = "update_color";
    public final static String UPDATE_THEME = "update_theme";

    public ColorSchemeEvent(String subtype, Object ...args) {
        super(subtype,args);
    }
}
