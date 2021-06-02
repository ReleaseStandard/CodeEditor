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
package io.github.rosemoe.editor.langs.universal;

import io.github.rosemoe.editor.mvc.controller.EditorLanguageController;
import io.github.rosemoe.editor.mvc.controller.widget.completion.AutoCompleteProviderController;
import io.github.rosemoe.editor.mvc.controller.CodeAnalyzerController;
import io.github.rosemoe.editor.mvc.controller.widget.completion.IdentifierAutoComplete;
import io.github.rosemoe.editor.langs.internal.MyCharacter;

import static io.github.rosemoe.editor.langs.universal.UniversalTokens.EOF;

/**
 * Universal Language support
 *
 * @author Rose
 */
public class UniversalLanguage extends EditorLanguageController {

    private LanguageDescription mLanguage;
    private UniversalTokenizer tokenizer;
    private UniversalTokenizer tokenizer2;

    public UniversalLanguage(LanguageDescription languageDescription) {
        mLanguage = languageDescription;
        tokenizer = new UniversalTokenizer(mLanguage);
        tokenizer2 = new UniversalTokenizer(mLanguage);
    }

    @Override
    public CodeAnalyzerController getAnalyzer() {
        return new UniversalCodeAnalyzer(mLanguage,tokenizer,tokenizer2);
    }

    @Override
    public AutoCompleteProviderController getAutoCompleteProvider() {
        IdentifierAutoComplete autoComplete = new IdentifierAutoComplete();
        autoComplete.setKeywords(mLanguage.getKeywords());
        return autoComplete;
    }

    @Override
    public boolean isAutoCompleteChar(char ch) {
        return MyCharacter.isJavaIdentifierPart(ch);
    }

    @Override
    public int getIndentAdvance(String content) {
        int advance = 0;
        try {
            tokenizer2.setInput(content);
            UniversalTokens token;
            while ((token = tokenizer2.nextToken()) != EOF) {
                if (token == UniversalTokens.OPERATOR) {
                    advance += mLanguage.getOperatorAdvance(tokenizer.getTokenString().toString());
                }
            }
        } catch (Exception e) {
            advance = 0;
        }
        return Math.max(0, advance);
    }

    @Override
    public boolean useTab() {
        return mLanguage.useTab();
    }
}
