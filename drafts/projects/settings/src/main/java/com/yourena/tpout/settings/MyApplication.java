package com.yourena.tpout.settings;

import android.app.Application;

import com.yourena.tpout.librarys.util.LogUtil;

/**
 * Created by shengjieli on 17-7-17.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class MyApplication extends Application {

    public static Application sApp;

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;
    }
}
