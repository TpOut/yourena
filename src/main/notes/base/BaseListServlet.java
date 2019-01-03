package main.notes.base;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.notes.util.NotesServletUtil;
import main.util.FileUtil;

import static main.config.ConfigConstant.PROJECT_NAME;

/**
 * Created by shengjieli on 2018/11/5.<br>
 * Email address: 416756910@qq.com<br>
 */
public class BaseListServlet extends HttpServlet {

    /**
     * 统一行为：设置格式
     */
    protected static void printJsonFromFile(HttpServletRequest req, HttpServletResponse resp, String listPath) throws IOException {
        NotesServletUtil.setCharset(resp);
        NotesServletUtil.formatJson(resp);

        //yourena_war_exploded
        String webPath = req.getSession().getServletContext().getRealPath("");
        String notesPath = webPath.substring(0, webPath.indexOf(PROJECT_NAME) + PROJECT_NAME.length() + 1) + listPath;

        //项目生成到out后，存到服务器的/java-web/yourena目录
        // 软引用到webapps目录下，文章在/java-web/yourena的同级目录my-article
        resp.getWriter().println(FileUtil.getAllFileName(notesPath));
    }

}
