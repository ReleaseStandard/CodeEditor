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
package io.github.rosemoe.editor.core.codeanalysis.analyzer;

import java.util.HashMap;

/**
 * Convenience class, for a given language, user provide
 * analyzers : example, spellcheck token emitter.
 *
 * This is called by this analysis thread.
 * Any analyzer attached to this container will receive analyze updates.
 */
public class CodeAnalyzerContainer {

    public HashMap<Integer, CodeAnalyzer> analyzers = new HashMap<>();

    public void analyze(CharSequence content, CodeAnalyzerThread.Delegate delegate) {
        for(CodeAnalyzer analyzer : analyzers.values()) {
            analyzer.analyze(content, delegate);
        }
    }

    public void add(CodeAnalyzer analyzer) {
        analyzers.put(analyzer.hashCode(),analyzer);
    }
    public void remove(CodeAnalyzer analyzer) {
        analyzers.remove(analyzer.hashCode());
    }
}
