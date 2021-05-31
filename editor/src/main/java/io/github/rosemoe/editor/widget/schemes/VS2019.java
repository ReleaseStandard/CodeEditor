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

    public VS2019() {
        base00 = 0xffdcdcdc;
        base1 = 0xff57a64a;
        base2 = 0xff3676b8;
        base3 = 0xff1e1e1e;
        setColor(BLOCK_LINE_CURRENT, 0);
        setColor(NON_PRINTABLE_CHAR, 0xffdddddd);
    }
    @Override public int getMatchedTextBackground() { return 0xff653306; }
    @Override public int getSelectedTextBackground(){return 0xff3676b8;}
    @Override public int getSelectionInsert(){return 0xffffffff;}
    @Override public int getSelectionHandle() { return 0xffffffff;  }
    @Override public int getScrollBarThumb() { return 0xff3e3e42; }
    @Override public int getScrollBarThumbPressed() { return 0xff9e9e9e; }
}
