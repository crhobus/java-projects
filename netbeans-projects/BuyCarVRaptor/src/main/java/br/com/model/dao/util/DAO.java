package br.com.model.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

    private Connection conn;

    public void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemcar", "key50500");
    }

    public void desconectar() throws SQLException {
        conn.close();
    }

    private Connection getConnection() {
        return conn;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public Long getSequence(String sequence) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select ");
            select.append(sequence);
            select.append(" from    dual ");

            ps = getPreparedStatement(select.toString());
            ResultSet rs = ps.executeQuery();

            Long seq = null;
            if (rs.next()) {
                seq = rs.getLong(1);
            }
            if (seq == null) {
                throw new SQLException("Invalid sequence");
            }
            return seq;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
