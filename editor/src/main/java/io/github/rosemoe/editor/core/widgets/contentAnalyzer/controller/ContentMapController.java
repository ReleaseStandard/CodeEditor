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

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController;
import io.github.rosemoe.editor.core.CharPosition;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.model.ContentMapModel;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.view.ContentMapView;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.processors.indexer.CachedIndexer;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.processors.indexer.Indexer;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.processors.ContentLineRemoveListener;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.processors.indexer.NoCacheIndexer;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.struct.BlockLinkedList;

/**
 * This class saves the text content for editor and maintains line widths
 *
 * @author Rose
 */
public class ContentMapController implements CharSequence {

    public ContentMapModel model = new ContentMapModel();
    public ContentMapView view   = new ContentMapView();

    public final static int DEFAULT_MAX_UNDO_STACK_SIZE = 100;
    public final static int DEFAULT_LIST_CAPACITY = 1000;

    static {
        ContentMapModel.setInitialLineCapacity(DEFAULT_LIST_CAPACITY);
    }

    private List<ContentLineController> lines;
    private Indexer indexer;
    private ContentManagerController contentManager;
    private CursorController cursor;
    private List<ContentListener> mListeners;
    private ContentLineRemoveListener mLineListener;

    private final CodeEditor editor;

    /**
     * This constructor will create a ContentMapController object with no text
     */
    public ContentMapController() {
        this(null,null);
    }

    /**
     * This constructor will create a ContentMapController object with the given source
     * If you give us null,it will just create a empty ContentMapController object
     *
     * @param src The source of ContentMapController
     */
    public ContentMapController(CharSequence src, CodeEditor editor) {
        this.editor = editor;
        if (src == null) {
            src = "";
        }
        model.textLength = 0;
        model.nestedBatchEdit = 0;
        if (!ContentMapModel.useBlock)
            lines = new ArrayList<>(ContentMapModel.getInitialLineCapacity());
        else
            lines = new BlockLinkedList<>(5000);
        lines.add(new ContentLineController());
        mListeners = new ArrayList<>();
        contentManager = new ContentManagerController(this);
        setMaxUndoStackSize(ContentMapController.DEFAULT_MAX_UNDO_STACK_SIZE);
        indexer = new NoCacheIndexer(this);
        if (src.length() == 0) {
            setUndoEnabled(true);
            return;
        }
        setUndoEnabled(false);
        insert(0, 0, src);
        setUndoEnabled(true);
    }

