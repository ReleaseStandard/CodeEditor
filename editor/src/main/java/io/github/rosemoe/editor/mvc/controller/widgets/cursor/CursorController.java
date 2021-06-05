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
package io.github.rosemoe.editor.mvc.controller.widgets.cursor;

import android.util.Log;

import io.github.rosemoe.editor.mvc.controller.LanguageController;
import io.github.rosemoe.editor.mvc.controller.widgets.Widget;
import io.github.rosemoe.editor.mvc.model.widget.cursor.CursorModel;
import io.github.rosemoe.editor.mvc.view.widget.cursor.CursorView;
import io.github.rosemoe.editor.processor.content.indexer.CachedIndexer;
import io.github.rosemoe.editor.mvc.controller.content.ContentMapController;
import io.github.rosemoe.editor.widget.CodeEditor;

/**
 * @author Rose
 * Warning:The cursor position will update automatically when the content has been changed by other way
 */
public final class CursorController extends Widget {

    private final ContentMapController mContent;
    private final CachedIndexer mIndexer;
    private LanguageController mLanguage;
    public CursorModel model = new CursorModel();
	public final CursorView view;
    public CursorBlinkController blink;        // Manage cursor blink effect
    public final CodeEditor editor;
    /**
     * Create a new CursorController for ContentMapController
     *
     * @param content Target content
     */
    public CursorController(ContentMapController content, CodeEditor editor) {
        mContent = content;
        mIndexer = new CachedIndexer(content);
        view     = new CursorView();
        this.editor = editor;
        blink = new CursorBlinkController(editor, CursorBlinkController.DEFAULT_CURSOR_BLINK_PERIOD);
    }

    /**
     * Whether the given character is a white space character
     *
     * @param c Character to check
     * @return Result whether a space char
     */
    protected static boolean isWhitespace(char c) {
        return (c == '\t' || c == ' ');
    }

    /**
     * Whether the char is a emoji
     *
     * @param ch Character to check
     * @return Whether the char is a emoji
     */
    private static boolean isEmoji(char ch) {
        return ch == 0xd83c || ch == 0xd83d;
    }

    /**
     * Make left and right cursor on the given position
     *
     * @param line   The line position
     * @param column The column position
     */
    public void set(int line, int column) {
        setLeft(line, column);
        setRight(line, column);
    }

    /**
     * Make left cursor on the given position
     *
     * @param line   The line position
     * @param column The column position
     */
    public void setLeft(int line, int column) {
        model.mLeft = mIndexer.getCharPosition(line, column).fromThis();
    }

    /**
     * Make right cursor on the given position
     *
     * @param line   The line position
     * @param column The column position
     */
    public void setRight(int line, int column) {
        model.mRight = mIndexer.getCharPosition(line, column).fromThis();
    }

    /**
     * Get the left cursor line
     *
     * @return line of left cursor
     */
    public int getLeftLine() {
        return model.mLeft.getLine();
    }

    /**
     * Get the left cursor column
     *
     * @return column of left cursor
     */
    public int getLeftColumn() {
        return model.mLeft.getColumn();
    }

    /**
     * Get the right cursor line
     *
     * @return line of right cursor
     */
    public int getRightLine() {
        return model.mRight.getLine();
    }

    /**
     * Get the right cursor column
     *
     * @return column of right cursor
     */
    public int getRightColumn() {
        return model.mRight.getColumn();
    }

    /**
     * Whether the given position is in selected region
     *
     * @param line   The line to query
     * @param column The column to query
     * @return Whether is in selected region
     */
    public boolean isInSelectedRegion(int line, int column) {
        if (line >= getLeftLine() && line <= getRightLine()) {
            boolean yes = true;
            if (line == getLeftLine()) {
                yes = column >= getLeftColumn();
            }
            if (line == getRightLine()) {
                yes = yes && column < getRightColumn();
            }
            return yes;
        }
        return false;
    }

    /**
     * Get the left cursor index
     *
     * @return index of left cursor
     */
    public int getLeft() {
        return model.mLeft.index;
    }

    /**
     * Get the right cursor index
     *
     * @return index of right cursor
     */
    public int getRight() {
        return model.mRight.index;
    }

