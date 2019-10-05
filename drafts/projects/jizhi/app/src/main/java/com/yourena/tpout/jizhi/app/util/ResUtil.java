package com.yourena.tpout.jizhi.app.util;

import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;

import com.yourena.tpout.jizhi.app.MyApplication;

/**
 * Created by shengjieli on 2018/3/8.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class ResUtil {

    public static float getDimen(@DimenRes int dimenResId) {
        return MyApplication.sApp.getResources().getDimension(dimenResId);
    }

    public static int getColor(@ColorRes int colorResId) {
        return MyApplication.sApp.getResources().getColor(colorResId);
    }
}
