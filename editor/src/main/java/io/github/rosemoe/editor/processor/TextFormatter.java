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
package io.github.rosemoe.editor.processor;

import io.github.rosemoe.editor.mvc.controller.LanguageController;
import io.github.rosemoe.editor.mvc.controller.content.ContentController;

/**
 * Process text formatting.
 */
public class TextFormatter extends Thread {

    private CharSequence mText;

    private LanguageController mLanguage;

    private FormatResultReceiver mReceiver;

    public TextFormatter(CharSequence text, LanguageController language, FormatResultReceiver receiver) {
        mText = text;
        mLanguage = language;
        mReceiver = receiver;
    }

    @Override
    public void run() {
        CharSequence result = null;
        try {
            CharSequence chars = ((mText instanceof ContentController) ? (((ContentController) mText).toStringBuilder()) : new StringBuilder(mText));
            result = mLanguage.format(chars);
        } catch (Throwable e) {
            if (mReceiver != null) {
                mReceiver.onFormatFail(e);
            }
        }
        if (mReceiver != null) {
            mReceiver.onFormatSucceed(mText, result);
        }
        mReceiver = null;
        mLanguage = null;
        mText = null;
    }

    public interface FormatResultReceiver {

        void onFormatSucceed(CharSequence originalText, CharSequence newText);

        void onFormatFail(Throwable throwable);

    }

}
