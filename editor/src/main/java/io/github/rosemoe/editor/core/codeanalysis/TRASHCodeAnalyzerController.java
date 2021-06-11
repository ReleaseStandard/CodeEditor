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
package io.github.rosemoe.editor.core.codeanalysis;


import io.github.rosemoe.editor.core.widgets.contentAnalyzer.controller.ContentMapController;

/**
 * Interface for analyzing highlight
 * Role of this class is to apply colors to language's syntaxe (i.e. produced by antlr).
 *
 * @author Rose
 */
public abstract class TRASHCodeAnalyzerController {

    /**
     * Analyze spans for the given input
     *
     * @param content  The input text
     * @param colors   Result dest
     * @param delegate Delegate between thread and analyzer
     * @see TextAnalyzerController#analyze(ContentMapController)
     * @see TextAnalyzerController.AnalyzeThread.Delegate#shouldAnalyze()
     */
/*    public void analyze(CharSequence content, TextAnalyzerView colors, TextAnalyzerController.AnalyzeThread.Delegate delegate) {
        this.colors = colors;
    }
    public static int antlrLineIndexToCodeEditor(int line) {
        return line-1;
    }



    public static ColorSchemeController theme = null;
    private TextAnalyzerView colors = null;

    public static void setTheme(ColorSchemeController theme) { TRASHCodeAnalyzerController.theme = theme; }
    private void addColor(TextAnalyzerView colors, int spanLine, int column, int color) {
        colors.addIfNeeded(spanLine,column,color);
    }
    private void processToken(TextAnalyzerView colors, int color, Token token) {
        if ( token == null ) {
            Logger.debug("token was null");
            return ;
        }
        Logger.debug("Add color for token=" + token.getText() + ",color=" + color + ",line=" + token.getLine() + ",col=" + token.getCharPositionInLine());
        addColor(colors,antlrLineIndexToCodeEditor(token.getLine()),token.getCharPositionInLine(),color);
        addColor(colors,antlrLineIndexToCodeEditor(token.getLine()),token.getCharPositionInLine() + token.getText().length(),theme.getTextNormal());
    }
    public void processNode(int color, Object node) {
        processNodes(color,node);
    }
    public void processNodes(int color, List<Object> objs) {
        processNodes(color,objs.toArray());
    }
    public void processNodes(int color,Object... nodes) {
        Logger.debug("nodes.length=",nodes.length);
        Logger.debug("nodes[0] is null ",nodes.length==0 || nodes[0]==null);
        for( int a = 0; a < nodes.length ; a = a + 1 ) {
            Object node = nodes[a];
            Logger.debug("node==null?=",node==null);
            if ( node instanceof Token ) {
                Token token = (Token) node;
                processToken(colors,color,token);
                Logger.debug("Found a token");
            } else {
                if ( node instanceof TerminalNode ) {
                    TerminalNode tn = (TerminalNode) node;
                    Token token = tn.getSymbol();
                    processToken(colors,color,token);
                    Logger.debug("Found a TerminalNode");
                } else {
                    Logger.debug("No corresponding found, it's probably an error");
                }
            }
        }
    }*/
}
