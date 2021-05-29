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
public final class SchemeNano extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();

        int textNormal = 0xFFFFFFFF;
        int bgNormal = 0xFF373737;
        setColor(COMMENT,0xFF009990);
        setColor(LITERAL,0xFFe7de00);
        setColor(WHOLE_BACKGROUND, bgNormal);
        setColor(LINE_NUMBER_BACKGROUND, bgNormal);
        setColor(CURRENT_LINE, bgNormal);
        setColor(LINE_NUMBER, textNormal);
        setColor(TEXT_NORMAL,textNormal);

        setColor(KEYWORD, 0xFF045400);
        setColor(OPERATOR, 0xFF045400);
        setColor(FUNCTION_NAME, textNormal);

        setColor(SELECTED_TEXT_BACKGROUND,textNormal);
        setColor(TEXT_SELECTED,bgNormal);
    }

}
