package n2;

import java.awt.BorderLayout;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

public class ExeFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public ExeFrame(String nmExercicio, Exercicio renderer) {
        // Cria o frame.
        super("Exercício " + nmExercicio);
        setBounds(50, 100, 500, 500);
        getContentPane().setLayout(new BorderLayout());

        /*
         * Cria um objeto GLCapabilities para especificar o número de bits por
         * pixel para RGBA
         */
        GLCapabilities glCaps = new GLCapabilities();
        glCaps.setRedBits(8);
        glCaps.setBlueBits(8);
        glCaps.setGreenBits(8);
        glCaps.setAlphaBits(8);

        /*
         * Cria um canvas, adiciona ao frame e objeto "ouvinte" para os eventos
         * Gl, de mouse e teclado
         */
        GLCanvas canvas = new GLCanvas(glCaps);
        add(canvas, BorderLayout.CENTER);
        canvas.addGLEventListener(renderer);
        canvas.addKeyListener(renderer);
        canvas.requestFocus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
