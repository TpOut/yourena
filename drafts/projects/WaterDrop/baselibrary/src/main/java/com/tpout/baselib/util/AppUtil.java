package com.tpout.baselib.util;

import android.app.Application;

/**
 * Created by shengjieli on 18-12-28.<br>
 * Email address: 416756910@qq.com<br>
 */
public class AppUtil {

    private static Application mApplication;

    public static void init(Application app) {
        mApplication = app;
    }

    public static Application getApp() {
        return mApplication;
    }

}
