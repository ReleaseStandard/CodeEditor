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
package io.github.rosemoe.editor.struct;

import java.util.Map;
import java.util.TreeMap;

import io.github.rosemoe.editor.text.spanmap.Recycler;
import io.github.rosemoe.editor.util.Logger;

/**
 * This class is a SpanLine container (line displayed to the screen).
 * All indexes are from 0 to n
 *
 * @author Release Standard
 */
public class SpanMap {

    /**
     * lineindex, SpanLine
     * This associate a TreeMap with each line.
     * This allow row shifting durign analysis.
     * line 0..n-1
     */
    public TreeMap<Integer,SpanLine> map = new TreeMap<>();
    public void SpanMap() {

    }
    /**
     * Append an empty line to the span map.
     * @return
     */
    public SpanLine appendLine() {
        int newIndex = map.size();
        map.put(newIndex,SpanLine.EMPTY());
        return map.get(newIndex);
    }
    /**
     * Insert a SpanLine at a specific position in the span map.
     */
    public void add(int index, SpanLine line) {
        map.put(index,line);
    }
    /**
     * Complete the current spanmap such as it while contains finalSizeInLines.
     * It will not remove extra lines
     *
     * @param finalSizeInLines 0..n
     */
    public void appendLines(int finalSizeInLines) {
        while(map.size() < finalSizeInLines) {
            appendLine();
        }
    }

    /**
     * lineno : 0..n-1 the span line to get
     * @param lineno
     */
    public SpanLine get(int lineno) {
        return map.get(lineno);
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
        return map.size();
    }

    /**
     * clear the spanmap, it will remove everything from the spanmap
     */
    public void clear() {
        map.clear();
    }

    /**
     * Test if the spanmap is empty.
     * @return
     */
    public boolean isEmpty() {
        return map.size()==0;
    }

    /**
     * Remove the SpanLine at the specified index.
     * @param index
     */
    public void remove(int index) {
        SpanLine sl = map.get(index);
        Span.recycleAll(sl.concurrentSafeGetValues());
        map.remove(index);
    }

    /**
     * Recycle spans in the map.
     */
    public void recyle() {
        Recycler.getInstance().recycle(this);
    }
    public SpanLine[] getLines() {
        return concurrentSafeGetValues();
    }

    /**
     * Split the specified line at specified position and put it down.
     * @param line index 0..n-1
     * @param col index 0..n-1
     * @param cutSize size 1..n the cut size, eg a cut of one will split a line in to lines, a cut of two will split line in two lines plus insert an empty line.
     */
    public void splitLine(int line, int col, int cutSize) {
        dump();
        Logger.debug("cutDown line=",line,",col=",col,",cutSize=",cutSize);
        SpanLine startLine = map.get(line);
        SpanLine []parts = startLine.split(col);
        map.put(line,parts[0]);
        for(int i = size()-1; i > line+cutSize; i=i-1 ) {
            map.put(i,map.get(i-1));
        }
        for(int i = line +1 ; i < line + cutSize ; i=i+1 ) {
            map.put(i, SpanLine.EMPTY());
        }
        map.put(line,parts[0]);
        map.put(line+cutSize,parts[1]);
    }
    /**
     * Insert the Specified content (Spanlines) at the specified position.
     * @param lines lines to insert
     * @param line index 0..n-1
     * @param col index 0..n-1
     */
    public void insertLines(SpanLine[]lines, int line, int col) {
        splitLine(line,col,lines.length);
        for(int i = 0; i < lines.length;i=i+1) {
            SpanLine spanLine = map.get(line+i);
            spanLine.add(lines[i]);
        }
    }

    /**
     * Remove spans and lines of all lines found between bounds.
     * @param startLine index 0..n-1 of the start line
     * @param startColumn index 0..n-1 of the start column
     * @param endLine index 0..n-1 of the end line
     * @param endColumn index 0..n-1 of the end column
     */
    public void cutLines(int startLine, int startColumn, int endLine, int endColumn) {
        SpanLine startSpanLine = map.get(startLine);
        SpanLine stopSpanLine = map.get(endLine);
        startSpanLine.removeContent(startColumn,Integer.MAX_VALUE);
        stopSpanLine.removeContent(0,endColumn);
        SpanLine newLine = SpanLine.merge(startSpanLine,stopSpanLine);
        map.put(startLine,newLine);

        for(int i = startLine + 1; i <= endLine; i=i+1) {
            remove(i);
        }
        for(int i = endLine ; i < map.size();i=i+1) {
            SpanLine line = map.remove(i);
            map.put(i - (endLine-startLine),line);
        }
    }

    /**
     * Insert some content(a Span) into the desired line.
     * @param line
     * @param col
     * @param sz
     */
    public void insertContent(int line, int col, int sz) {
        insertContent(Span.EMPTY(),line,col,sz);
    }
    public void insertContent(Span span, int line, int col, int sz) {
        SpanLine dest = map.get(line);
        dest.insertContent(span,col,sz);
    }
    /**
     * Dump debug information on this class.
     */
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        if ( !Logger.DEBUG ) { return; }
        Logger.debug(offset+"number of lines in : "+ map.size());
        for(Map.Entry<Integer,SpanLine> sl : map.entrySet().toArray(new Map.Entry[map.keySet().size()])) {
            Logger.debug(offset+"dump for line index " + sl.getKey());
            sl.getValue().dump(Logger.OFFSET);
        }
    }

    /**
     * This function is used to avoid concurrent exception when working with Collections.
     * @return
     */
    public SpanLine[] concurrentSafeGetValues() {
        SpanLine[] lines = null;
        while (lines == null ) {
            try {
                lines = map.values().toArray(new SpanLine[size()]);
            } catch (java.util.ConcurrentModificationException e) {
                Logger.debug("This error is harmless if not repeat to much");
                e.printStackTrace();
                lines=null;
            }
        }
        return lines;
    }
}
