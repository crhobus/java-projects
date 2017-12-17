package n2.exe07;

import java.awt.event.KeyEvent;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import n2.ExeFrame;
import n2.Exercicio;

public class Exe07 extends Exercicio {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private float pontoX = 0;
    private float pontoY = 0;
    private float bBoxX;
    private float bBoxY;
    private float raioMaiorQuadrado;

    public Exe07() {
        raioMaiorQuadrado = (float) Math.pow(10.0f, 2);
    }

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

        circulo(10.0f, 0.0f, 0.0f);

        quadrado(10.0f);

        ponto();
        circulo(3.0f, pontoX, pontoY);

        gl.glFlush();
    }

    private void circulo(float raio, float x, float y) {
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        for (int i = 0; i <= 360; i += 5) {
            gl.glVertex2f((float) getX(i, raio) + x, (float) getY(i, raio) + y);
        }
        gl.glEnd();
    }

    private void ponto() {
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glPointSize(3);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(pontoX, pontoY);
        gl.glEnd();
    }

    private void quadrado(float raio) {
        bBoxX = (float) getX(45.0f, raio);
        bBoxY = (float) getY(45.0f, raio);

        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(-bBoxX, bBoxY);
        gl.glVertex2f(bBoxX, bBoxY);
        gl.glVertex2f(bBoxX, -bBoxY);
        gl.glVertex2f(-bBoxX, -bBoxY);
        gl.glEnd();
    }

    private boolean calcularPontoDentroEspaco() {
        if (pontoX > -bBoxX
                && pontoX < +bBoxX
                && pontoY > -bBoxY
                && pontoY < bBoxY) {
            glDrawable.display();
            return true;
        } else if (getDistancia(0.0f, pontoX, 0.0f, pontoY) < raioMaiorQuadrado) {
            glDrawable.display();
            return true;
        }
        return false;
    }

    private float getDistancia(float x1, float x2, float y1, float y2) {
        return (float) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                pontoX -= 0.5;
                if (!calcularPontoDentroEspaco()) {
                    pontoX += 0.5;
                }
                break;
            case KeyEvent.VK_RIGHT:
                pontoX += 0.5;
                if (!calcularPontoDentroEspaco()) {
                    pontoX -= 0.5;
                }
                break;
            case KeyEvent.VK_UP:
                pontoY += 0.5;
                if (!calcularPontoDentroEspaco()) {
                    pontoY -= 0.5;
                }
                break;
            case KeyEvent.VK_DOWN:
                pontoY -= 0.5;
                if (!calcularPontoDentroEspaco()) {
                    pontoY += 0.5;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    public static void main(String[] args) {
        new ExeFrame("7", new Exe07());
    }
}
