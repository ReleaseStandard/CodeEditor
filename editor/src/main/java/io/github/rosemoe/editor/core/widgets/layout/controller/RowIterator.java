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
package io.github.rosemoe.editor.core.widgets.layout.controller;

import java.util.NoSuchElementException;

/**
 * RowModel iterator.
 * This iterator is able to return a series of RowModel objects linearly
 * Editor uses this to get information of rows and paint them accordingly
 *
 * @author Rose
 */
public interface RowIterator {

    /**
     * Return next RowModel object
     * <p>
     * The result should not be stored, because implementing classes will always return the same
     * object due to performance
     *
     * @return RowModel object contains the information about a row
     * @throws NoSuchElementException If no more row available
     */
    RowController next();

    /**
     * Whether there is more RowModel object
     *
     * @return Whether more row available
     */
    boolean hasNext();

}
