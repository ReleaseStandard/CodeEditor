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
package io.github.rosemoe.editor.core.widgets.contentAnalyzer.model;

import io.github.rosemoe.editor.core.util.annotations.Experimental;

public class ContentMapModel {
    public int textLength;
    public int nestedBatchEdit;

    /**
     * Use a BlockLinkedList instead of ArrayList.
     * <p>
     * This can be faster while inserting in large text.
     * But in other conditions, it is quite slow.
     * <p>
     * Disabled by default.
     */
    @Experimental
    public static boolean useBlock = false;

    private static int sInitialListCapacity;

    /**
     * Returns the default capacity of text line list
     *
     * @return Default capacity
     */
    public static int getInitialLineCapacity() {
        return ContentMapModel.sInitialListCapacity;
    }

    /**
     * Set the default capacity of text line list
     *
     * @param capacity Default capacity
     */
    public static void setInitialLineCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity can not be negative or zero");
        }
        ContentMapModel.sInitialListCapacity = capacity;
    }
}
