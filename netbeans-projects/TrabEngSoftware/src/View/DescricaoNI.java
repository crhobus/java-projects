package View;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DescricaoNI extends JDialog {

    private static final long serialVersionUID = 1L;

    public DescricaoNI() {
        setTitle("Nível de Influência NI");
        JTextArea textArea = new JTextArea("O FA (Fator de Ajuste) é baseado em 14 características gerais"
                + "\nde sistema que determina a funcionalidade geral da aplicação"
                + "\nque esté sendo contada. O nível (grau) de influência varia em"
                + "\numa escala de 0 a 5."
                + "\n0 - Nenhuma influência"
                + "\n1 - Influência mínima"
                + "\n2 - Influência moderada"
                + "\n3 - Influência média"
                + "\n4 - Influência significante"
                + "\n5 - Influência forte");
        textArea.setEditable(false);
        add(textArea);
        setSize(370, 210);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
