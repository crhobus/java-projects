package negativoBinarizarContBrancosLenna;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Caio Renan Hobus
 */
public class Sistema {

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "LookAndFeel não suportado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
        new ImagemLena();
    }
}
