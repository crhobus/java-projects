package LoginAcesso;

import java.sql.Connection;

public class LoginControl {

    private LoginDAO loginDAO;

    public LoginControl(Connection con) {
        loginDAO = new LoginDAO(con);
    }

    public boolean insertSenha(String senha) throws Exception {
        return loginDAO.insertSenha(senha);
    }

    public boolean updateSenha(String senha) throws Exception {
        return loginDAO.updateSenha(senha);
    }

    public String selectSenha() throws Exception {
        return loginDAO.selectSenha();
    }
}
