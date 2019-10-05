package com.yourena.tpout.settings.custompref;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yourena.tpout.librarys.util.SPUtil;
import com.yourena.tpout.settings.BaseActivity;
import com.yourena.tpout.settings.MyApplication;
import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 17-7-17.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class ChangeValueActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SPUtil.getDefaultSharedPreference(MyApplication.sApp).edit()
                .putString(getString(R.string.pref_custom_tip_key), "temp tip k2")
                .putBoolean(getString(R.string.pref_custom_tip_key1), true)
                .apply();
    }
}
