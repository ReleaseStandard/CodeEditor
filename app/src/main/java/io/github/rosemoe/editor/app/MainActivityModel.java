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
package io.github.rosemoe.editor.app;

import java.util.HashMap;

public class MainActivityModel {

    public String [] themes = new String[]{"Default", "Eclipse", "Darcula", "VS2019", "NotepadXX", "HTML","Solarized", "SolarizedDark","GitHub"};
    public int checkedTheme = 0;

    public String[] languages = new String[]{"C", "C++", "Java", "JavaScript", "HTML", "Python", "None", "GoLang", "Mksh","Cobol85"};
    public HashMap<String,String> languages_samples = new HashMap<String, String>(){{
        put("C","samples/c/c.txt");
        put("C++","samples/cpp/cpp.txt");
        put("Java","samples/java/java.txt");
        put("JavaScript","samples/javascript/test.txt");
        put("HTML","samples/html/html.txt");
        put("Python","samples/python/python.txt");
        put("None","samples/java/java.txt");
        put("GoLang","samples/golang/golang.txt");
        put("Mksh","samples/mksh/mksh_while.txt");
        put("Cobol85","samples/cobol85/text.txt");
    }};
    public int checkedLanguage = 0;

    public MainActivityModel() {

    }
}
