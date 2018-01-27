package Principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDAO {

    private Connection con;

    public DBDAO(Connection con) {
        this.con = con;
    }

    public int getCadastrado(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            throw new SQLException(msg);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isCadastrado(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new SQLException(msg);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isVazio(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(msg);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getProxCod(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException ex) {
            throw new SQLException(msg);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean delete(String sql, String msg) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            return stm.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new SQLException(msg);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }
}
