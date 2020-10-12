package main.notes.servlet.database.word;

import main.config.SecConfig;
import main.notes.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.config.ConfigConstant.DATABASE_URL;

public class WordListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("pageNum");
        int page = -1;
        if (pageNum == null || pageNum.isEmpty()) {
            return;
        } else {
            try {
                page = Integer.parseInt(pageNum);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (page == -1) {
            return;
        }
        List<WordBean> list = queryWord(page);
        if (list.size() == 0) {
            return;
        }
        ServletUtil.setCharset(resp);
        ServletUtil.formatJson(resp);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"page\":")
                .append("\"").append(pageNum).append("\"")
                .append(",")
                .append("\"result\":[");
        for (int i = 0; i < list.size(); i++) {
            WordBean bean = list.get(i);
            if (i == 0) {

            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append("{")
                    .append("\"src\":").append("\"").append(bean.getSrc()).append("\"")
                    .append(",")
                    .append("\"dst\":").append("\"").append(bean.getDst()).append("\"")
                    .append(",")
                    .append("\"sentence\":").append("\"").append(bean.getSentence()).append("\"")
                    .append("}");
        }
        stringBuilder
                .append("]")
                .append("}");
        resp.getWriter().println(stringBuilder.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private List<WordBean> queryWord(int pageNum) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + DATABASE_URL + ":3306/lishengjie?useSSL=false";
        String USER = SecConfig.name;
        String PASS = SecConfig.ps;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM words LIMIT " + (pageNum - 1) * 10 + ",20";
        List<WordBean> wordBeanList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                WordBean bean = new WordBean(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                wordBeanList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wordBeanList;
    }
}
