package com.yourena.tpout.jizhi.app.util;

import android.util.Log;

/**
 * Created by 41675 on 2018/2/23.
 */

public class LogUtil {

    private boolean isOnLine;

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }


    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

}
