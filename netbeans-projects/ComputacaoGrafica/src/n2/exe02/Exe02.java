package n2.exe02;

import java.awt.event.KeyEvent;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import n2.ExeFrame;
import n2.Exercicio;

public class Exe02 extends Exercicio {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private int xMin, xMax, yMin, yMax;

    @Override
    public void init(GLAutoDrawable drawable) {
        xMin = -20;
        xMax = 20;
        yMin = -20;
        yMax = 20;
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

        glu.gluOrtho2D(xMin, xMax, yMin, yMax);

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
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_O) {
            if (xMin > -414
                    && xMax < 414
                    && yMin > -414
                    && yMax < 414) {
                xMin--;
                xMax++;
                yMin--;
                yMax++;
                glDrawable.display();
            } else {
                xMin = -414;
                xMax = 414;
                yMin = -414;
                yMax = 414;
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_P) {
            if (xMin < -11
                    && xMax > 11
                    && yMin < -11
                    && yMax > 11) {
                xMin++;
                xMax--;
                yMin++;
                yMax--;
                glDrawable.display();
            } else {
                xMin = -11;
                xMax = 11;
                yMin = -11;
                yMax = 11;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    public static void main(String[] args) {
        new ExeFrame("2", new Exe02());
    }
}
