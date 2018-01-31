package View;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Sistema {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            new Principal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O software encontrou um problema na aparência e presisa ser fechado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
