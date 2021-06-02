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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.rosemoe.editor.mvc.controller.widget.completion.AutoCompleteProviderController;
import io.github.rosemoe.editor.mvc.controller.widget.completion.CompletionItemController;
import io.github.rosemoe.editor.mvc.view.NewlineHandler;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.widget.SymbolPairMatch;

/**
 * Language for editor
 * <p>
 * A Language helps editor to highlight text and provide auto-completion.
 * Implement this interface when you want to add new language support for editor.
 * <p>
 * <strong>NOTE:</strong> A language must not be single instance.
 * One language instance should always serves for only one editor.
 * It means that you should not give a language object to other editor instances
 * after it has been applied to one editor.
 * This is to provide better connection between auto completion provider and code analyzer.
 *
 * @author Rose
 */
public abstract class EditorLanguageController {

    /**
     * Get CodeAnalyzerController of this language object
     *
     * @return CodeAnalyzerController
     */
    public abstract CodeAnalyzerController getAnalyzer();


    public String[] getKeywords() {
        return new String[0];
    }
    public class DefaultAutoComplete implements AutoCompleteProviderController {
        public HashMap<String,CompletionItemController> items = new HashMap<>();
        @Override
        public List<CompletionItemController> getAutoCompleteItems(String prefix, boolean isInCodeBlock, TextAnalyzerView colors, int line) {
            for(String key : getKeywords()) {
                if ( ! items.containsKey(key) ) {
                    items.put(key, new CompletionItemController(key, "key"));
                }
            }
            return (List<CompletionItemController>) items.values();
        }
    }
    public AutoCompleteProviderController getAutoCompleteProvider() {
        return new DefaultAutoComplete();
    }

    /**
     * Called by editor to check whether this is a character for auto completion
     *
     * @param ch Character to check
     * @return Whether is character for auto completion
     */
    public boolean isAutoCompleteChar(char ch) {
        return Character.isLetter(ch);
    }

    /**
     * Get advance for indent
     *
     * @param content ContentController of a line
     * @return Advance space count
     */
    public int getIndentAdvance(String content) {
        return 0;
    }

    /**
     * Whether use tab to format
     *
     * @return Whether use tab
     */
    public boolean useTab() {
        return false;
    }

    /**
     * Format the given content
     *
     * @param text ContentController to format
     * @return Formatted code
     */
    public CharSequence format(CharSequence text) {
        return text;
    }

    /**
     * Returns language specified symbol pairs.
     * The method is called only once when the language is applied.
     */
    public SymbolPairMatch getSymbolPairs() { return new SymbolPairMatch.DefaultSymbolPairs(); }

    /**
     * Get newline handlers of this language.
     * This method is called each time the user presses ENTER key.
     * <p>
     * Pay attention to the performance as this method is called frequently
     *
     * @return NewlineHandlers , maybe null
     */
    public NewlineHandler[] getNewlineHandlers() { return new NewlineHandler[0]; }

}
