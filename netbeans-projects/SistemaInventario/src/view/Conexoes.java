package view;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import view.login.Login;

public class Conexoes {

    public Conexoes() {
        try {
            aparencia();
            new Login(getConnection());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de conexão", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void aparencia() throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            throw new Exception("O sistema operacional não suporta este look and feel");
        }
    }

    private Connection getConnection() throws Exception {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemiven", "key50800");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
