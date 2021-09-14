package main.notes.servlet.base;

import java.io.IOException;

import main.notes.util.ServletUtil;
import main.util.FileUtil;

import jakarta.servlet.http.*;

import static main.config.ConfigConstant.PROJECT_NAME;

/**
 * Created by shengjieli on 2018/11/5.<br>
 * Email address: 416756910@qq.com<br>
 *
 *     目录下是一篇篇文档，直接输出成列表
 */
public class BaseListServlet extends HttpServlet {

    /**
     * 统一行为：设置格式
     */
    protected static void printJsonFromFile(HttpServletRequest req, HttpServletResponse resp, String listPath){
        ServletUtil.setCharset(resp);
        ServletUtil.formatJson(resp);

        //yourena_war_exploded
        String webPath = req.getSession().getServletContext().getRealPath("");
        String docsPath = webPath.substring(0, webPath.indexOf(PROJECT_NAME) + PROJECT_NAME.length() + 1) + listPath;

        //项目生成到out后，存到服务器的/java-web/yourena目录
        //软引用到webapps目录下，文章在/java-web/yourena的同级目录
        resp.getWriter().println(FileUtil.getAllFileName(docsPath));
    }

}
