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

import android.util.Log;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import io.github.rosemoe.editor.interfaces.CodeAnalyzer;
import io.github.rosemoe.editor.interfaces.EditorLanguage;
import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.text.TextAnalyzeResult;
import io.github.rosemoe.editor.text.TextAnalyzer;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.EditorColorScheme;

import static io.github.rosemoe.editor.langs.mksh.MkshLexer.*;
import static io.github.rosemoe.editor.widget.EditorColorScheme.*;

public class MkshAnalyzer implements CodeAnalyzer {

    private static int antlrLineIndexToCodeEditor(int line) {
        return line-1;
    }
    private void _addColorNoCheck(TextAnalyzeResult colors, int spanLine, int column, int colorId) {
        colors.add(spanLine,Span.obtain(column, colorId));
    }
    private void __addColorIfNeeded(TextAnalyzeResult colors, int spanLine, int column, int colorId) {
        colors.addIfNeeded(spanLine,column,colorId);
    }
    private void addColor(TextAnalyzeResult colors, int spanLine, int column, int colorId) {
        __addColorIfNeeded(colors,spanLine,column,colorId);
    }
    private void setTerminalSymbolColor(TextAnalyzeResult colors, TerminalNode tn, int color) {
        if ( tn == null ) { return ; }
        setTokenColor(colors,tn.getSymbol(),color);
    }
    private void setTokenColor(TextAnalyzeResult colors, Token token, int color) {
        if ( token == null ) {
            Logger.debug("token was null");
            return ;
        }
        Logger.debug("Add color for token=" + token.getText() + ",color=" + color + ",line=" + token.getLine() + ",col=" + token.getCharPositionInLine());
        addColor(colors,antlrLineIndexToCodeEditor(token.getLine()),token.getCharPositionInLine(),color);
    }
    private void resetTerminalSymbolColor(TextAnalyzeResult colors,TerminalNode tn) {
        if ( tn == null ) {
            return;
        }
        resetTokenColor(colors,tn.getSymbol());
    }
    private void resetTokenColor(TextAnalyzeResult colors,Token token) {
        if (token == null) {
            return;
        }
        addColor(colors,antlrLineIndexToCodeEditor(token.getLine()),token.getCharPositionInLine() + token.getText().length(),TEXT_NORMAL);
    }
    private void processKeyword(TextAnalyzeResult colors,TerminalNode ...nodes) {
        processNodes(colors,KEYWORD,nodes);
    }
    private void processKeyword(TextAnalyzeResult colors, List<TerminalNode> nodes) {
        processNodes(colors,KEYWORD,nodes);
    }
    private void processStrings(TextAnalyzeResult colors, List<TerminalNode> nodes) {
        processNodes(colors,STRING,nodes);
    }
    private void processStrings(TextAnalyzeResult colors, TerminalNode ...nodes) {
        processNodes(colors,STRING,nodes);
    }
    private void processNodes(TextAnalyzeResult colors,int color,TerminalNode ...nodes) {
        for(TerminalNode node : nodes) {
            setTerminalSymbolColor(colors,node,color);
            resetTerminalSymbolColor(colors,node);
        }
    }
    private void processNodes(TextAnalyzeResult colors, int color, List<TerminalNode> nodes) {
        for(TerminalNode node : nodes) {
            setTerminalSymbolColor(colors,node,color);
            resetTerminalSymbolColor(colors,node);
        }
    }
    @Override
    public void analyze(CharSequence content, TextAnalyzeResult colors, TextAnalyzer.AnalyzeThread.Delegate delegate) {

        CodePointCharStream stream = null;
        try {
            stream = CharStreams.fromReader(new StringReader(content.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MkshLexer lexer = new MkshLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MkshParser parser = new MkshParser(tokens);
        MkshParserListener walkListener = new MkshParserListener() {
            @Override
            public void visitTerminal(TerminalNode node) {
                Token token = node.getSymbol();
                if ( token != null ) {
                    Logger.debug(token.getText());
                } else {
                    Logger.debug("Token was null");
                }
            }

            @Override
            public void visitErrorNode(ErrorNode node) {
                Logger.debug();
            }

            @Override
            public void enterEveryRule(ParserRuleContext ctx) {
                Logger.debug();
            }

            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
                Logger.debug();
            }

            @Override
            public void enterStart(MkshParser.StartContext ctx) {
                Logger.debug();
            }

            @Override
            public void exitStart(MkshParser.StartContext ctx) {
                Logger.debug();
            }

            @Override
            public void enterFile(MkshParser.FileContext ctx) {

            }

            @Override
            public void exitFile(MkshParser.FileContext ctx) {

            }

            @Override
            public void enterExpr(MkshParser.ExprContext ctx) {

            }

            @Override
            public void exitExpr(MkshParser.ExprContext ctx) {

            }

            @Override
            public void enterInstruction(MkshParser.InstructionContext ctx) {
                Logger.debug();
                setTokenColor(colors,ctx.getStart(),COMMENT);
                resetTokenColor(colors,ctx.getStart());
            }

            @Override
            public void exitInstruction(MkshParser.InstructionContext ctx) {
                Logger.debug();
            }

            @Override
            public void enterExpression_end(MkshParser.Expression_endContext ctx) {

            }

            @Override
            public void exitExpression_end(MkshParser.Expression_endContext ctx) {

            }

            @Override
            public void enterExecution_control(MkshParser.Execution_controlContext ctx) {
            }

            @Override
            public void exitExecution_control(MkshParser.Execution_controlContext ctx) {

            }

            @Override
            public void enterFor_do_done(MkshParser.For_do_doneContext ctx) {
                processKeyword(colors, ctx.FOR(), ctx.DO(), ctx.DONE(), ctx.IN());
                processStrings(colors,ctx.STRING());
            }

            @Override
            public void exitFor_do_done(MkshParser.For_do_doneContext ctx) {

            }

            @Override
            public void enterIf_then_else(MkshParser.If_then_elseContext ctx) {
                Logger.debug();
                for(TerminalNode tn : ctx.ELIF()) { processKeyword(colors,tn); }
                for(TerminalNode tn : ctx.THEN()) { processKeyword(colors,tn); }
                processKeyword(colors,ctx.IF(),ctx.ELSE(),ctx.FI());
            }

            @Override
            public void exitIf_then_else(MkshParser.If_then_elseContext ctx) {

            }

            @Override
            public void enterSelect_in(MkshParser.Select_inContext ctx) {
                processKeyword(colors, ctx.SELECT(), ctx.IN(), ctx.DONE(), ctx.DO());
            }

            @Override
            public void exitSelect_in(MkshParser.Select_inContext ctx) {

            }

            @Override
            public void enterUntil_do(MkshParser.Until_doContext ctx) {
                processKeyword(colors, ctx.UNTIL(), ctx.DO(), ctx.DONE());
            }

            @Override
            public void exitUntil_do(MkshParser.Until_doContext ctx) {

            }

            @Override
            public void enterWhile_do(MkshParser.While_doContext ctx) {
                processKeyword(colors, ctx.WHILE(), ctx.DO(), ctx.DONE());
            }

            @Override
            public void exitWhile_do(MkshParser.While_doContext ctx) {
            }

            @Override
            public void enterFunction(MkshParser.FunctionContext ctx) {
                processKeyword(colors, ctx.FUNCTION(),ctx.P_L_BRACKET(),ctx.P_L_PARENTHESIS(),ctx.P_R_PARENTHESIS(),ctx.P_R_BRACKET());
            }

            @Override
            public void exitFunction(MkshParser.FunctionContext ctx) {

            }

            @Override
            public void enterArit(MkshParser.AritContext ctx) {

            }

            @Override
            public void exitArit(MkshParser.AritContext ctx) {

            }

            @Override
            public void enterA_operator(MkshParser.A_operatorContext ctx) {

            }

            @Override
            public void exitA_operator(MkshParser.A_operatorContext ctx) {

            }

            @Override
            public void enterA_immediate(MkshParser.A_immediateContext ctx) {

            }

            @Override
            public void exitA_immediate(MkshParser.A_immediateContext ctx) {

            }

            @Override
            public void enterA_expr(MkshParser.A_exprContext ctx) {

            }

            @Override
            public void exitA_expr(MkshParser.A_exprContext ctx) {

            }
        };
        ParseTreeWalker.DEFAULT.walk(walkListener,parser.start());

        /*
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
            Log.v("TEST",token.getText());
            switch (token.getType()) {
                case CAT_KEYWORDS:
                case CAT_ADDITIONNAL_BUILTINS:
                    colors.addIfNeeded(line,column, EditorColorScheme.KEYWORD);
                    break;

                case LINE_COMMENT:
                    colors.addIfNeeded(line,column,EditorColorScheme.COMMENT);
                    break;

                case OPERATORS:
                case CAT_PUNCTUATIONS:
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
        */
    }

}
