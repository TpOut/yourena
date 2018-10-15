package main.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.util.FileUtil;
import main.util.LogUtil;

import static main.config.ConfigConstant.NOTES_NAME;
import static main.config.ConfigConstant.PROJECT_NAME;

/**
 * Created by shengjieli on 18-10-10.<br>
 * Email address: 416756910@qq.com<br>
 */
public class NotesListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        //yourena_war_exploded
        String webPath = req.getSession().getServletContext().getRealPath("");
        String notesPath = webPath.substring(0, webPath.indexOf(PROJECT_NAME) + PROJECT_NAME.length() + 1) + NOTES_NAME;
        LogUtil.printL(notesPath);
        //项目生成到out后，存到服务器的/java-web/yourena目录
        // 软引用到webapps目录下，文章在/java-web/yourena的同级目录my-article
        resp.getWriter().println(FileUtil.getAllFileName(notesPath));

    }
}
