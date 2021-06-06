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
package io.github.rosemoe.editor.mvc.controller.widgets.color;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;

import androidx.annotation.StyleableRes;

import java.util.HashMap;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.extension.events.Event;
import io.github.rosemoe.editor.mvc.controller.widgets.Widget;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.util.Objects;
import io.github.rosemoe.editor.widget.CodeEditor;

import static io.github.rosemoe.editor.mvc.controller.widgets.color.ColorSchemeEvent.*;

/**
 * This class manages the colors of editor.
 * Colors scheme must be very simple, eg: we define colors types.
 * Then it's up to the language analysis to apply and on which part of the text.
 * Themes cannot have language specific colors except by overriding getters below.
 * https://github.com/altercation/solarized
 * @author Rose
 */
public class ColorSchemeController extends Widget {

    CodeEditor editor;

    public static final int UPDATE_COLOR_SLEEP = 100;
    /**
     *
     * @return the default color scheme (theme)
     */
    public static final int TODO = 0xFFFF0000;
    public static final int HIDDEN = 0;
    private final int DEFAULT = HIDDEN;
    /**
     * Base color scheme to display the CodeEditor.
     * You should provide a gradient here.
     * Check for example the Solarized theme here : https://ethanschoonover.com/solarized/.
     */
    // Background tone
    public int base03 = DEFAULT;
    public int base02 = DEFAULT;
    // ContentMapController tone
    public int base01 = DEFAULT;
    public int base00 = DEFAULT; // textNormal, textSelected
    public int base0 = DEFAULT;
    public int base1 = DEFAULT; // line number text
    // Background tone
    public int base2 = DEFAULT; // line number panel, line number background, currentline, selected text background
    public int base3 = DEFAULT; // whole background


    // Accent colors : Theses colors are put on text for show up to user a particular meaning, purpose may vary between languages.
    /**
     * EXAMPLE: keyword.
     */
    private Integer accent1 = DEFAULT;
    public int getAccent1() { return accent1 == null ? getTextNormal() : accent1; }
    /**
     * EXAMPLE: Secondary keyword.
     */
    private Integer accent2 = DEFAULT;
    public int getAccent2() { return accent2 == null ? getTextNormal() : accent2; }
    /**
     * EXAMPLE: underline.
     */
    private Integer accent3 = DEFAULT;
    public int getAccent3() { return accent3 == null ? getTextNormal() : accent3; }
    /**
     * EXAMPLE: variable identifier.
     */
    private Integer accent4 = DEFAULT;
    public int getAccent4() { return accent4 == null ? getTextNormal() : accent4; }
    /**
     * EXAMPLE: Class identifier.
     */
    private Integer accent5 = DEFAULT;
    public int getAccent5() { return accent5 == null ? getTextNormal() : accent5; }
    /**
     * EXAMPLE: Function identifier.
     */
    private Integer accent6 = DEFAULT;
    public int getAccent6() { return accent6 == null ? getTextNormal() : accent6; }
    /**
     * EXAMPLE: Literals.
     */
    private Integer accent7 = DEFAULT;
    public int getAccent7() { return accent7 == null ? getTextNormal() : accent7; }
    /**
     * EXAMPLE: Punctuation.
     */
    private Integer accent8 = DEFAULT;
    public int getAccent8() { return accent8 == null ? getTextNormal() : accent8; }


