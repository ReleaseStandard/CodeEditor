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

import java.util.Stack;

import io.github.rosemoe.editor.langs.IdentifierAutoComplete;
import io.github.rosemoe.editor.langs.helpers.LineNumberCalculator;
import io.github.rosemoe.editor.mvc.controller.CodeAnalyzerController;
import io.github.rosemoe.editor.mvc.model.BlockLineModel;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;

import static io.github.rosemoe.editor.langs.universal.UniversalTokens.EOF;

public class UniversalCodeAnalyzer extends CodeAnalyzerController {
    private LanguageDescription mLanguage;
    private UniversalTokenizer tokenizer;
    private UniversalTokenizer tokenizer2;

    public UniversalCodeAnalyzer(LanguageDescription description, UniversalTokenizer tokenizer1, UniversalTokenizer tokenizer2) {
        this.mLanguage = description;
        this.tokenizer = tokenizer1;
        this.tokenizer2 = tokenizer2;
    }
    @Override
    public void analyze(CharSequence content, TextAnalyzerView colors, io.github.rosemoe.editor.mvc.controller.TextAnalyzerController.AnalyzeThread.Delegate delegate) {
        super.analyze(content,colors,delegate);
        StringBuilder text = content instanceof StringBuilder ? (StringBuilder) content : new StringBuilder(content);
        tokenizer.setInput(text);
        LineNumberCalculator helper = new LineNumberCalculator(text);
        IdentifierAutoComplete autoComplete = new IdentifierAutoComplete();
        autoComplete.setKeywords(mLanguage.getKeywords());
        IdentifierAutoComplete.Identifiers identifiers = new IdentifierAutoComplete.Identifiers();
        identifiers.begin();
        int maxSwitch = 0;
        int layer = 0;
        int currSwitch = 0;
        try {
            UniversalTokens token;
            Stack<BlockLineModel> stack = new Stack<>();
            while ((token = tokenizer.nextToken()) != EOF) {
                int index = tokenizer.getOffset();
                int line = helper.getLine();
                int column = helper.getColumn();
                switch (token) {
                    case KEYWORD:
                        colors.addIfNeeded(line, column, theme.accent1);
                        break;
                    case IDENTIFIER:
                        identifiers.addIdentifier(text.substring(index, index + tokenizer.getTokenLength()));
                        colors.addIfNeeded(line, column, theme.getTextNormal());
                        break;
                    case LITERAL:
                        colors.addIfNeeded(line, column, theme.accent7);
                        break;
                    case LINE_COMMENT:
                    case LONG_COMMENT:
                        colors.addIfNeeded(line, column, theme.getComment());
                        break;
                    case OPERATOR:
                        colors.addIfNeeded(line, column, theme.getTextNormal());
                        if (mLanguage.isSupportBlockLine()) {
                            String op = text.substring(index, index + tokenizer.getTokenLength());
                            if (mLanguage.isBlockStart(op)) {
                                BlockLineModel blockLine = colors.obtainNewBlock();
                                blockLine.startLine = line;
                                blockLine.startColumn = column;
                                stack.add(blockLine);
                                if (layer == 0) {
                                    currSwitch = 1;
                                } else {
                                    currSwitch++;
                                }
                                layer++;
                            } else if (mLanguage.isBlockEnd(op)) {
                                if (!stack.isEmpty()) {
                                    BlockLineModel blockLine = stack.pop();
                                    blockLine.endLine = line;
                                    blockLine.endColumn = column;
                                    colors.addBlockLine(blockLine);
                                    if (layer == 1) {
                                        if (currSwitch > maxSwitch) {
                                            maxSwitch = currSwitch;
                                        }
                                    }
                                    layer--;
                                }
                            }
                        }
                        break;
                    case WHITESPACE:
                    case NEWLINE:
                        colors.addNormalIfNull();
                        break;
                    case UNKNOWN:
                        colors.addIfNeeded(line, column, theme.getTextNormal());
                        break;
                }
                helper.update(tokenizer.getTokenLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        colors.determine(helper.getLine());
        identifiers.finish();
        colors.mExtra = identifiers;
        tokenizer.setInput(null);
        if (currSwitch > maxSwitch) {
            maxSwitch = currSwitch;
        }
        colors.setSuppressSwitch(maxSwitch + 50);
    }

}
