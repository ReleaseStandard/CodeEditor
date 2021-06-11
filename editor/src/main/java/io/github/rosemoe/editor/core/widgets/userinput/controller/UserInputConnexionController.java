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
package io.github.rosemoe.editor.core.widgets.userinput.controller;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;

import io.github.rosemoe.editor.core.CharPosition;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;
import io.github.rosemoe.editor.core.widgets.userinput.model.UserInputConnexionModel;
import io.github.rosemoe.editor.core.widgets.userinput.view.UserInputConnexionView;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;

import static android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES;

/**
 * Connection between input method and editor
 * with this controller we extract composing* variables from input method
 * Then we inject variable into the editor.
 *
 * @author Rose
 */
public class UserInputConnexionController {

    public UserInputConnexionModel model = new UserInputConnexionModel();
    public final UserInputConnexionView view;


    /**
     * Create a connection for the given editor
     *
     * @param targetView Host editor
     */
    public UserInputConnexionController(CodeEditor targetView) {
        view =  new UserInputConnexionView(targetView, true) {
            @Override
            public void handleComposingRegionUpdate(int line, int startCol, int endCol) {
                model.init(line,startCol,endCol);
            }
            @Override
            public boolean handleComposingText(CharSequence text, int newCursorPosition) {
                //Logs.log("setComposingText: text = " + text + ", newCursorPosition = " + newCursorPosition);
                if (!editor.isEditable() || model.invalid) {
                    return false;
                }
                if (TextUtils.indexOf(text, '\n') != -1) {
                    return false;
                }
                //Log.d(LOG_TAG, "set composing text:text = " + text + ", newCur =" + newCursorPosition);
                if (model.composingLine == -1) {
                    // Create composing info
                    deleteSelected();
                    model.init(getCursor().getLeftLine(),getCursor().getLeftColumn(),text.length());
                    getCursor().onCommitText(text);
                } else {
                    // Already have composing text
                    // Delete first
                    if (model.composingStart != model.composingEnd) {
                        editor.getText().delete(model.composingLine, model.composingStart, model.composingLine, model.composingEnd);
                    }
                    // Reset range
                    model.composingEnd = model.composingStart + text.length();
                    editor.getText().insert(model.composingLine, model.composingStart, text);
                }
                if (text.length() == 0) {
                    finishComposingText();
                    return true;
                }
                return true;
            }
            @Override
            public boolean handleFinishComposingText(boolean isEditable) {
                //Logs.log("Finish composing text");
                if (!editor.isEditable() || model.invalid) {
                    return false;
                }
                model.composingLine = model.composingStart = model.composingEnd = -1;
                return true;
            }

            @Override public boolean isInvalid() {
                return model.invalid;
            }

            @Override
            public void handleCloseConnection() {
                model.composingLine = model.composingEnd = model.composingStart = -1;
            }
            @Override
            public CharSequence handleSelectedText(int right, int left, int flags) {
                return left == right ? null : getTextRegion(left, right, flags);
            }
            @Override
            public CharSequence handleGetTextBeforeCursor(int length, int flags, int start) {
                return getTextRegion(start - length, start, flags);
            }
            @Override
            public CharSequence handleGetTextAfterCursor(int length, int flags, int end) {
                return getTextRegion(end, end + length, flags);
            }
            @Override
            public void handleCommitTextInternal() {
                deleteComposingText();
            }
            @Override
            public boolean handleDeleteSurroudingText(int beforeLength, int afterLength) {
                boolean composing = model.composingLine != -1;
                int composingStart = composing ? getCursor().getIndexer().getCharIndex(model.composingLine, model.composingStart) : 0;
                int composingEnd = composing ? getCursor().getIndexer().getCharIndex(model.composingLine, model.composingEnd) : 0;

                int rangeEnd = getCursor().getLeft();
                int rangeStart = rangeEnd - beforeLength;
                if (rangeStart < 0) {
                    rangeStart = 0;
                }
                editor.getText().delete(rangeStart, rangeEnd);

                if (composing) {
                    int crossStart = Math.max(rangeStart, composingStart);
                    int crossEnd = Math.min(rangeEnd, composingEnd);
                    composingEnd -= Math.max(0, crossEnd - crossStart);
                    int delta = Math.max(0, crossStart - rangeStart);
                    composingEnd -= delta;
                    composingStart -= delta;
                }

                rangeStart = getCursor().getRight();
                rangeEnd = rangeStart + afterLength;
                if (rangeEnd > editor.getText().length()) {
                    rangeEnd = editor.getText().length();
                }
                editor.getText().delete(rangeStart, rangeEnd);

                if (composing) {
                    int crossStart = Math.max(rangeStart, composingStart);
                    int crossEnd = Math.min(rangeEnd, composingEnd);
                    composingEnd -= Math.max(0, crossEnd - crossStart);
                    int delta = Math.max(0, crossStart - rangeStart);
                    composingEnd -= delta;
                    composingStart -= delta;
                }

                if (beforeLength > 0 && afterLength > 0) {
                    endBatchEdit();
                }

                if (composing) {
                    CharPosition start = getCursor().getIndexer().getCharPosition(composingStart);
                    CharPosition end = getCursor().getIndexer().getCharPosition(composingEnd);
                    if (start.line != end.line) {
                        invalid();
                        return false;
                    }
                    if (start.column == end.column) {
                        model.composingLine = -1;
                    } else {
                        model.init(start.line,start.column,end.column);
                    }
                }
                return false;
            }
        };
        model.invalid = false;
    }

