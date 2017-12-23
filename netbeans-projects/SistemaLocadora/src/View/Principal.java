package View;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Principal {

    public Principal() {
        try {
            aparencia();
            new Locadora(getConnectionOracle());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Connection getConnectionOracle() throws Exception {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemloc", "key50700");
        } catch (Exception ex) {
            throw new Exception("Não foi possivel estabelecer uma conexão com o banco de dados oracle");
        }
    }

    private void aparencia() throws Exception {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            throw new Exception("O sistema encontrou um problema na aparência e presisa ser fechado");
        }
    }
}
