package Principal;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Sistema {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro com os editores gr�ficos do sistema", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        new Principal();
    }
}
