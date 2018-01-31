package LoginAcesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    private Connection con;

    public LoginDAO(Connection con) {
        this.con = con;
    }

    public boolean insertSenha(String senha) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBSENHA (SENHA) VALUES (?)");
            stm.setString(1, senha);
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação da senha no banco de dados");
        }
    }

    public boolean updateSenha(String senha) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBSENHA SET SENHA = ?");
            stm.setString(1, senha);
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao regravar a senha no banco de dados");
        }
    }

    public String selectSenha() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBSENHA").executeQuery();
            if (rs.next()) {
                return rs.getString("SENHA");
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura da senha do banco de dados");
        }
    }
}
