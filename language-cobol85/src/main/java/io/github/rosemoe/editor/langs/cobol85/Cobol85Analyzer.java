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
public class Cobol85Analyzer extends CodeAnalyzerController {

    public void processIdentifier(Object... nodes) { processNodes(theme.accent1,nodes); }
    public void processIdentifier(List<Object> nodes) { processNodes(theme.accent1,nodes); }
    public void processKeyword(Object... nodes) { processNodes(theme.accent2,nodes); }
    public void processKeyword(List<Object> nodes) { processNodes(theme.accent2,nodes); }
    public void processSecondaryKeyword(Object... nodes) { processNodes(theme.accent3,nodes); }
    public void processSecondaryKeyword(List<Object> nodes) { processNodes(theme.accent3,nodes); }
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
        Cobol85Lexer lexer = new Cobol85Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Cobol85Parser parser = new Cobol85Parser(tokens);
        Cobol85BaseListener walkListener = new Cobol85BaseListener() {
            @Override public void enterAddStatement(Cobol85Parser.AddStatementContext ctx) {
                Logger.debug();
                processKeyword(ctx.ADD(),ctx.END_ADD());
            }

            @Override
            public void enterAddTo(Cobol85Parser.AddToContext ctx) {
                Logger.debug();
                processIdentifier(ctx.identifier().getStart());
            }
        };
        ParseTreeWalker.DEFAULT.walk(walkListener,parser.startRule());
    }

}
