package dao;

import domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userUtil {
    public void add(user user) throws SQLException {
        // 与特定数据库的连接（会话）。
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into users(UserName,Pass) values(?,?)";

        // 创建一个 PreparedStatement 对象来将参数化的 SQL 语句发送到数据库。
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
  /*
   * void setBigDecimal(int parameterIndex,BigDecimal x)throws SQLException
   * 将指定参数设置为给定 Java String 值。在将此值发送给数据库时，驱动程序将它转换成一个 SQL VARCHAR
   * 或 LONGVARCHAR 值（取决于该参数相对于驱动程序在 VARCHAR 值上的限制的大小）。
   */
        ptmt.setString(1, user.getUsername());
        ptmt.setString(2, user.getPass());

        // 在此 PreparedStatement 对象中执行 SQL 语句
        ptmt.execute();
    }

    public user getSelect(String username) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from users where UserName = ?";
            user user = new user();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user.setUID(rs.getInt("UID"));
                user.setUsername(rs.getString("UserName"));
                user.setPass(rs.getString("Pass"));
                return user;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSelectByID(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select UserName from users where UID = ?";
            user user = new user();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("UserName");
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
