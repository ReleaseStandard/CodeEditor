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
package io.github.rosemoe.editor.interfaces;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.text.content.Content;
import io.github.rosemoe.editor.text.TextAnalyzeResult;
import io.github.rosemoe.editor.text.TextAnalyzer;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.widget.EditorColorScheme;

import static io.github.rosemoe.editor.widget.EditorColorScheme.*;

/**
 * Interface for analyzing highlight
 *
 * @author Rose
 */
public abstract class CodeAnalyzer {

    /**
     * Analyze spans for the given input
     *
     * @param content  The input text
     * @param colors   Result dest
     * @param delegate Delegate between thread and analyzer
     * @see TextAnalyzer#analyze(Content)
     * @see TextAnalyzer.AnalyzeThread.Delegate#shouldAnalyze()
     */
    public abstract void analyze(CharSequence content, TextAnalyzeResult colors, TextAnalyzer.AnalyzeThread.Delegate delegate);
    public static int antlrLineIndexToCodeEditor(int line) {
        return line-1;
    }
    public void _addColorNoCheck(TextAnalyzeResult colors, int spanLine, int column, int colorId) {
        colors.add(spanLine, Span.obtain(column, colorId));
    }
    public void __addColorIfNeeded(TextAnalyzeResult colors, int spanLine, int column, int colorId) {
        colors.addIfNeeded(spanLine,column,colorId);
    }
    public void addColor(TextAnalyzeResult colors, int spanLine, int column, int colorId) {
        __addColorIfNeeded(colors,spanLine,column,colorId);
    }
    public void setTerminalSymbolColor(TextAnalyzeResult colors, TerminalNode tn, int color) {
        if ( tn == null ) { return ; }
        setTokenColor(colors,tn.getSymbol(),color);
    }
    public void setTokenColor(TextAnalyzeResult colors, Token token, int color) {
        if ( token == null ) {
            Logger.debug("token was null");
            return ;
        }
        Logger.debug("Add color for token=" + token.getText() + ",color=" + color + ",line=" + token.getLine() + ",col=" + token.getCharPositionInLine());
        addColor(colors,antlrLineIndexToCodeEditor(token.getLine()),token.getCharPositionInLine(),color);
    }
    public void resetTerminalSymbolColor(TextAnalyzeResult colors,TerminalNode tn) {
        if ( tn == null ) {
            return;
        }
        resetTokenColor(colors,tn.getSymbol());
    }
    public void resetTokenColor(TextAnalyzeResult colors,Token token) {
        if (token == null) {
            return;
        }
        addColor(colors,antlrLineIndexToCodeEditor(token.getLine()),token.getCharPositionInLine() + token.getText().length(),TEXT_NORMAL);
    }
    public void processKeyword(TextAnalyzeResult colors,TerminalNode ...nodes) {
        processNodes(colors,KEYWORD,nodes);
    }
    public void processKeyword(TextAnalyzeResult colors, List<TerminalNode> nodes) {
        processNodes(colors,KEYWORD,nodes);
    }
    public void processStrings(TextAnalyzeResult colors, List<TerminalNode> nodes) {
        //processNodes(colors,STRING,nodes);
    }
    public void processStrings(TextAnalyzeResult colors, TerminalNode ...nodes) {
        //processNodes(colors,STRING,nodes);
    }
    public void processNodes(TextAnalyzeResult colors,int color,TerminalNode ...nodes) {
        for(TerminalNode node : nodes) {
            setTerminalSymbolColor(colors,node,color);
            resetTerminalSymbolColor(colors,node);
        }
    }
    public void processNodes(TextAnalyzeResult colors, int color, List<TerminalNode> nodes) {
        for(TerminalNode node : nodes) {
            setTerminalSymbolColor(colors,node,color);
            resetTerminalSymbolColor(colors,node);
        }
    }

}
