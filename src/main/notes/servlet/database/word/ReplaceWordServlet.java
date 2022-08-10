package main.notes.servlet.database.word;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.config.SecConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static main.config.ConfigConstant.isDeployed;
import static main.config.SecConfig.DATABASE_URL;

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
        String DB_URL = "jdbc:mysql://" + DATABASE_URL + ":" + SecConfig.mysql_port + "/lishengjie?useSSL=false";
        String USER = SecConfig.mysql_user_name;
        String PASS = SecConfig.mysql_user_ps;

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
