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
 * picked from Visual Studio 2019
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public class VS2019 extends ColorSchemeController {

    @Override
    public void initTheme() {
        theme_name = "VS2019";
        theme_description = "picked from Visual Studio 2019, Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)";
        base00 = 0xffdcdcdc;
        base1 = 0xff57a64a;
        base2 = 0xff3676b8;
        base3 = 0xff1e1e1e;
        comment = 0xff57a64a;
        wholeBackground = 0xff1e1e1e;
        textNormal = 0xffdcdcdc;
        lineNumberBackground = 0xff1e1e1e;
        lineNumberPanel = 0xff2b9eaf;
        lineDivider = 0xff2b9eaf;
        scrollbarthumb = 0xff3e3e42;
        scrollbarthumbpressed = 0xff9e9e9e;
        selectedTextBackground = 0xff3676b8;
        matchedTextBackground = 0xff653306;
        currentLine = 0xff464646;
        selectionInsert = 0xffffffff;
        selectionHandle = 0xffffffff;
        blockLine = 0xff717171;
        blockLineCurrent = 0;
        nonprintablechar = 0xffdddddd;
    }
}
