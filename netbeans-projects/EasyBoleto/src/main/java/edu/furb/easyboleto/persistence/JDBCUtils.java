package edu.furb.easyboleto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

public class JDBCUtils {

    @Produces
    @RequestScoped
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/EasyBoletoDB", "root", "key50100");
    }

    public void closeConnection(@Disposes Connection conn) throws SQLException {
        conn.close();
    }
}
