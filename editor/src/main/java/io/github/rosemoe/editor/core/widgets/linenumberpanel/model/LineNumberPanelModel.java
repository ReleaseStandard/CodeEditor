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
package io.github.rosemoe.editor.core.widgets.linenumberpanel.model;

public class LineNumberPanelModel {
    public char[] computedText = new char[16];
    /**
     *
     * @param line
     * @return count
     */
    public int computeAndGetText(int line) {
        // Avoid Integer#toString() calls
        char[] text = computedText;
        int copy = line + 1;
        int count = 0;
        while (copy > 0) {
            int digit = copy % 10;
            text[count++] = (char) ('0' + digit);
            copy /= 10;
        }
        for (int i = 0, j = count - 1; i < j; i++, j--) {
            char tmp = text[i];
            text[i] = text[j];
            text[j] = tmp;
        }
        return count;
    }

    public static final int ALIGN_RIGHT = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_DEFAULT = ALIGN_LEFT;
    public int mLineNumberAlign = ALIGN_DEFAULT;

}
