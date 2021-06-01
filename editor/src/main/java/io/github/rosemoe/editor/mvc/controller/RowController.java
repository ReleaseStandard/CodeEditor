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
package io.github.rosemoe.editor.mvc.controller;

import io.github.rosemoe.editor.mvc.model.RowModel;
import io.github.rosemoe.editor.mvc.model.widget.layout.WordwrapModel;

public class RowController {
    public RowModel model = new RowModel();
    public void initFromRegion(WordwrapModel.RowRegion currentRegion,WordwrapModel.RowRegion previousRegion,int currentRow) {
        model.lineIndex = currentRegion.line;
        model.startColumn = currentRegion.startColumn;
        model.endColumn = currentRegion.endColumn;
        model.isLeadingRow = currentRow <= 0 || previousRegion.line != currentRegion.line;
    }
}
