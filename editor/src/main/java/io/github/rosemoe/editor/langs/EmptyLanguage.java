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
package io.github.rosemoe.editor.langs;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.mvc.controller.widget.completion.AutoCompleteProviderController;
import io.github.rosemoe.editor.mvc.controller.EditorLanguageController;
import io.github.rosemoe.editor.mvc.controller.CodeAnalyzerController;
import io.github.rosemoe.editor.mvc.view.NewlineHandler;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.mvc.controller.widget.completion.CompletionItemController;
import io.github.rosemoe.editor.mvc.controller.TextAnalyzerController.AnalyzeThread.Delegate;
import io.github.rosemoe.editor.widget.SymbolPairMatch;

/**
 * Empty language without any effect
 *
 * @author Rose
 */
public class EmptyLanguage extends EditorLanguageController {

    @Override
    public CodeAnalyzerController getAnalyzer() {
        return new EmptyCodeAnalyzer();
    }

    @Override
    public AutoCompleteProviderController getAutoCompleteProvider() {
        return new EmptyAutoCompleteProvider();
    }

    @Override
    public boolean isAutoCompleteChar(char ch) {
        return false;
    }

    public static class EmptyAutoCompleteProvider implements AutoCompleteProviderController {

        @Override
        public List<CompletionItemController> getAutoCompleteItems(String prefix, boolean isInCodeBlock, TextAnalyzerView colors, int line) {
            return new ArrayList<>();
        }

    }

    private static class EmptyCodeAnalyzer extends CodeAnalyzerController {

        @Override
        public void analyze(CharSequence content, TextAnalyzerView colors, Delegate delegate) {
            colors.addNormalIfNull();
        }

    }
}

