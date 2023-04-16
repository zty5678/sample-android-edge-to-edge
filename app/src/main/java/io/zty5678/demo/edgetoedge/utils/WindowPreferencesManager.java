/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.zty5678.demo.edgetoedge.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.Window;

import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Helper that saves the current window preferences for the Catalog.
 */
public class WindowPreferencesManager {


    private final Context context;
    private final OnApplyWindowInsetsListener listener;

    public WindowPreferencesManager(Context context) {
        this.context = context;
        this.listener =
                (v, insets) -> {
                    if (v.getResources().getConfiguration().orientation
                            != Configuration.ORIENTATION_LANDSCAPE) {
                        return insets;
                    }
                    if (VERSION.SDK_INT >= VERSION_CODES.R) {
                        v.setPadding(
                                insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                                0,
                                insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                                insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
                    } else {
                        v.setPadding(
                                insets.getStableInsetLeft(),
                                0,
                                insets.getStableInsetRight(),
                                insets.getStableInsetBottom());
                    }
                    return insets;
                };
    }

    public boolean isEdgeToEdgeEnabled() {

        return true;
    }

    @SuppressWarnings("RestrictTo")
    public void applyEdgeToEdgePreference(Window window) {
        EdgeToEdgeUtils.applyEdgeToEdge(window, isEdgeToEdgeEnabled());
        ViewCompat.setOnApplyWindowInsetsListener(
                window.getDecorView(), isEdgeToEdgeEnabled() ? listener : null);
    }


}
