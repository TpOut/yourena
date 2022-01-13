package main.notes.servlet.database.word;

import main.config.SecConfig;
import main.notes.util.ServletUtil;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.config.ConfigConstant.DATABASE_URL;

public class WordListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            resp.getWriter().println("{\"resultStatus\":\"fail\"}");
            return;
        }
        ServletUtil.setCharset(resp);
        ServletUtil.formatJson(resp);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{")
                .append("\"resultStatus\":\"success\"")
                .append(",")
                .append("\"page\":")
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
                    .append("\"id\":").append("\"").append(bean.getId()).append("\"")
                    .append(",")
                    .append("\"src\":").append("\"").append(bean.getSrc()).append("\"")
                    .append(",")
                    .append("\"dst\":").append("\"").append(bean.getDst()).append("\"")
                    .append(",")
                    .append("\"sentence\":").append("\"").append(bean.getSentence()).append("\"")
                    .append(",")
                    .append("\"createTime\":").append(bean.getCreateTime())
                    .append("}");
        }
        stringBuilder
                .append("]")
                .append("}");
        resp.getWriter().println(stringBuilder.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    private List<WordBean> queryWord(int pageNum) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + DATABASE_URL + ":"+ SecConfig.mysql_port + "/lishengjie?useSSL=false";
        String USER = SecConfig.mysql_user_name;
        String PASS = SecConfig.mysql_user_ps;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM words ORDER BY id DESC LIMIT " + (pageNum - 1) * 20 + ",20";
        List<WordBean> wordBeanList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                WordBean bean = new WordBean(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5).getTime()
                );
                wordBeanList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wordBeanList;
    }
}
