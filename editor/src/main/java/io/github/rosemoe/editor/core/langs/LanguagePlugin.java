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
package io.github.rosemoe.editor.core.langs;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.NewlineHandler;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.core.extension.Extension;
import io.github.rosemoe.editor.core.extension.ExtensionChooser;
import io.github.rosemoe.editor.core.widgets.completion.controller.AutoCompleteProviderController;
import io.github.rosemoe.editor.core.widgets.completion.controller.IdentifierAutoComplete;
import io.github.rosemoe.editor.core.widgets.completion.controller.SymbolPairMatch;
import io.github.rosemoe.editor.plugins.Plugin;

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
public abstract class LanguagePlugin extends Extension {

    protected CodeAnalyzer analyzer;

    public LanguagePlugin(CodeEditor editor) {
        super(editor);
    }

    /**
     * Get TRASHCodeAnalyzerController of this language object
     *
     * @return TRASHCodeAnalyzerController
     */
    public CodeAnalyzer getAnalyzer() {
        return analyzer;
    }

    public AutoCompleteProviderController getAutoCompleteProvider() {
        IdentifierAutoComplete autoComplete = new IdentifierAutoComplete();
        autoComplete.setKeywords(new String[0]);
        return autoComplete;
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
     * @param content ContentMapController of a line
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
     * @param text ContentMapController to format
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
