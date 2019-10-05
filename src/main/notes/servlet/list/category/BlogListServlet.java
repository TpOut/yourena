package main.notes.servlet.list.category;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.notes.servlet.base.BaseListServlet;

import static main.config.ConfigConstant.DOCS_NAME;
import static main.config.ConfigConstant.DOC_BLOG_NAME;

/**
 * Created by shengjieli on 8/2/19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class BlogListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String listPath = DOCS_NAME + File.separator + DOC_BLOG_NAME;
        printJsonFromFile(req, resp, listPath);

    }

}
