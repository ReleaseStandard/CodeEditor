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
package io.github.rosemoe.editor.core.widgets.userinput.view;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController;
import io.github.rosemoe.editor.core.CharPosition;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.widgets.completion.controller.SymbolPairMatch;

public class UserInputConnexionView extends BaseInputConnection {
    public final CodeEditor editor;
    public final static int TEXT_LENGTH_LIMIT = 1000000;

    public UserInputConnexionView(View targetView, boolean fullEditor) {
        super(targetView, fullEditor);
        editor = (CodeEditor) targetView;
    }

    @Override
    public synchronized void closeConnection() {
        //Logs.log("close connection");
        super.closeConnection();
        ContentMapController content = editor.getText();
        while (content.isInBatchEdit()) {
            content.endBatchEdit();
        }
        handleCloseConnection();
        editor.onCloseConnection();
    }

    /**
     * Private use.
     * Get the CursorController of ContentMapController displaying by Editor
     *
     * @return CursorController
     */
    public CursorController getCursor() {
        return editor.getCursor();
    }

    @Override
    public int getCursorCapsMode(int reqModes) {
        return TextUtils.getCapsMode(editor.getText(), getCursor().getLeft(), reqModes);
    }

    public CharSequence handleSelectedText(int right, int left, int flags) { return ""; }
    @Override
    public CharSequence getSelectedText(int flags) {
        //Logs.log("getSelectedText()");
        //This text should be limited because when the user try to select all text
        //it can be quite large text and costs time, which will finally cause ANR
        int left = getCursor().getLeft();
        int right = getCursor().getRight();
        if (right - left > TEXT_LENGTH_LIMIT) {
            right = left + TEXT_LENGTH_LIMIT;
        }
        return handleSelectedText(right,left,flags);
    }
    @Override
    public boolean clearMetaKeyStates(int states) {
        editor.mKeyMetaStates.clearMetaStates(states);
        return true;
    }
    /**
     * Perform enter key pressed
     */
    private void sendEnterKeyEvent() {
        long eventTime = SystemClock.uptimeMillis();
        sendKeyEvent(new KeyEvent(eventTime, eventTime,
                KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0, 0,
                KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE));
        sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), eventTime,
                KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER, 0, 0,
                KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE));
    }
    public CharSequence handleGetTextBeforeCursor(int length, int flags, int start) { return ""; }
    @Override
    public CharSequence getTextBeforeCursor(int length, int flags) {
        Logger.debug("length=",length,",flags=",flags);
        int start = getCursor().getLeft();
        return handleSelectedText(length,flags,start);
    }

    public CharSequence handleGetTextAfterCursor(int length, int flags, int end) { return "" ;}
    @Override
    public CharSequence getTextAfterCursor(int length, int flags) {
        Logger.debug("length=",length,",flags=",flags);
        int end = getCursor().getRight();
        return handleGetTextAfterCursor(length,flags,end);
    }

    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        //Logs.log("Commit text: text = " + text + ", newCursorPosition = " + newCursorPosition);
        //Log.d(LOG_TAG, "commit text:text = " + text + ", newCur = " + newCursorPosition);
        if (!editor.isEditable() || isInvalid()) {
            return false;
        }
        if (text.equals("\n")) {
            sendEnterKeyEvent();
            return true;
        }
        commitTextInternal(text, true);
        return true;
    }

    public boolean handleDeleteSurroudingText(int beforeLength, int afterLength) { return false; }
    @Override
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        //Logs.log("deleteSurroundingText: before = " + beforeLength + ", after = " + afterLength);
        if (!editor.isEditable() || isInvalid()) {
            return false;
        }
        if (beforeLength < 0 || afterLength < 0) {
            return false;
        }

        // Start a batch edit when the operation can not be finished by one call to delete()
        if (beforeLength > 0 && afterLength > 0) {
            beginBatchEdit();
        }

        return handleDeleteSurroudingText(beforeLength,afterLength);
    }

    @Override
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        // Unsupported operation
        // According to document, we should return false
        return false;
    }

    @Override
    public synchronized boolean beginBatchEdit() {
        //Logs.log("beginBatchEdit()");
        return editor.getText().beginBatchEdit();
    }

    @Override
    public synchronized boolean endBatchEdit() {
        //Logs.log("endBatchEdit()");
        boolean inBatch = editor.getText().endBatchEdit();
        if (!inBatch) {
            editor.updateSelection();
        }
        return inBatch;
    }

    @Override
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        return handleComposingText(text, newCursorPosition);
    }

    @Override
    public boolean finishComposingText() {
        boolean rv = handleFinishComposingText(editor.isEditable());
        editor.invalidate();
        return rv;
    }

    @Override
    public boolean setSelection(int start, int end) {
        //Logs.log("set selection:" + start + ".." + end);
        //Log.d(LOG_TAG, " set selection:" + start + ".." + end);
        if (!editor.isEditable() || isInvalid()) {
            return false;
        }
        start = getWrappedIndex(start);
        end = getWrappedIndex(end);
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        editor.getAutoCompleteWindow().view.hide();
        ContentMapController content = editor.getText();
        CharPosition startPos = content.getIndexer().getCharPosition(start);
        CharPosition endPos = content.getIndexer().getCharPosition(end);
        editor.setSelectionRegion(startPos.line, startPos.column, endPos.line, endPos.column, false);
        return true;
    }

    public boolean handleComposingText(CharSequence text, int newCursorPosition) { return false; }
    public void handleComposingRegionUpdate(int line, int startCol, int endCol) { }
    public boolean handleFinishComposingText(boolean isEditable) { return false; }
    public void handleCloseConnection() { }
    public void handleCommitTextInternal() { }
    public boolean isInvalid() {
        return false;
    }
    public boolean handlePerformContextMenuAction(int id) {
        return false;
    }
    @Override
    public boolean setComposingRegion(int start, int end) {
        //Logs.log("set composing region:" + start + ".." + end);
        //Log.d(LOG_TAG, "set composing region:" + start + ".." + end);
        if (!editor.isEditable() || isInvalid()) {
            return false;
        }
        try {
            if (start > end) {
                int tmp = start;
                start = end;
                end = tmp;
            }
            if (start < 0) {
                start = 0;
            }
            ContentMapController content = editor.getText();
            if (end > content.length()) {
                end = content.length();
            }
            CharPosition startPos = content.getIndexer().getCharPosition(start);
            CharPosition endPos = content.getIndexer().getCharPosition(end);
            if (startPos.line != endPos.line) {
                editor.restartInput();
                return false;
            }
            handleComposingRegionUpdate(startPos.line,startPos.column,endPos.column);

            editor.invalidate();
        } catch (IndexOutOfBoundsException e) {
            Logger.debug("set composing region for IME failed", e);
            return false;
        }
        return true;
    }

    /**
     * This will select the right context menu action in response to user imput.
     * @param id
     * @return
     */
    @Override
    public boolean performContextMenuAction(int id) {
        switch (id) {
            case android.R.id.selectAll:
                editor.selectAll();
                return true;
            case android.R.id.cut:
                editor.copyText();
                if (getCursor().isSelected()) {
                    getCursor().onDeleteKeyPressed();
                }
                return true;
            case android.R.id.paste:
            case android.R.id.pasteAsPlainText:
                editor.pasteText();
                return true;
            case android.R.id.copy:
                editor.copyText();
                return true;
            case android.R.id.undo:
                editor.undo();
                return true;
            case android.R.id.redo:
                editor.redo();
                return true;
        }
        return false;
    }

    @Override
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        //Logs.log("Receive update cursor anchor from input method");
        editor.updateCursorAnchor();
        return true;
    }

    @Override
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        //Logs.log("Get extracted text from input method");
        if ((flags & GET_EXTRACTED_TEXT_MONITOR) != 0) {
            //Logs.log("Monitor flag is set");
            editor.setExtracting(request);
        }

        return editor.extractText(request);
    }

    @Override
    public boolean reportFullscreenMode(boolean enabled) {
        return false;
    }

    public void commitTextInternal(CharSequence text, boolean applyAutoIndent) {
        // NOTE: Text styles are ignored by editor
        //Remove composing text first if there is
        handleCommitTextInternal();
        // Replace text
        SymbolPairMatch.Replacement replacement = null;
        if (text.length() == 1 && editor.isSymbolCompletionEnabled()) {
            replacement = editor.mLanguageSymbolPairs.getCompletion(text.charAt(0));
        }
        // newCursorPosition ignored
        // Call onCommitText() can make auto indent and delete text selected automatically
        if (replacement == null || replacement == SymbolPairMatch.Replacement.NO_REPLACEMENT) {
            getCursor().onCommitText(text, applyAutoIndent);
        } else {
            getCursor().onCommitText(replacement.text, applyAutoIndent);
            int delta = (replacement.text.length() - replacement.selection);
            if (delta != 0) {
                int newSel = Math.max(getCursor().getLeft() - delta, 0);
                CharPosition charPosition = getCursor().getIndexer().getCharPosition(newSel);
                editor.setSelection(charPosition.line, charPosition.column);
            }
        }
    }
    protected void deleteSelected() {
        if (getCursor().isSelected()) {
            // Delete selected text
            getCursor().onDeleteKeyPressed();
        }
    }
    private int getWrappedIndex(int index) {
        if (index < 0) {
            return 0;
        }
        if (index > editor.getText().length()) {
            return editor.getText().length();
        }
        return index;
    }
}
