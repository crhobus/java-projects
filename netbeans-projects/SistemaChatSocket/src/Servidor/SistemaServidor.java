package Servidor;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class SistemaServidor {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            new Servidor();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O chat encontrou um problema na aparência e presisa ser fechado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
