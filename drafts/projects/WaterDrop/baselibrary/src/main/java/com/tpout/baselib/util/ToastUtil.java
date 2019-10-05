package com.tpout.baselib.util;

import android.app.Application;
import android.widget.Toast;

/**
 * todo 现在看来，这种方法会一直持有一个toast实例，应该也不是最优解
 */
public class ToastUtil {

    private static   String oldMsg;
    protected static Toast  sToast      = null;
    private static   long   preTime     = 0;

    private static int preDuration = -1;

    private static void checkPreDuration() {
        if (preDuration == Toast.LENGTH_SHORT || preDuration == Toast.LENGTH_LONG) {
            throw new IllegalStateException("请重新设置preDuration的初始值");
        }
    }

    private static void setPreDuration(int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            preDuration = duration;
        }
    }

    public static void showShort(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 目前来看，两者
     *
     * @param message 要显示的字符串
     */
    public static void showToast(String message, int duration) {

        Application mApp = AppUtil.getApp();
        if (null == mApp) {
            return;
        }
        if (sToast == null) {
            checkPreDuration();
            sToast = Toast.makeText(mApp, message, duration);
            oldMsg = message;
            sToast.show();
            preTime = System.currentTimeMillis();
            setPreDuration(duration);
        } else {
            long currentTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (currentTime - preTime > preDuration) {
                    sToast.show();
                    preTime = currentTime;
                    setPreDuration(duration);
                }
            } else {
                oldMsg = message;
                sToast.setText(message);
                sToast.show();
                preTime = currentTime;
                setPreDuration(duration);
            }
        }
    }
}