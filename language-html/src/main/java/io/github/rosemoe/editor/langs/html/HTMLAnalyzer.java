package io.github.rosemoe.editor.langs.html;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.CodeAnalyzerController;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.TextAnalyzerController;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.TokenEmitter;
import io.github.rosemoe.editor.mvc.controller.widgets.color.analysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;

public class HTMLAnalyzer extends TokenEmitter {

    CodeAnalyzerResultColor colorResult = new CodeAnalyzerResultColor();

    public HTMLAnalyzer() {
        addResultListener("color",colorResult);
    }

    @Override
    public void analyze(CharSequence content, TextAnalyzerController.AnalyzeThread.Delegate delegate) {
        try {
            CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
            HTMLLexer lexer = new HTMLLexer(stream);
            Token token = null;
            boolean first = true;
            int lastLine = 1;
            int line = 0, column = 0;
            while (delegate.shouldAnalyze()) {
                token = lexer.nextToken();
                if (token == null) break;
                if (token.getType() == HTMLLexer.EOF) {
                    lastLine = token.getLine() - 1;
                    break;
                }
                line = token.getLine() - 1;
                column = token.getCharPositionInLine();
                lastLine = line;
                dispatchResult(line,column);
                switch (token.getType()) {
                    case HTMLLexer.TAG_WHITESPACE:
                        if (first) colorResult.dispatchResult();
                        break;
                    case HTMLLexer.TAG_OPEN:
                    case HTMLLexer.TAG_SLASH:
                    case HTMLLexer.TAG_SLASH_CLOSE:
                    case HTMLLexer.TAG_CLOSE:
                    case HTMLLexer.TAG_NAME:
                    case HTMLLexer.XML:
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent6());
                        break;
                    case HTMLLexer.CDATA:
                    case HTMLLexer.ATTRIBUTE:
                    case HTMLLexer.TAG_EQUALS:
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent1());
                        break;
                    case HTMLLexer.ATTVALUE_VALUE:
                        colorResult.dispatchResult(line, column, colorResult.theme.getAccent7());
                        break;
                    case HTMLLexer.HTML_CONDITIONAL_COMMENT:
                    case HTMLLexer.HTML_COMMENT:
                        colorResult.dispatchResult(line, column, colorResult.theme.getComment());
                        break;
                    case HTMLLexer.HTML_TEXT:
                    default:
                        colorResult.dispatchResult(line, column, colorResult.theme.getTextNormal());
                        break;
                }

                first = false;
            }
            //TODO:colors.determine(lastLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
