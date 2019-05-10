package dao;

import domain.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class homeworkUtil {
    public void add(homework homework) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into homework(CourseID,Discription) values(?,?)";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, homework.getCourseID());
        ptmt.setString(2, homework.getDiscription());
        ptmt.execute();
    }

    public void update(String discription, int HID) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "update homework set Discription=? where HID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setString(1,discription);
        ptmt.setInt(2,HID);
        ptmt.execute();
    }

    public List<homework> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from homework where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<homework> list = new ArrayList<>();
            while (rs.next()) {
               homework homework = new homework();
               homework.setCourseID(rs.getInt("CourseID"));
               homework.setHID(rs.getInt("HID"));
               homework.setDiscription(rs.getString("Discription"));
               list.add(homework);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
