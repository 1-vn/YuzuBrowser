/*
 * Copyright (C) 2019 DiepDT
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

package com.onevn.browser.legacy.settings.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import com.onevn.browser.legacy.R;
import com.onevn.browser.legacy.speeddial.view.SpeedDialSettingActivity;

public class SpeeddialFragment extends OneVNPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOneVNPreferences(@Nullable Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_speeddial);

        findPreference("edit_speeddial").setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity(), SpeedDialSettingActivity.class);
            startActivity(intent);
            return true;
        });
    }
}
