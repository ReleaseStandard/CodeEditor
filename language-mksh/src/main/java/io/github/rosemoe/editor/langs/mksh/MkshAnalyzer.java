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

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import io.github.rosemoe.editor.interfaces.CodeAnalyzer;
import io.github.rosemoe.editor.interfaces.EditorLanguage;
import io.github.rosemoe.editor.text.TextAnalyzeResult;
import io.github.rosemoe.editor.text.TextAnalyzer;
import io.github.rosemoe.editor.widget.EditorColorScheme;

import static io.github.rosemoe.editor.langs.mksh.MkshLexer.*;

public class MkshAnalyzer implements CodeAnalyzer {

    @Override
    public void analyze(CharSequence content, TextAnalyzeResult colors, TextAnalyzer.AnalyzeThread.Delegate delegate) {

        CodePointCharStream stream = null;
        try {
            stream = CharStreams.fromReader(new StringReader(content.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MkshLexer lexer = new MkshLexer(stream);
        Token token = null;
        int line = 0;
        int column = 0;
        int lastLine = 0;
        while(delegate.shouldAnalyze()) {
            token = lexer.nextToken();
            if (token == null) break;
            if (token.getType() == MkshLexer.EOF) {
                lastLine = token.getLine() - 1;
                break;
            }
            line = token.getLine() - 1;
            lastLine = line;
            column = token.getCharPositionInLine();
            switch (token.getType()) {

                case CAT_KEYWORDS:
                case CAT_ADDITIONNAL_BUILTINS:
                    colors.addIfNeeded(line,column, EditorColorScheme.KEYWORD);
                    break;

                case LINE_COMMENT:
                    colors.addIfNeeded(line,column,EditorColorScheme.COMMENT);
                    break;

                case OPERATORS:
                case P_PUNCTUATIONS:
                case LET_OPERATOR:
                    colors.addIfNeeded(line,column,EditorColorScheme.OPERATOR);
                    break;

                case STRING:
                    colors.addIfNeeded(line, column, EditorColorScheme.LITERAL);
                    break;

                default:
                    colors.addIfNeeded(line,column,EditorColorScheme.TEXT_NORMAL);
            }
        }
        colors.determine(lastLine);
    }

}
