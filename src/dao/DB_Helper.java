package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Helper {
    public static Connection connect = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
            // 观察以下2个语句的差别，
            // DB_Helper =
            // DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/onlinepj?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "");

            //System.out.println("Success loading Mysql Driver!");
        } catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connect;
    }
}

