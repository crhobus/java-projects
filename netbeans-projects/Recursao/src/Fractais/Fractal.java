package Fractais;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fractal extends JFrame {

    private final int largura = 400;
    private final int altura = 480;
    private final int NIVEL_MIN = 0, NIVEL_MAX = 15;
    private Color cor = Color.BLUE;
    private JButton btMudarCor, btAumentarNivel, btDiminuirNivel;
    private JLabel lbNivel;
    private FractalJPanel desenharEspaco;
    private JPanel painelPrincipal, painelControle;

    public Fractal() {
        super("Fractal");
        painelControle = new JPanel();
        painelControle.setLayout(new FlowLayout());
        btMudarCor = new JButton("Cores");
        painelControle.add(btMudarCor);
        btMudarCor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evento) {
                cor = JColorChooser.showDialog(Fractal.this, "Escolha uma cor", cor);
                if (cor == null) {
                    cor = Color.BLUE;
                }
                desenharEspaco.setCor(cor);
            }
        });
        btDiminuirNivel = new JButton("Diminuir nível");
        painelControle.add(btDiminuirNivel);
        btDiminuirNivel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evento) {
                int nivel = desenharEspaco.getNivel();
                nivel--;
                if (nivel >= NIVEL_MIN && nivel <= NIVEL_MAX) {
                    lbNivel.setText("Nível: " + nivel);
                    desenharEspaco.setNivel(nivel);
                    repaint();
                }
            }
        });
        btAumentarNivel = new JButton("Aumentar nível");
        painelControle.add(btAumentarNivel);
        btAumentarNivel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evento) {
                int nivel = desenharEspaco.getNivel();
                nivel++;
                if (nivel >= NIVEL_MIN && nivel <= NIVEL_MAX) {
                    lbNivel.setText("Nível: " + nivel);
                    desenharEspaco.setNivel(nivel);
                    repaint();
                }
            }
        });
        lbNivel = new JLabel("Nível 0:");
        painelControle.add(lbNivel);
        desenharEspaco = new FractalJPanel(0);
        painelPrincipal = new JPanel();
        painelPrincipal.add(painelControle);
        painelPrincipal.add(desenharEspaco);
        add(painelPrincipal);
        setSize(largura, altura);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Fractal fractal = new Fractal();
        fractal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