    /**
     * Notify the Indexer to update its cache for current display position
     *
     * This will make querying actions quicker
     *
     * Especially when the editor user want to set a new cursor position after scrolling long time
     *
     * @param line First visible line
     */
    public void updateCache(int line) {
        mIndexer.getCharIndex(line, 0);
    }

    /**
     * Get the using Indexer object
     *
     * @return Using Indexer
     */
    public CachedIndexer getIndexer() {
        return mIndexer;
    }

    /**
     * Get whether text is selected
     *
     * @return Whether selected
     */
    public boolean isSelected() {
        return model.mLeft.index != model.mRight.index;
    }

    /**
     * Returns whether auto indent is enabled
     *
     * @return Enabled or disabled
     */
    public boolean isAutoIndent() {
        return model.mAutoIndentEnabled;
    }

    /**
     * Enable or disable auto indent when insert text through CursorController
     *
     * @param enabled Auto Indent state
     */
    public void setAutoIndent(boolean enabled) {
        model.mAutoIndentEnabled = enabled;
    }

    /**
     * Set language for auto indent
     *
     * @param lang The target language
     */
    public void setLanguage(LanguageController lang) {
        mLanguage = lang;
    }

    /**
     * Set tab width for auto indent
     *
     * @param width tab width
     */
    public void setTabWidth(int width) {
        model.mTabWidth = width;
    }

    public void onCommitText(CharSequence text) {
        onCommitText(text, true);
    }

    /**
     * Commit text at current state
     *
     * @param text Text commit by InputConnection
     */
    public void onCommitText(CharSequence text, boolean applyAutoIndent) {
        if (isSelected()) {
            mContent.replace(getLeftLine(), getLeftColumn(), getRightLine(), getRightColumn(), text);
        } else {
            if (model.mAutoIndentEnabled && text.length() != 0 && applyAutoIndent) {
                char first = text.charAt(0);
                if (first == '\n') {
                    String line = mContent.getLineString(getLeftLine());
                    int p = 0, count = 0;
                    while (p < getLeftColumn()) {
                        if (isWhitespace(line.charAt(p))) {
                            if (line.charAt(p) == '\t') {
                                count += model.mTabWidth;
                            } else {
                                count++;
                            }
                            p++;
                        } else {
                            break;
                        }
                    }
                    String sub = line.substring(0, getLeftColumn());
                    try {
                        count += mLanguage.getIndentAdvance(sub);
                    } catch (Exception e) {
                        Log.w("EditorCursor", "Language object error", e);
                    }
                    StringBuilder sb = new StringBuilder(text);
                    sb.insert(1, createIndent(count));
                    text = sb;
                }
            }
            mContent.insert(getLeftLine(), getLeftColumn(), text);
        }
    }

