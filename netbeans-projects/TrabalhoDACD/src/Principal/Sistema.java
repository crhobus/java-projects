package Principal;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import LogTela.VariaveisOrdTabela;

public class Sistema {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            VariaveisOrdTabela controle = new VariaveisOrdTabela();
            new Principal(controle);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O programa encontrou um erro e precisa ser fechado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
