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
package io.github.rosemoe.editor.core.widgets.userinput.extension;

import io.github.rosemoe.editor.core.extension.events.Event;

/**
 * Event process by UserInput stuff.
 *
 * @author Release Standard
 */
public class UserInputEvent extends Event {
    // Events from this widget
    public final static String ONSCROLL = "onscroll";
    public final static String SINGLETAPUP = "singletapup";
    public final static String LONGPRESS = "longpress";
    public final static String ONFLING = "onfling";
    public final static String ONSCALE = "onscale";
    public final static String ONSCALEBEGIN = "onscalebegin";
    public final static String ONSCALEEND = "onscaleend";
    public final static String ONDOWN = "ondown";
    public final static String ONSHOWPRESS = "onshowpress";
    public final static String ONSINGLETAPCONFIRMED = "onsingletapconfirmed";
    public final static String ONDOUBLETAP = "ondoubletap";
    public final static String ONDOUBLETAPEVENT = "ondoubletapevent";
}