    /**
     * We can choose to invert the color scheme.
     * Assuming Theme editor put white variant, isInverted <=> is black theme.
     */
    public void invertColorScheme() {
        int aux  = base03;
        base03 = base3; base3 = aux;
        aux = base02 ; base02 = base2 ; base2 = aux;
        aux = base01 ; base01 = base1 ; base1 = aux;
        aux = base00 ; base00 = base0 ; base0 = aux;
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
    public int getMatchedTextBackground() { return matchedTextBackground == null ? getAccent1(): matchedTextBackground; }
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
    public Integer scrollBarTrack = null;

    public int getScrollBarTrack() { return scrollBarTrack == null ? getWholeBackground() : scrollBarTrack; }
    public Integer nonprintablechar = null;
    public int getNonPrintableChar() { return nonprintablechar == null ? 0x00000000 : nonprintablechar ; }
    public Integer completionPanelBackground = null;
    public int getCompletionPanelBackground() { return completionPanelBackground == null ? base1 : completionPanelBackground ; }
    public Integer completionPanelCorner = null;
    public int getCompletionPanelCorner() { return completionPanelCorner == null ?  base2 : completionPanelCorner ; }
    public Integer underLine = null;
    public int getUnderline() { return underLine == null ? getAccent3(): underLine; }
    public Integer lineDivider = null;
    public int getLineDivider() { return lineDivider == null ? base1: lineDivider; }
    public Integer autoCompleteItemCurrentPosition = null;
    public int getAutoCompleteItemCurrentPosition() { return autoCompleteItemCurrentPosition == null ? base1: autoCompleteItemCurrentPosition; }
    public Integer autoCompleteItem = null;
    public int getAutoCompleteItem() { return autoCompleteItem == null ? base2: autoCompleteItem; }


    /**
     * Real color saver
     */
    protected final SparseIntArray mColors;
    /**
     * Host editor object
     */
    private CodeEditor mEditor;

    /**
     * For sub classes
     */
    public ColorSchemeController(CodeEditor editor) {
        this.editor = editor;
        subscribe(TYPE_COLOR_SCHEME);
        mColors = new SparseIntArray();
    }
    public ColorSchemeController(CodeEditor editor, boolean invert) {
        this.editor = editor;
        subscribe(TYPE_COLOR_SCHEME);
        mColors = new SparseIntArray();
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
        for(int entry : ALL_COLORS) {
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
    /**
     * Called by editor
     */
    public void attachEditor(CodeEditor editor) {
        mEditor = Objects.requireNonNull(editor);
    }
    public void updateColor(@StyleableRes int colorId, Integer colorValue) {
        if ( colorId == R.styleable.CodeEditor_widget_color_base03 ||
                colorId == R.styleable.CodeEditor_widget_color_base02 ||
                colorId == R.styleable.CodeEditor_widget_color_base01 ||
                colorId == R.styleable.CodeEditor_widget_color_base00 ||
                colorId == R.styleable.CodeEditor_widget_color_base0 ||
                colorId == R.styleable.CodeEditor_widget_color_base1 ||
                colorId == R.styleable.CodeEditor_widget_color_base2 ||
                colorId == R.styleable.CodeEditor_widget_color_base3 ) {
            if ( colorValue == null ) {
                return;
            }
        }


        if (colorId == R.styleable.CodeEditor_widget_color_base03) {
            base03 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base02) {
            base02 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base01) {
            base01 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base00) {
            base00 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base0) {
            base0 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base1) {
            base1 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base2) {
            base2 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_base3) {
            base3 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent1) {
            accent1 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent2) {
            accent2 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent3) {
            accent3 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent4) {
            accent4 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent5) {
            accent5 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent6) {
            accent6 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent7) {
            accent7 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_accent8) {
            accent8 = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_lineNumberPanel) {
            lineNumberPanel = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_lineNumberBackground) {
            lineNumberBackground = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_currentLine) {
            currentLine = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_textSelected) {
            textSelected = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_selectedTextBackground) {
            textSelectedBackground = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_lineNumberPanelText) {
            lineNumberPanelText = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_wholeBackground) {
            wholeBackground = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_textNormal) {
            textNormal = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_comment) {
            comment = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_matchedTextBackground) {
            matchedTextBackground = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_blockLine) {
            blockLine = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_blockLineCurrent) {
            blockLineCurrent = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_selectionInsert) {
            selectionInsert = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_selectionHandle) {
            selectionHandle = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_scrollbarThumb) {
            scrollbarthumb = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_scrollbarThumbPressed) {
            scrollbarthumbpressed = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_nonPrintableChar) {
            nonprintablechar = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_completion_color_panelBackground) {
            completionPanelBackground = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_completion_color_panelCorner) {
            completionPanelCorner = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_scrollbartrack) {
            scrollBarTrack = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_underline) {
            underLine = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_color_linedivider) {
            lineDivider = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_completion_color_item) {
            autoCompleteItem = colorValue;
        } else if (colorId == R.styleable.CodeEditor_widget_completion_color_itemCurrentPosition) {
            autoCompleteItemCurrentPosition = colorValue;
        }
    }
    int [] ALL_COLORS = new int[] {
        R.styleable.CodeEditor_widget_color_accent1,R.styleable.CodeEditor_widget_color_accent2,R.styleable.CodeEditor_widget_color_accent3,R.styleable.CodeEditor_widget_color_accent4,
                R.styleable.CodeEditor_widget_color_accent5,R.styleable.CodeEditor_widget_color_accent6,R.styleable.CodeEditor_widget_color_accent7,R.styleable.CodeEditor_widget_color_accent8,
                R.styleable.CodeEditor_widget_color_base03, R.styleable.CodeEditor_widget_color_base02, R.styleable.CodeEditor_widget_color_base01, R.styleable.CodeEditor_widget_color_base00,
                R.styleable.CodeEditor_widget_color_base0, R.styleable.CodeEditor_widget_color_base1, R.styleable.CodeEditor_widget_color_base2, R.styleable.CodeEditor_widget_color_base3, R.styleable.CodeEditor_widget_color_lineNumberPanel, R.styleable.CodeEditor_widget_color_lineNumberBackground, R.styleable.CodeEditor_widget_color_currentLine, R.styleable.CodeEditor_widget_color_textSelected,
                R.styleable.CodeEditor_widget_color_selectedTextBackground, R.styleable.CodeEditor_widget_color_lineNumberPanelText, R.styleable.CodeEditor_widget_color_wholeBackground, R.styleable.CodeEditor_widget_color_textNormal, R.styleable.CodeEditor_widget_color_comment, R.styleable.CodeEditor_widget_color_matchedTextBackground, R.styleable.CodeEditor_widget_color_blockLine, R.styleable.CodeEditor_widget_color_blockLineCurrent, R.styleable.CodeEditor_widget_color_selectionInsert,
                R.styleable.CodeEditor_widget_color_selectionHandle, R.styleable.CodeEditor_widget_color_scrollbarThumb, R.styleable.CodeEditor_widget_color_scrollbarThumbPressed, R.styleable.CodeEditor_widget_color_nonPrintableChar, R.styleable.CodeEditor_widget_completion_color_panelBackground, R.styleable.CodeEditor_widget_completion_color_panelCorner, R.styleable.CodeEditor_widget_color_scrollbartrack, R.styleable.CodeEditor_widget_color_underline, R.styleable.CodeEditor_widget_color_linedivider, R.styleable.CodeEditor_widget_completion_color_item, R.styleable.CodeEditor_widget_completion_color_itemCurrentPosition,
    };
    public void initFromAttributeSets(AttributeSet attrs, TypedArray a) {
        int test = 235363207;
        for(@StyleableRes int colorId : ALL_COLORS) {
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


    Thread t = null;
    @Override
    public void handleEventDispatch(Event e, String type, String subtype) {
        ColorSchemeEvent cse = (ColorSchemeEvent) e;
        @StyleableRes int colorId;
        int colorValue;
        HashMap<Integer, Integer> colors;
        switch(subtype) {
            case UPDATE_COLOR:
                colorId = (int) e.getArg(0);
                colorValue = (int) e.getArg(1);
                Logger.debug("Receive update color change colorId=",colorId,",colorValue=",colorValue);
                updateColor(colorId,colorValue);
                editor.setColorScheme(ColorSchemeController.this);
                break;
            case UPDATE_THEME:
                Logger.debug("Theme update received");
                colors = (HashMap<Integer, Integer>) e.getArg(0);
                applyDefault();
                for( Integer entry : colors.keySet()) {
                    updateColor(entry, colors.get(entry));
                }
                editor.setColorScheme(ColorSchemeController.this);
                break;
        }
    }
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        Logger.debug(offset, "0xFFFFFF=",0xFFFFFFFF,",TODO=",TODO,",DEFAULT=",DEFAULT);
        Logger.debug(offset , "base00=" , base00 ,",base01=" ,base01 ,",base02=" , base02 , ",base0=",base0, ",base1=",base1,",base2=",base2,",base3=",base3);
        Logger.debug(offset, "accent1=",getAccent1(),",accent2=",getAccent2(),",accent3=",getAccent3(),",accent4=",getAccent4(), "accent5=",getAccent5(),",accent6=",getAccent6(),",accent7=",getAccent7(),",accent8=",getAccent8());
        Logger.debug(offset, "lineNumberPanel=",lineNumberPanel,",lineNumberBackground=",lineNumberBackground,",currentLine=",currentLine,",textSelected=",textSelected);
        Logger.debug(offset, "textSelectedBackground=", textSelectedBackground, ",lineNumberPanelText=", lineNumberPanelText ,",wholeBackground=",wholeBackground);
        Logger.debug(offset, "textNormal=",textNormal,",comment=",comment,",matchedTextBackground=",matchedTextBackground);
        Logger.debug(offset , ",blockLine=", blockLine, ",blockLineCurrent=", blockLineCurrent);
        Logger.debug(offset,"selectionInsert=",selectionInsert,",selectionHandle=",selectionHandle,",scrollbarthumb=",scrollbarthumb,",scrollbarthumbpressed=",scrollbarthumbpressed);
        Logger.debug(offset, "nonprintablechar=", nonprintablechar, "panelBackground",completionPanelBackground,",completionPanelCorner=",completionPanelCorner);
        Logger.debug(offset, "scrollBarTrack=",scrollBarTrack,",underLine=",underLine,",lineDivider=",lineDivider,",autoCompleteItem=",autoCompleteItem,"autoCompleteItemCurrentPosition=",autoCompleteItemCurrentPosition);
    }
}
