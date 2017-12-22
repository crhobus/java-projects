package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBDAO {

    private Connection con;

    public DBDAO(Connection con) {
        this.con = con;
    }

    protected int getCadastrado(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            throw new SQLException(msg + "\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    protected boolean isCadastrado(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new SQLException(msg + "\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    protected boolean isVazio(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(msg + "\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    protected int getProxCod(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException ex) {
            throw new SQLException(msg + "\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    protected boolean delete(String sql, String msg) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            return stm.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new SQLException(msg + "\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }
}
