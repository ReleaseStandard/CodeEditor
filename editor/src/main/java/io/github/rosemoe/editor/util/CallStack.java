package io.github.rosemoe.editor.util;

import android.util.Log;

import java.util.Map;
import java.util.Set;

import static java.lang.Thread.currentThread;

public class CallStack {


    public static String getLastCaller() {
        return getLastCaller(Thread.getAllStackTraces().entrySet());
    }

    public static String getLastCaller(Integer off) {
        return getLastCaller(Thread.getAllStackTraces().entrySet());
    }

    private static String getLastCaller(Set<Map.Entry<Thread, StackTraceElement[]>> set) {
        String c = "§";
        for (Map.Entry<Thread, StackTraceElement[]> entry : set) {
            if ( entry.getKey() == currentThread() ) {
                for(StackTraceElement ste : entry.getValue()) {
                    String classname = ste.getClassName();
                    String methodname = ste.getMethodName();
                    if (    (classname.lastIndexOf("io.github.rosemoe.editor") != -1 || classname.lastIndexOf("org.antlr.v4") != -1) &&
                            classname.lastIndexOf(".util.Logger") == -1 &&
                            classname.lastIndexOf(".util.CallStack") == -1) {
                        if (methodname.lastIndexOf("dump") == -1) {
                            return clearName(classname) + c + clearName(methodname + c + ste.getLineNumber());
                        }
                    }
                }
            }
        }
        return null;
    }

    private static String clearName(String name) {
        int i = name.lastIndexOf(".");
        if (i == -1) {
            return name;
        }
        return name.substring(i+1);
    }
}