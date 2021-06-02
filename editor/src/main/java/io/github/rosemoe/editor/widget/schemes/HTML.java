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

import io.github.rosemoe.editor.mvc.controller.ColorSchemeController;

/**
 * ColorScheme for HTML Language for editor
 */
public class HTML extends ColorSchemeController {
    @Override
    public void initTheme() {
        theme_name = "HTML";
        theme_description = "ColorScheme for HTML Language for editor";
        base00 = 0xffffffff;
        base1 = 0xffbdbdbd;
        base2 = 0xff464646;
        base3 = 0xff212121;
        comment = 0xffbdbdbd;
        wholeBackground = 0xff212121;
        textNormal = 0xffffffff;
        lineNumberBackground = 0xff1e1e1e;
        currentLine = 0xff464646;
        nonprintablechar = 0xffdddddd;
        selectionInsert = 0xffffffff;
        selectionHandle = 0xffffffff;
        lineNumberPanel = 0xff2b9eaf;
        lineDivider = 0xff2b9eaf;
    }
}
