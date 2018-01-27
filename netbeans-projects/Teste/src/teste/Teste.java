package teste;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Caio
 */
public class Teste {

    public static void main(String[] args) {
        try {
            Connection con = null;
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemdb", "key50400");
                updateUsuario(con, gerarHash("12345678"), 1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer uma conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (con != null) {
                    con.close();
                }
            }
            System.out.println("Senha alterada");
        } catch (Exception ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static byte[] gerarHash(String conteudo) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(conteudo.getBytes());
        return md.digest();
    }

    private static void updateUsuario(Connection con, byte[] senha, int codUsuario) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBUSUARIO SET SENHA = ? WHERE CODUSUARIO = ?");
            stm.setBytes(1, senha);
            stm.setInt(2, codUsuario);
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do usuário no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }
}
