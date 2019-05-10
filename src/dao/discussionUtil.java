package dao;

import domain.discussion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class discussionUtil {
    public void add(discussion discussion) throws SQLException {
        // 与特定数据库的连接（会话）。
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into discussion(CourseID,UID,Discussion) values(?,?,?)";

        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, discussion.getCourseID());
        ptmt.setInt(2, discussion.getUID());
        ptmt.setString(3, discussion.getDiscussion());
        //ptmt.setDate(4, discussion.getCreateTime());

        ptmt.execute();
    }

    public void delete(int id) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql1 = "delete from discussion where DID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql1);
        ptmt.setInt(1,id);
        String sql2 = "delete from discell where DID=?";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql2);
        pst.setInt(1,id);
        ptmt.execute();
        pst.execute();
    }

    public List<discussion> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from discussion where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<discussion> list = new ArrayList<>();
            while (rs.next()) {
                discussion dis = new discussion();
                dis.setCourseID(rs.getInt("CourseID"));
                dis.setDID(rs.getInt("DID"));
                dis.setUID(rs.getInt("UID"));
                dis.setDiscussion(rs.getString("Discussion"));
                //dis.setCreateTime(rs.getDate("CreateTime"));
                list.add(dis);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
