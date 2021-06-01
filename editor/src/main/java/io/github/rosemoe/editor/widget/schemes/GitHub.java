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
 * picked from Notepad++ v7.8.1
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public final class GitHub extends ColorSchemeController {

    @Override
    public void initTheme() {
        base1 = 0xff6a737d;
        base3 = 0xffffffff;
        base00 = 0xff24292e;
        base2 = 0xffbec0c1;
    }
    @Override public int getComment() { return 0xff6a737d; }
    @Override public int getWholeBackground() {
        return 0xffffffff;
    }
    @Override public int getTextNormal() { return 0xff24292e; }
    @Override public int getLineNumberBackground() { return 0xffffffff; }
    @Override public int getLineNumberPanel() { return 0xffbec0c1; }
    @Override public int getSelectionInsert() { return 0xffc7edcc; }
    @Override public int getSelectionHandle() { return 0xffc7edcc; }
}
