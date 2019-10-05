package com.yourena.tpout.settings.frag.pref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yourena.tpout.librarys.util.SPUtil;
import com.yourena.tpout.settings.MyApplication;
import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 2017/10/25.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class LifecyclePrefFragment extends BasePrefFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_lifecycle);

    }

}
