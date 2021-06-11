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

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.model.ContentManagerModel;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.view.ContentManagerView;

/**
 * Helper class for ContentMapController to take down modification
 * As well as provide Undo/Redo actions
 *
 * @author Rose
 */
public final class ContentManagerController implements ContentListener {

    public ContentManagerModel model = new ContentManagerModel();
    public ContentManagerView view   = new ContentManagerView();

    private final ContentMapController mContent;
    private final List<ContentAction> mActionStack;
    private InsertAction mInsertAction;
    private DeleteAction mDeleteAction;

    private int mStackPointer;

    /**
     * Create ContentManagerController with the target content
     *
     * @param content The ContentMapController going to attach
     */
    public ContentManagerController(ContentMapController content) {
        mContent = content;
        mActionStack = new ArrayList<>();
        mInsertAction = null;
        mDeleteAction = null;
        mStackPointer = 0;
    }

    /**
     * Undo on the given ContentMapController
     *
     * @param content Undo Target
     */
    public void undo(ContentMapController content) {
        if (canUndo()) {
            model.mIgnoreModification = true;
            mActionStack.get(mStackPointer - 1).undo(content);
            mStackPointer--;
            model.mIgnoreModification = false;
        }
    }

    /**
     * Redo on the given ContentMapController
     *
     * @param content Redo Target
     */
    public void redo(ContentMapController content) {
        if (canRedo()) {
            model.mIgnoreModification = true;
            mActionStack.get(mStackPointer).redo(content);
            mStackPointer++;
            model.mIgnoreModification = false;
        }
    }

    /**
     * Whether can undo
     *
     * @return Whether can undo
     */
    public boolean canUndo() {
        return model.isUndoEnabled() && (mStackPointer > 0);
    }

    /**
     * Whether can redo
     *
     * @return Whether can redo
     */
    public boolean canRedo() {
        return model.isUndoEnabled() && (mStackPointer < mActionStack.size());
    }

    /**
     * Set whether enable this module
     *
     * @param enabled Enable or disable
     */
    public void setUndoEnabled(boolean enabled) {
        model.mUndoEnabled = enabled;
        if (!enabled) {
            cleanStack();
        }
    }

