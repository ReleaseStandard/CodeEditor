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
package io.github.rosemoe.editor.core.widgets.userinput.model;

public class UserInputConnexionModel {
    public int composingLine = -1;
    public int composingStart = -1;
    public int composingEnd = -1;
    public boolean invalid;
    public void invalid() {
        //Logs.log("Connection is set to invalid");
        //Logs.dumpStack();
        invalid = true;
        composingEnd = composingStart = composingLine = -1;
    }
    public void reset() {
        //Logs.log("Connection reset");
        composingEnd = composingStart = composingLine = -1;
        invalid = false;
    }
    public void init(Integer line, Integer startCol, Integer endCol) {
        if ( line != null ) { composingLine = line; }
        if ( startCol != null ) { composingStart = startCol; }
        if ( endCol != null ) { composingEnd = endCol; }
    }
}
