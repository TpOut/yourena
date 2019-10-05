package com.yourena.tpout.settings.frag.pref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.librarys.util.SPUtil;
import com.yourena.tpout.settings.R;
import com.yourena.tpout.settings.custompref.ChangeValueActivity;

/**
 * Created by shengjieli on 17-7-15.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class SettingPrefFragment2 extends BasePrefFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference2);

        //需要8.0以上，参看原文底部
//        // Assign the data store to a single preference
//        findPreference("myPref").setPreferenceDataStore(new MyDataStore());
//
//        // Assign the data store to all the preferences in the current fragment
//        getPreferenceManager().setPreferenceDataStore(new MyDataStore());

        SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.pref_custom_tip_key1));
        LogUtil.d(TAG, "switchPreference : " + switchPreference);
        //点击事件
        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                LogUtil.d(TAG, "onPreferenceClick");
                startActivity(new Intent(getActivity(), ChangeValueActivity.class));
                return false;
            }
        });
        //setOnPreferenceChangeListener不会强引用onPreferenceChangeListener
        //所以需要自己来建立强引用，保证可用性
        //用户点击事件，如果在某些条件下不符合，那么就返回false，会不做变化
        Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                LogUtil.d(TAG, "onPreferenceChange");
                return true;
            }
        };
        switchPreference.setOnPreferenceChangeListener(onPreferenceChangeListener);

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
//        getPreferenceScreen().getSharedPreferences()
//                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        super.onSharedPreferenceChanged(sharedPreferences, key);
        if (key.equals(getString(R.string.pref_custom_tip_key))) {
            Preference preference = findPreference(key);
            preference.setSummary(SPUtil.getString(key));
        } else if (key.equals(getString(R.string.pref_custom_tip_key1))) {

        }
    }
}
