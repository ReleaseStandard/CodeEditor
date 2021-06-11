package io.github.rosemoe.editor.langs.html;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.codeanalysis.CodeAnalyzerResultColor;
import io.github.rosemoe.editor.core.widgets.completion.controller.AutoCompleteProviderController;
import io.github.rosemoe.editor.core.widgets.completion.controller.CompletionItemController;

/**
 * Provides auto complete items for HTML Language
 * This is basic support
 *
 * @author Akash Yadav
 */

public class HTMLAutoComplete implements AutoCompleteProviderController {
    @Override
    public List<CompletionItemController> getAutoCompleteItems(String prefix, boolean isInCodeBlock, CodeAnalyzerResultColor colors, int line) {
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
