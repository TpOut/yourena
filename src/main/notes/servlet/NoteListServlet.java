package main.notes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.notes.base.BaseListServlet;

import static main.config.ConfigConstant.NOTES_NAME;

/**
 * Created by shengjieli on 18-10-10.<br>
 * Email address: 416756910@qq.com<br>
 */
public class NoteListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        printJsonFromFile(req, resp, NOTES_NAME);

    }
}
