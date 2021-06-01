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
 * picked from Visual Studio 2019
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public class VS2019 extends ColorSchemeController {

    @Override
    public void initTheme() {
        base00 = 0xffdcdcdc;
        base1 = 0xff57a64a;
        base2 = 0xff3676b8;
        base3 = 0xff1e1e1e;
    }
    @Override public int getComment() { return 0xff57a64a; }
    @Override public int getWholeBackground() {
        return 0xff1e1e1e;
    }
    @Override public int getTextNormal() { return 0xffdcdcdc; }
    @Override public int getLineNumberBackground() { return 0xff1e1e1e; }
    @Override public int getLineNumberPanel() { return 0xff2b9eaf; }
    @Override public int getLineDivider() { return 0xff2b9eaf; }
    @Override public int getScrollBarThumb() { return 0xff3e3e42; }
    @Override public int getScrollBarThumbPressed() { return 0xff9e9e9e; }
    @Override public int getSelectedTextBackground(){return 0xff3676b8;}
    @Override public int getMatchedTextBackground() { return 0xff653306; }
    @Override public int getCurrentLine() { return 0xff464646; }
    @Override public int getSelectionInsert() { return 0xffffffff; }
    @Override public int getSelectionHandle() { return 0xffffffff; }
    @Override public int getBlockLine() { return 0xff717171; }
    @Override public int getBlockLineCurrent() { return 0; }
    @Override public int getNonPrintableChar() { return 0xffdddddd; }
}
