package dao;

import domain.unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class unitUtil {
    public void add(unit unit) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into courseunit(UnitName,CourseID) values(?,?)";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setString(1, unit.getUnitName());
        ptmt.setInt(2, unit.getCourseID());
        ptmt.execute();
    }

    private void update(int unitID, String name) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "update courseunit set UnitName = ? where UnitID = ?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setString(1,name);
        ptmt.setInt(2,unitID);
        ptmt.execute();
    }

    private void delete(int id) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "delete from courseunit,coursecell where UnitID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setInt(1,id);
        ptmt.execute();
    }

    public List<unit> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from courseunit where CourseID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<unit> list = new ArrayList<>();
            while (rs.next()) {
                unit unit = new unit();
                unit.setCourseID(rs.getInt("CourseID"));
                unit.setUnitID(rs.getInt("UnitID"));
                unit.setUnitName(rs.getString("UnitName"));
                list.add(unit);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
