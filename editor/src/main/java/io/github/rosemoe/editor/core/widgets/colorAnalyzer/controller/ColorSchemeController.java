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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller;

import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.StyleableRes;

import java.util.HashMap;
import java.util.Map;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.extension.events.Event;
import io.github.rosemoe.editor.core.widgets.Widget;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.CodeEditor;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.extension.ColorSchemeEvent;

import static io.github.rosemoe.editor.core.widgets.colorAnalyzer.extension.ColorSchemeEvent.*;

/**
 * This class manages the colors of editor.
 * Colors scheme must be very simple, eg: we define colors types.
 * Then it's up to the language analysis to apply and on which part of the text.
 * Themes cannot have language specific colors except by overriding getters below.
 * https://github.com/altercation/solarized
 * @author Rose
 */
public class ColorSchemeController extends Widget {

    public static final int UPDATE_COLOR_SLEEP = 100;
    private static final int TODO = 0xFFFF0000;
    private static final int HIDDEN = 0;
    private static final int DEFAULT = HIDDEN;
    // colors definition
    private HashMap<Integer,Integer> COLORS = new HashMap<Integer, Integer>(){{
        put(R.styleable.CodeEditor_widget_color_base03,DEFAULT);put(R.styleable.CodeEditor_widget_color_base02,DEFAULT);put(R.styleable.CodeEditor_widget_color_base01,DEFAULT);put(R.styleable.CodeEditor_widget_color_base00,DEFAULT);put(R.styleable.CodeEditor_widget_color_base0,DEFAULT);put(R.styleable.CodeEditor_widget_color_base1,DEFAULT);put(R.styleable.CodeEditor_widget_color_base2,DEFAULT);
        put(R.styleable.CodeEditor_widget_color_base3,DEFAULT);put(R.styleable.CodeEditor_widget_color_accent1,null);put(R.styleable.CodeEditor_widget_color_accent2,null);put(R.styleable.CodeEditor_widget_color_accent3,null);put(R.styleable.CodeEditor_widget_color_accent4,null);put(R.styleable.CodeEditor_widget_color_accent5,null);put(R.styleable.CodeEditor_widget_color_accent6,null);put(R.styleable.CodeEditor_widget_color_accent7,null);
        put(R.styleable.CodeEditor_widget_color_accent8,null);put(R.styleable.CodeEditor_widget_color_lineNumberPanel,null);put(R.styleable.CodeEditor_widget_color_lineNumberBackground,null);put(R.styleable.CodeEditor_widget_color_currentLine,null);put(R.styleable.CodeEditor_widget_color_textSelected,null);put(R.styleable.CodeEditor_widget_color_selectedTextBackground,null);put(R.styleable.CodeEditor_widget_color_lineNumberPanelText,null);put(R.styleable.CodeEditor_widget_color_wholeBackground,null);put(R.styleable.CodeEditor_widget_color_textNormal,null);
        put(R.styleable.CodeEditor_widget_color_comment,null);put(R.styleable.CodeEditor_widget_color_matchedTextBackground,null);put(R.styleable.CodeEditor_widget_color_blockLine,null);put(R.styleable.CodeEditor_widget_color_blockLineCurrent,null);put(R.styleable.CodeEditor_widget_color_selectionInsert,null);put(R.styleable.CodeEditor_widget_color_selectionHandle,null);put(R.styleable.CodeEditor_widget_color_scrollbarThumb,null);put(R.styleable.CodeEditor_widget_color_scrollbarThumbPressed,null);put(R.styleable.CodeEditor_widget_color_nonPrintableChar,null);
        put(R.styleable.CodeEditor_widget_completion_color_panelBackground,null);put(R.styleable.CodeEditor_widget_completion_color_panelCorner,null);put(R.styleable.CodeEditor_widget_color_scrollbartrack,null);put(R.styleable.CodeEditor_widget_color_underline,null);put(R.styleable.CodeEditor_widget_color_linedivider,null);put(R.styleable.CodeEditor_widget_completion_color_item,null);put(R.styleable.CodeEditor_widget_completion_color_itemCurrentPosition,null);
    }};
    // defined for easy key retrieve
    public static final HashMap<String,Integer> CONVENINENT = new HashMap<String, Integer>(){{
        put("base02",R.styleable.CodeEditor_widget_color_base02);put("base01",R.styleable.CodeEditor_widget_color_base01);put("base00",R.styleable.CodeEditor_widget_color_base00);
        put("base0",R.styleable.CodeEditor_widget_color_base0);put("base1",R.styleable.CodeEditor_widget_color_base1);put("base2",R.styleable.CodeEditor_widget_color_base2);put("base3",R.styleable.CodeEditor_widget_color_base3);put("accent1",R.styleable.CodeEditor_widget_color_accent1);put("accent2",R.styleable.CodeEditor_widget_color_accent2);put("accent3",R.styleable.CodeEditor_widget_color_accent3);put("accent4",R.styleable.CodeEditor_widget_color_accent4);put("accent5",R.styleable.CodeEditor_widget_color_accent5);
        put("accent6",R.styleable.CodeEditor_widget_color_accent6);put("accent7",R.styleable.CodeEditor_widget_color_accent7);put("accent8",R.styleable.CodeEditor_widget_color_accent8);put("lineNumberPanel",R.styleable.CodeEditor_widget_color_lineNumberPanel);put("lineNumberBackground",R.styleable.CodeEditor_widget_color_lineNumberBackground);put("currentLine",R.styleable.CodeEditor_widget_color_currentLine);put("textSelected",R.styleable.CodeEditor_widget_color_textSelected);
        put("selectedTextBackground",R.styleable.CodeEditor_widget_color_selectedTextBackground);put("lineNumberPanelText",R.styleable.CodeEditor_widget_color_lineNumberPanelText);put("wholeBackground",R.styleable.CodeEditor_widget_color_wholeBackground);put("textNormal",R.styleable.CodeEditor_widget_color_textNormal);put("comment",R.styleable.CodeEditor_widget_color_comment);put("matchedTextBackground",R.styleable.CodeEditor_widget_color_matchedTextBackground);put("blockLine",R.styleable.CodeEditor_widget_color_blockLine);put("blockLineCurrent",R.styleable.CodeEditor_widget_color_blockLineCurrent);
        put("selectionInsert",R.styleable.CodeEditor_widget_color_selectionInsert);put("selectionHandle",R.styleable.CodeEditor_widget_color_selectionHandle);put("scrollbarThumb",R.styleable.CodeEditor_widget_color_scrollbarThumb);put("scrollbarThumbPressed",R.styleable.CodeEditor_widget_color_scrollbarThumbPressed);put("nonPrintableChar",R.styleable.CodeEditor_widget_color_nonPrintableChar);put("panelBackground",R.styleable.CodeEditor_widget_completion_color_panelBackground);put("panelCorner",R.styleable.CodeEditor_widget_completion_color_panelCorner);put("scrollbartrack",R.styleable.CodeEditor_widget_color_scrollbartrack);put("underline",R.styleable.CodeEditor_widget_color_underline);put("linedivider",R.styleable.CodeEditor_widget_color_linedivider);put("item",R.styleable.CodeEditor_widget_completion_color_item);put("itemCurrentPosition",R.styleable.CodeEditor_widget_completion_color_itemCurrentPosition);
    }};


