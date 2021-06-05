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

import io.github.rosemoe.editor.mvc.controller.editor.AbstractPlugin;
import io.github.rosemoe.editor.mvc.controller.editor.events.Event;
import io.github.rosemoe.editor.mvc.controller.editor.events.EventDestination;
import io.github.rosemoe.editor.mvc.controller.editor.events.EventQueue;
import io.github.rosemoe.editor.mvc.controller.editor.events.EventSource;
import io.github.rosemoe.editor.mvc.controller.editor.events.UserInputEvent;
import io.github.rosemoe.editor.util.Logger;

import static io.github.rosemoe.editor.mvc.controller.editor.events.UserInputEvent.TYPE_SCROLL;

/**
 * This class provide a widget system for CodeEditor.
 * Each widget can provide custom xml attributes and colors for the color widget.
 *
 * @author Release Standard
 */
public class Widget extends AbstractPlugin {


}
