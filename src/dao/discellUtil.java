package dao;

import domain.discell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class discellUtil {
    public void add(discell discell) throws SQLException {
        // 与特定数据库的连接（会话）。
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into discell(DID,UID,Discussion) values(?,?,?)";

        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, discell.getDID());
        ptmt.setInt(2, discell.getUID());
        ptmt.setString(3, discell.getDiscussion());
        //ptmt.setDate(4, discell.getCreateTime());

        ptmt.execute();
    }

    public void delete(int id) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "delete from discell where ID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setInt(1,id);
        ptmt.execute();
    }

    public List<discell> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from discell where DID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<discell> list = new ArrayList<>();
            while (rs.next()) {
                discell cell = new discell();
                cell.setID(rs.getInt("ID"));
                cell.setDID(rs.getInt("DID"));
                cell.setUID(rs.getInt("UID"));
                cell.setDiscussion(rs.getString("Discussion"));
                //cell.setCreateTime(rs.getDate("CreateTime"));
                list.add(cell);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
