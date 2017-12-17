package n2.exe01;

import java.awt.event.KeyEvent;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import n2.ExeFrame;
import n2.Exercicio;

public class Exe01 extends Exercicio {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;

    @Override
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));

        gl.glClearColor(0.95f, 0.95f, 0.95f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable glad) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        glu.gluOrtho2D(-20.0f, 20.0f, -20.0f, 20.0f);

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(10.0f, 0.0f);
        gl.glEnd();

        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(0.0f, 10.0f);
        gl.glEnd();

        gl.glColor3f(0.0f, 0.0f, 0.67f);
        gl.glPointSize(3);
        gl.glBegin(GL.GL_POINTS);
        for (int i = 0; i <= 360; i += 5) {
            gl.glVertex2f((float) getX(i, 10.0), (float) getY(i, 10.0));
        }
        gl.glEnd();

        gl.glFlush();
    }

    private double getX(double angulo, double raio) {
        return (raio * Math.cos(Math.PI * angulo / 180.0));
    }

    private double getY(double angulo, double raio) {
        return (raio * Math.sin(Math.PI * angulo / 180.0));
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new ExeFrame("1", new Exe01());
    }
}
