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
package io.github.rosemoe.editor.plugins.langs.golang;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import static io.github.rosemoe.editor.plugins.langs.golang.GoLangLexer.*;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzerThread;

public class GoLangAnalyzer extends CodeAnalyzer {

    @Override
    public void analyze(CharSequence content, CodeAnalyzerThread.Delegate delegate) {
/*        CodePointCharStream stream = null;
        try {
            stream = CharStreams.fromReader(new StringReader(content.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GoLangLexer lexer = new GoLangLexer(stream);
        Token token = null;
        int line = 0;
        int column = 0;
        int lastLine = 0;
        while(delegate.shouldAnalyze()) {
            token = lexer.nextToken();
            if (token == null) break;
            if (token.getType() == GoLangLexer.EOF) {
                lastLine = token.getLine() - 1;
                break;
            }
            line = token.getLine() - 1;
            lastLine = line;
            column = token.getCharPositionInLine();
            switch (token.getType()) {

                case BREAK: case DEFAULT: case FUNC: case INTERFACE: case SELECT: case CASE: case DEFER:
                case GO: case MAP: case STRUCT: case CHAN: case ELSE: case GOTO: case PACKAGE:
                case SWITCH: case CONST: case FALLTHROUGH: case IF: case RANGE: case TYPE:
                case CONTINUE: case FOR: case IMPORT: case RETURN: case VAR:
                    colors.addIfNeeded(line,column, theme.getAccent2());
                    break;

                case PLUS:  case MINUS: case STAR: case DIV: case MOD:
                case AMPERSAND: case OR: case CARET: case LSHIFT: case RSHIFT:
                case BIT_CLEAR: case PLUS_ASSIGN: case MINUS_ASSIGN: case STAR_ASSIGN:
                case DIV_ASSIGN: case MOD_ASSIGN: case LOGICAL_AND_ASSIGN: case LOGICAL_OR_ASSIGN:
                case CARET_ASSIGN: case LSHIFT_ASSIGN: case RSHIFT_ASSIGN: case BIT_CLEAR_ASSIGN:
                case LOGICAL_AND: case LOGICAL_OR: case RECEIVE: case PLUS_PLUS: case MINUS_MINUS:
                case EQUALS: case GREATER:  case LESS: case ASSIGN: case EXCLAMATION:
                case NOT_EQUALS: case LESS_OR_EQUALS:case GREATER_OR_EQUALS: case DECLARE_ASSIGN:
                case ELLIPSIS: case L_PAREN: case R_PAREN: case L_CURLY: case R_CURLY: case L_BRACKET: case R_BRACKET:
                case COMMA: case SEMI: case DOT: case COLON:
                    colors.addIfNeeded(line,column,theme.getTextNormal());
                    break;

                case COMMENT: case LINE_COMMENT:
                    colors.addIfNeeded(line,column,theme.getComment());
                    break;

                case DECIMAL_LIT: case BINARY_LIT: case DECIMAL_FLOAT_LIT: case FLOAT_LIT:
                case HEX_FLOAT_LIT: case HEX_LIT: case IMAGINARY_LIT: case INTERPRETED_STRING_LIT: case NIL_LIT:
                case OCTAL_LIT: case RAW_STRING_LIT: case RUNE_LIT:
                    colors.addIfNeeded(line,column,theme.getAccent7());

                default:
                    colors.addIfNeeded(line,column,theme.getTextNormal());
            }
        }
        colors.determine(lastLine);
        */

    }

}
