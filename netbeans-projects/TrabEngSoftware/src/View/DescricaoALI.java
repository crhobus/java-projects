package View;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DescricaoALI extends JDialog {

    private static final long serialVersionUID = 1L;

    public DescricaoALI() {
        setTitle("Arquivo Lógico Interno ALI");
        JTextArea textArea = new JTextArea("Grupo de dados ou informações de controle logicamente"
                + "\nrelacionado, mantido na fronteira da aplicação e recohlecido"
                + "\npelo usuário. A principal intenção de um ALI é armazenar"
                + "\ndados mantidos por um ou mais processos elementares"
                + "\nda aplicação sendo contada.");
        textArea.setEditable(false);
        add(textArea);
        setSize(370, 150);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
