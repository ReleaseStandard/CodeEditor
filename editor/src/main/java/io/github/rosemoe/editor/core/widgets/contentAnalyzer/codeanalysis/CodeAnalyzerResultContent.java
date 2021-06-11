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
package io.github.rosemoe.editor.core.widgets.contentAnalyzer.codeanalysis;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzerResult;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.tokenemitter.TokenEmitterResult;
import io.github.rosemoe.editor.core.BlockLineModel;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.BlockLineManager;

/**
 * Manage how the analyzer show display the content to the screen.
 */
public class CodeAnalyzerResultContent extends TokenEmitterResult {
    public final List<BlockLineModel> mBlocks;
    public CodeAnalyzerResultContent() {
        mBlocks = new ArrayList<>(1024);
    }
    /**
     * Get a new BlockLineModel object
     * <strong>It fields maybe not initialized with zero</strong>
     *
     * @return An idle BlockLineModel
     */
    public BlockLineModel obtainNewBlock() {
        return BlockLineManager.obtain();
    }
    /**
     * Add a new code block info
     *
     * @param block Info of code block
     */
    public void addBlockLine(BlockLineModel block) {
        mBlocks.add(block);
    }

    @Override
    public void clear() {
        super.clear();
        mBlocks.clear();
    }

    @Override
    public CodeAnalyzerResult clone() {
        return new CodeAnalyzerResultContent();
    }
}