    /**
     * Create indent space
     *
     * @param p Target width effect
     * @return Generated space string
     */
    private String createIndent(int p) {
        int tab = 0;
        int space;
        if (mLanguage.useTab()) {
            tab = p / model.mTabWidth;
            space = p % model.mTabWidth;
        } else {
            space = p;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tab; i++) {
            s.append('\t');
        }
        for (int i = 0; i < space; i++) {
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * Handle delete submit by InputConnection
     */
    public void onDeleteKeyPressed() {
        if (isSelected()) {
            mContent.delete(getLeftLine(), getLeftColumn(), getRightLine(), getRightColumn());
        } else {
            int col = getLeftColumn(), len = 1;
            //Do not put cursor inside a emotion character
            if (col > 1) {
                char before = mContent.charAt(getLeftLine(), col - 2);
                if (isEmoji(before)) {
                    len = 2;
                }
            }
            mContent.delete(getLeftLine(), getLeftColumn() - len, getLeftLine(), getLeftColumn());
        }
    }

    /**
     * Internal call back before insertion
     *
     * @param startLine   Start line
     * @param startColumn Start column
     */
    public void beforeInsert(int startLine, int startColumn) {
        model.cache0 = mIndexer.getCharPosition(startLine, startColumn).fromThis();
    }

    /**
     * Internal call back before deletion
     *
     * @param startLine   Start line
     * @param startColumn Start column
     * @param endLine     End line
     * @param endColumn   End column
     */
    public void beforeDelete(int startLine, int startColumn, int endLine, int endColumn) {
        model.cache1 = mIndexer.getCharPosition(startLine, startColumn).fromThis();
        model.cache2 = mIndexer.getCharPosition(endLine, endColumn).fromThis();
    }

    /**
     * Internal call back before replace
     */
    public void beforeReplace() {
        mIndexer.beforeReplace(mContent);
    }

    /**
     * Internal call back after insertion
     *
     * @param startLine       Start line
     * @param startColumn     Start column
     * @param endLine         End line
     * @param endColumn       End column
     * @param insertedContent Inserted content
     */
    public void afterInsert(int startLine, int startColumn, int endLine, int endColumn,
                     CharSequence insertedContent) {
        mIndexer.afterInsert(mContent, startLine, startColumn, endLine, endColumn, insertedContent);
        int beginIdx = model.cache0.getIndex();
        if (getLeft() >= beginIdx) {
            model.mLeft = mIndexer.getCharPosition(getLeft() + insertedContent.length()).fromThis();
        }
        if (getRight() >= beginIdx) {
            model.mRight = mIndexer.getCharPosition(getRight() + insertedContent.length()).fromThis();
        }
    }

    /**
     * Internal call back
     *
     * @param startLine      Start line
     * @param startColumn    Start column
     * @param endLine        End line
     * @param endColumn      End column
     * @param deletedContent Deleted content
     */
    public void afterDelete(int startLine, int startColumn, int endLine, int endColumn,
                     CharSequence deletedContent) {
        mIndexer.afterDelete(mContent, startLine, startColumn, endLine, endColumn, deletedContent);
        int beginIdx = model.cache1.getIndex();
        int endIdx = model.cache2.getIndex();
        int left = getLeft();
        int right = getRight();
        if (beginIdx > right) {
            return;
        }
        if (endIdx <= left) {
            model.mLeft = mIndexer.getCharPosition(left - (endIdx - beginIdx)).fromThis();
            model.mRight = mIndexer.getCharPosition(right - (endIdx - beginIdx)).fromThis();
        } else if (/* endIdx > left && */ endIdx < right) {
            if (beginIdx <= left) {
                model.mLeft = mIndexer.getCharPosition(beginIdx).fromThis();
                model.mRight = mIndexer.getCharPosition(right - (endIdx - Math.max(beginIdx, left))).fromThis();
            } else {
                model.mRight = mIndexer.getCharPosition(right - (endIdx - beginIdx)).fromThis();
            }
        } else {
            if (beginIdx <= left) {
                model.mLeft = mIndexer.getCharPosition(beginIdx).fromThis();
                model.mRight = model.mLeft.fromThis();
            } else {
                model.mRight = mIndexer.getCharPosition(left + (right - beginIdx)).fromThis();
            }
        }
		/*
        if(beginIdx <= left) {
            _left = _indexer.getCharPosition(left - (Math.min(endIdx, left) - beginIdx)).fromThis();
            int region = endIdx - Math.max(beginIdx, left);
            int region2 = right - left;
            int len = region2 - region;
            if(len > 0) {
                _right = _indexer.getCharPosition(getLeft() + len).fromThis();
            }else {
                _right = _left.fromThis();
            }
        }else {
            int len = Math.max(0,Math.min(endIdx, right) - beginIdx);
            if(len > 0) {
                _right = _indexer.getCharPosition(right - len).fromThis();
            }
        }*/
    }

    /**
     * Set cursor blinking period
     * If zero or negative period is passed, the cursor will always be shown.
     *
     * @param period The period time of cursor blinking
     */
    public void setBlinkPeriod(int period) {
        if (blink == null) {
            blink = new CursorBlinkController(editor, period);
        } else {
            int before = blink.model.period;
            blink.model.period = period;
            if (before <= 0 && blink.model.valid && blink.view.editor.isAttachedToWindow()) {
                blink.view.editor.post(blink);
            }
        }
    }
    /**
     * Set whether blinking is enabled on the cursor.
     * @param state
     */
    public void setBlinkEnabled(boolean state) {
        if ( blink != null ) {
            blink.setEnabled(state);
        }
    }
    @Override
    public void setEnabled(boolean state) {
        super.setEnabled(state);
        if ( blink != null ) {
            blink.setEnabled(state);
        }
    }

}
