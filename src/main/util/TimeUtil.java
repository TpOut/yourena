package main.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shengjieli on 8/5/19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class TimeUtil {

    public static boolean isDate(String value, String format) {

        SimpleDateFormat sdf = null;
        ParsePosition pos = new ParsePosition(0);//指定从所传字符串的首位开始解析

        if (value == null || StringUtil.isEmpty(format)) {
            return false;
        }
        try {
            sdf = new SimpleDateFormat(format);
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01
            sdf.setLenient(false);
            Date date = sdf.parse(value, pos);
            if (date == null) {
                return false;
            } else {
                System.out.println("-------->pos : " + pos.getIndex());
                System.out.println("-------->date : " + sdf.format(date));
                //更为严谨的日期,如2011-03-024认为是不合法的
                if (pos.getIndex() > sdf.format(date).length()) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
