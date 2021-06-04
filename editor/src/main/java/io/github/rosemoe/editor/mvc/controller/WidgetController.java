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
package io.github.rosemoe.editor.mvc.controller;

/**
 * This class provide a plugin system for CodeEditor.
 * Each widget can provide custom xml attributes and colors for the color widget.
 */
public class WidgetController {
    public boolean enabled = true;
    public void setEnabled(boolean state) {
        enabled = state;
    }
    public boolean isEnabled() {
        return enabled;
    }
}