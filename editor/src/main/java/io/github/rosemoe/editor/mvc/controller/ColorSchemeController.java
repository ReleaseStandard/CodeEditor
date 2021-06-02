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
package io.github.rosemoe.editor.mvc.controller;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.util.Objects;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.themes.Solarized;

/**
 * This class manages the colors of editor.
 * Colors scheme must be very simple, eg: we define colors types.
 * Then it's up to the language analysis to apply and on which part of the text.
 * Themes cannot have language specific colors except by overriding getters below.
 * https://github.com/altercation/solarized
 * @author Rose
 */
public class ColorSchemeController {

    public static ColorSchemeController DEFAULT() { return new Solarized(); }
    public static final int TODO = 0xFFFF0000;
    public static final int HIDDEN = 0;
    private final int DEFAULT = TODO;
    /**
     * That's our color scheme, no matter what it correspond to in the language, we just need colors.
     * The it's up to the analyzer to apply colors on keywords and so on.
     * By default the theme do nothing on the text.
     * If you want a specific behaviour in your theme, eg that the selected text color not follow rules, please override the method getTextSelected()
     */
    // Background tone
    public int base03 = DEFAULT;
    public int base02 = DEFAULT;
    // ContentController tone
    public int base01 = DEFAULT;
    public int base00 = DEFAULT; // textNormal, textSelected
    public int base0 = DEFAULT;
    public int base1 = DEFAULT; // line number text
    // Background tone
    public int base2 = DEFAULT; // line number panel, line number background, currentline, selected text background
    public int base3 = DEFAULT; // whole background
    // Accent colors
    public int accent1 = DEFAULT;
    public int accent2 = DEFAULT;
    public int accent3 = DEFAULT;
    public int accent4 = DEFAULT;
    public int accent5 = DEFAULT;
    public int accent6 = DEFAULT;
    public int accent7 = DEFAULT;
    public int accent8 = DEFAULT;
    /**
     * We can choose to invert the color scheme.
     * Assuming Theme editor put white variant, isInverted <=> is black theme.
     */
    protected boolean isInverted = false;
    public void invertColorScheme() {
        int aux  = base03;
        base03 = base3; base3 = aux;
        aux = base02 ; base02 = base2 ; base2 = aux;
        aux = base01 ; base01 = base1 ; base1 = aux;
        aux = base00 ; base00 = base0 ; base0 = aux;
        isInverted = true;
    }
    public String theme_name = "Default";
    public String theme_description = "Default description";
    /**
     * Here we put colors that do not depends on which language is parsed.
     * All language inserted into CodeEditor must have theses.
     * Override it in your theme if you want to change the behaviour.
     */
    public Integer lineNumberPanel = null;
    public int getLineNumberPanel() {
        return lineNumberPanel == null ? base2 : lineNumberPanel;
    }
    public Integer lineNumberBackground = null;
    public int getLineNumberBackground() {
        return lineNumberBackground == null ? base2 : lineNumberBackground;
    }
    public Integer currentLine = null;
    public int getCurrentLine() {
        return currentLine == null ? base2 : currentLine;
    }
    public Integer textSelected = null;
    public int getTextSelected() {
        return textSelected == null ? base2 : textSelected ;
    }
    public Integer textSelectedBackground = null;
    public int getTextSelectedBackground() {
        return textSelectedBackground == null ? base00 : textSelectedBackground;
    }
    public Integer lineNumberPanelText = null;
    public int getLineNumberPanelText() {
        return lineNumberPanelText == null ? base1 : lineNumberPanelText;
    }
    public Integer wholeBackground = null;
    public int getWholeBackground() {
        return wholeBackground == null ? base3 : wholeBackground;
    }
    public Integer textNormal = null;
    public int getTextNormal() {
        return textNormal == null ? base00: textNormal;
    }
    public Integer comment = null;
    public int getComment() { return comment == null ? base1: comment; }
    public Integer matchedTextBackground = null;
    public int getMatchedTextBackground() { return matchedTextBackground == null ? accent1: matchedTextBackground; }
    public Integer blockLine = null;
    public int getBlockLine() { return blockLine == null ? base2:blockLine ; }
    public Integer blockLineCurrent = null;
    public int getBlockLineCurrent() { return blockLineCurrent == null ? base2: blockLineCurrent; }
    public Integer selectionInsert = null;
    public int getSelectionInsert() { return selectionInsert == null ? getTextNormal() : selectionInsert ; }
    public Integer selectionHandle = null;
    public int getSelectionHandle() { return selectionHandle == null ? getTextNormal():selectionHandle ; }
    public Integer scrollbarthumb = null;
    public int getScrollBarThumb() { return scrollbarthumb == null ? base1 : scrollbarthumb ; }
    public Integer scrollbarthumbpressed = null;
    public int getScrollBarThumbPressed() { return scrollbarthumbpressed == null ? base2 : scrollbarthumbpressed ; }
    public Integer nonprintablechar = null;
    public int getNonPrintableChar() { return nonprintablechar == null ? 0x00000000 : nonprintablechar ; }
    public Integer completionPanelBackground = null;
    public int getCompletionPanelBackground() { return completionPanelBackground == null ? base1 : completionPanelBackground ; }
    public Integer completionPanelCorner = null;
    public int getCompletionPanelCorner() { return completionPanelCorner == null ?  base2 : completionPanelCorner ; }
    public Integer scrollBarTrack = null;
    public int getScrollBarTrack() { return scrollBarTrack == null ? base0 : scrollBarTrack; }
    public Integer underLine = null;
    public int getUnderline() { return underLine == null ? accent3: underLine; }
    public Integer lineDivider = null;
    public int getLineDivider() { return lineDivider == null ? base1: lineDivider; }

