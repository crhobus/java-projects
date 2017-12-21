package L102M2G03;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelArmazem extends JPanel {

    private JLabel[] numArmazem;
    private JLabel[] iniFila;
    private JLabel[] fimFila;
    private Font fonte;
    private final int TAMANHO;

    public PanelArmazem(Armazem armazem, Font fonte) {
        this.fonte = fonte;
        setLayout(null);
        TAMANHO = armazem.getTamanho();
        numArmazem = new JLabel[TAMANHO];
        iniFila = new JLabel[TAMANHO];
        fimFila = new JLabel[TAMANHO];
        this.initComponents();

    }

    public void initComponents() {
        for (int i = 0; i < TAMANHO; i++) {
            iniFila[i] = new JLabel();
            iniFila[i].setBounds(10, i * 20, 20, 20);
            numArmazem[i] = new JLabel("0");
            numArmazem[i].setBounds(30, i * 20, 50, 20);
            fimFila[i] = new JLabel();
            fimFila[i].setBounds(80, i * 20, 20, 20);
            add(numArmazem[i]).setFont(fonte);
            add(iniFila[i]).setFont(fonte);
            add(fimFila[i]).setFont(fonte);
        }
        iniFila[0].setText(">");
        fimFila[0].setText("<");
    }

    public void atualizar(int[] armazem, int inicio, int fim) {
        for (int i = 0; i < TAMANHO; i++) {
            if (i == inicio) {
                iniFila[i].setText(">");
            } else {
                iniFila[i].setText("");
            }
            if (i == fim) {
                fimFila[i].setText("<");
            } else {
                fimFila[i].setText("");
            }
            numArmazem[i].setText(armazem[i] + "");
        }
    }
}
