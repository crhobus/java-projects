package View;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DescricaoEE extends JDialog {

    private static final long serialVersionUID = 1L;

    public DescricaoEE() {
        setTitle("Entrada Externa EE");
        JTextArea textArea = new JTextArea("PE (Processo Elementar, que é a menor unidade de atividade"
                + "\nsignificativa para o usuário) que processa dados e/ou"
                + "\ninfornações de controle vindos de fora da froteira da aplicação."
                + "\nSua principal intenção é manter um ou mais ALIs e/ou alterar"
                + "\no comportamento dos sistema.");
        textArea.setEditable(false);
        add(textArea);
        setSize(370, 150);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
