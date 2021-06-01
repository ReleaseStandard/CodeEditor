package io.github.rosemoe.editor.langs.html;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.interfaces.AutoCompleteProvider;
import io.github.rosemoe.editor.mvc.view.TextAnalyzerView;
import io.github.rosemoe.editor.mvc.controller.CompletionItemController;

/**
 * Provides auto complete items for HTML Language
 * This is basic support
 *
 * @author Akash Yadav
 */

public class HTMLAutoComplete implements AutoCompleteProvider {
    @Override
    public List<CompletionItemController> getAutoCompleteItems(String prefix, boolean isInCodeBlock, TextAnalyzerView colors, int line) {
        List<CompletionItemController> items = new ArrayList<>();
        for (String key : HTMLLanguage.TAGS)
            if (key.toLowerCase().startsWith(prefix.toLowerCase()))
                items.add(new CompletionItemController(key, "HTML Tag"));

        for (String key : HTMLLanguage.TAGS)
            if (key.toLowerCase().startsWith(prefix.toLowerCase()))
                items.add(new CompletionItemController(key, "HTML Attribute"));
        return items;
    }
}
