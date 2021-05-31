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
 * picked from Eclipse IDE for Java Developers Version 2019-12 (4.14.0)
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public final class Eclipse extends EditorColorScheme {

    @Override
    public void initTheme() {
        base3 = 0xffffffff;
        base2 = 0xffe8f2fe;
        setColor(BLOCK_LINE_CURRENT, 0);
        base00 = 0xff000000;
        base1 = 0xff3f7f5f;
    }
    @Override public int getMatchedTextBackground() { return 0xffd4d4d4; }
    @Override public int getSelectedTextBackground() { return 0xff3399ff; }
    @Override public int getLineNumberPanelText() { return 0xff787878; }
    @Override public int getLineNumberBackground() { return 0xffffffff; }
    @Override public int getSelectionInsert() { return 0xff03ebeb; }
    @Override public int getSelectionHandle() { return 0xff03ebeb; }
}
