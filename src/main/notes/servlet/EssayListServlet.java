package main.notes.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.notes.base.BaseListServlet;

import static main.config.ConfigConstant.ESSAY_NAME;
import static main.config.ConfigConstant.NOTES_NAME;

/**
 * Created by shengjieli on 2018/11/5.<br>
 * Email address: 416756910@qq.com<br>
 */
public class EssayListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String listPath = NOTES_NAME + File.separator + ESSAY_NAME;
        printJsonFromFile(req, resp, listPath);

    }


}
