package main.notes.servlet.database.word;

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

public class ReplaceWordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req);
    }

    private void doAction(HttpServletRequest req) {
        String id = req.getParameter("id");
        String result = req.getParameter("result");

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + DATABASE_URL + ":3306/lishengjie?useSSL=false";
        String USER = SecConfig.name;
        String PASS = SecConfig.ps;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String addSql = "UPDATE words SET result="
                + "'" + result + "'"
                + " WHERE id=" + id;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = connection.createStatement()) {
            stmt.execute(addSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
