package main.notes.servlet.database.word;

import main.config.SecConfig;
import main.notes.util.ServletUtil;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static main.config.ConfigConstant.DATABASE_URL;
import static main.config.ConfigConstant.isDeployed;

public class AddWordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isDeployed) {
            addWord(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        addWord(req, resp);
    }

    private void addWord(HttpServletRequest req, HttpServletResponse resp) {
        String src = req.getParameter("src");
        String result = req.getParameter("result");
        String sentence = req.getParameter("sentence");

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + DATABASE_URL + ":3306/lishengjie?useSSL=false";
        String USER = SecConfig.name;
        String PASS = SecConfig.ps;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String addSql = "INSERT INTO words (src,result,sentence, create_time)"
                + " VALUES ( ?, ?, ?, NOW())";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(addSql)) {
            stmt.setString(1, src);
            stmt.setString(2, result);
            stmt.setString(3, sentence);
            stmt.execute();
            printSuccess(resp);
        } catch (SQLException e) {
            e.printStackTrace();
            printFail(resp);
        }
    }

    private void printSuccess(HttpServletResponse resp) {
        ServletUtil.setCharset(resp);
        ServletUtil.formatJson(resp);
        try {
            resp.getWriter().println("{\"resultStatus\":\"success\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFail(HttpServletResponse resp) {
        ServletUtil.setCharset(resp);
        ServletUtil.formatJson(resp);
        try {
            resp.getWriter().println("{\"resultStatus\":\"fail\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
