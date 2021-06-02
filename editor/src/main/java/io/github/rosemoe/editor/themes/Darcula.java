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

import io.github.rosemoe.editor.mvc.controller.ColorSchemeController;

/**
 * ColorScheme for editor
 * picked from Android Studio
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public class Darcula extends ColorSchemeController {

    @Override
    public void initTheme() {
        theme_name = "Darcula";
        theme_description = "picked from Android Studio, Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)";
        accent1 = 0xff32593d;
        base00 = 0xffffffff;
        base1 = 0xff606366;
        base2 = 0xff323232;
        base3 = 0xff2b2b2b;

        comment = 0xff808080;
        wholeBackground = 0xff2b2b2b;
        textNormal = 0xffffffff;
        lineNumberBackground = 0xff313335;
        lineNumberPanel = 0xff606366;
        lineDivider = 0xff606366;
        scrollbarthumb = 0xffa6a6a6;
        scrollbarthumbpressed = 0xff565656;
        selectedTextBackground = 0xff3676b8;
        matchedTextBackground = 0xff32593d;
        currentLine = 0xff323232;
        selectionInsert = 0xffffffff;
        selectionHandle = 0xffffffff;
        blockLine = 0xff575757;
        blockLineCurrent = 0xdd575757;
        nonprintablechar = 0xff6a8759;
    }
}
