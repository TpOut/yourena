package main.util;

/**
 * Created by shengjieli on 8/5/19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class StringUtil {

    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }
}
