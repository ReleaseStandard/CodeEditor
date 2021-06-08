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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import io.github.rosemoe.editor.mvc.controller.LanguageController;
import io.github.rosemoe.editor.langs.empty.EmptyLanguage;
import io.github.rosemoe.editor.langs.desc.CDescription;
import io.github.rosemoe.editor.langs.desc.CppDescription;
import io.github.rosemoe.editor.langs.html.HTMLLanguage;
import io.github.rosemoe.editor.langs.java.JavaLanguage;
import io.github.rosemoe.editor.langs.python.PythonLanguage;
import io.github.rosemoe.editor.langs.universal.UniversalLanguage;
import io.github.rosemoe.editor.mvc.controller.widgets.color.ColorSchemeController;
import io.github.rosemoe.editor.util.Logger;
import io.github.rosemoe.editor.utils.CrashHandler;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.SymbolInputView;
import io.github.rosemoe.editor.plugins.color.*;

public class MainActivity extends AppCompatActivity {

    private CodeEditor editor;
    private LinearLayout panel;
    private EditText search, replace;

    private MainActivityModel mam = new MainActivityModel();
    private static HashMap<String, ColorPlugin> themes = new HashMap<String, ColorPlugin>() {{

    }};
    private static HashMap<String, LanguageController> languages = new HashMap<String, LanguageController>() {{
        put("C",new UniversalLanguage(new CDescription()));
        put("C++",new UniversalLanguage(new CppDescription()));
        put("Java",new JavaLanguage());
        put("HTML",new HTMLLanguage());
        put("Python",new PythonLanguage());
        put("None",new EmptyLanguage());
    }};

    protected void loadThemes() {
        themes.put("Default", ColorPlugin.DEFAULT(editor));
        themes.put("Eclipse",new ColorPluginEclipse(editor));
        themes.put("Darcula",new ColorPluginDarcula(editor));
        themes.put("VS2019",new ColorPluginVS2019(editor));
        themes.put("NotepadXX",new ColorPluginNotepadXX(editor));
        themes.put("HTML",new ColorPluginHTML(editor));
        themes.put("Solarized",new ColorPluginSolarized(editor));
        themes.put("GitHub",new ColorPluginGithub(editor));
        themes.put("None", new ColorPluginNone(editor));
        themes.put("Debug", new ColorPluginDebug(editor));
    }
    protected void setEditorLanguage(LanguageController el, String fname) {
        editor.setEditorLanguage(el);
        new Thread(() -> {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(fname)));
                String line;
                StringBuilder text = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    text.append(line).append('\n');
                }
                runOnUiThread(() -> editor.setText(text));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashHandler.INSTANCE.init(this);
        setContentView(R.layout.activity_main);
        editor = findViewById(R.id.editor);
        loadThemes();
        panel = findViewById(R.id.search_panel);
        search = findViewById(R.id.search_editor);
        replace = findViewById(R.id.replace_editor);
        SymbolInputView inputView = findViewById(R.id.symbol_input);
        inputView.bindEditor(editor);
        inputView.addSymbols(new String[]{"->", "{", "}", "(", ")", ",", ".", ";", "\"", "?", "+", "-", "*", "/"},
                new String[]{"\t", "{}", "}", "(", ")", ",", ".", ";", "\"", "?", "+", "-", "*", "/"});

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editor.getSearcher().search(editable.toString());
            }
        });
        editor.setTypefaceText(Typeface.MONOSPACE);
        editor.setOverScrollEnabled(false);
        //setEditorLanguage(new MkshLanguage(), "samples/mksh/mksh.txt");
        setEditorLanguage(new JavaLanguage(), "samples/java/java.txt");
        //setEditorLanguage(new HTMLLanguage(),"samples/html/html.txt");
        if ( Logger.DEBUG ) {
            new ColorPluginDebug(editor).apply();
        } else {
            ColorPlugin.DEFAULT(editor).apply();
        }
        editor.setNonPrintablePaintingFlags(CodeEditor.FLAG_DRAW_WHITESPACE_LEADING | CodeEditor.FLAG_DRAW_LINE_SEPARATOR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.text_undo:
                editor.undo();
                break;
            case R.id.text_redo:
                editor.redo();
                break;
            case R.id.goto_end:
                editor.setSelection(editor.getText().getLineCount() - 1, editor.getText().getColumnCount(editor.getText().getLineCount() - 1));
                break;
            case R.id.move_up:
                editor.moveSelectionUp();
                break;
            case R.id.move_down:
                editor.moveSelectionDown();
                break;
            case R.id.home:
                editor.moveSelectionHome();
                break;
            case R.id.end:
                editor.moveSelectionEnd();
                break;
            case R.id.move_left:
                editor.moveSelectionLeft();
                break;
            case R.id.move_right:
                editor.moveSelectionRight();
                break;
            case R.id.code_format:
                editor.formatCodeAsync();
                break;
            case R.id.switch_language:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.switch_language)
                        .setSingleChoiceItems(mam.languages, mam.checkedLanguage, (dialog, which) -> {
                            setEditorLanguage(languages.get(mam.languages[which]), mam.languages_samples.get(mam.languages[which]));
                            mam.checkedLanguage=which;
                            dialog.dismiss();
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
                break;
            case R.id.search_panel_st:
                if (panel.getVisibility() == View.GONE) {
                    replace.setText("");
                    search.setText("");
                    editor.getSearcher().stopSearch();
                    panel.setVisibility(View.VISIBLE);
                    item.setChecked(true);
                } else {
                    panel.setVisibility(View.GONE);
                    editor.getSearcher().stopSearch();
                    item.setChecked(false);
                }
                break;
            case R.id.search_am:
                replace.setText("");
                search.setText("");
                editor.getSearcher().stopSearch();
                editor.beginSearchMode();
                break;
            case R.id.switch_colors:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.color_scheme)
                        .setSingleChoiceItems(mam.themes, mam.checkedTheme, (dialog, which) -> {
                            ColorPlugin theme = themes.get(mam.themes[which]);
                            //editor.setColorScheme(theme);
                            theme.apply();
                            mam.checkedTheme=which;
                            dialog.dismiss();
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
                break;
            case R.id.text_wordwrap:
                item.setChecked(!item.isChecked());
                editor.setWordwrap(item.isChecked());
                break;
            case R.id.open_logs: {
                FileInputStream fis = null;
                try {
                    fis = openFileInput("crash-journal.log");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                    Toast.makeText(this, "Succeeded", Toast.LENGTH_SHORT).show();
                    editor.setText(sb);
                } catch (Exception e) {
                    Toast.makeText(this, "Failed:" + e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
            case R.id.clear_logs: {
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("crash-journal.log", MODE_PRIVATE);
                    Toast.makeText(this, "Succeeded", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Failed:" + e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
            case R.id.open_debug_logs: {
                //editor.setText(Logs.getLogs());
                break;
            }
            case R.id.enable_logcat_logs:
                Logger.DEBUG = ! Logger.DEBUG;
                MenuItem mi = editor.findViewById(R.id.enable_logcat_logs);
                item.setChecked(Logger.DEBUG);
                break;
            case R.id.editor_line_number: {
                editor.setLineNumberEnabled(!editor.isLineNumberEnabled());
                item.setChecked(editor.isLineNumberEnabled());
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void gotoNext(View view) {
        try {
            editor.getSearcher().gotoNext();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void gotoLast(View view) {
        try {
            editor.getSearcher().gotoLast();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void replace(View view) {
        try {
            editor.getSearcher().replaceThis(replace.getText().toString());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void replaceAll(View view) {
        try {
            editor.getSearcher().replaceAll(replace.getText().toString());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
