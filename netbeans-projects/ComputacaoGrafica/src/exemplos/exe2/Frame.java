package exemplos.exe2;

import java.awt.BorderLayout;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Draw3D renderer = new Draw3D();

    public Frame() {
        // Cria o frame.
        super("02_Sample-3d");
        setBounds(50, 100, 500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        /*
         * Cria um objeto GLCapabilities para especificar o n�mero de bits por
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
    }

    public static void main(String[] args) {
        new Frame().setVisible(true);
    }
}