    /**
     * Test whether the two ContentLineController have the same content
     *
     * @param a ContentLineController
     * @param b another ContentLineController
     * @return Whether equals in content
     */
    private static boolean equals(ContentLineController a, ContentLineController b) {
        if (a.length() != b.length()) {
            return false;
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public char charAt(int index) {
        checkIndex(index);
        CharPosition p = getIndexer().getCharPosition(index);
        return charAt(p.line, p.column);
    }

    @Override
    public int length() {
        return model.textLength;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start > end) {
            throw new StringIndexOutOfBoundsException("start > end");
        }
        CharPosition s = getIndexer().getCharPosition(start);
        CharPosition e = getIndexer().getCharPosition(end);
        return subContent(s.getLine(), s.getColumn(), e.getLine(), e.getColumn());
    }

    /**
     * Set a line listener
     *
     * @param lis the listener,maybe null
     * @see ContentLineRemoveListener
     */
    public void setLineListener(ContentLineRemoveListener lis) {
        this.mLineListener = lis;
    }

    /**
     * Get the character at the given position
     * If (column == getColumnCount(line)),it returns '\n'
     * IndexOutOfBoundsException is thrown
     *
     * @param line   The line position of character
     * @param column The column position of character
     * @return The character at the given position
     */
    public char charAt(int line, int column) {
        checkLineAndColumn(line, column, true);
        if (column == getColumnCount(line)) {
            return '\n';
        }
        return lines.get(line).charAt(column);
    }

    /**
     * Get raw data of line
     * The result is not expected to be modified
     *
     * @param line Line
     * @return Raw ContentLineController used by ContentMapController
     */
    public ContentLineController getLine(int line) {
        return lines.get(line);
    }

    /**
     * Get how many lines there are
     *
     * @return Line count
     */
    public int getLineCount() {
        return lines.size();
    }

    /**
     * Get how many characters is on the given line
     * If (line < 0 or line >= getLineCount()),it will throw a IndexOutOfBoundsException
     *
     * @param line The line to get
     * @return Character count on line
     */
    public int getColumnCount(int line) {
        checkLine(line);
        return lines.get(line).length();
    }

    /**
     * Get the given line text without '\n' character
     *
     * @param line The line to get
     * @return New String object of this line
     */
    public String getLineString(int line) {
        checkLine(line);
        return lines.get(line).toString();
    }

    /**
     * Get characters of line
     */
    public void getLineChars(int line, char[] dest) {
        lines.get(line).getChars(0, getColumnCount(line), dest, 0);
    }

    /**
     * Transform the (line,column) position to index
     * This task will usually completed by {@link Indexer}
     *
     * @param line   Line of index
     * @param column Column on line of index
     * @return Transformed index for the given arguments
     */
    public int getCharIndex(int line, int column) {
        return getIndexer().getCharIndex(line, column);
    }

    /**
     * Insert content to this object
     *
     * @param line   The insertion's line position
     * @param column The insertion's column position
     * @param text   The text you want to insert at the position
     */
    public void insert(int line, int column, CharSequence text) {
        checkLineAndColumn(line, column, true);
        if (text == null) {
            throw new IllegalArgumentException("text can not be null");
        }

        //-----Notify------
        if (cursor != null)
            cursor.beforeInsert(line, column);

        int workLine = line;
        int workIndex = column;
        if (workIndex == -1) {
            workIndex = 0;
        }
        ContentLineController currLine = lines.get(workLine);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\n') {
                ContentLineController newLine = new ContentLineController();
                newLine.append(currLine, workIndex, currLine.length());
                currLine.delete(workIndex, currLine.length());
                lines.add(workLine + 1, newLine);
                currLine = newLine;
                workIndex = 0;
                workLine++;
            } else {
                currLine.insert(workIndex, c);
                workIndex++;
            }
        }
        model.textLength += text.length();
        this.dispatchAfterInsert(line, column, workLine, workIndex, text);
    }

    /**
     * Delete character in [start,end)
     *
     * @param start Start position in content
     * @param end   End position in content
     */
    public void delete(int start, int end) {
        checkIndex(start);
        checkIndex(end);
        CharPosition startPos = getIndexer().getCharPosition(start);
        CharPosition endPos = getIndexer().getCharPosition(end);
        if (start != end) {
            delete(startPos.line, startPos.column, endPos.line, endPos.column);
        }
    }

    /**
     * Delete text in the given region
     *
     * @param startLine         The start line position
     * @param columnOnStartLine The start column position
     * @param endLine           The end line position
     * @param columnOnEndLine   The end column position
     */
    public void delete(int startLine, int columnOnStartLine, int endLine, int columnOnEndLine) {
        StringBuilder changedContent = new StringBuilder();
        if (startLine == endLine) {
            checkLineAndColumn(endLine, columnOnEndLine, true);
            checkLineAndColumn(startLine, columnOnStartLine == -1 ? 0 : columnOnStartLine, true);
            int beginIdx = columnOnStartLine;
            if (columnOnStartLine == -1) {
                beginIdx = 0;
            }
            if (beginIdx > columnOnEndLine) {
                throw new IllegalArgumentException("start > end");
            }
            ContentLineController curr = lines.get(startLine);
            int len = curr.length();
            if (beginIdx < 0 || beginIdx > len || columnOnEndLine > len) {
                throw new StringIndexOutOfBoundsException("column start or column end is out of bounds");
            }

            //-----Notify------
            if (cursor != null)
                if (columnOnStartLine != -1)
                    cursor.beforeDelete(startLine, columnOnStartLine, endLine, columnOnEndLine);
                else
                    cursor.beforeDelete(startLine == 0 ? 0 : startLine - 1, startLine == 0 ? 0 : getColumnCount(startLine - 1), endLine, columnOnEndLine);

            changedContent.append(curr, beginIdx, columnOnEndLine);
            curr.delete(beginIdx, columnOnEndLine);
            model.textLength -= columnOnEndLine - columnOnStartLine;
            if (columnOnStartLine == -1) {
                if (startLine == 0) {
                    model.textLength++;
                } else {
                    ContentLineController previous = lines.get(startLine - 1);
                    previous.append(curr);
                    ContentLineController rm = lines.remove(startLine);
                    if (mLineListener != null) {
                        mLineListener.onRemove(this, rm);
                    }
                    changedContent.insert(0, '\n');
                    startLine--;
                    columnOnStartLine = getColumnCount(startLine);
                }
            }
        } else if (startLine < endLine) {
            checkLineAndColumn(startLine, columnOnStartLine, true);
            checkLineAndColumn(endLine, columnOnEndLine, true);

            //-----Notify------
            if (cursor != null)
                cursor.beforeDelete(startLine, columnOnStartLine, endLine, columnOnEndLine);

            for (int i = 0; i < endLine - startLine - 1; i++) {
                ContentLineController line = lines.remove(startLine + 1);
                if (mLineListener != null) {
                    mLineListener.onRemove(this, line);
                }
                model.textLength -= line.length() + 1;
                changedContent.append('\n').append(line);
            }
            int currEnd = startLine + 1;
            ContentLineController start = lines.get(startLine);
            ContentLineController end = lines.get(currEnd);
            model.textLength -= start.length() - columnOnStartLine;
            changedContent.insert(0, start, columnOnStartLine, start.length());
            start.delete(columnOnStartLine, start.length());
            model.textLength -= columnOnEndLine;
            changedContent.append('\n').append(end, 0, columnOnEndLine);
            end.delete(0, columnOnEndLine);
            model.textLength--;
            ContentLineController r = lines.remove(currEnd);
            if (mLineListener != null) {
                mLineListener.onRemove(this, r);
            }
            start.append(end);
        } else {
            throw new IllegalArgumentException("start line > end line");
        }
        this.dispatchAfterDelete(startLine, columnOnStartLine, endLine, columnOnEndLine, changedContent);
    }

    /**
     * Replace the text in the given region
     * This action will completed by calling {@link ContentMapController#delete(int, int, int, int)} and {@link ContentMapController#insert(int, int, CharSequence)}
     *
     * @param startLine         The start line position
     * @param columnOnStartLine The start column position
     * @param endLine           The end line position
     * @param columnOnEndLine   The end column position
     * @param text              The text to replace old text
     */
    public void replace(int startLine, int columnOnStartLine, int endLine, int columnOnEndLine, CharSequence text) {
        if (text == null) {
            throw new IllegalArgumentException("text can not be null");
        }
        this.dispatchBeforeReplace();
        delete(startLine, columnOnStartLine, endLine, columnOnEndLine);
        insert(startLine, columnOnStartLine, text);
    }

    /**
     * When you are going to use {@link CharSequence#charAt(int)} frequently,you are required to call
     * this method.Because the way ContentMapController save text,it is usually slow to transform index to
     * (line,column) from the start of text when the text is big.
     * By calling this method,you will be able to get faster because calling this will
     * cause the ITextContent object use a Indexer with cache.
     * The performance is highly improved while linearly getting characters.
     *
     * @param initialIndex The Indexer with cache will take it into this index to its cache
     */
    public void beginStreamCharGetting(int initialIndex) {
        indexer = new CachedIndexer(this);
        indexer.getCharPosition(initialIndex);
    }

    /**
     * When you finished calling {@link CharSequence#charAt(int)} frequently,you can call this method
     * to free the Indexer with cache.
     * This is not forced.
     */
    public void endStreamCharGetting() {
        indexer = new NoCacheIndexer(this);
    }

    /**
     * Undo the last modification
     * NOTE:When there are too much modification,old modification will be deleted from ContentManagerController
     */
    public void undo() {
        contentManager.undo(this);
    }

    /**
     * Redo the last modification
     */
    public void redo() {
        contentManager.redo(this);
    }

    /**
     * Whether we can undo
     *
     * @return Whether we can undo
     */
    public boolean canUndo() {
        return contentManager.canUndo();
    }

    /**
     * Whether we can redo
     *
     * @return Whether we can redo
     */
    public boolean canRedo() {
        return contentManager.canRedo();
    }

    /**
     * Get whether ContentManagerController is enabled
     *
     * @return Whether ContentManagerController is enabled
     */
    public boolean isUndoEnabled() {
        return contentManager.model.isUndoEnabled();
    }

    /**
     * Set whether enable the ContentManagerController.
     * If false,any modification will not be taken down and previous modification that
     * is already in ContentManagerController will be removed.Does not make changes to content.
     *
     * @param enabled New state for ContentManagerController
     */
    public void setUndoEnabled(boolean enabled) {
        contentManager.setUndoEnabled(enabled);
    }

    /**
     * Get current max stack size of ContentManagerController
     *
     * @return current max stack size
     */
    public int getMaxUndoStackSize() {
        return contentManager.model.getMaxUndoStackSize();
    }

    /**
     * Set the max size of stack in ContentManagerController
     *
     * @param maxSize New max size
     */
    public void setMaxUndoStackSize(int maxSize) {
        contentManager.setMaxUndoStackSize(maxSize);
    }

    /**
     * A delegate method.
     * Notify the ContentManagerController to begin batch edit(enter a new layer).
     * NOTE: batch edit in Android can be nested.
     *
     * @return Whether in batch edit
     */
    public boolean beginBatchEdit() {
        model.nestedBatchEdit++;
        return isInBatchEdit();
    }

    /**
     * A delegate method.
     * Notify the ContentManagerController to end batch edit(exit current layer).
     *
     * @return Whether in batch edit
     */
    public boolean endBatchEdit() {
        model.nestedBatchEdit--;
        if (model.nestedBatchEdit < 0) {
            model.nestedBatchEdit = 0;
        }
        return isInBatchEdit();
    }

    /**
     * Returns whether we are in batch edit
     *
     * @return Whether in batch edit
     */
    public boolean isInBatchEdit() {
        return model.nestedBatchEdit > 0;
    }

    /**
     * Add a new {@link ContentListener} to the ContentMapController
     *
     * @param listener The listener to add
     */
    public void addContentListener(ContentListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener can not be null");
        }
        if (listener instanceof Indexer) {
            throw new IllegalArgumentException("Permission denied");
        }
        if (!mListeners.contains(listener)) {
            mListeners.add(listener);
        }
    }

    /**
     * Remove the given listener of this ContentMapController
     *
     * @param listener The listener to remove
     */
    public void removeContentListener(ContentListener listener) {
        if (listener instanceof Indexer) {
            throw new IllegalArgumentException("Permission denied");
        }
        mListeners.remove(listener);
    }

    /**
     * Get the using {@link Indexer} object
     *
     * @return Indexer for this object
     */
    public Indexer getIndexer() {
        if (indexer.getClass() != CachedIndexer.class && cursor != null) {
            return cursor.getIndexer();
        }
        return indexer;
    }

    /**
     * Quick method to get sub string of this object
     *
     * @param startLine   The start line position
     * @param startColumn The start column position
     * @param endLine     The end line position
     * @param endColumn   The end column position
     * @return sub sequence of this ContentMapController
     */
    public ContentMapController subContent(int startLine, int startColumn, int endLine, int endColumn) {
        Logger.debug("w : startLine=",startLine,",startColumn=",startColumn,",endLine=",endLine,",endColumn=",endColumn);
        ContentMapController c = new ContentMapController();
        c.setUndoEnabled(false);
        if (startLine == endLine) {
            c.insert(0, 0, lines.get(startLine).subSequence(startColumn, endColumn));
        } else if (startLine < endLine) {
            c.insert(0, 0, lines.get(startLine).subSequence(startColumn, lines.get(startLine).length()));
            for (int i = startLine + 1; i < endLine; i++) {
                c.lines.add(new ContentLineController(lines.get(i)));
                c.model.textLength += lines.get(i).length() + 1;
            }
            ContentLineController end = lines.get(endLine);
            c.lines.add(new ContentLineController().insert(0, end, 0, endColumn));
            c.model.textLength += endColumn + 1;
        } else {
            throw new IllegalArgumentException("start > end");
        }
        c.setUndoEnabled(true);
        return c;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (anotherObject instanceof ContentMapController) {
            ContentMapController content = (ContentMapController) anotherObject;
            if (content.getLineCount() != this.getLineCount()) {
                return false;
            }
            for (int i = 0; i < this.getLineCount(); i++) {
                if (!equals(lines.get(i), content.lines.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (ContentLineController line : lines) {
            if (!first) {
                sb.append('\n');
            } else {
                first = false;
            }
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Get the text in StringBuilder form
     * Used by TextColorProvider
     * This can improve the speed in char getting for tokenizing
     *
     * @return StringBuilder form of ContentMapController
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.ensureCapacity(model.textLength + 10);
        boolean first = true;
        final int lines = getLineCount();
        for (int i = 0; i < lines; i++) {
            ContentLineController line = this.lines.get(i);
            if (!first) {
                sb.append('\n');
            } else {
                first = false;
            }
            line.appendTo(sb);
        }
        return sb;
    }

    public void instanciateCursor() {

    }
    /**
     * Get CursorController for editor (Create if there is not)
     *
     * @return CursorController
     */
    public CursorController getCursor() {
        if (cursor == null) {
            cursor = new CursorController(this,editor);
        }
        return cursor;
    }

    /**
     * Dispatch events to listener before replacement
     */
    private void dispatchBeforeReplace() {
        contentManager.beforeReplace(this);
        if (cursor != null)
            cursor.beforeReplace();
        if (indexer instanceof ContentListener) {
            ((ContentListener) indexer).beforeReplace(this);
        }
        for (ContentListener lis : mListeners) {
            lis.beforeReplace(this);
        }
    }

    /**
     * Dispatch events to listener after deletion
     *
     * @param startLine Start line
     * @param startCol Start Column
     * @param endLine End line
     * @param endCol End column
     * @param text Text deleted
     */
    private void dispatchAfterDelete(int startLine, int startCol, int endLine, int endCol, CharSequence text) {
        contentManager.afterDelete(this, startLine, startCol, endLine, endCol, text);
        if (cursor != null)
            cursor.afterDelete(startLine, startCol, endLine, endCol, text);
        if (indexer instanceof ContentListener) {
            ((ContentListener) indexer).afterDelete(this, startLine, startCol, endLine, endCol, text);
        }
        for (ContentListener lis : mListeners) {
            lis.afterDelete(this, startLine, startCol, endLine, endCol, text);
        }
    }

    /**
     * Dispatch events to listener after insertion
     *
     * @param startLine Start line
     * @param startCol Start Column
     * @param endLine End line
     * @param endCol End column
     * @param text Text deleted
     */
    private void dispatchAfterInsert(int startLine, int startCol, int endLine, int endCol, CharSequence text) {
        contentManager.afterInsert(this, startLine, startCol, endLine, endCol, text);
        if (cursor != null)
            cursor.afterInsert(startLine, startCol, endLine, endCol, text);
        if (indexer instanceof ContentListener) {
            ((ContentListener) indexer).afterInsert(this, startLine, startCol, endLine, endCol, text);
        }
        for (ContentListener lis : mListeners) {
            lis.afterInsert(this, startLine, startCol, endLine, endCol, text);
        }
    }

    /**
     * Check whether the index is valid
     *
     * @param index Index to check
     */
    public void checkIndex(int index) {
        if (index > length()) {
            throw new StringIndexOutOfBoundsException("Index " + index + " out of bounds. length:" + length());
        }
    }

    /**
     * Check whether the line is valid
     *
     * @param line Line to check
     */
    protected void checkLine(int line) {
        if (line >= getLineCount()) {
            throw new StringIndexOutOfBoundsException("Line " + line + " out of bounds. line count:" + getLineCount());
        }
    }

    /**
     * Check whether the line and column is valid
     *
     * @param line       The line to check
     * @param column     The column to check
     * @param allowEqual Whether allow (column == getColumnCount(line))
     */
    public void checkLineAndColumn(int line, int column, boolean allowEqual) {
        checkLine(line);
        int len = lines.get(line).length();
        if (column > len || (!allowEqual && column == len)) {
            throw new StringIndexOutOfBoundsException(
                    "Column " + column + " out of bounds.line: " + line + " ,column count:" + len);
        }
    }

}
