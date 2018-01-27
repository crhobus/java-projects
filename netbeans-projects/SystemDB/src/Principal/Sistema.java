package Principal;

import java.sql.DriverManager;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Login.Login;
import java.sql.Connection;

public class Sistema {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O sistema encontrou um problema e presisa ser fechado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemdb", "key50400");

            new Login(con);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer uma conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
