package main.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.util.FileUtil;
import main.util.LogUtil;

/**
 * Created by shengjieli on 18-10-10.<br>
 * Email address: 416756910@qq.com<br>
 */
public class MainListServlet extends HttpServlet {

    private static final String PROJECT_NAME = "yourena";
    private static final String NOTES_NAME   = "notes";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        //yourena_war_exploded
        String webPath = req.getSession().getServletContext().getRealPath("");
        String notesPath = webPath.substring(0, webPath.indexOf(PROJECT_NAME) + PROJECT_NAME.length() + 1) + NOTES_NAME;
        LogUtil.printI(notesPath);
        //项目生成到out后，存到服务器的/java-web/yourena目录
        // 软引用到webapps目录下，文章在/java-web/yourena的同级目录my-article
        resp.getWriter().println(FileUtil.getAllFileName(notesPath));

    }
}
