package DownloadManager;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Sistema {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Download Manager encontrou um problema e presisa ser fechado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            new DownloadManager();
        } catch (Exception ex) {
            System.exit(0);
        }
    }
}