    /**
     * Real color saver
     */
    protected final SparseIntArray mColors;
    /**
     * Host editor object
     */
    private CodeEditor mEditor;

    public void initTheme() {
        Logger.debug("You must implement the init function of your theme in order to act on the text");
    }
    /**
     * For sub classes
     */
    public ColorSchemeController() {
        mColors = new SparseIntArray();
        initTheme();
    }
    public ColorSchemeController(boolean invert) {
        mColors = new SparseIntArray();
        initTheme();
        if ( invert ) {
            invertColorScheme();
        }
    }

    /**
     * Called by editor
     */
    public void attachEditor(CodeEditor editor) {
        mEditor = Objects.requireNonNull(editor);
    }
    public void initFromAttributeSets(AttributeSet attrs, TypedArray a) {
        int test = 235363207;
        for(int colorId : new int[] {
                R.styleable.CodeEditor_color_base03,
                R.styleable.CodeEditor_color_base02,
                R.styleable.CodeEditor_color_base01,
                R.styleable.CodeEditor_color_base00,
                R.styleable.CodeEditor_color_base0,
                R.styleable.CodeEditor_color_base1,
                R.styleable.CodeEditor_color_base2,
                R.styleable.CodeEditor_color_base3,
/*                R.styleable.CodeEditor_color_lineNumberPanel,
                R.styleable.CodeEditor_color_lineNumberBackground,
                R.styleable.CodeEditor_color_currentLine,
                R.styleable.CodeEditor_color_textSelected,
                R.styleable.CodeEditor_color_selectedTextBackground,
                R.styleable.CodeEditor_color_lineNumberPanelText,
                R.styleable.CodeEditor_color_wholeBackground,
                R.styleable.CodeEditor_color_textNormal,
                R.styleable.CodeEditor_color_comment,
                R.styleable.CodeEditor_color_matchedTextBackground,
                R.styleable.CodeEditor_color_blockLine,
                R.styleable.CodeEditor_color_blockLineCurrent,
                R.styleable.CodeEditor_color_selectionInsert,
                R.styleable.CodeEditor_color_selectionHandle,
                R.styleable.CodeEditor_color_scrollbarThumb,
                R.styleable.CodeEditor_color_scrollbarThumbPressed,
                R.styleable.CodeEditor_color_nonPrintableChar,
                R.styleable.CodeEditor_color_completionPanelBackground,
                R.styleable.CodeEditor_color_completionPanelCorner,
                R.styleable.CodeEditor_color_scrollbartrack,
                R.styleable.CodeEditor_color_underline,
                R.styleable.CodeEditor_color_linedivider,*/
        }) {
            int colorValue = a.getColor(colorId,test);
            if ( colorValue == test) { continue; }
            if (colorId == R.styleable.CodeEditor_color_base03) {
                base03 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base02) {
                base02 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base01) {
                base01 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base00) {
                base00 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base0) {
                base0 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base1) {
                base1 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base2) {
                base2 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_base3) {
                base3 = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_lineNumberPanel) {
                lineNumberPanel = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_lineNumberBackground ) {
                lineNumberBackground = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_currentLine) {
                currentLine = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_textSelected) {
                textSelected = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_selectedTextBackground) {
                textSelectedBackground = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_lineNumberPanelText) {
                lineNumberPanelText = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_wholeBackground) {
                wholeBackground = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_textNormal) {
                textNormal = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_comment) {
                comment = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_matchedTextBackground) {
                matchedTextBackground = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_blockLine) {
                blockLine = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_blockLineCurrent) {
                blockLineCurrent = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_selectionInsert) {
                selectionInsert = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_selectionHandle) {
                selectionHandle = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_scrollbarThumb) {
                scrollbarthumb = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_scrollbarThumbPressed) {
                scrollbarthumbpressed = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_nonPrintableChar) {
                nonprintablechar = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_completionPanelBackground) {
                completionPanelBackground = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_completionPanelCorner) {
                completionPanelCorner = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_scrollbartrack) {
                scrollBarTrack = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_underline) {
                underLine = colorValue;
            } else if (colorId == R.styleable.CodeEditor_color_linedivider) {
                lineDivider = colorValue;
            }
        }
        a.recycle();
    }
}
