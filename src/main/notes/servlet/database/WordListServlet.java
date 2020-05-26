package main.notes.servlet.database;

import main.config.SecConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static main.config.ConfigConstant.DATABASE_URL;

public class WordListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addWord(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addWord(req);
    }

    private void addWord(HttpServletRequest req) {
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
                + " VALUES ("
                + "'" + src + "',"
                + "'" + result + "',"
                + "'" + sentence + "',"
                + "NOW()"
                + ")";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = connection.createStatement()) {
            stmt.execute(addSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
