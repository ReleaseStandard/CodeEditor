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

import android.util.SparseIntArray;

import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.util.Objects;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.schemes.Solarized;

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
    public int getLineNumberPanel() {
        return base2;
    }
    public int getLineNumberBackground() {
        return base2;
    }
    public int getCurrentLine() {
        return base2;
    }
    public int getTextSelected() {
        return base2;
    }
    public int getSelectedTextBackground() {
        return base00;
    }
    public int getLineNumberPanelText() {
        return base1;
    }
    public int getWholeBackground() {
        return base3;
    }
    public int getTextNormal() {
        return base00;
    }
    public int getComment() { return base1; }
    public int getMatchedTextBackground() { return accent1; }
    public int getBlockLine() { return base2; }
    public int getBlockLineCurrent() { return base2; }
    public int getSelectionInsert() { return getTextNormal(); }
    public int getSelectionHandle() { return getTextNormal(); }
    public int getScrollBarThumb() { return base1; }
    public int getScrollBarThumbPressed() { return base2; }
    public int getNonPrintableChar() { return 0x00000000; }
    public int getCompletionPanelBackground() { return base1; }
    public int getCompletionPanelCorner() { return base2; }
    public int getScrollBarTrack() { return base0; }
    public int getUnderline() { return accent3; }
    public int getLineDivider() { return base1; }

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
}
