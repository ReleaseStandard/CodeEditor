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
 * picked from Notepad++ v7.8.1
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public final class NotepadXX extends EditorColorScheme {

    public NotepadXX() {
        base1 = 0xff008000;
        base3 = 0xffffffff;
        base00 = 0xff000000;
        base2 = 0xffe4e4e4;
        setColor(BLOCK_LINE_CURRENT, 0);
    }
    @Override public int getMatchedTextBackground() { return 0xffc0c0c0; }
    @Override public int getSelectedTextBackground(){return 0xff75d975;}
    @Override public int getSelectionInsert() { return 0xff8000ff; }
    @Override public int getSelectionHandle() { return 0xff8000ff; }

}
