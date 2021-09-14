package main.notes.servlet.database;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.servlet.http.*;

import main.config.ConfigConstant;
import main.config.SecConfig;
import main.notes.util.ServletUtil;

import static main.config.ConfigConstant.DATABASE_URL;

/**
 * Created by shengjieli on 19-1-2.<br>
 * Email address: 416756910@qq.com<br>
 */
public class AccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletUtil.setCharset(resp);
        ServletUtil.formatJson(resp);
        resp.getWriter().println("{\"num\":\"" + String.valueOf(queryAndUpdateAccess(req)) + "\"}");
    }

    /**
     * @return currentAccessNum after update
     */
    private int queryAndUpdateAccess(HttpServletRequest req) {
        if(!ConfigConstant.isDeployed)
            return 0;
        int num = 0;

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + DATABASE_URL + ":3306/lishengjie";
        String USER = SecConfig.name;
        String PASS = SecConfig.ps;

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            String ip = req.getLocalAddr();
            Timestamp time = new Timestamp(new Date().getTime());
            System.out.println(" 插入数据对象... ip : " + ip + " --- time : " + time);
            String addSql = "insert into AccessAnalyze(repeatNum,time,ip) values(1,\"" + time + "\",\"" + ip + "\")";
            System.out.println(" 插入数据对象... 语句为 " + addSql);
            stmt.executeUpdate(addSql);

            String accessNumSql = "select table_schema,table_name,table_type,table_rows from information_schema.tables where table_name='AccessAnalyze'";
            ResultSet rs = stmt.executeQuery(accessNumSql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                num = rs.getInt("table_rows");
                System.out.print("ID: " + num);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");

        return num;
    }

}
