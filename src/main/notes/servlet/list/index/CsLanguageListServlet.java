package main.notes.servlet.list.index;

import main.notes.servlet.base.BaseListServlet;

import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

import static main.config.ConfigConstant.*;

/**
 * Created by TpOut on 2019/10/7.<br>
 * Email address: 416756910@qq.com<br>
 */
public class CsLanguageListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String listPath = DOCS_NAME + File.separator + DOC_CS_LANGUAGE_NAME;
        printJsonFromFile(req, resp, listPath);

    }


}
