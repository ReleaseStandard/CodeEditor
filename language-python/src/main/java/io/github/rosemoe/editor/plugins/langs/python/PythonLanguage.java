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
package io.github.rosemoe.editor.plugins.langs.python;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.widgets.completion.controller.AutoCompleteProviderController;
import io.github.rosemoe.editor.core.langs.LanguagePlugin;
import io.github.rosemoe.editor.core.NewlineHandler;
import io.github.rosemoe.editor.core.widgets.completion.controller.IdentifierAutoComplete;
import io.github.rosemoe.editor.core.langs.helpers.MyCharacter;
import io.github.rosemoe.editor.core.langs.helpers.TextUtils;

public class PythonLanguage extends LanguagePlugin {

    public PythonLanguage(CodeEditor editor) {
        super(editor);
        name = "Python";
        description = "Python parsing";
        analyzer = new PythonCodeAnalyzer();
    }


    @Override
    public AutoCompleteProviderController getAutoCompleteProvider() {
        return new IdentifierAutoComplete(new String[]{
                "and", "as", "assert", "break", "class", "continue", "def",
                "del", "elif", "else", "except", "exec", "finally", "for",
                "from", "global", "if", "import", "in", "is", "lambda",
                "not", "or", "pass", "print", "raise", "return", "try",
                "while", "with", "yield"
        });
    }

    @Override
    public boolean isAutoCompleteChar(char ch) {
        return MyCharacter.isJavaIdentifierPart(ch);
    }

    @Override
    public int getIndentAdvance(String content) {
        Token token = null;
        int advance = 0;
        boolean openBlock = false;
        try {
            PythonLexer lexer = new PythonLexer(CharStreams.fromReader(new StringReader(content)));
            while (((token = lexer.nextToken()) != null && token.getType() != token.EOF)) {
                switch (token.getType()) {
                    case PythonLexer.CLASS:
                    case PythonLexer.DEF:
                    case PythonLexer.IF:
                    case PythonLexer.ELIF:
                    case PythonLexer.FOR:
                    case PythonLexer.WHILE:
                    case PythonLexer.TRY:
                    case PythonLexer.EXCEPT:
                        openBlock = !openBlock;
                        break;
                    case PythonLexer.COLON:
                        advance++;
                        break;
                    case PythonLexer.CONTINUE:
                    case PythonLexer.BREAK:
                        openBlock = !openBlock;
                        advance--;
                        break;
                }
            }
            advance = Math.max(0, advance);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return openBlock ? advance * 4 : 0;
    }

    @Override
    public boolean useTab() {
        return true;
    }


    private NewlineHandler[] newlineHandlers = new NewlineHandler[]{new ColonHandler()};

    @Override
    public NewlineHandler[] getNewlineHandlers() {
        return newlineHandlers;
    }

    class ColonHandler implements NewlineHandler {

        @Override
        public boolean matchesRequirement(String beforeText, String afterText) {
            return beforeText.endsWith(":");
        }

        @Override
        public HandleResult handleNewline(String beforeText, String afterText, int tabSize) {
            int count = TextUtils.countLeadingSpaceCount(beforeText, tabSize);
            int advanceBefore = getIndentAdvance(beforeText);
            int advanceAfter = getIndentAdvance(afterText);
            String text;
            StringBuilder sb = new StringBuilder("\n")
                    .append(TextUtils.createIndent(count + advanceBefore, tabSize, useTab()))
                    .append('\n')
                    .append(text = TextUtils.createIndent(count + advanceAfter, tabSize, useTab()));
            int shiftLeft = text.length() + 1;
            return new HandleResult(sb, shiftLeft);
        }
    }
}
