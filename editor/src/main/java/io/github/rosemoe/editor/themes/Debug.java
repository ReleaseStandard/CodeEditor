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
package io.github.rosemoe.editor.themes;

import android.content.res.Resources;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.mvc.controller.ColorSchemeController;

public class Debug extends ColorSchemeController {

    @Override
    public void initTheme() {
        base03    = 0xFFFF0000;
        base02    = 0xFF00FF00;
        base01    = 0xFF0000FF;
        base00    = 0xFFFFFF00;
        base0     = 0xFF00FFFF;
        base1     = 0xFFFF00FF;
        base2     = 0xFFFFFFFF;
        base3     = 0xFF000000;
        accent1   = 0xFFaaFF00;
        accent2   = 0xFFaa0011;
        accent3   = 0xFFFFaa00;
        accent4   = 0xFF0aFFaF;
        accent5   = 0xFF6c71c4;
        accent6   = 0xFF268bd2;
        accent7   = 0xFF2aa198;
        accent8   = 0xFF859900;

        lineNumberPanel = 0xFFFF0000;
        lineNumberBackground = 0xFF00FF00;
        currentLine = 0xFFFF0000;

        textSelected = 0xFF00FF00;
        textSelectedBackground = 0xFFFF0000;
    }
}
