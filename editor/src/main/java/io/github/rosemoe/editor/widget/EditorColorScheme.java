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
package io.github.rosemoe.editor.widget;

import android.util.SparseIntArray;

import io.github.rosemoe.editor.util.Objects;
import io.github.rosemoe.editor.widget.schemes.Solarized;

/**
 * This class manages the colors of editor.
 * Colors scheme must be very simple, eg: we define colors types.
 * Then it's up to the language analysis to apply and on which part of the text.
 * Themes cannot have language specific colors except by overriding getters below.
 * https://github.com/altercation/solarized
 * @author Rose
 */
public class EditorColorScheme {

    public static EditorColorScheme DEFAULT() { return new Solarized(); }
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
    // Content tone
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
     * Here we colors that do not depends on which language is parsed.
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
    public int getBlockLine() { return base1; }
    public int getSelectionInsert() { return getTextNormal(); }
    public int getSelectionHandle() { return getTextNormal(); }
    public int getScrollBarThumb() { return base1; }
    public int getScrollBarThumbPressed() { return base2; }

    //-------------View colors---------------------

    public static final int NON_PRINTABLE_CHAR = 31;
    public static final int AUTO_COMP_PANEL_CORNER = 20;
    public static final int AUTO_COMP_PANEL_BG = 19;

    /**
     * No longer supported
     */
    public static final int LINE_BLOCK_LABEL = 18;

    public static final int BLOCK_LINE_CURRENT = 15;
    public static final int SCROLL_BAR_TRACK = 13;
    public static final int UNDERLINE = 10;
    public static final int LINE_DIVIDER = 1;

    /**
     * Real color saver
     */
    protected final SparseIntArray mColors;
    /**
     * Host editor object
     */
    private CodeEditor mEditor;

    /**
     * Create a new ColorScheme for the given editor
     *
     * @param editor Host editor
     */
    EditorColorScheme(CodeEditor editor) {
        mEditor = editor;
        mColors = new SparseIntArray();
    }

    /**
     * For sub classes
     */
    public EditorColorScheme() {
        mColors = new SparseIntArray();
    }

    /**
     * Called by editor
     */
    void attachEditor(CodeEditor editor) {
        if (mEditor != null) {
            throw new IllegalStateException("A editor is already attached to this ColorScheme object");
        }
        mEditor = Objects.requireNonNull(editor);
    }

    /**
     * Apply a new color for the given type
     *
     * @param type  The type
     * @param color New color
     */
    public void setColor(int type, int color) {
        //Do not change if the old value is the same as new value
        //due to avoid unnecessary invalidate() calls
        int old = getColor(type);
        if (old == color) {
            return;
        }

        mColors.put(type, color);

        //Notify the editor
        if (mEditor != null) {
            mEditor.onColorUpdated(type);
        }
    }

    /**
     * Get color by type
     *
     * @param type The type
     * @return The color for type
     */
    public int getColor(int type) {
        return mColors.get(type);
    }

}
