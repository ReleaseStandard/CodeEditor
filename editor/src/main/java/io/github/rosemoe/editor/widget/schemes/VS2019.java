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

import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * ColorScheme for editor
 * picked from Visual Studio 2019
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public class VS2019 extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();
        setColor(ANNOTATION, 0xff4ec9b0);
        setColor(FUNCTION_NAME, 0xffdcdcdc);
        setColor(IDENTIFIER_NAME, 0xff4ec9b0);
        setColor(LITERAL, 0xffd69d85);
        setColor(OPERATOR, 0xffdcdcdc);
        setColor(KEYWORD, 0xff569cd6);
        base1 = 0xff57a64a;
        base3 = 0xff1e1e1e;
        base00 = 0xffdcdcdc;
        setColor(LINE_NUMBER_BACKGROUND, 0xff1e1e1e);
        setColor(LINE_NUMBER, 0xff2b9eaf);

        setColor(LINE_DIVIDER, 0xff2b9eaf);
        setColor(SCROLL_BAR_THUMB, 0xff3e3e42);
        setColor(SCROLL_BAR_THUMB_PRESSED, 0xff9e9e9e);
        setColor(CURRENT_LINE, 0xff464646);
        setColor(SELECTION_INSERT, 0xffffffff);
        setColor(SELECTION_HANDLE, 0xffffffff);
        setColor(BLOCK_LINE, 0xff717171);
        setColor(BLOCK_LINE_CURRENT, 0);
        setColor(NON_PRINTABLE_CHAR, 0xffdddddd);
    }
    @Override public int getMatchedTextBackground() { return 0xff653306; }
    @Override public int getSelectedTextBackground(){return 0xff3676b8;}


}
