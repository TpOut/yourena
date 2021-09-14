package main.notes.servlet.list.index;

import main.notes.servlet.base.BaseListServlet;

import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

import static main.config.ConfigConstant.DOCS_NAME;
import static main.config.ConfigConstant.DOC_STORY_NAME;

/**
 * Created by shengjieli on 7/31/19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class StoryListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String listPath = DOCS_NAME + File.separator + DOC_STORY_NAME;
        printJsonFromFile(req, resp, listPath);

    }


}