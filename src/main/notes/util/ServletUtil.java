package main.notes.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by shengjieli on 2018/11/5.<br>
 * Email address: 416756910@qq.com<br>
 */
public class ServletUtil {

    public static void setCharset(HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
    }

    public static void formatJson(HttpServletResponse resp) {
        resp.setContentType("application/json");
    }

}
