package dao;

import domain.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class resourceUtil {
    public void add(resource resource) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into resource(CourseID,Path) values(?,?)";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, resource.getCourseID());
        //ptmt.setDate(2, resource.getCreateTime());
        ptmt.setString(2, resource.getPath());

        ptmt.execute();
    }

    public void delete(int id) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "delete from resource where RID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setInt(1,id);
        ptmt.execute();
    }

    public List<resource> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from resource where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<resource> list = new ArrayList<>();
            while (rs.next()) {
                resource resource = new resource();
                resource.setRID(rs.getInt("RID"));
                resource.setCourseID(rs.getInt("CourseID"));
                resource.setPath(rs.getString("Path"));
                //resource.setCreateTime(rs.getDate("CreateTime"));
                list.add(resource);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
