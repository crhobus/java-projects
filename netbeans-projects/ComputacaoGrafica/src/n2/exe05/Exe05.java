package n2.exe05;

import java.awt.event.KeyEvent;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import n2.ExeFrame;
import n2.Exercicio;

public class Exe05 extends Exercicio {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private int tipo = 1;

    @Override
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable glad) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        glu.gluOrtho2D(-20.0f, 20.0f, -20.0f, 20.0f);

        switch (tipo) {
            case 1://GL_POINTS
                desenhar(GL.GL_POINTS);
                break;
            case 2://GL_LINES
                desenhar(GL.GL_LINES);
                break;
            case 3://GL_LINE_LOOP
                desenhar(GL.GL_LINE_LOOP);
                break;
            case 4://GL_LINE_STRIP
                desenhar(GL.GL_LINE_STRIP);
                break;
            case 5://GL_TRIANGLES
                desenhar(GL.GL_TRIANGLES);
                break;
            case 6://GL_TRIANGLE_FAN
                desenhar(GL.GL_TRIANGLE_FAN);
                break;
            case 7://GL_TRIANGLE_STRIP
                desenhar(GL.GL_TRIANGLE_STRIP);
                break;
            case 8://GL_QUADS
                desenhar(GL.GL_QUADS);
                break;
            case 9://GL_QUAD_STRIP
                desenhar(GL.GL_QUAD_STRIP);
                break;
            case 10://GL_POLYGON
                desenhar(GL.GL_POLYGON);
                break;
        }

        gl.glFlush();
    }

    private void desenhar(int primitiva) {
        gl.glBegin(primitiva);
        gl.glColor3f(0f, 1f, 0f);
        gl.glVertex2f(10.0f, -10.0f);
        gl.glColor3f(1f, 0f, 0f);
        gl.glVertex2f(10.0f, 10.0f);
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex2f(-10.0f, 10.0f);
        gl.glColor3f(0.8f, 0f, 0.6f);
        gl.glVertex2f(-10.0f, -10.0f);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
    }

    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tipo++;
            if (tipo == 11) {
                tipo = 1;
            }
            glDrawable.display();
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    public static void main(String[] args) {
        new ExeFrame("5", new Exe05());
    }
}
