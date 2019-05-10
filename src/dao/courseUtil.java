package dao;

import domain.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class courseUtil {
    public void add(course course) throws SQLException {
        // 与特定数据库的连接（会话）。
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into course(CourseName,Discription,ImagePath,UID) values(?,?,?,?)";

        // 创建一个 PreparedStatement 对象来将参数化的 SQL 语句发送到数据库。
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
  /*
   * void setBigDecimal(int parameterIndex,BigDecimal x)throws SQLException
   * 将指定参数设置为给定 Java String 值。在将此值发送给数据库时，驱动程序将它转换成一个 SQL VARCHAR
   * 或 LONGVARCHAR 值（取决于该参数相对于驱动程序在 VARCHAR 值上的限制的大小）。
   */
        ptmt.setString(1, course.getCourseName());
        ptmt.setString(2, course.getDiscription());
        ptmt.setString(3, course.getImagePath());
        ptmt.setInt(4, course.getUID());
        //ptmt.setDate(5, course.getCreateTime());

        // 在此 PreparedStatement 对象中执行 SQL 语句
        ptmt.execute();
    }

    //所有课程
    public List<course> getSelect() {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from course ORDER BY CourseID DESC";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<course> list = new ArrayList<>();
            while (rs.next()) {
                course course = new course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setDiscription(rs.getString("Discription"));
                course.setImagePath(rs.getString("ImagePath"));
                course.setUID(rs.getInt("UID"));
                //course.setCreateTime(rs.getDate("CreateTime"));
                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                list.add(course);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //查课程名字
    public List<course> getSelectByName(String coursename) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from course where CourseName like ? ORDER BY CourseID DESC";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1,"%"+coursename+"%");
            ResultSet rs = pst.executeQuery();
            List<course> list = new ArrayList<>();
            while (rs.next()) {
                course course = new course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setDiscription(rs.getString("Discription"));
                course.setImagePath(rs.getString("ImagePath"));
                course.setUID(rs.getInt("UID"));
                //course.setCreateTime(rs.getDate("CreateTime"));
                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                list.add(course);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //查简介
    public List<course> getSelectByDis(String discription) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from course where Discription like ? ORDER BY CourseID DESC";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1,"%"+discription+"%");
            ResultSet rs = pst.executeQuery();

            List<course> list = new ArrayList<>();
            while (rs.next()) {
                course course = new course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setDiscription(rs.getString("Discription"));
                course.setImagePath(rs.getString("ImagePath"));
                course.setUID(rs.getInt("UID"));
                //course.setCreateTime(rs.getDate("CreateTime"));
                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                list.add(course);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //查老师名字
    public List<course> getSelectByTeacher(String name) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql1 = "select UID from users where UserName = ?";
            PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement(sql1);
            pst1.setString(1,name);
            ResultSet rs1 = pst1.executeQuery();
            int UID = 0;
            if (rs1.next()){
                UID = rs1.getInt("UID");
            }

            if(UID > 0){
                String sql = "select * from course where UID = ? ORDER BY CourseID DESC";

                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1,UID);
                ResultSet rs = pst.executeQuery();
                List<course> list = new ArrayList<>();
                while (rs.next()) {
                    course course = new course();
                    course.setCourseID(rs.getInt("CourseID"));
                    course.setCourseName(rs.getString("CourseName"));
                    course.setDiscription(rs.getString("Discription"));
                    course.setImagePath(rs.getString("ImagePath"));
                    course.setUID(rs.getInt("UID"));
                    //course.setCreateTime(rs.getDate("CreateTime"));
                    // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                    list.add(course);
                }
                return list;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //最热课程
    public List<Integer> selectHot(){
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select CourseID, COUNT(*) favornumber from student GROUP BY CourseID ORDER BY favornumber DESC";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("CourseID"));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //按ID找课程
    public course getSelectByID(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from course where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                course course = new course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setDiscription(rs.getString("Discription"));
                course.setImagePath(rs.getString("ImagePath"));
                course.setUID(rs.getInt("UID"));
                //course.setCreateTime(rs.getDate("CreateTime"));
                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                return course;
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //按用户找课程
    public List<course> getSelectByUID(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from course where UID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<course> list = new ArrayList<>();
            while (rs.next()) {
                course course = new course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setDiscription(rs.getString("Discription"));
                course.setImagePath(rs.getString("ImagePath"));
                course.setUID(rs.getInt("UID"));
                //course.setCreateTime(rs.getDate("CreateTime"));
                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                list.add(course);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int selectStudentNum(int courseID){
        Connection conn = (Connection) DB_Helper.getConnection();
        int num = 0;
        try {
            String sql = "select * from student where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,courseID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                num++;
            }
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return num;
        }
    }
}
