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
package io.github.rosemoe.editor.langs.python;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import io.github.rosemoe.editor.mvc.controller.widgets.contentAnalyzer.analysis.CodeAnalyzerResultContent;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.tokenemitter.TokenEmitter;
import io.github.rosemoe.editor.mvc.controller.widgets.colorAnalyzer.analysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzerThread;

public class PythonCodeAnalyzer extends TokenEmitter {

    public PythonCodeAnalyzer() {
        addResultListener("color",new CodeAnalyzerResultColor());
    }
    @Override
    public void analyze(CharSequence content, CodeAnalyzerThread.Delegate delegate) {
        try {
            CodeAnalyzerResultColor colorResult = (CodeAnalyzerResultColor) getResultInBuild("color");
            CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
            PythonLexer lexer = new PythonLexer(stream);
            Token token = null, previous = null;
            boolean first = true;

            int lastLine = 1;
            int line = 0, column = 0;

            while (delegate.shouldAnalyze()) {
                token = lexer.nextToken();
                if (token == null) break;
                if (token.getType() == PythonLexer.EOF) {
                    lastLine = token.getLine() - 1;
                    break;
                }
                line = token.getLine() - 1;
                column = token.getCharPositionInLine();
                lastLine = line;
                dispatchResultPart(line,column);
                switch (token.getType()) {
                    case PythonLexer.WS:
                    case PythonLexer.NEWLINE:
                        if (first) {
                            colorResult.dispatchResult();
                        }
                        break;
                    case PythonLexer.DEF:
                    case PythonLexer.RETURN:
                    case PythonLexer.RAISE:
                    case PythonLexer.FROM:
                    case PythonLexer.IMPORT:
                    case PythonLexer.NONLOCAL:
                    case PythonLexer.AS:
                    case PythonLexer.GLOBAL:
                    case PythonLexer.ASSERT:
                    case PythonLexer.IF:
                    case PythonLexer.ELIF:
                    case PythonLexer.ELSE:
                    case PythonLexer.WHILE:
                    case PythonLexer.FOR:
                    case PythonLexer.IN:
                    case PythonLexer.TRY:
                    case PythonLexer.NONE:
                    case PythonLexer.FINALLY:
                    case PythonLexer.WITH:
                    case PythonLexer.EXCEPT:
                    case PythonLexer.LAMBDA:
                    case PythonLexer.OR:
                    case PythonLexer.AND:
                    case PythonLexer.NOT:
                    case PythonLexer.IS:
                    case PythonLexer.CLASS:
                    case PythonLexer.YIELD:
                    case PythonLexer.DEL:
                    case PythonLexer.PASS:
                    case PythonLexer.CONTINUE:
                    case PythonLexer.BREAK:
                    case PythonLexer.ASYNC:
                    case PythonLexer.AWAIT:
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent2());
                        break;
                    case PythonLexer.COMMENT:
                        colorResult.dispatchResult(line, column, colorResult.theme.getComment());
                        break;
                    case PythonLexer.STRING:
                    case PythonLexer.DECIMAL_INTEGER:
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent7());
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent7());
                        break;
                    case PythonLexer.OPEN_BRACE:
                    case PythonLexer.CLOSE_BRACE:
                    case PythonLexer.OPEN_BRACKET:
                    case PythonLexer.CLOSE_BRACKET:
                    case PythonLexer.OPEN_PAREN:
                    case PythonLexer.CLOSE_PAREN:
                    case PythonLexer.DOT:
                    case PythonLexer.ELLIPSIS:
                    case PythonLexer.STAR:
                    case PythonLexer.COMMA:
                    case PythonLexer.COLON:
                    case PythonLexer.SEMI_COLON:
                    case PythonLexer.POWER:
                    case PythonLexer.ASSIGN:
                    case PythonLexer.OR_OP:
                    case PythonLexer.XOR:
                    case PythonLexer.AND_OP:
                    case PythonLexer.LEFT_SHIFT:
                    case PythonLexer.RIGHT_SHIFT:
                    case PythonLexer.ADD:
                    case PythonLexer.MINUS:
                    case PythonLexer.DIV:
                    case PythonLexer.MOD:
                    case PythonLexer.IDIV:
                    case PythonLexer.NOT_OP:
                    case PythonLexer.LESS_THAN:
                    case PythonLexer.GREATER_THAN:
                    case PythonLexer.EQUALS:
                    case PythonLexer.GT_EQ:
                    case PythonLexer.LT_EQ:
                    case PythonLexer.NOT_EQ_1:
                    case PythonLexer.NOT_EQ_2:
                    case PythonLexer.AT:
                    case PythonLexer.ARROW:
                    case PythonLexer.ADD_ASSIGN:
                    case PythonLexer.SUB_ASSIGN:
                    case PythonLexer.MULT_ASSIGN:
                    case PythonLexer.AT_ASSIGN:
                    case PythonLexer.DIV_ASSIGN:
                    case PythonLexer.MOD_ASSIGN:
                    case PythonLexer.AND_ASSIGN:
                    case PythonLexer.OR_ASSIGN:
                    case PythonLexer.XOR_ASSIGN:
                    case PythonLexer.LEFT_SHIFT_ASSIGN:
                    case PythonLexer.RIGHT_SHIFT_ASSIGN:
                    case PythonLexer.POWER_ASSIGN:
                    case PythonLexer.IDIV_ASSIGN:
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent1());
                        break;
                    case PythonLexer.NAME: {
                        if (previous == null) {
                            colorResult.dispatchResult(line, column, colorResult.theme.getTextNormal());
                            break;
                        } else if (previous.getType() == PythonLexer.DEF) {
                            colorResult.dispatchResult(line, column, colorResult.theme.getAccent6());
                            break;
                        } else if (previous.getType() == PythonLexer.CLASS) {
                            colorResult.dispatchResult(line, column, colorResult.theme.getAccent6());
                            break;
                        }
                    }
                    default:
                        colorResult.dispatchResult(line, column, colorResult.theme.getTextNormal());
                        break;
                }
                first = false;
                int currentTokenType = token.getType();
                if (token != null && currentTokenType != PythonLexer.WS && currentTokenType != PythonLexer.NEWLINE) {
                    previous = token;
                }
            }
            colorResult.determine(lastLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
