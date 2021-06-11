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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans;

import java.util.Map;
import java.util.TreeMap;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.processors.SpanRecycler;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.model.analysis.spans.SpanMapModel;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.view.analysis.spans.SpanMapView;
import io.github.rosemoe.editor.core.util.Logger;

/**
 * This class is a SpanLineController container (line displayed to the screen).
 * All indexes are from 0 to n
 *
 * @author Release Standard
 */
public class SpanMapController {

    public SpanMapModel model = new SpanMapModel();
    public SpanMapView view   = new SpanMapView();

    /**
     * lineindex, SpanLineController
     * This associate a TreeMap with each line.
     * This allow row shifting durign analysis.
     * line 0..n-1
     */
    public TreeMap<Integer, SpanLineController> map = new TreeMap<Integer, SpanLineController>();
    public void SpanMap() {

    }
    /**
     * Append an empty line to the span map.
     * @return
     */
    public SpanLineController appendLine() {
        int newIndex = map.size();
        map.put(newIndex, SpanLineController.EMPTY());
        return map.get(newIndex);
    }
    /**
     * Insert a SpanLineController at a specific position in the span map.
     */
    public void add(int index, SpanLineController line) {
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
     * @return null if the line is not in the spanmap
     */
    public SpanLineController get(int lineno) {
        return map.get(lineno);
    }
    /**
     * This will get the required span line or create it if it doesn't exists.
     * lineno : 0..n-1 the span line to get.
     * @param lineno
     * @return
     */
    public SpanLineController getAddIfNeeded(int lineno) {
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
     * Remove the SpanLineController at the specified index.
     * @param index
     */
    public void remove(int index) {
        SpanLineController sl = map.get(index);
        SpanController.recycleAll(sl.concurrentSafeGetValues());
        map.remove(index);
    }

    /**
     * Recycle spans in the map.
     */
    public void recyle() {
        SpanRecycler.getInstance().recycle(this);
    }
    public SpanLineController[] getLines() {
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
        SpanLineController startLine = map.get(line);
        SpanLineController[]parts = startLine.split(col);
        map.put(line,parts[0]);
        for(int i = size()-1; i > line+cutSize; i=i-1 ) {
            map.put(i,map.get(i-1));
        }
        for(int i = line +1 ; i < line + cutSize ; i=i+1 ) {
            map.put(i, SpanLineController.EMPTY());
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
    public void insertLines(SpanLineController[]lines, int line, int col) {
        splitLine(line,col,lines.length);
        for(int i = 0; i < lines.length;i=i+1) {
            SpanLineController spanLine = map.get(line+i);
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
        SpanLineController startSpanLine = map.get(startLine);
        SpanLineController stopSpanLine = map.get(endLine);
        startSpanLine.removeContent(startColumn,Integer.MAX_VALUE);
        stopSpanLine.removeContent(0,endColumn);
        SpanLineController newLine = SpanLineController.merge(startSpanLine,stopSpanLine);
        map.put(startLine,newLine);

        for(int i = startLine + 1; i <= endLine; i=i+1) {
            remove(i);
        }
        for(int i = endLine ; i < map.size();i=i+1) {
            SpanLineController line = map.remove(i);
            map.put(i - (endLine-startLine),line);
        }
    }

    /**
     * Insert some content(a SpanController) into the desired line.
     * @param line
     * @param col
     * @param sz
     */
    public void insertContent(int line, int col, int sz) {
        insertContent(SpanController.EMPTY(),line,col,sz);
    }
    public void insertContent(SpanController span, int line, int col, int sz) {
        SpanLineController dest = map.get(line);
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
        //noinspection unchecked
        for(Map.Entry<Integer, SpanLineController> sl : map.entrySet().toArray(new Map.Entry[map.keySet().size()])) {
            Logger.debug(offset+"dump for line index " + sl.getKey());
            sl.getValue().dump(Logger.OFFSET);
        }
    }

    public SpanLineController addNormalIfNull() {
        appendLines(1);
        return get(0);
    }
    /**
     * This function is used to avoid concurrent exception when working with Collections.
     * @return
     */
    public SpanLineController[] concurrentSafeGetValues() {
        SpanLineController[] lines = null;
        while (lines == null ) {
            try {
                lines = map.values().toArray(new SpanLineController[size()]);
            } catch (java.util.ConcurrentModificationException e) {
                Logger.debug("This error is harmless if not repeat to much");
                e.printStackTrace();
                lines=null;
            }
        }
        return lines;
    }
}
