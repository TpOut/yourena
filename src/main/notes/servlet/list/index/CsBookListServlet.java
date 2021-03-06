package main.notes.servlet.list.index;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.notes.servlet.base.BaseListServlet;

import static main.config.ConfigConstant.DOCS_NAME;
import static main.config.ConfigConstant.DOC_CS_BOOK_NAME;

/**
 * Created by TpOut on 2019/10/7.<br>
 * Email address: 416756910@qq.com<br>
 */
public class CsBookListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String listPath = DOCS_NAME + File.separator + DOC_CS_BOOK_NAME;
        printJsonFromFile(req, resp, listPath);

    }


}
