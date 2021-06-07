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
package io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer;

import io.github.rosemoe.editor.mvc.controller.core.codeanalysis.analyzer.CodeAnalyzerResult;
import io.github.rosemoe.editor.util.Logger;

/**
 * Token analysis produce a token with at a line and column in editor.
 */
public abstract class TokenEmitterResult extends CodeAnalyzerResult {
    @Override
    public void putResult(Object... args) {
        if( args.length < 2 ) {
            Logger.debug("Not enough arguments to put in this result");
            return;
        }
        putResult(args[0],args[1]);
    }
    public void putResult(int col, int line) {
        Logger.debug("Using default implementation");
    }
}
