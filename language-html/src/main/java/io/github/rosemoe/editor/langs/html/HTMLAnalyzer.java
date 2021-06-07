package io.github.rosemoe.editor.langs.html;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.CodeAnalyzerController;
import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.TextAnalyzerController;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;

public class HTMLAnalyzer extends CodeAnalyzerController {
    @Override
    public void analyze(CharSequence content, TextAnalyzerView colors, TextAnalyzerController.AnalyzeThread.Delegate delegate) {
        super.analyze(content,colors,delegate);
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

                switch (token.getType()) {
                    case HTMLLexer.TAG_WHITESPACE:
                        if (first) colors.addNormalIfNull();
                        break;
                    case HTMLLexer.TAG_OPEN:
                    case HTMLLexer.TAG_SLASH:
                    case HTMLLexer.TAG_SLASH_CLOSE:
                    case HTMLLexer.TAG_CLOSE:
                    case HTMLLexer.TAG_NAME:
                    case HTMLLexer.XML:
                        colors.addIfNeeded(line, column, theme.getAccent6());
                        break;
                    case HTMLLexer.CDATA:
                    case HTMLLexer.ATTRIBUTE:
                    case HTMLLexer.TAG_EQUALS:
                        colors.addIfNeeded(line, column, theme.getAccent1());
                        break;
                    case HTMLLexer.ATTVALUE_VALUE:
                        colors.addIfNeeded(line, column, theme.getAccent7());
                        break;
                    case HTMLLexer.HTML_CONDITIONAL_COMMENT:
                    case HTMLLexer.HTML_COMMENT:
                        colors.addIfNeeded(line, column, theme.getComment());
                        break;
                    case HTMLLexer.HTML_TEXT:
                    default:
                        colors.addIfNeeded(line, column, theme.getTextNormal());
                        break;
                }

                first = false;
            }
            colors.determine(lastLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
