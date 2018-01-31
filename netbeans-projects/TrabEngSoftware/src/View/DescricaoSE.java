package View;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DescricaoSE extends JDialog {

    private static final long serialVersionUID = 1L;

    public DescricaoSE() {
        setTitle("Saída Externa SE");
        JTextArea textArea = new JTextArea("Gera dados ou informações de controle que saem pela"
                + "\nfronteira da aplicação. Sua principal intenção é apresentar"
                + "\ndados ao usuário com outra lógica que não só a sua simples"
                + "\nrecuperação. Deve conter fórmula matemática ou cálculo,"
                + "\ncriar dados derivados, manter um ou mais ALI e/ou alterar"
                + "\no comportamento do sistema.");
        textArea.setEditable(false);
        add(textArea);
        setSize(370, 150);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
