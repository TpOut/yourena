package com.yourena.tpout.jizhi.app.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.yourena.tpout.jizhi.app.MyApplication;

/**
 * Created by 41675 on 2018/2/23.
 */

public class SpUtil {

    public static final String KEY_USER_NAME = "KEY_USER_NAME";

    private static SpUtil sInstance;

    public static SpUtil getInstance() {
        if (null == sInstance) {
            sInstance = new SpUtil();
        }
        return sInstance;
    }

    private SharedPreferences appSp;

    private SpUtil() {
        appSp = PreferenceManager.getDefaultSharedPreferences(MyApplication.sApp);
    }

    public void addChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        appSp.registerOnSharedPreferenceChangeListener(listener);
    }

    public void removeChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        appSp.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void putString(String key, String value) {
        appSp.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultStr) {
        return appSp.getString(key, defaultStr);
    }

}
