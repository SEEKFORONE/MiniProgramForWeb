package dao;

import domain.student;
import domain.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class studentUtil {
    public void add(student student) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into student(CourseID,UID) values(?,?)";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, student.getCourseID());
        ptmt.setInt(2, student.getUID());

        ptmt.execute();
    }

    public void delete(int UID,int CourseID) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "delete from student where UID=? and CourseID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setInt(1,UID);
        ptmt.setInt(2,CourseID);
        ptmt.execute();
    }

    //查学生
    public List<Integer> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select UID from student where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("UID"));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //查自己选课
    public List<course> getSelectByUID(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select CourseID from student where UID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<course> list = new ArrayList<>();
            courseUtil courseUtil = new courseUtil();
            while (rs.next()) {
                course course = courseUtil.getSelectByID(rs.getInt("CourseID"));
                if(course != null){
                    list.add(course);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
