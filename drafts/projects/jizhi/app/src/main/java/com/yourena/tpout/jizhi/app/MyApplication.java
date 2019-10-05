package com.yourena.tpout.jizhi.app;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by 41675 on 2018/2/23.
 */

public class MyApplication extends Application {

    public static Application sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        LitePal.initialize(this);

    }
}
