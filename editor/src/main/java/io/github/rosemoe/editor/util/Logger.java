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
package io.github.rosemoe.editor.util;

import android.util.Log;

public class Logger {
    public static boolean DEBUG = true;
    public static String PREFIX = "TEST";
    public static String OFFSET = "    ";
    public static void debug() {
        debug("");
    }
    public static void debug(Object ...args) {
        if (! DEBUG ) { return ; }
        String msg = "";
        for(Object arg : args) {
            msg += arg.toString();
        }
        Log.v(PREFIX + "/" + CallStack.getLastCaller(), msg);
    }
}