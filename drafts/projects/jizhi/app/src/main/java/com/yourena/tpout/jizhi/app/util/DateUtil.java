package com.yourena.tpout.jizhi.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 41675 on 2018/2/24.
 */

public class DateUtil {
    private static String TAG = DateUtil.class.getSimpleName();

    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static Date getCurDate() {
        return new Date(System.currentTimeMillis());
    }

    public static String getCurDateOfString(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        String d = df.format(getCurDate());
        LogUtil.d(TAG, "---getCurDateOfString---d : " + d);
        return d;
    }

}
