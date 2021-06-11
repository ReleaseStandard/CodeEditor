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
package io.github.rosemoe.editor.core.widgets.cursor.model;

import io.github.rosemoe.editor.core.CharPosition;

import static io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController.DEFAULT_ISAUTO_IDENT;

public class CursorModel {
    public int mTabWidth = 4;
    public boolean mAutoIndentEnabled = DEFAULT_ISAUTO_IDENT;
    public CharPosition mLeft, mRight;
    public CharPosition cache0, cache1, cache2;
    public CursorModel() {
        mLeft = new CharPosition().zero();
        mRight = new CharPosition().zero();
    }
}
