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
package io.github.rosemoe.editor.langs.java;

import io.github.rosemoe.editor.langs.helpers.TrieTree;
import io.github.rosemoe.editor.mvc.controller.content.CodeAnalyzerResultContent;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzerThread;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.tokenemitter.TokenEmitter;
import io.github.rosemoe.editor.mvc.controller.widgets.colorAnalyzer.analysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.langs.helpers.LineNumberCalculator;
import io.github.rosemoe.editor.mvc.controller.widgets.completion.IdentifierAutoComplete;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;
import io.github.rosemoe.editor.core.util.Logger;

import java.util.Stack;

/**
 * Note:Navigation not supported
 *
 * @author Rose
 */
public class JavaCodeAnalyzer extends TokenEmitter {

    private final static Object OBJECT = new Object();

    CodeAnalyzerResultColor colorResult = new CodeAnalyzerResultColor();
    CodeAnalyzerResultContent contentResult   = new CodeAnalyzerResultContent();

    /**
     * By default we enable color result and content result (minimal functionnality for editor).
     */
    public JavaCodeAnalyzer() {
        Logger.debug("Adding analyzer results");
        addResultListener("color", colorResult);
        addResultListener("content", contentResult);
    }

    @Override
    public void analyze(CharSequence content, CodeAnalyzerThread.Delegate delegate) {
        StringBuilder text = content instanceof StringBuilder ? (StringBuilder) content : new StringBuilder(content);
        JavaTextTokenizer tokenizer = new JavaTextTokenizer(text);
        tokenizer.setCalculateLineColumn(false);
        Tokens token, previous = Tokens.UNKNOWN;
        int line = 0, column = 0;
        LineNumberCalculator helper = new LineNumberCalculator(text);
        IdentifierAutoComplete.Identifiers identifiers = new IdentifierAutoComplete.Identifiers();
        identifiers.begin();
        Stack<BlockLineModel> stack = new Stack<>();
        int maxSwitch = 1, currSwitch = 0;
        //Tree to save class names and query
        TrieTree<Object> classNames = new TrieTree<>();
        //Whether previous token is class name
        boolean classNamePrevious = false;
        //Add default class name
        classNames.put("String", OBJECT);
        classNames.put("Object", OBJECT);
        boolean first = true;
        while (delegate.shouldAnalyze()) {
            try {
                // directNextToken() does not skip any token
                token = tokenizer.directNextToken();
            } catch (RuntimeException e) {
                //When a spelling input is in process, this will happen because of format mismatch
                token = Tokens.CHARACTER_LITERAL;
            }
            if (token == Tokens.EOF) {
                break;
            }
            // Backup values because looking ahead in function name match will change them
            int thisIndex = tokenizer.getIndex();
            int thisLength = tokenizer.getTokenLength();
            dispatchResultPart(line,column);
            switch (token) {
                case WHITESPACE:
                case NEWLINE:
                    if (first) {
                        colorResult.dispatchResult();
                    }
                    break;
                case IDENTIFIER:
                    //Add a identifier to auto complete
                    identifiers.addIdentifier(text.substring(tokenizer.getIndex(), tokenizer.getTokenLength() + tokenizer.getIndex()));
                    //The previous so this will be the annotation's type name
                    if (previous == Tokens.AT) {
                        colorResult.dispatchResult(line, column, "accent1");
                        break;
                    }
                    //Here we have to get next token to see if it is function
                    //We can only get the next token in stream.
                    //If more tokens required, we have to use a stack in tokenizer
                    Tokens next = tokenizer.directNextToken();
                    //The next is LPAREN,so this is function name or type name
                    if (next == Tokens.LPAREN) {
                        colorResult.dispatchResult(line, column, "accent6");
                        tokenizer.pushBack(tokenizer.getTokenLength());
                        break;
                    }
                    //Push back the next token
                    tokenizer.pushBack(tokenizer.getTokenLength());
                    //This is a class definition
                    if (previous == Tokens.CLASS) {
                        colorResult.dispatchResult(line, column, "accent5");
                        //Add class name
                        classNames.put(text, thisIndex, thisLength, OBJECT);
                        break;
                    }
                    //Has class name
                    if (classNames.get(text, thisIndex, thisLength) == OBJECT) {
                        colorResult.dispatchResult(line, column, "textNormal");
                        //Mark it
                        classNamePrevious = true;
                        break;
                    }
                    if (classNamePrevious) {
                        //Var name
                        colorResult.dispatchResult(line, column, "accent4");
                        classNamePrevious = false;
                        break;
                    }
                    colorResult.dispatchResult(line, column, "textNormal");
                    break;
                case CHARACTER_LITERAL:
                case STRING:
                case FLOATING_POINT_LITERAL:
                case INTEGER_LITERAL:
                    classNamePrevious = false;
                    colorResult.dispatchResult(line, column, "accent7");
                    break;
                case INT:
                case LONG:
                case BOOLEAN:
                case BYTE:
                case CHAR:
                case FLOAT:
                case DOUBLE:
                case SHORT:
                case VOID:
                    classNamePrevious = true;
                    colorResult.dispatchResult(line, column, "accent1");
                    break;
                case ABSTRACT:
                case ASSERT:
                case CLASS:
                case DO:
                case FINAL:
                case FOR:
                case IF:
                case NEW:
                case PUBLIC:
                case PRIVATE:
                case PROTECTED:
                case PACKAGE:
                case RETURN:
                case STATIC:
                case SUPER:
                case SWITCH:
                case ELSE:
                case VOLATILE:
                case SYNCHRONIZED:
                case STRICTFP:
                case GOTO:
                case CONTINUE:
                case BREAK:
                case TRANSIENT:
                case TRY:
                case CATCH:
                case FINALLY:
                case WHILE:
                case CASE:
                case DEFAULT:
                case CONST:
                case ENUM:
                case EXTENDS:
                case IMPLEMENTS:
                case IMPORT:
                case INSTANCEOF:
                case INTERFACE:
                case NATIVE:
                case THIS:
                case THROW:
                case THROWS:
                case TRUE:
                case FALSE:
                case NULL:
                    classNamePrevious = false;
                    colorResult.dispatchResult(line, column, "accent1");
                    break;
                case LBRACE: {
                    classNamePrevious = false;
                    colorResult.dispatchResult(line, column, "accent8");
                    if (stack.isEmpty()) {
                        if (currSwitch > maxSwitch) {
                            maxSwitch = currSwitch;
                        }
                        currSwitch = 0;
                    }
                    currSwitch++;
                    BlockLineModel block = contentResult.obtainNewBlock();
                    block.startLine = line;
                    block.startColumn = column;
                    stack.push(block);
                    break;
                }
                case RBRACE: {
                    classNamePrevious = false;
                    colorResult.dispatchResult(line, column, "accent8");
                    if (!stack.isEmpty()) {
                        BlockLineModel block = stack.pop();
                        block.endLine = line;
                        block.endColumn = column;
                        if (block.startLine != block.endLine) {
                            contentResult.addBlockLine(block);
                        }
                    }
                    break;
                }
                case LINE_COMMENT:
                case LONG_COMMENT:
                    Logger.debug("Long comment line=",line,",column=",column);
                    colorResult.dispatchResult(line, column, "comment");
                    break;
                default:
                    Logger.debug("Default case line=",line,",column=",column);
                    if (token == Tokens.LBRACK || (token == Tokens.RBRACK && previous == Tokens.LBRACK)) {
                        colorResult.dispatchResult(line, column, "accent8");
                        break;
                    }
                    classNamePrevious = false;
                    colorResult.dispatchResult(line, column, "accent8");
            }
            first = false;
            helper.update(thisLength);
            line = helper.getLine();
            column = helper.getColumn();
            if (token != Tokens.WHITESPACE && token != Tokens.NEWLINE) {
                previous = token;
            }
        }
        if (stack.isEmpty()) {
            if (currSwitch > maxSwitch) {
                maxSwitch = currSwitch;
            }
        }
        identifiers.finish();
        colorResult.determine(line);
        //TODO:colorResult.mExtra = identifiers;
        //TODO:colorResult.setSuppressSwitch(maxSwitch + 10);
    }

}
