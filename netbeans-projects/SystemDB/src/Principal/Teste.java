package Principal;

import Seguranca.Seguranca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Teste {

    public static void main(String[] args) {
        PreparedStatement stm = null;
        Connection con = null;
        try {
            Seguranca seguranca = new Seguranca("123456789".getBytes());
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemdb", "key50400");

            stm = con.prepareStatement("INSERT INTO TBUSUARIO (CODUSUARIO, DATACADAS, ULTALTERACAO, "
                    + "USUARIO, PERMISSAO, SENHA, CODFUNC) VALUES (?, ?, ?, ?, ?, ?, 1)");
            stm.setInt(1, 1);
            stm.setTimestamp(2, new Timestamp(new Date().getTime()));
            stm.setTimestamp(3, new Timestamp(new Date().getTime()));
            stm.setString(4, "admin");
            stm.setInt(5, 1);
            stm.setBytes(6, seguranca.gerarHash("key50100"));
            stm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
