package n3;

import java.awt.BorderLayout;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Desenhar renderer;

    public Frame() {
        super("N3");
        setBounds(0, 0, 500, 500);
        getContentPane().setLayout(new BorderLayout());

        renderer = new Desenhar(this.getWidth() - 7, this.getHeight() - 28);

        GLCapabilities glCaps = new GLCapabilities();
        glCaps.setRedBits(8);
        glCaps.setBlueBits(8);
        glCaps.setGreenBits(8);
        glCaps.setAlphaBits(8);

        GLCanvas canvas = new GLCanvas(glCaps);
        add(canvas, BorderLayout.CENTER);
        canvas.addGLEventListener(renderer);
        canvas.addMouseListener(renderer);
        canvas.addMouseMotionListener(renderer);
        canvas.requestFocus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
