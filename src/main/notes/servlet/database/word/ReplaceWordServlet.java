package main.notes.servlet.database.word;

import main.config.SecConfig;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import static main.config.ConfigConstant.DATABASE_URL;
import static main.config.ConfigConstant.isDeployed;

public class ReplaceWordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isDeployed) {
            doAction(req);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        String addSql = "UPDATE words SET result = ? WHERE id = ? ";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(addSql)) {
            stmt.setString(1, result);
            stmt.setString(2, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
