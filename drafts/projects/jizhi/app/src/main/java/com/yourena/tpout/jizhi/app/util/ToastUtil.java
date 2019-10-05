package com.yourena.tpout.jizhi.app.util;

import android.widget.Toast;

import com.yourena.tpout.jizhi.app.MyApplication;

/**
 * Created by shengjieli on 2018/3/1.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class ToastUtil {

    public static void showShort(String text) {
        Toast.makeText(MyApplication.sApp, text, Toast.LENGTH_SHORT).show();
    }

}
