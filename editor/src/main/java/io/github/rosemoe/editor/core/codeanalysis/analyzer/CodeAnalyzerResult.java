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

import io.github.rosemoe.editor.core.util.Logger;

public abstract class CodeAnalyzerResult {
    /**
     * This method choose how to process arguments when the analyzer send some.
     * @param args
     */
    public abstract void dispatchResult(Object... args);

    /**
     * Report if the result is computer/ready.
     * @return is the result computed
     */
    public boolean isReady() {
        return true;
    }
    public boolean isAvaliable() {
        return isReady();
    }

    /**
     * Clear the analysis report.
     */
    public void clear() {

    }

    public CodeAnalyzerResultRecycler recycler = new CodeAnalyzerResultRecycler();

    /**
     * Recycle this result.
     */
    public void recycle() {
        Logger.v("Recycling results");
        //if ( recycler != null ) {
            //recycler.recycle();
        //}
    }
    public void dump() {
        dump("");
    }
    public void dump(String offset) {
        Logger.debug(offset,"CodeAnalyzerResult:");
        Logger.debug(offset," isReady=",isReady());
        Logger.debug(offset, " isAvaliable=",isAvaliable());
    }

    public abstract CodeAnalyzerResult clone();

}
