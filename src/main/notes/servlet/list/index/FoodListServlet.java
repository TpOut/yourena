package main.notes.servlet.list.index;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.notes.base.BaseListServlet;

import static main.config.ConfigConstant.DOCS_NAME;
import static main.config.ConfigConstant.DOC_FOOD_NAME;

/**
 * Created by shengjieli on 7/31/19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class FoodListServlet extends BaseListServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String listPath = DOCS_NAME + File.separator + DOC_FOOD_NAME;
        printJsonFromFile(req, resp, listPath);

    }


}