    /**
     * Set a max stack size for this ContentManagerController
     *
     * @param maxSize max stack size
     */
    public void setMaxUndoStackSize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException(
                    "max size can not be zero or smaller.Did you want to disable undo module by calling set_undoEnabled(false)?");
        }
        model.mMaxStackSize = maxSize;
        cleanStack();
    }

    /**
     * Clean stack after add or state change
     * This is to limit stack size
     */
    private void cleanStack() {
        if (!model.mUndoEnabled) {
            mActionStack.clear();
            mStackPointer = 0;
        } else {
            while (mStackPointer > 1 && mActionStack.size() > model.mMaxStackSize) {
                mActionStack.remove(0);
                mStackPointer--;
            }
        }
    }

    /**
     * Clean the stack before pushing
     * If we are not at the end(Undo action executed),remove those actions
     */
    private void cleanBeforePush() {
        while (mStackPointer < mActionStack.size()) {
            mActionStack.remove(mActionStack.size() - 1);
        }
    }

    /**
     * Push a new {@link ContentAction} to stack
     * It will merge actions if possible
     *
     * @param action New {@link ContentAction}
     */
    private void pushAction(ContentAction action) {
        if (!model.isUndoEnabled()) {
            return;
        }
        cleanBeforePush();
        if (mContent.isInBatchEdit()) {
            if (mActionStack.isEmpty()) {
                MultiAction a = new MultiAction();
                a.addAction(action);
                mActionStack.add(a);
                mStackPointer++;
            } else {
                ContentAction a = mActionStack.get(mActionStack.size() - 1);
                if (a instanceof MultiAction) {
                    MultiAction ac = (MultiAction) a;
                    ac.addAction(action);
                } else {
                    MultiAction ac = new MultiAction();
                    ac.addAction(action);
                    mActionStack.add(ac);
                    mStackPointer++;
                }
            }
        } else {
            if (mActionStack.isEmpty()) {
                mActionStack.add(action);
                mStackPointer++;
            } else {
                ContentAction last = mActionStack.get(mActionStack.size() - 1);
                if (last.canMerge(action)) {
                    last.merge(action);
                } else {
                    mActionStack.add(action);
                    mStackPointer++;
                }
            }
        }
        cleanStack();
    }

    @Override
    public void beforeReplace(ContentMapController content) {
        if (model.mIgnoreModification) {
            return;
        }
        model.mReplaceMark = true;
    }

    @Override
    public void afterInsert(ContentMapController content, int startLine, int startColumn, int endLine, int endColumn,
                            CharSequence insertedContent) {
        if (model.mIgnoreModification) {
            return;
        }
        mInsertAction = new InsertAction();
        mInsertAction.startLine = startLine;
        mInsertAction.startColumn = startColumn;
        mInsertAction.endLine = endLine;
        mInsertAction.endColumn = endColumn;
        mInsertAction.text = insertedContent;
        if (model.mReplaceMark) {
            ReplaceAction rep = new ReplaceAction();
            rep._delete = mDeleteAction;
            rep._insert = mInsertAction;
            pushAction(rep);
        } else {
            pushAction(mInsertAction);
        }
        model.mReplaceMark = false;
    }

    @Override
    public void afterDelete(ContentMapController content, int startLine, int startColumn, int endLine, int endColumn,
                            CharSequence deletedContent) {
        if (model.mIgnoreModification) {
            return;
        }
        mDeleteAction = new DeleteAction();
        mDeleteAction.endColumn = endColumn;
        mDeleteAction.startColumn = startColumn;
        mDeleteAction.endLine = endLine;
        mDeleteAction.startLine = startLine;
        mDeleteAction.text = deletedContent;
        if (!model.mReplaceMark) {
            pushAction(mDeleteAction);
        }
    }

    /**
     * For saving modification better
     *
     * @author Rose
     */
    public interface ContentAction {

        /**
         * Undo this action
         *
         * @param content On the given object
         */
        void undo(ContentMapController content);

        /**
         * Redo this action
         *
         * @param content On the given object
         */
        void redo(ContentMapController content);

        /**
         * Get whether the target action can be merged with this action
         *
         * @param action Target action to merge
         * @return Whether can merge
         */
        boolean canMerge(ContentAction action);

        /**
         * Merge with target action
         *
         * @param action Target action to merge
         */
        void merge(ContentAction action);

    }

    /**
     * Insert action model for ContentManagerController
     *
     * @author Rose
     */
    public static final class InsertAction implements ContentAction {

        public int startLine, endLine, startColumn, endColumn;

        public CharSequence text;

        @Override
        public void undo(ContentMapController content) {
            content.delete(startLine, startColumn, endLine, endColumn);
        }

        @Override
        public void redo(ContentMapController content) {
            content.insert(startLine, startColumn, text);
        }

        @Override
        public boolean canMerge(ContentAction action) {
            if (action instanceof InsertAction) {
                InsertAction ac = (InsertAction) action;
                return (ac.startColumn == endColumn && ac.startLine == endLine && ac.text.length() + text.length() < 10000);
            }
            return false;
        }

        @Override
        public void merge(ContentAction action) {
            if (!canMerge(action)) {
                throw new IllegalArgumentException();
            }
            InsertAction ac = (InsertAction) action;
            this.endColumn = ac.endColumn;
            this.endLine = ac.endLine;
            StringBuilder sb;
            if (text instanceof StringBuilder) {
                sb = (StringBuilder) text;
            } else {
                sb = new StringBuilder(text);
                text = sb;
            }
            sb.append(ac.text);
        }

    }

    /**
     * MultiAction saves several actions for ContentManagerController
     *
     * @author Rose
     */
    public static final class MultiAction implements ContentAction {

        private final List<ContentAction> _actions = new ArrayList<>();

        public void addAction(ContentAction action) {
            if (_actions.isEmpty()) {
                _actions.add(action);
            } else {
                ContentAction last = _actions.get(_actions.size() - 1);
                if (last.canMerge(action)) {
                    last.merge(action);
                } else {
                    _actions.add(action);
                }
            }
        }

        @Override
        public void undo(ContentMapController content) {
            for (int i = _actions.size() - 1; i >= 0; i--) {
                _actions.get(i).undo(content);
            }
        }

        @Override
        public void redo(ContentMapController content) {
            for (int i = 0; i < _actions.size(); i++) {
                _actions.get(i).redo(content);
            }
        }

        @Override
        public boolean canMerge(ContentAction action) {
            return false;
        }

        @Override
        public void merge(ContentAction action) {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * Delete action model for ContentManagerController
     *
     * @author Rose
     */
    public static final class DeleteAction implements ContentAction {

        public int startLine, endLine, startColumn, endColumn;

        public CharSequence text;

        @Override
        public void undo(ContentMapController content) {
            content.insert(startLine, startColumn, text);
        }

        @Override
        public void redo(ContentMapController content) {
            content.delete(startLine, startColumn, endLine, endColumn);
        }

        @Override
        public boolean canMerge(ContentAction action) {
            if (action instanceof DeleteAction) {
                DeleteAction ac = (DeleteAction) action;
                return (ac.endColumn == startColumn && ac.endLine == startLine && ac.text.length() + text.length() < 10000);
            }
            return false;
        }

        @Override
        public void merge(ContentAction action) {
            if (!canMerge(action)) {
                throw new IllegalArgumentException();
            }
            DeleteAction ac = (DeleteAction) action;
            this.startColumn = ac.startColumn;
            this.startLine = ac.startLine;
            StringBuilder sb;
            if (text instanceof StringBuilder) {
                sb = (StringBuilder) text;
            } else {
                sb = new StringBuilder(text);
                text = sb;
            }
            sb.insert(0, ac.text);
        }

    }

    /**
     * Replace action model for ContentManagerController
     *
     * @author Rose
     */
    public static final class ReplaceAction implements ContentAction {

        public InsertAction _insert;
        public DeleteAction _delete;

        @Override
        public void undo(ContentMapController content) {
            _insert.undo(content);
            _delete.undo(content);
        }

        @Override
        public void redo(ContentMapController content) {
            _delete.redo(content);
            _insert.redo(content);
        }

        @Override
        public boolean canMerge(ContentAction action) {
            return false;
        }

        @Override
        public void merge(ContentAction action) {
            throw new UnsupportedOperationException();
        }

    }
}
