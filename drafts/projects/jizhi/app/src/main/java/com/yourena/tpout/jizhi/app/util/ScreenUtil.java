package com.yourena.tpout.jizhi.app.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

import com.yourena.tpout.jizhi.app.MyApplication;

/**
 * Created by shengjieli on 2018/3/8.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class ScreenUtil {

    private static String TAG = ScreenUtil.class.getSimpleName();

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) MyApplication.sApp.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return MyApplication.sApp.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        LogUtil.d(TAG, "---getScreenWidth--- x : " + point.x);
        return point.x;
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) MyApplication.sApp.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return MyApplication.sApp.getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

}
