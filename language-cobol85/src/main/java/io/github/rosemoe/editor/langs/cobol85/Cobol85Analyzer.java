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
package io.github.rosemoe.editor.langs.cobol85;
/*
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
*/
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.util.Logger;

import static io.github.rosemoe.editor.langs.cobol85.Cobol85Parser.*;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.TextAnalyzerController;

/**
 * Role of this class is to apply colors to language's syntaxe (here produced by antlr).
 */
public class Cobol85Analyzer extends CodeAnalyzer {

    @Override
    public void analyze(CharSequence content, TextAnalyzerController.AnalyzeThread.Delegate delegate) {
      /*  CodePointCharStream stream = null;
        try {
            stream = CharStreams.fromReader(new StringReader(content.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cobol85Lexer lexer = new Cobol85Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Cobol85Parser parser = new Cobol85Parser(tokens);
        Cobol85BaseListener walkListener = new Cobol85BaseListener() {
            @Override public void visitTerminal(TerminalNode node) {
                Token token = node.getSymbol();
                if ( token == null ) { return ; }
                switch(token.getType()) {
                    // Primary keywords
                    case PROCEDURE: case COMMON: case SOURCE_COMPUTER: case OBJECT_COMPUTER: case WORKING_STORAGE:
                    case SECTION: case CALL: case DATA: case DIVISION: case ADD: case END_ADD: case IF: case END_IF:
                    case ELSE: case FOR: case IDENTIFICATION: case PROGRAM_ID: case ENVIRONMENT: case CONFIGURATION:
                    case MOVE: case TO: case USING: case THEN: case PERFORM: case NOT: case END_ACCEPT:
                    case END_CALL: case END_COMPUTE: case END_DELETE: case END_DIVIDE: case END_EVALUATE:
                    case END_MULTIPLY: case END_PERFORM:
                        //TODO:processNode(theme.getAccent2(),token);
                        break;
                    case STRING:
                    case INTEGER: case INTEGERLITERAL:
                        //TODO:processNode(theme.getAccent7(),token);
                        break;
                    case COMMACHAR:
                        //TODO:processNodes(theme.getAccent1(),token);
                }
            }
        };
        ParseTreeWalker.DEFAULT.walk(walkListener,parser.startRule());

       */
    }


}
