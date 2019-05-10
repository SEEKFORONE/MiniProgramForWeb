package dao;

import domain.execution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class executionUtil {
    public void add(execution execution) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "insert into execution(HID,UID,Homework) values(?,?,?)";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);

        ptmt.setInt(1, execution.getHID());
        ptmt.setInt(2, execution.getUID());
        ptmt.setString(3,execution.getHomework());
        ptmt.execute();
    }

    //评分
    public void updateScore(int score, int UID) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "update execution set Score = ? where UID = ?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setInt(1,score);
        ptmt.setInt(2,UID);
        ptmt.execute();
    }

    //上交作业
    public void update(String homework, int UID) throws SQLException {
        Connection conn = (Connection) DB_Helper.getConnection();

        String sql = "update execution set Homework = ? where UID = ?";
        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
        ptmt.setString(1,homework);
        ptmt.setInt(2,UID);
        ptmt.execute();
    }

    public List<execution> getSelect(int id) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from execution where HID=?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<execution> list = new ArrayList<>();
            while (rs.next()) {
                execution execution = new execution();
                execution.setHID(rs.getInt("HID"));
                execution.setUID(rs.getInt("UID"));
                execution.setScore(rs.getInt("Score"));
                execution.setHomework(rs.getString("Homework"));
                list.add(execution);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public execution getMyHW(int HID, int UID) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from execution where HID=? and UID= ?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,HID);
            pst.setInt(2,UID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                execution execution = new execution();
                execution.setHID(rs.getInt("HID"));
                execution.setUID(rs.getInt("UID"));
                execution.setScore(rs.getInt("Score"));
                execution.setHomework(rs.getString("Homework"));
                return  execution;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean select(int HID, int UID) {
        Connection conn = (Connection) DB_Helper.getConnection();
        try {
            String sql = "select * from execution where HID=? and UID= ?";

            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,HID);
            pst.setInt(2,UID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
              return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
