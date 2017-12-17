package n4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Desenhar renderer;
    private JLabel lbPontuacao;

    public Frame() {
        super("N4");

        setBounds(0, 0, 600, 640);
        getContentPane().setLayout(new BorderLayout());

        JPanel pnPontuacao = new JPanel();
        pnPontuacao.setLayout(new FlowLayout());
        pnPontuacao.setPreferredSize(new Dimension(0, 30));

        lbPontuacao = new JLabel("Pontuação: 0");
        pnPontuacao.add(lbPontuacao);

        JPanel pnAreaJogo = new JPanel();
        pnAreaJogo.setLayout(new BorderLayout());

        renderer = new Desenhar(this);

        GLCapabilities glCaps = new GLCapabilities();
        glCaps.setRedBits(8);
        glCaps.setBlueBits(8);
        glCaps.setGreenBits(8);
        glCaps.setAlphaBits(8);

        GLCanvas canvas = new GLCanvas(glCaps);
        pnAreaJogo.add(canvas, BorderLayout.CENTER);
        canvas.addGLEventListener(renderer);
        canvas.addKeyListener(renderer);
        canvas.requestFocus();

        add(pnPontuacao, BorderLayout.NORTH);
        add(pnAreaJogo, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setPontos(int pontos) {
        lbPontuacao.setText("Pontuação: " + pontos);
    }
}
