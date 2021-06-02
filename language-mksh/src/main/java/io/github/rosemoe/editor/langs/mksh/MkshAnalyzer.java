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
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import io.github.rosemoe.editor.mvc.controller.CodeAnalyzerController;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.util.Logger;

/**
 * Role of this class is to apply colors to language's syntaxe (here produced by antlr).
 */
public class MkshAnalyzer extends CodeAnalyzerController {

    public void processKeyword(Object... nodes) { processNodes(theme.accent3,nodes); }
    public void processKeyword(List<Object> nodes) { processNodes(theme.accent3,nodes); }
    public void processStrings(List<Object> nodes) { processNodes(theme.accent7,nodes); }
    public void processStrings(Object... nodes) { processNodes(theme.accent7,nodes); }
    public void processComments(List<Object> nodes) { processNodes(theme.base1,nodes); }
    public void processComments(Object... nodes) { processNodes(theme.base1,nodes); }

    @Override
    public void analyze(CharSequence content, TextAnalyzerView colors, io.github.rosemoe.editor.mvc.controller.TextAnalyzerController.AnalyzeThread.Delegate delegate) {
        super.analyze(content,colors,delegate);
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
                processKeyword(ctx.getStart());
            }

            @Override
            public void exitInstruction(MkshParser.InstructionContext ctx) {
                Logger.debug();
            }

            @Override
            public void enterExpression_end(MkshParser.Expression_endContext ctx) {
                processNodes(theme.accent3,ctx.getStart());
            }

            @Override
            public void exitExpression_end(MkshParser.Expression_endContext ctx) {

            }

            @Override
            public void enterComment(MkshParser.CommentContext ctx) {
                Logger.debug("Proceed Terminal node : linecommentisnull?=" , ctx.LINE_COMMENT()==null);
                processComments(ctx.LINE_COMMENT());
            }

            @Override
            public void exitComment(MkshParser.CommentContext ctx) {

            }

            @Override
            public void enterIdentifier(MkshParser.IdentifierContext ctx) {
            }

            @Override
            public void exitIdentifier(MkshParser.IdentifierContext ctx) { }

            @Override
            public void enterPrimary_keyword(MkshParser.Primary_keywordContext ctx) { }

            @Override
            public void exitPrimary_keyword(MkshParser.Primary_keywordContext ctx) { }

            @Override
            public void enterSecondary_keyword(MkshParser.Secondary_keywordContext ctx) { }

            @Override
            public void exitSecondary_keyword(MkshParser.Secondary_keywordContext ctx) { }

            @Override
            public void enterExecution_control(MkshParser.Execution_controlContext ctx) { }

            @Override
            public void exitExecution_control(MkshParser.Execution_controlContext ctx) { }

            @Override
            public void enterFor_do_done(MkshParser.For_do_doneContext ctx) {
                processKeyword(ctx.FOR(), ctx.DO(), ctx.DONE(), ctx.IN());
            }

            @Override
            public void exitFor_do_done(MkshParser.For_do_doneContext ctx) {

            }

            @Override
            public void enterIf_then_else(MkshParser.If_then_elseContext ctx) {
                Logger.debug();
                processKeyword(ctx.ELIF());
                processKeyword(ctx.THEN());
                processKeyword(ctx.IF(),ctx.ELSE(),ctx.FI());
            }

            @Override
            public void exitIf_then_else(MkshParser.If_then_elseContext ctx) {

            }

            @Override
            public void enterSelect_in(MkshParser.Select_inContext ctx) {
                processKeyword(ctx.SELECT(), ctx.IN(), ctx.DONE(), ctx.DO());
            }

            @Override
            public void exitSelect_in(MkshParser.Select_inContext ctx) {

            }

            @Override
            public void enterUntil_do(MkshParser.Until_doContext ctx) {
                processKeyword(ctx.UNTIL(), ctx.DO(), ctx.DONE());
            }

            @Override
            public void exitUntil_do(MkshParser.Until_doContext ctx) {

            }

            @Override
            public void enterWhile_do(MkshParser.While_doContext ctx) {
                processKeyword(ctx.WHILE(), ctx.DO(), ctx.DONE());
            }

            @Override
            public void exitWhile_do(MkshParser.While_doContext ctx) {
            }

            @Override
            public void enterFunction(MkshParser.FunctionContext ctx) {
                processKeyword(ctx.FUNCTION(),ctx.P_L_BRACKET(),ctx.P_L_PARENTHESIS(),ctx.P_R_PARENTHESIS(),ctx.P_R_BRACKET());
            }

            @Override
            public void exitFunction(MkshParser.FunctionContext ctx) {

            }

            @Override
            public void enterString(MkshParser.StringContext ctx) {
                processStrings(ctx.STRING());
            }

            @Override
            public void exitString(MkshParser.StringContext ctx) {

            }

            @Override
            public void enterArit(MkshParser.AritContext ctx) {
                processKeyword(ctx.LET(),ctx.ARIT_OPERATOR_L(),ctx.ARIT_OPERATOR_R());
            }

            @Override
            public void exitArit(MkshParser.AritContext ctx) {

            }

            @Override
            public void enterA_immediate(MkshParser.A_immediateContext ctx) {
                processNodes(theme.accent4,ctx.getStart());
            }

            @Override
            public void exitA_immediate(MkshParser.A_immediateContext ctx) {

            }

            @Override
            public void enterA_operand(MkshParser.A_operandContext ctx) {

            }

            @Override
            public void exitA_operand(MkshParser.A_operandContext ctx) {

            }

            @Override
            public void enterA_expr(MkshParser.A_exprContext ctx) {

            }

            @Override
            public void exitA_expr(MkshParser.A_exprContext ctx) {

            }

            @Override
            public void enterA_operator_binary(MkshParser.A_operator_binaryContext ctx) {
                processKeyword(ctx.getStart());
            }

            @Override
            public void exitA_operator_binary(MkshParser.A_operator_binaryContext ctx) {
            }

            @Override
            public void enterA_operator_unary(MkshParser.A_operator_unaryContext ctx) {

            }

            @Override
            public void exitA_operator_unary(MkshParser.A_operator_unaryContext ctx) {

            }
        };
        ParseTreeWalker.DEFAULT.walk(walkListener,parser.start());
    }

}
