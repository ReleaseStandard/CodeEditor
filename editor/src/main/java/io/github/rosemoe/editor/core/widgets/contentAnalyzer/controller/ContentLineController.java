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

import android.text.GetChars;
import android.text.TextUtils;

import io.github.rosemoe.editor.core.widgets.contentAnalyzer.model.ContentLineModel;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.view.ContentLineView;

/**
 * One line of content in the ContentMapController.
 */
public class ContentLineController implements CharSequence, GetChars {

    public ContentLineModel model = new ContentLineModel();
    public ContentLineView view   = new ContentLineView();

    public ContentLineController() {
        this(true);
    }

    public ContentLineController(CharSequence text) {
        this(true);
        insert(0, text);
    }

    private ContentLineController(boolean extendedInit) {
        model.initialise(extendedInit);
    }



    public int getId() {
        return model.id;
    }

    public void setId(int id) {
        model.id = id;
    }

    public int getWidth() {
        return model.width;
    }

    public void setWidth(int width) {
        model.width = width;
    }

    /**
     * Inserts the specified {@code CharSequence} into this sequence.
     * <p>
     * The characters of the {@code CharSequence} argument are inserted,
     * in order, into this sequence at the indicated offset, moving up
     * any characters originally above that position and increasing the length
     * of this sequence by the length of the argument s.
     * <p>
     * The result of this method is exactly the same as if it were an
     * invocation of this object's
     * {@link #insert(int, CharSequence, int, int) insert}(dstOffset, s, 0, s.length())
     * method.
     *
     * <p>If {@code s} is {@code null}, then the four characters
     * {@code "null"} are inserted into this sequence.
     *
     * @param dstOffset the offset.
     * @param s         the sequence to be inserted
     * @return a reference to this object.
     * @throws IndexOutOfBoundsException if the offset is invalid.
     */
    public ContentLineController insert(int dstOffset, CharSequence s) {
        if (s == null)
            s = "null";
        if (s instanceof String)
            return insert(dstOffset, (String) s);
        return insert(dstOffset, s, 0, s.length());
    }
    public ContentLineController insert(int dstOffset, CharSequence s,
                       int start, int end) {
        model.insert(dstOffset,s,start,end);
        return this;
    }
    /**
     * Removes the characters in a substring of this sequence.
     * The substring begins at the specified {@code start} and extends to
     * the character at index {@code end - 1} or to the end of the
     * sequence if no such character exists. If
     * {@code start} is equal to {@code end}, no changes are made.
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @return This object.
     * @throws StringIndexOutOfBoundsException if {@code start}
     *                                         is negative, greater than {@code length()}, or
     *                                         greater than {@code end}.
     */
    public ContentLineController delete(int start, int end) {
        model.delete(start,end);
        return this;
    }

    public ContentLineController insert(int offset, char c) {
        model.insert(offset,c);
        return this;
    }

    public ContentLineController append(CharSequence s, int start, int end) {
        model.append(s,start,end);
        return this;
    }

    public ContentLineController append(CharSequence text) {
        return insert(model.length, text);
    }

    public int indexOf(CharSequence text, int index) {
        return TextUtils.indexOf(this, text, index);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return ContentLineModel.lastIndexOf(model.value, model.length,
                str.toCharArray(), str.length(), fromIndex);
    }

    @Override
    public int length() {
        return model.length;
    }

    @Override
    public char charAt(int index) {
        model.checkIndex(index);
        return model.value[index];
    }

    @Override
    public ContentLineController subSequence(int start, int end) {
        model.checkIndex(start);
        model.checkIndex(end);
        if (end < start) {
            throw new StringIndexOutOfBoundsException("start is bigger than end");
        }
        char[] newValue = new char[end - start + 16];
        System.arraycopy(model.value, start, newValue, 0, end - start);
        ContentLineController res = new ContentLineController(false);
        res.model.value = newValue;
        res.model.length = end - start;
        return res;
    }

    /**
     * A quick method to append itself to a StringBuilder
     */
    public void appendTo(StringBuilder sb) {
        sb.append(model.value, 0, model.length);
    }

    @Override
    public String toString() {
        return new String(model.value, 0, model.length);
    }

    @Override
    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        if (srcBegin < 0)
            throw new StringIndexOutOfBoundsException(srcBegin);
        if ((srcEnd < 0) || (srcEnd > model.length))
            throw new StringIndexOutOfBoundsException(srcEnd);
        if (srcBegin > srcEnd)
            throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
        System.arraycopy(model.value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
    }

}
