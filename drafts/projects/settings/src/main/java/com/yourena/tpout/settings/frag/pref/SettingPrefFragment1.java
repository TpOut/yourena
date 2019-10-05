package com.yourena.tpout.settings.frag.pref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

import com.yourena.tpout.settings.R;

import static com.yourena.tpout.settings.R.string.pref_setting2_key;

/**
 * Created by shengjieli on 17-7-14.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class SettingPrefFragment1 extends BasePrefFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypref);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
