package View;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DescricaoAIE extends JDialog {

    private static final long serialVersionUID = 1L;

    public DescricaoAIE() {
        setTitle("Arquivos de Interface Externa AIE");
        JTextArea textArea = new JTextArea("Grupo de dados ou informações de controle logicamente"
                + "\nrelacionado, reconhecido pelo usuário, referenciado pela"
                + "\naplicação mas mantido na fronteira de outra. Sua principal"
                + "\nintenção é armazenar dados referenciados por um ou mais"
                + "\nPEs (Processo Elementar, que é a menor unidade de atividade"
                + "\nsignificativa para o usuário) da alicação sendo contada. O AIE"
                + "\ncontado para a aplicação deve ser um ALI para outra aplicação.");
        textArea.setEditable(false);
        add(textArea);
        setSize(370, 170);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
