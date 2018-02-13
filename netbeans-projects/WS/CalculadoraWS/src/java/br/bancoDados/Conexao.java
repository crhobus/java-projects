package br.bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Caio
 */
public class Conexao {

    private Connection con;

    public void iniciarConexaoBD() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemuser", "keyuser");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fecharConexaoBD() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConexao() {
        return con;
    }
}
