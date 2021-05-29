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
package io.github.rosemoe.editor.langs.mksh;

import io.github.rosemoe.editor.interfaces.AutoCompleteProvider;
import io.github.rosemoe.editor.interfaces.CodeAnalyzer;
import io.github.rosemoe.editor.interfaces.EditorLanguage;
import io.github.rosemoe.editor.interfaces.NewlineHandler;
import io.github.rosemoe.editor.langs.IdentifierAutoComplete;
import io.github.rosemoe.editor.widget.SymbolPairMatch;

public class MkshLanguage implements EditorLanguage {

    @Override
    public CodeAnalyzer getAnalyzer() {
        return new MkshAnalyzer();
    }

    @Override
    public AutoCompleteProvider getAutoCompleteProvider() {
        return new IdentifierAutoComplete(MkshLexer.keywords);
    }

    @Override
    public boolean isAutoCompleteChar(char ch) {
        return Character.isLetter(ch);
    }


    public int getIndentAdvance(String content) {
        //This is will be called when a newline is at start of input
        //And you will receive text on current line before cursor
        //The return value is counted as space
        return 0;
    }

    public boolean useTab() {
        //Whether we use tab to indent
        //And how many spaces can tab replace is related to editor's settings
        return false;
    }

    public CharSequence format(CharSequence text) {
        //Format code
        //You should return formatted code
        //If you can not,just return the given text
        return text;
    }

    @Override
    public SymbolPairMatch getSymbolPairs() {
        return null;
    }

    @Override
    public NewlineHandler[] getNewlineHandlers() {
        //This is a handler which is called each time the user enters a single newline character
        //You are able to set a different text instead of "\n" to format text
        return new NewlineHandler[]{};
    }
}