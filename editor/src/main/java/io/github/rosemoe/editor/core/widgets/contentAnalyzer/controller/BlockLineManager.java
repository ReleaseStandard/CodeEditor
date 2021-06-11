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
package io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller;

import java.util.List;

import io.github.rosemoe.editor.core.BlockLineModel;

/**
 * A object provider for speed improvement
 * Now meaningless because it is not as well as it expected
 *
 * @author Rose
 */
public class BlockLineManager {

    private static final int RECYCLE_LIMIT = 1024 * 8;
    private static List<BlockLineModel> blockLines;

    public static void recycle(List<BlockLineModel> src) {
        if (src == null) {
            return;
        }
        if (blockLines == null) {
            blockLines = src;
            return;
        }
        int size = blockLines.size();
        int sizeAnother = src.size();
        while (sizeAnother > 0 && size < RECYCLE_LIMIT) {
            size++;
            sizeAnother--;
            blockLines.add(src.get(sizeAnother));
        }
    }

    public static BlockLineModel obtain() {
        return (blockLines == null || blockLines.isEmpty()) ? new BlockLineModel() : blockLines.remove(blockLines.size() - 1);
    }

}
