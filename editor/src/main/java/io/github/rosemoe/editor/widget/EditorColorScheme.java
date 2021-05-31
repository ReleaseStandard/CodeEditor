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
 * https://github.com/altercation/solarized
 * @author Rose
 */
public class EditorColorScheme {

    public static EditorColorScheme DEFAULT() { return new Solarized(); }
    public static final int TODO = 0xFFFF0000;
    private final int DEFAULT = 0x00000000;
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

    //-----------------Highlight colors-----------

    public static final int ANNOTATION = 28;
    public static final int FUNCTION_NAME = 27;
    public static final int IDENTIFIER_NAME = 26;
    public static final int LITERAL = 24;
    public static final int OPERATOR = 23;
    public static final int KEYWORD = 21;

    //-------------View colors---------------------

    public static final int NON_PRINTABLE_CHAR = 31;
    public static final int MATCHED_TEXT_BACKGROUND = 29;
    public static final int AUTO_COMP_PANEL_CORNER = 20;
    public static final int AUTO_COMP_PANEL_BG = 19;

    /**
     * No longer supported
     */
    public static final int LINE_BLOCK_LABEL = 18;

    public static final int BLOCK_LINE_CURRENT = 15;
    public static final int BLOCK_LINE = 14;
    public static final int SCROLL_BAR_TRACK = 13;
    public static final int SCROLL_BAR_THUMB_PRESSED = 12;
    public static final int SCROLL_BAR_THUMB = 11;
    public static final int UNDERLINE = 10;
    public static final int CURRENT_LINE = 9;
    public static final int SELECTION_HANDLE = 8;
    public static final int SELECTION_INSERT = 7;
    public static final int SELECTED_TEXT_BACKGROUND = 6;
    public static final int LINE_NUMBER_BACKGROUND = 3;
    public static final int LINE_NUMBER = 2;
    public static final int LINE_DIVIDER = 1;

    /**
     * Min pre-defined color id
     */
    protected static final int START_COLOR_ID = 1;

    /**
     * Max pre-defined color id
     */
    protected static final int END_COLOR_ID = 31;
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
        applyDefault();
    }

    /**
     * For sub classes
     */
    public EditorColorScheme() {
        mColors = new SparseIntArray();
        applyDefault();
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
     * Apply default colors
     */
    public void applyDefault() {
        for (int i = START_COLOR_ID; i <= END_COLOR_ID; i++) {
            applyDefault(i);
        }
    }

    /**
     * Apply default color for the given type
     *
     * @param type The type
     */
    private void applyDefault(int type) {
        int color;
        switch (type) {
            case LINE_DIVIDER:
                color = 0xFFdddddd;
                break;
            case LINE_NUMBER:
                color = 0xFF808080;
                break;
            case LINE_NUMBER_BACKGROUND:
                color = 0xfff0f0f0;
                break;
            case AUTO_COMP_PANEL_BG:
            case AUTO_COMP_PANEL_CORNER:
                color = 0xffffffff;
                break;
            case OPERATOR:
                color = 0xFF0066D6;
                break;
            case SELECTION_INSERT:
                color = 0xFF03EBEB;
                break;
            case UNDERLINE:
                color = 0xff000000;
                break;
            case SELECTION_HANDLE:
                color = 0xff03ebff;
                break;
            case ANNOTATION:
                color = 0xFF03A9F4;
                break;
            case CURRENT_LINE:
                color = 0x10000000;
                break;
            case KEYWORD:
                color = 0xFF2196F3;
                break;
            case LITERAL:
                color = 0xFF008080;
                break;
            case SCROLL_BAR_THUMB:
                color = 0xffd8d8d8;
                break;
            case SCROLL_BAR_THUMB_PRESSED:
                color = 0xFF27292A;
                break;
            case BLOCK_LINE:
                color = 0xffdddddd;
                break;
            case LINE_BLOCK_LABEL:
            case SCROLL_BAR_TRACK:
                color = 0;
                break;
            case BLOCK_LINE_CURRENT:
                color = 0xff999999;
                break;
            case IDENTIFIER_NAME:
            case FUNCTION_NAME:
                color = 0xff333333;
                break;
            case NON_PRINTABLE_CHAR:
                color = 0xff505050;
                break;
            default:
                color=0;
            //default:
            //    throw new IllegalArgumentException("Unexpected type:" + type);
        }
        setColor(type, color);
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
