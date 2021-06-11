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
package io.github.rosemoe.editor.core.widgets.completion.controller;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.ColorSchemeController;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.codeanalysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.core.widgets.cursor.controller.CursorController;
import io.github.rosemoe.editor.core.widgets.completion.model.CompletionWindowModel;
import io.github.rosemoe.editor.core.widgets.completion.view.CompleteWindowView;
import io.github.rosemoe.editor.core.CharPosition;
import io.github.rosemoe.editor.core.CodeEditor;

/**
 * Auto complete window for editing code quicker
 *
 * @author Rose
 */
public class CompletionWindowController {

    private final CodeEditor mEditor;

    public CompletionWindowModel    model = new CompletionWindowModel();
    public final CompleteWindowView view;
    private AutoCompleteProviderController mProvider;


    /**
     * Create a panel instance for the given editor
     *
     * @param editor Target editor
     */
    public CompletionWindowController(CodeEditor editor) {
        mEditor = editor;
        view = new CompleteWindowView(editor) {
            @Override
            public void handleShow() {
                if (model.mCancelShowUp) {
                    return;
                }
            }
            @Override
            public void handleCursorSelect(CursorController cursor, int pos) {
                if (!cursor.isSelected()) {
                    CompletionItemController item = ((CompletionAdapter) mListView.getAdapter()).getItem(pos);
                    model.mCancelShowUp = true;
                    mEditor.getText().delete(cursor.getLeftLine(), cursor.getLeftColumn() - model.mLastPrefix.length(), cursor.getLeftLine(), cursor.getLeftColumn());
                    cursor.onCommitText(item.model.commit);
                    if (item.model.cursorOffset != item.model.commit.length()) {
                        int delta = (item.model.commit.length() - item.model.cursorOffset);
                        if (delta != 0) {
                            int newSel = Math.max(mEditor.getCursor().getLeft() - delta, 0);
                            CharPosition charPosition = mEditor.getCursor().getIndexer().getCharPosition(newSel);
                            mEditor.setSelection(charPosition.line, charPosition.column);
                        }
                    }
                    model.mCancelShowUp = false;
                }
            }
        };
        setLoading(true);
        applyColorScheme();
    }

    /**
     * Select current position
     */
    public void select() {
        select(model.mCurrent);
    }
    public void select(int pos) {
        view.select(pos);
    }


    public Context getContext() {
        return mEditor.getContext();
    }

    public int getCurrentPosition() {
        return model.mCurrent;
    }

    /**
     * Set a auto completion items provider
     *
     * @param provider New provider.can not be null
     */
    public void setProvider(AutoCompleteProviderController provider) {
        mProvider = provider;
    }

    /**
     * Apply colors for self
     */
    public void applyColorScheme() {
        ColorSchemeController colors = mEditor.getColorScheme();
        view.applyColorScheme(colors);
    }

    /**
     * Change layout to loading/idle
     *
     * @param state Whether loading
     */
    public void setLoading(boolean state) {
        model.mLoading = state;
        if (state) {
            mEditor.postDelayed(() -> {
                if (model.mLoading) {
                    view.mTip.setVisibility(View.VISIBLE);
                }
            }, 300);
        } else {
            view.mTip.setVisibility(View.GONE);
        }
        //mListView.setVisibility((!state) ? View.VISIBLE : View.GONE);
        //update();
    }

    /**
     * Move selection down
     */
    public void moveDown() {
        if (model.mCurrent + 1 >= view.mListView.getAdapter().getCount()) {
            return;
        }
        model.mCurrent++;
        ((CompletionAdapter) view.mListView.getAdapter()).notifyDataSetChanged();
        ensurePosition();
    }

    /**
     * Move selection up
     */
    public void moveUp() {
        if (model.mCurrent - 1 < 0) {
            return;
        }
        model.mCurrent--;
        ((CompletionAdapter) view.mListView.getAdapter()).notifyDataSetChanged();
        ensurePosition();
    }

    /**
     * Make current selection visible
     */
    private void ensurePosition() {
        view.mListView.setSelection(model.mCurrent);
    }



    /**
     * Get prefix set
     *
     * @return The previous prefix
     */
    public String getPrefix() {
        return model.mLastPrefix;
    }

    /**
     * Set prefix for auto complete analysis
     *
     * @param prefix The user's input code's prefix
     */
    public void setPrefix(String prefix) {
        if (model.mCancelShowUp) {
            return;
        }
        setLoading(true);
        model.mLastPrefix = prefix;
        model.mRequestTime = System.currentTimeMillis();
        new MatchThread(model.mRequestTime, prefix).start();
    }

    public void setMaxHeight(int height) {
        model.mMaxHeight = height;
    }

    /**
     * Display result of analysis
     *
     * @param results     Items of analysis
     * @param requestTime The time that this thread starts
     */
    private void displayResults(final List<CompletionItemController> results, long requestTime) {
        if (model.mRequestTime != requestTime) {
            return;
        }
        mEditor.post(() -> {
            setLoading(false);
            if (results == null || results.isEmpty()) {
                view.hide();
                return;
            }
            view.mAdapter.attachAttributes(this, results);
            view.mListView.setAdapter(view.mAdapter);
            model.mCurrent = 0;
            float newHeight = mEditor.getDpUnit() * 30 * results.size();
            if (view.isShowing()) {
                view.update(view.getWidth(), (int) Math.min(newHeight, model.mMaxHeight));
            }
        });
    }

    /**
     * Analysis thread
     *
     * @author Rose
     */
    private class MatchThread extends Thread {

        private final long mTime;
        private final String mPrefix;
        private final boolean mInner;
        private final CodeAnalyzerResultColor colorResult;
        private final int mLine;
        private final AutoCompleteProviderController mLocalProvider = mProvider;

        public MatchThread(long requestTime, String prefix) {
            mTime = requestTime;
            mPrefix = prefix;
            // get production ready result from here
            colorResult = mEditor.getTextAnalyzeResult();
            mLine = mEditor.getCursor().getLeftLine();
            mInner = (!mEditor.isHighlightCurrentBlock()) || (mEditor.getBlockIndex() != -1);
        }

        @Override
        public void run() {
            try {
                displayResults(mLocalProvider.getAutoCompleteItems(mPrefix, mInner, colorResult, mLine), mTime);
            } catch (Exception e) {
                e.printStackTrace();
                displayResults(new ArrayList<>(), mTime);
            }
        }


    }

    public void updatePosition(float panelX, float panelY, float restY, int width, float mDpUnit) {
        view.setExtendedX(panelX);
        view.setExtendedY(panelY);
        if (view.getWidth() < 500 * mDpUnit) {
            //Open center mode
            view.setWidth(width * 7 / 8);
            view.setExtendedX(width / 8f / 2f);
        } else {
            //Follow cursor mode
            view.setWidth(width / 3);
        }
        if (!view.isShowing()) {
            view.setHeight((int) restY);
        }
        setMaxHeight((int) restY);
        view.updatePosition();
    }

}

