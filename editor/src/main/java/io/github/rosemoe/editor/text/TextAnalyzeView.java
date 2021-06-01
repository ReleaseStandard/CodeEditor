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
package io.github.rosemoe.editor.text;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.struct.BlockLine;
import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.struct.SpanLine;
import io.github.rosemoe.editor.struct.SpanMap;
import io.github.rosemoe.editor.util.Logger;

/**
 * Display the result of analysis.
 * Update spans in response to the analysis.
 */
public class TextAnalyzeView {

    protected final List<BlockLine> mBlocks;
    public final SpanMap spanMap;
    public Object mExtra;
    protected int mSuppressSwitch = Integer.MAX_VALUE;

    /**
     * Create a new result
     */
    public TextAnalyzeView() {
        spanMap = new SpanMap();
        mBlocks = new ArrayList<>(1024);
    }

    /**
     * Add a new span if required (colorId is different from last)
     *
     * @param spanLine Line
     * @param column   Column
     * @param colorId  Type
     */
    public void addIfNeeded(int spanLine, int column, int colorId) {
        Logger.debug("spanLine=",spanLine,",column=",column,",colorId=",colorId);
        add(spanLine, Span.obtain(column, colorId));
    }
    /**
     * Add a span directly
     * Note: the line should always >= the line of span last committed
     * if two spans are on the same line, you must add them in order by their column
     *
     * @param spanLine The line position of span
     * @param span     The span
     */
    public void add(int spanLine, Span span) {
        spanMap.getAddIfNeeded(spanLine).add(span);
        spanMap.dump();
    }

    /**
     * This method must be called when whole text is analyzed
     *
     * @param line The line is the line last of text
     */
    public void determine(int line) {
        spanMap.appendLines(line);
    }

    /**
     * Get a new BlockLine object
     * <strong>It fields maybe not initialized with zero</strong>
     *
     * @return An idle BlockLine
     */
    public BlockLine obtainNewBlock() {
        return ObjectAllocator.obtainBlockLine();
    }

    /**
     * Add a new code block info
     *
     * @param block Info of code block
     */
    public void addBlockLine(BlockLine block) {
        mBlocks.add(block);
    }

    /**
     * Get list of code blocks
     *
     * @return code blocks
     */
    public List<BlockLine> getBlocks() {
        return mBlocks;
    }

    /**
     * Returns suppress switch
     *
     * @return suppress switch
     * @see TextAnalyzeView#setSuppressSwitch(int)
     */
    public int getSuppressSwitch() {
        return mSuppressSwitch;
    }

    /**
     * Set suppress switch for editor
     * What is 'suppress switch' ?:
     * Suppress switch is a switch size for code block line drawing
     * and for the process to find out which code block the cursor is in.
     * Because the code blocks are not saved by the order of both start line and
     * end line,we are unable to know exactly when we should stop the process.
     * So without a suppress switch,it will cost a large of time to search code
     * blocks.So I added this switch.
     * A suppress switch is the code block count in the first layer code block
     * (as well as its sub code blocks).
     * If you are unsure,do not set it.
     * The default value if Integer.MAX_VALUE
     *
     * @param suppressSwitch Suppress switch
     */
    public void setSuppressSwitch(int suppressSwitch) {
        mSuppressSwitch = suppressSwitch;
    }
    public SpanMap getSpanMap() {
        return spanMap;
    }

    /**
     * Add text line in the span line if there is nothing in the map.
     * @return
     */
    public SpanLine addNormalIfNull() {
        spanMap.appendLines(1);
        return spanMap.get(0);
    }

    /**
     * Reset the anaylysis result
     */
    public void clear() {
        spanMap.clear();
        mBlocks.clear();
        mSuppressSwitch = Integer.MAX_VALUE;
        mExtra = null;
    }
}