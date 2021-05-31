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
 * ColorScheme for HTML Language for editor
 */
public class HTML extends EditorColorScheme {
    @Override
    public void initTheme() {
        setColor(BLOCK_LINE_CURRENT, 0xffffffff);
        base00 = 0xffffffff;
        base1 = 0xffbdbdbd;
        base2 = 0xff464646;
        base3 = 0xff212121;
    }
    @Override public int getSelectionInsert() { return 0xffffffff; }
    @Override public int getSelectionHandle() { return 0xffffffff; }
    @Override public int getNonPrintableChar() { return 0xffdddddd; }
}
