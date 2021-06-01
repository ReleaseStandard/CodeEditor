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
package io.github.rosemoe.editor.widget.schemes;

import io.github.rosemoe.editor.mvc.controller.EditorColorSchemeController;

/**
 * Solarized theme
 * https://github.com/altercation/solarized
 */
public class Solarized extends EditorColorSchemeController {
    @Override
    public void initTheme() {
        theme_name        = "Solarized";
        theme_description = "Extracted from https://github.com/altercation/solarized";
        base03    = 0xFF002b36;
        base02    = 0xFF073642;
        base01    = 0xFF586e75;
        base00    = 0xFF657b83;
        base0     = 0xFF839496;
        base1     = 0xFF93a1a1;
        base2     = 0xFFeee8d5;
        base3     = 0xFFfdf6e3;
        accent1   = 0xFFb58900;
        accent2   = 0xFFcb4b16;
        accent3   = 0xFFdc322f;
        accent4   = 0xFFd33682;
        accent5   = 0xFF6c71c4;
        accent6   = 0xFF268bd2;
        accent7   = 0xFF2aa198;
        accent8   = 0xFF859900;
    }
}
