package View;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DescricaoCE extends JDialog {

    private static final long serialVersionUID = 1L;

    public DescricaoCE() {
        setTitle("Consulta Externa CE");
        JTextArea textArea = new JTextArea("Recuperação de dados ou informações de controle, enviados"
                + "\npara fora da fronteira da aplicação. Sua principal intenção é"
                + "\napresentar informações ao usuário pela simples recuperação"
                + "\nde dados ou informações de controle de um ALIs/AIEs.");
        textArea.setEditable(false);
        add(textArea);
        setSize(370, 140);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
