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
package io.github.rosemoe.editor.core.util;

import android.util.Log;

public class Logger {

    public static final String LOG_TAG = "CodeEditor";
    public static boolean DEBUG = true;
    public static boolean VERBOSE = true;
    public static String OFFSET = "    ";

    /**
     * This function is used to Show debug information to end user.
     * WARNING : you must always put Logger.debug on a single line.
     *   scripts/getprodcode.sh can so remove them clearly.
     */
    public static void debug() {
        debug("");
    }
    public static void debug(Object ...args) {
        if (! DEBUG ) { return ; }
        Log.v(LOG_TAG + "/" + CallStack.getLastCaller(), getMessage(args));
    }
    public static void v(Object ...args) {
        if ( ! VERBOSE ) { return ; }
        Log.v(LOG_TAG + "/" + CallStack.getLastCaller(), getMessage(args));
    }
    public static String getMessage(Object ...args) {
        String msg = "";
        for(Object arg : args) {
            if ( arg == null ) {
                msg += "null";
            }
            else {
                msg += arg.toString();
            }
        }
        return msg;
    }
    public static void printStackTrace() {
        if ( Logger.DEBUG ) {
            CallStack.printStackTrace();
        }
    }
}
