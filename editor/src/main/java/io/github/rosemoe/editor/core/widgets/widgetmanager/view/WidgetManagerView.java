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
package io.github.rosemoe.editor.core.widgets.widgetmanager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.rosemoe.editor.R;
import io.github.rosemoe.editor.core.extension.Extension;
import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.widgets.widgetmanager.controller.WidgetManagerController;

public class WidgetManagerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.debug("START");
        setContentView(R.layout.extensionmanager);
        loadExtensions("plugins", R.id.extension_manager_plugins);
        loadExtensions("widgets", R.id.extension_manager_widgets);
    }

    public void loadExtensions(String kind, int insertionPoint) {
        Parcelable[] extensions = getIntent().getParcelableArrayExtra(kind);
        LinearLayout ll = findViewById(insertionPoint);
        for(Parcelable p : extensions) {
            buildAndInsertExtensionView((Extension) p,ll,kind);
        }
    }

    public View buildAndInsertExtensionView(Extension e, ViewGroup ip, String kind) {
        View v = buildExtensionView(e,kind);
        ip.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        Logger.debug("END");
        return v;
    }

    public View buildExtensionView(Extension e, String kind) {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.extensionmanageritem, null);

        TextView titleView = v.findViewById(R.id.title);
        titleView.setText(e.name);
        TextView descView = v.findViewById(R.id.description);
        descView.setText(e.description);

        ImageView icon = v.findViewById(R.id.icon);
        byte[] imageBytes = Base64.decode(e.image, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        icon.setImageBitmap(decodedImage);

        Switch s = v.findViewById(R.id.isEnabledSwitch);
        s.setChecked(e.isEnabled());
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        e.toggleIsEnabled();
                        WidgetManagerController.DataHolder.put("extension",e);
                        WidgetManagerController.DataHolder.put("kind",kind);
                        finish();
                    }
                }.start();
            }
        });
        return v;
    }

}
