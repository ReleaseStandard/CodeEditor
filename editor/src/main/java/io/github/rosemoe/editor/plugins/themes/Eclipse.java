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
package io.github.rosemoe.editor.plugins.themes;

import io.github.rosemoe.editor.mvc.controller.widgets.color.ColorSchemeController;

/**
 * ColorScheme for editor
 * picked from Eclipse IDE for Java Developers Version 2019-12 (4.14.0)
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public final class Eclipse extends ColorSchemeController {

    @Override
    public void initTheme() {
        theme_name = "Eclipse";
        theme_description = "picked from Eclipse IDE for Java Developers Version 2019-12 (4.14.0)";
        base3 = 0xffffffff;
        base2 = 0xffe8f2fe;
        base00 = 0xff000000;
        base1 = 0xff3f7f5f;
        comment = 0xff3f7f5f;
        wholeBackground = 0xffffffff;
        textNormal = 0xff000000;
        lineNumberBackground = 0xffffffff;
        lineNumberPanel = 0xff787878;
        textSelectedBackground = 0xff3399ff;
        matchedTextBackground = 0xffd4d4d4;
        currentLine = 0xffe8f2fe;
        selectionInsert = 0xff03ebeb;
        selectionHandle = 0xff03ebeb;
        blockLine = 0xffd8d8d8;
        blockLineCurrent = 0;
    }
}
