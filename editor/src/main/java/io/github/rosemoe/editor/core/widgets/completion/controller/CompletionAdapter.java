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
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * A class to make custom adapter for auto-completion window
 * @see CompletionAdapter#getItemHeight()
 * @see CompletionAdapter#getView(int, View, ViewGroup, boolean)
 */
public abstract class CompletionAdapter extends BaseAdapter {

    private CompletionWindowController autocompleteWindow;
    private List<CompletionItemController> completeItems;

    /**
     * Called by {@link CompletionWindowController} to attach some arguments
     */
    public void attachAttributes(CompletionWindowController window, List<CompletionItemController> items) {
        autocompleteWindow = window;
        completeItems = items;
    }

    @Override
    public CompletionItemController getItem(int position) {
        return completeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getCount() {
        return completeItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent, position == autocompleteWindow.getCurrentPosition());
    }

    /**
     * Get context from editor
     */
    protected Context getContext() {
        return autocompleteWindow.getContext();
    }

    /**
     * Implementation of this class should provide exact height of its item
     */
    public abstract int getItemHeight();

    /**
     * @see BaseAdapter#getView(int, View, ViewGroup)
     * @param isCurrentCursorPosition Is the {@param position} currently selected
     */
    protected abstract View getView(int position, View convertView, ViewGroup parent, boolean isCurrentCursorPosition);

}
