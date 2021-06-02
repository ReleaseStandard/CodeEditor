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
                    case KILL: case LET: case MKNOD: case PRINT: case PWD: case READ: case REALPATH: case RENAME:
                    case SLEEP: case SUSPEND: case TEST: case ULIMIT: case UMASK: case UNALIAS: case WHENCE:
                    // primary keywords
                    case CASE: case ELSE: case FUNCTION: case THEN: case DO: case ESAC: case IF: case TIME:
                    case DONE: case FI: case IN: case UNTIL: case ELIF: case FOR: case SELECT: case WHILE:
                        processNode(theme.accent3,token);
                        break;
                    case LINE_COMMENT:
                        processNodes(theme.getComment(),token);
                    case STRING:
                        processNodes(theme.accent7,token);
                }
            }

        };
        ParseTreeWalker.DEFAULT.walk(walkListener,parser.start());
    }

}
