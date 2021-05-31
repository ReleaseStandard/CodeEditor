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
    public void initTheme() {
        setColor(BLOCK_LINE_CURRENT, 0xffff00ff);
        accent1 = 0xff32593d;
        base00 = 0xffffffff;
        base1 = 0xff606366;
        base2 = 0xff323232;
        base3 = 0xff2b2b2b;
    }
    @Override public int getSelectionInsert() { return 0xff0000ff; }
    @Override public int getComment() { return 0xff808080; }
    @Override public int getMatchedTextBackground() { return 0xff32593d; }
    @Override public int getSelectedTextBackground(){return 0xff3676b8;}
    @Override public int getSelectionHandle(){ return 0xffff0000; }
    @Override public int getScrollBarThumb() { return 0xffff0000; }
    @Override public int getScrollBarThumbPressed() { return 0xff00ff00; }
    @Override public int getNonPrintableChar() { return 0xfff0f0f0; }
}
