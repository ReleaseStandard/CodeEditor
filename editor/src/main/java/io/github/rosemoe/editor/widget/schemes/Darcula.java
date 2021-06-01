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
    }

    @Override public int getComment() { return 0xff808080; }
    @Override public int getWholeBackground() {
        return 0xff2b2b2b;
    }
    @Override public int getTextNormal() { return 0xffffffff; }
    @Override public int getLineNumberBackground() { return 0xff313335; }
    @Override public int getLineNumberPanel() { return 0xff606366; }
    @Override public int getLineDivider() { return 0xff606366; }
    @Override public int getScrollBarThumb() { return 0xffa6a6a6; }
    @Override public int getScrollBarThumbPressed() { return 0xff565656; }
    @Override public int getSelectedTextBackground(){return 0xff3676b8;}
    @Override public int getMatchedTextBackground() { return 0xff32593d; }
    @Override public int getCurrentLine() { return 0xff323232; }
    @Override public int getSelectionInsert() { return 0xffffffff; }
    @Override public int getSelectionHandle() { return 0xffffffff; }
    @Override public int getBlockLine() { return 0xff575757; }
    @Override public int getBlockLineCurrent() { return 0xdd575757; }
    @Override public int getNonPrintableChar(){ return 0xff6a8759; }
}
