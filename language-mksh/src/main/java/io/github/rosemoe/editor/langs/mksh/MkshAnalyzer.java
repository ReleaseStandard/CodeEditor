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

import static io.github.rosemoe.editor.langs.mksh.MkshParser.*;

/**
 * Role of this class is to apply colors to language's syntaxe (here produced by antlr).
 */
public class MkshAnalyzer extends CodeAnalyzerController {

    public void processKeywords(Object ...nodes) {
        processNodes(theme.accent3,nodes);
    }
    public void processFunctionIdentifier(Object ...nodes) {
        processNodes(0xFF00FF00,nodes);
    }
    public void processPunctuation(Object ...nodes) {
        processNodes(theme.accent3,nodes);
    }
    public void processIdentifier(Object ...nodes) {
        processNodes(theme.accent1,nodes);
    }
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
        MkshParserListener walkListener = new MkshParserBaseListener() {
            @Override public void visitTerminal(TerminalNode node) {
                Token token = node.getSymbol();
                if ( token == null ) { return ; }
                switch (token.getType()) {
                    // secondary keywords
                    case BREAK: case CONTINUE: case EVAL: case EXEC: case EXIT: case EXPORT: case READONLY:
                    case RETURN: case SET: case SHIFT: case TIMES: case TRAP: case UNSET: case BUILTIN:
                    case GLOBAL: case TYPESET: case WAIT: case ALIAS: case BG: case BIND: case CAT: case CD:
                    case COMMAND: case ECHO: case FALSE: case TRUE: case FC: case FG: case GETOPTS: case JOBS:
                    case KILL: case MKNOD: case PRINT: case PWD: case READ: case REALPATH: case RENAME:
                    case SLEEP: case SUSPEND: case TEST: case ULIMIT: case UMASK: case UNALIAS: case WHENCE:
                        processNode(theme.accent3,token);
                        break;
                    case LINE_COMMENT:
                        processNodes(theme.getComment(),token);
                    case STRING:
                        processNodes(theme.accent7,token);
                }
            }

            // This should normally all primary keywords.
            @Override public void enterFor_do_done(For_do_doneContext ctx) { processKeywords(ctx.DO(),ctx.DONE(),ctx.IN(),ctx.FOR()); }
            @Override public void enterIf_then_else(If_then_elseContext ctx) { processKeywords(ctx.IF(),ctx.ELSE(),ctx.FI()); processKeywords(ctx.THEN()); processKeywords(ctx.ELIF()); }
            @Override public void enterSelect_in(Select_inContext ctx) { processKeywords(ctx.DO(),ctx.SELECT(),ctx.DONE(),ctx.IN()); }
            @Override public void enterUntil_do(Until_doContext ctx) { processKeywords(ctx.UNTIL(), ctx.DO(), ctx.DONE()); }
            @Override public void enterWhile_do(While_doContext ctx) { processKeywords(ctx.DONE(),ctx.DO(),ctx.WHILE()); }
            @Override public void enterFunction(FunctionContext ctx) {
                processKeywords(ctx.FUNCTION(),ctx.P_R_BRACKET(),ctx.P_L_BRACKET());
                processFunctionIdentifier(ctx.identifier());
                processFunctionIdentifier(ctx.P_R_PARENTHESIS(),ctx.P_L_PARENTHESIS());
            }
            // arithmetic expression TODO
            @Override public void enterArit(AritContext ctx) {
                processKeywords(ctx.ARIT_OPERATOR_L(),ctx.ARIT_OPERATOR_R(),ctx.LET());
            }
            @Override public void enterExpression_end(Expression_endContext ctx) { processPunctuation(ctx.P_SEMI()); }

            @Override
            public void enterAssignment(AssignmentContext ctx) {
                processPunctuation(ctx.ARIT_A());
                if ( ctx.identifier() != null ) {
                    processIdentifier(ctx.identifier().IDENTIFIER());
                }
            }
        };
        ParseTreeWalker.DEFAULT.walk(walkListener,parser.start());
    }

}
