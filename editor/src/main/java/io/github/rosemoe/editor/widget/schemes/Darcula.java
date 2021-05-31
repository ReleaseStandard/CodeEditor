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
 * picked from Android Studio
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public class Darcula extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();
        setColor(ANNOTATION, 0xffbbb529);
        setColor(FUNCTION_NAME, 0xffffffff);
        setColor(IDENTIFIER_NAME, 0xffffffff);
        setColor(LITERAL, 0xff6a8759);
        setColor(OPERATOR, 0xffffffff);
        setColor(KEYWORD, 0xffcc7832);
        setColor(SCROLL_BAR_THUMB, 0xffa6a6a6);
        setColor(SCROLL_BAR_THUMB_PRESSED, 0xff565656);
        setColor(SELECTION_INSERT, 0xffffffff);
        setColor(SELECTION_HANDLE, 0xffffffff);
        setColor(BLOCK_LINE, 0xff575757);
        setColor(BLOCK_LINE_CURRENT, 0xdd575757);
        setColor(NON_PRINTABLE_CHAR, 0xffdddddd);

        accent1 = 0xff32593d;
        base00 = 0xffffffff;
        base1 = 0xff606366;
        base2 = 0xff323232;
        base3 = 0xff2b2b2b;
    }

    @Override public int getComment() {
        return 0xff808080;
    }
    @Override public int getMatchedTextBackground() { return 0xff32593d; }
    @Override public int getSelectedTextBackground(){return 0xff3676b8;}
}
