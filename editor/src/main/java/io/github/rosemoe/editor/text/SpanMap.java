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

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.EditorColorScheme;

/**
 * This class is a SpanLine container (line displayed to the screen).
 * All indexes are from 0 to n
 */
public class SpanMap {

    /**
     * lineindex, SpanLine
     * This associate a TreeMap with each line.
     * This allow row shifting durign analysis.
     * line 0..n-1
     */
    public TreeMap<Integer,SpanLine> spanmap = new TreeMap<>();
    public void SpanMap() {

    }
    /**
     * Append an empty line to the span map.
     * @return
     */
    public SpanLine appendLine() {
        int newIndex = spanmap.size();
        spanmap.put(newIndex,SpanLine.EMPTY());
        return spanmap.get(newIndex);
    }
    /**
     * Insert a SpanLine at a specific position in the span map.
     */
    public void add(int index, SpanLine line) {
        spanmap.put(index,line);
    }
    /**
     * Complete the current spanmap such as it while contains finalSizeInLines.
     * It will not remove extra lines
     *
     * @param finalSizeInLines 0..n
     */
    public void appendLines(int finalSizeInLines) {
        while(spanmap.size() < finalSizeInLines) {
            appendLine();
        }
    }

    /**
     * lineno : 0..n-1 the span line to get
     * @param lineno
     */
    public SpanLine get(int lineno) {
        return spanmap.get(lineno);
    }
    /**
     * This will get the required span line or create it if it doesn't exists.
     * lineno : 0..n-1 the span line to get.
     * @param lineno
     * @return
     */
    public SpanLine getAddIfNeeded(int lineno) {
        appendLines(lineno+1);
        return get(lineno);
    }

    /**
     * returns the size of the map.
     * @return
     */
    public int size() {
        return spanmap.size();
    }

    /**
     * clear the spanmap, it will remove everything from the spanmap
     */
    public void clear() {
        spanmap.clear();
    }

    /**
     * Test if the spanmap is empty.
     * @return
     */
    public boolean isEmpty() {
        return spanmap.size()==0;
    }

    /**
     * Remove the SpanLine at the specified index.
     * @param index
     */
    public void remove(int index) {
        SpanLine sl = spanmap.get(index);
        Span.recycleAll(sl.line.values());
        spanmap.remove(index);
    }

    /**
     * Recycle spans in the map.
     */
    public void recyle() {
        SpanRecycler.getInstance().recycle(this);
    }
    public Collection<SpanLine> getLines() {
        return spanmap.values();
    }
    /**
     * Dump debug information on this class.
     */
    public void dump() {
        if ( !Logger.DEBUG ) { return; }
        Logger.debug("number of lines in : ",spanmap.size());
        for(Map.Entry<Integer,SpanLine> sl : spanmap.entrySet().toArray(new Map.Entry[spanmap.keySet().size()])) {
            sl.getValue().dump();
        }
    }


}
