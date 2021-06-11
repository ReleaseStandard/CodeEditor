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
package io.github.rosemoe.editor.plugins.langs.cobol85;

import io.github.rosemoe.editor.core.langs.LanguagePlugin;
import io.github.rosemoe.editor.core.codeanalysis.analyzer.CodeAnalyzer;
import io.github.rosemoe.editor.core.CodeEditor;

public class Cobol85Language extends LanguagePlugin {
    @Override
    public CodeAnalyzer getAnalyzer() {
        return new Cobol85Analyzer();
    }
    public Cobol85Language(CodeEditor editor) {
        super(editor);
        name = "Cobol85";
        description = "Cobol85 parser";
    }
}