    public void invalid() {
        model.invalid();
        view.editor.invalidate();
    }

    /**
     * Reset the state of this connection
     */
    public void reset() {
        model.reset();
    }


    /**
     * Get content region internally
     */
    private CharSequence getTextRegionInternal(int start, int end, int flags) {
        ContentMapController origin = view.editor.getText();
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (start < 0) {
            start = 0;
        }
        if (end > origin.length()) {
            end = origin.length();
        }
        if (end < start) {
            start = end = 0;
        }
        ContentMapController sub = (ContentMapController) origin.subSequence(start, end);
        if (flags == GET_TEXT_WITH_STYLES) {
            sub.beginStreamCharGetting(0);
            SpannableStringBuilder text = new SpannableStringBuilder(sub);
            // Apply composing span
            if (model.composingLine != -1) {
                try {
                    int originalComposingStart = view.getCursor().getIndexer().getCharIndex(model.composingLine, model.composingStart);
                    int originalComposingEnd = view.getCursor().getIndexer().getCharIndex(model.composingLine, model.composingEnd);
                    int transferredStart = originalComposingStart - start;
                    if (transferredStart >= text.length()) {
                        return text;
                    }
                    if (transferredStart < 0) {
                        transferredStart = 0;
                    }
                    int transferredEnd = originalComposingEnd - start;
                    if (transferredEnd <= 0) {
                        return text;
                    }
                    if (transferredEnd >= text.length()) {
                        transferredEnd = text.length();
                    }
                    text.setSpan(Spanned.SPAN_COMPOSING, transferredStart, transferredEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (IndexOutOfBoundsException e) {
                    //ignored
                }
            }
            return text;
        }
        return sub.toString();
    }

    public CharSequence getTextRegion(int start, int end, int flags) {
        try {
            return getTextRegionInternal(start, end, flags);
        } catch (IndexOutOfBoundsException e) {
            Logger.debug( "Failed to get text region for IME", e);
            return "";
        }
    }




    /**
     * Delete composing region
     */
    private void deleteComposingText() {
        if (model.composingLine == -1) {
            return;
        }
        try {
            view.editor.getText().delete(model.composingLine, model.composingStart, model.composingLine, model.composingEnd);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        model.composingLine = model.composingStart = model.composingEnd = -1;
    }

}
