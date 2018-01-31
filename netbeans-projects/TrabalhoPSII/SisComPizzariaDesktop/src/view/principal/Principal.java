package view.principal;

import control.Servidor;
import control.ServidorAction;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import view.login.Login;

public class Principal {

    public Principal() {
        try {
            aparencia();
            Login login = new Login(getServidor());
            login.ativar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Inicialização do sistema", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void aparencia() throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            throw new Exception("Não foi possível iniciar o sistema"
                    + "\nVerifique se o sistema foi instalado corretamente");
        }
    }

    private Servidor getServidor() throws Exception {
        Servidor serv = new ServidorAction();
        serv.conectar();
        return serv;
    }
}