    // Accent colors : Theses colors are put on text for show up to user a particular meaning, purpose may vary between languages.
    /**
     * EXAMPLE: keyword.
     */
    public int getAccent1() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent1),getTextNormal());
    }
    /**
     * EXAMPLE: Secondary keyword.
     */
    public int getAccent2() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent2),getTextNormal());
    }
    /**
     * EXAMPLE: underline.
     */
    public int getAccent3() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent3),getTextNormal());
    }
    /**
     * EXAMPLE: variable identifier.
     */
    public int getAccent4() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent4),getTextNormal());
    }
    /**
     * EXAMPLE: Class identifier.
     */
    public int getAccent5() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent5),getTextNormal());
    }
    /**
     * EXAMPLE: Function identifier.
     */
    public int getAccent6() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent6),getTextNormal());
    }
    /**
     * EXAMPLE: Literals.
     */
    public int getAccent7() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent7),getTextNormal());
    }
    /**
     * EXAMPLE: Punctuation.
     */
    public int getAccent8() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_accent8),getTextNormal());
    }


    /**
     * We can choose to invert the color scheme.
     * Assuming Theme editor put white variant, isInverted <=> is black theme.
     */
    public void invertColorScheme() {
        int aux  = COLORS.get(R.styleable.CodeEditor_widget_color_base03);
        COLORS.put(R.styleable.CodeEditor_widget_color_base3, COLORS.get(R.styleable.CodeEditor_widget_color_base03));
        COLORS.put(R.styleable.CodeEditor_widget_color_base03, COLORS.get(aux));
        COLORS.put(R.styleable.CodeEditor_widget_color_base2, COLORS.get(R.styleable.CodeEditor_widget_color_base02));
        COLORS.put(R.styleable.CodeEditor_widget_color_base02, COLORS.get(aux));
        COLORS.put(R.styleable.CodeEditor_widget_color_base1, COLORS.get(R.styleable.CodeEditor_widget_color_base01));
        COLORS.put(R.styleable.CodeEditor_widget_color_base01, COLORS.get(aux));
        COLORS.put(R.styleable.CodeEditor_widget_color_base0, COLORS.get(R.styleable.CodeEditor_widget_color_base00));
        COLORS.put(R.styleable.CodeEditor_widget_color_base00, COLORS.get(aux));
    }


    /**
     * Here we put colors that do not depends on which language is parsed.
     * All language inserted into CodeEditor must have theses.
     * Override it in your theme if you want to change the behaviour.
     */
    public int getColor(String name) {
        return getColor(CONVENINENT.get(name));
    }
    public int getColor(@StyleableRes Integer colorId) {
            if( isBaseColor(colorId) ) {
                return COLORS.get(colorId);
            }
            if( R.styleable.CodeEditor_widget_color_accent1 == colorId ) {
                return getAccent1();
            } else if ( R.styleable.CodeEditor_widget_color_accent2 == colorId ) {
                return getAccent2();
            } else if ( R.styleable.CodeEditor_widget_color_accent3 == colorId ) {
                return getAccent3();
            }else if ( R.styleable.CodeEditor_widget_color_accent4 == colorId ) {
                return getAccent4();
            }else if ( R.styleable.CodeEditor_widget_color_accent5 == colorId ) {
                return getAccent5();
            }else if ( R.styleable.CodeEditor_widget_color_accent6 == colorId ) {
                return getAccent6();
            }else if ( R.styleable.CodeEditor_widget_color_accent7 == colorId ) {
                return getAccent7();
            }else if ( R.styleable.CodeEditor_widget_color_accent8 == colorId ) {
                return getAccent8();
            }else if ( R.styleable.CodeEditor_widget_color_lineNumberPanel == colorId ) {
                return getLineNumberPanel();
            }else if ( R.styleable.CodeEditor_widget_color_lineNumberBackground == colorId ) {
                return getLineNumberBackground();
            }else if ( R.styleable.CodeEditor_widget_color_currentLine == colorId ) {
                return getCurrentLine();
            }else if ( R.styleable.CodeEditor_widget_color_textSelected == colorId ) {
                return getTextSelected();
            }else if ( R.styleable.CodeEditor_widget_color_selectedTextBackground == colorId ) {
                return getTextSelectedBackground();
            }else if ( R.styleable.CodeEditor_widget_color_lineNumberPanelText == colorId ) {
                return getLineNumberPanelText();
            }else if ( R.styleable.CodeEditor_widget_color_wholeBackground== colorId ) {
                return getWholeBackground();
            }else if ( R.styleable.CodeEditor_widget_color_textNormal == colorId ) {
                return getTextNormal();
            }else if ( R.styleable.CodeEditor_widget_color_comment == colorId ) {
                return getComment();
            }else if ( R.styleable.CodeEditor_widget_color_matchedTextBackground == colorId ) {
                return getMatchedTextBackground();
            }else if ( R.styleable.CodeEditor_widget_color_blockLine == colorId ) {
                return getBlockLine();
            }else if ( R.styleable.CodeEditor_widget_color_blockLineCurrent== colorId ) {
                return getBlockLineCurrent();
            }else if ( R.styleable.CodeEditor_widget_color_selectionInsert== colorId ) {
                return getSelectionInsert();
            }else if ( R.styleable.CodeEditor_widget_color_selectionHandle == colorId ) {
                return getSelectionHandle();
            }else if ( R.styleable.CodeEditor_widget_color_scrollbarThumb == colorId ) {
                return getScrollBarThumb();
            }else if ( R.styleable.CodeEditor_widget_color_scrollbarThumbPressed == colorId ) {
                return getScrollBarThumbPressed();
            }else if ( R.styleable.CodeEditor_widget_color_nonPrintableChar == colorId ) {
                return getNonPrintableChar();
            }else if ( R.styleable.CodeEditor_widget_completion_color_panelBackground == colorId ) {
                return getCompletionPanelBackground();
            }else if ( R.styleable.CodeEditor_widget_completion_color_panelCorner== colorId ) {
                return getCompletionPanelCorner();
            }else if ( R.styleable.CodeEditor_widget_color_scrollbartrack== colorId ) {
                return getScrollBarTrack();
            }else if ( R.styleable.CodeEditor_widget_color_underline == colorId ) {
                return getUnderline();
            }else if ( R.styleable.CodeEditor_widget_color_linedivider== colorId ) {
                return getLineDivider();
            }else if ( R.styleable.CodeEditor_widget_completion_color_item == colorId ) {
                return getAutoCompleteItem();
            }else if ( R.styleable.CodeEditor_widget_completion_color_itemCurrentPosition == colorId ) {
                return getAutoCompleteItemCurrentPosition();
            }
        return DEFAULT;
    }
    private int getColor(Integer color, Integer fallback) {
        return color == null ? fallback: color;
    }
    public int getLineNumberPanel() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_lineNumberPanel),
                COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getLineNumberBackground() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_lineNumberBackground),
                COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getCurrentLine() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_currentLine),
                COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getTextSelected() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_textSelected),
                COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getTextSelectedBackground() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_selectedTextBackground),
                COLORS.get(R.styleable.CodeEditor_widget_color_base00));
    }
    public int getLineNumberPanelText() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_lineNumberPanelText),
                COLORS.get(R.styleable.CodeEditor_widget_color_base1));
    }
    public int getWholeBackground() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_wholeBackground),COLORS.get(R.styleable.CodeEditor_widget_color_base3));
    }
    public int getTextNormal() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_textNormal),COLORS.get(R.styleable.CodeEditor_widget_color_base00));
    }
    public int getComment() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_comment),COLORS.get(R.styleable.CodeEditor_widget_color_base1));
    }
    public int getMatchedTextBackground() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_matchedTextBackground),getAccent1());
    }
    public int getBlockLine() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_blockLine),COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getBlockLineCurrent() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_blockLineCurrent),COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getSelectionInsert() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_selectionInsert),getTextNormal());
    }
    public int getSelectionHandle() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_selectionHandle),getTextNormal());
    }
    public int getScrollBarThumb() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_scrollbarThumb),COLORS.get(R.styleable.CodeEditor_widget_color_base1));
    }
    public int getScrollBarThumbPressed() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_scrollbarThumbPressed),COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }

    public int getScrollBarTrack() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_scrollbartrack),getWholeBackground());
    }
    public int getNonPrintableChar() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_nonPrintableChar),0x00000000);
    }
    public int getCompletionPanelBackground() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_completion_color_panelBackground),COLORS.get(R.styleable.CodeEditor_widget_color_base1));
    }
    public int getCompletionPanelCorner() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_completion_color_panelCorner),COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }
    public int getUnderline() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_underline),getAccent3());
    }
    public int getLineDivider() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_color_linedivider),COLORS.get(R.styleable.CodeEditor_widget_color_base1));
    }
    public int getAutoCompleteItemCurrentPosition() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_completion_color_itemCurrentPosition),COLORS.get(R.styleable.CodeEditor_widget_color_base1));
    }
    public int getAutoCompleteItem() {
        return getColor(COLORS.get(R.styleable.CodeEditor_widget_completion_color_item),COLORS.get(R.styleable.CodeEditor_widget_color_base2));
    }

    /**
     * For sub classes
     */
    public ColorSchemeController(CodeEditor editor) {
        super(editor);
        initialize(false);
    }
    public ColorSchemeController(CodeEditor editor, boolean invert) {
        super(editor);
        initialize(invert);
    }
    private void initialize(boolean invert) {
        name        = "color";
        description = "widget responsible from displaying color to the screen";
        subscribe(ColorSchemeEvent.class);
        if ( invert ) {
            invertColorScheme();
        }
    }
    public static int DEFAULT_TEXT_COLOR() {
        return 0xFF999999;
    }
    public static int DEFAULT_BACKGROUND_COLOR() {
        return 0xffffffff;
    }
    public void applyDefault() {
        int text = DEFAULT_TEXT_COLOR();
        int background = DEFAULT_BACKGROUND_COLOR();
        for(int entry : COLORS.keySet()) {
            updateColor(entry,null);
        }
        updateColor(R.styleable.CodeEditor_widget_color_base03, background);
        updateColor(R.styleable.CodeEditor_widget_color_base02, background);
        updateColor(R.styleable.CodeEditor_widget_color_base01, text);
        updateColor(R.styleable.CodeEditor_widget_color_base00, text);
        updateColor(R.styleable.CodeEditor_widget_color_base0, text);
        updateColor(R.styleable.CodeEditor_widget_color_base1, 0xFFdddddd);
        updateColor(R.styleable.CodeEditor_widget_color_base2, 0x10000000);
        updateColor(R.styleable.CodeEditor_widget_color_base3, background);
    }
    private boolean isBaseColor(@StyleableRes Integer colorId) {
        return colorId == R.styleable.CodeEditor_widget_color_base03 ||
                colorId == R.styleable.CodeEditor_widget_color_base02 ||
                colorId == R.styleable.CodeEditor_widget_color_base01 ||
                colorId == R.styleable.CodeEditor_widget_color_base00 ||
                colorId == R.styleable.CodeEditor_widget_color_base0 ||
                colorId == R.styleable.CodeEditor_widget_color_base1 ||
                colorId == R.styleable.CodeEditor_widget_color_base2 ||
                colorId == R.styleable.CodeEditor_widget_color_base3;
    }
    public void updateColor(@StyleableRes int colorId, Integer colorValue) {
        if ( isBaseColor(colorId) ) {
            if ( colorValue == null ) {
                return;
            }
        }
        COLORS.put(colorId,colorValue);
    }
    public void initFromAttributeSets(AttributeSet attrs, TypedArray a) {
        int test = 235363207;
        for(@StyleableRes int colorId : COLORS.keySet()) {
            int colorValue = a.getColor(colorId,test);
            if ( colorValue == test) { continue; }
            updateColor(colorId, colorValue);
        }
        a.recycle();
    }

    @Override
    public void handleEventEmit(Event e) {
        super.handleEventEmit(e);
        editor.plugins.dispatch(e);
    }


    @Override
    public void handleEventDispatch(Event e, String subtype) {
        ColorSchemeEvent cse = (ColorSchemeEvent) e;
        @StyleableRes int colorId;
        int colorValue;
        HashMap<Integer, Integer> colors;
        switch(subtype) {
            case UPDATE_COLOR:
                colorId = (int) e.getArg(0);
                colorValue = (int) e.getArg(1);
                Logger.v("Receive update color change colorId=",colorId,",colorValue=",colorValue);
                updateColor(colorId,colorValue);
                reloadColorScheme();
                break;
            case UPDATE_THEME:
                Logger.v("Theme update received");
                //noinspection unchecked
                colors = (HashMap<Integer, Integer>) e.getArg(0);
                applyDefault();
                for( Integer entry : colors.keySet()) {
                    updateColor(entry, colors.get(entry));
                }
                reloadColorScheme();
                break;
        }
    }

    /**
     * Reload colors into the editor.
     */
    public void reloadColorScheme() {
        Logger.debug();
        // Update spanner
        //editor.analyzer.mCodeAnalyzer.lockView();
        //editor.analyzer.mCodeAnalyzer.lockBuild();
        //editor.analyzer.mCodeAnalyzer.clearInBuild();
        /*
        if (editor.analyzer != null) {
            editor.analyzer.shutdown();
            editor.analyzer.setCallback(null);
        }
        editor.setEditorLanguage(editor.mLanguage);
        editor.invalidate();
        */
        editor.invalidate();
    }
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        Logger.debug(offset, "0xFFFFFF=",0xFFFFFFFF,",TODO=",TODO,",DEFAULT=",DEFAULT);
        for(Map.Entry<String, Integer> e : CONVENINENT.entrySet()){
            Logger.debug(e.getKey(),"\t",COLORS.get(e.getValue()));
        }
    }
}
