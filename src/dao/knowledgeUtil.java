package dao;

import domain.knowledge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class knowledgeUtil {
    public void add(knowledge knowledge) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into coursecell(UnitID,CellName,Discription,Path) values(?,?,?,?)";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, knowledge.getUnitID());
        ptmt.setString(2, knowledge.getCellName());
        ptmt.setString(3, knowledge.getDiscription());
        ptmt.setString(4, knowledge.getPath());
        ptmt.execute();
    }

    public void update(int id, String cellName, String discription, String path) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "update coursecell set CellName=?,Discription=?,Path=? where CellID = ?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setString(1, cellName);
        ptmt.setString(2, discription);
        ptmt.setString(3, path);
        ptmt.setInt(4, id);
        ptmt.execute();
    }

    public void delete(int id) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "delete from coursecell where CellID=?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setInt(1,id);
        ptmt.execute();
    }

    public List<knowledge> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from coursecell where UnitID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<knowledge> list = new ArrayList<>();
            while (rs.next()) {
                knowledge knowledge = new knowledge();
                knowledge.setCellID(rs.getInt("CellID"));
                knowledge.setUnitID(rs.getInt("UnitID"));
                knowledge.setCellName(rs.getString("CellName"));
                knowledge.setDiscription(rs.getString("Discription"));
                knowledge.setPath(rs.getString("Path"));
                list.add(knowledge);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
