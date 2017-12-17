package n2.exe06;

import java.awt.event.KeyEvent;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import n2.ExeFrame;
import n2.Exercicio;

public class Exe06 extends Exercicio {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private int pontoControle, qtdPontosSpline;
    private Ponto pontos[];//vetor utilizado para o cálculo de Bezier que utiliza 4 pontos

    @Override
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));

        pontoControle = 0;//ponto inicial spline
        qtdPontosSpline = 50;//quantidade de pontos inicial spline
        pontos = new Ponto[4];//inicialniza os 4 pontos da spline
        pontos[0] = new Ponto(-10.0f, -10.0f);
        pontos[1] = new Ponto(-10.0f, 10.0f);
        pontos[2] = new Ponto(10.0f, 10.0f);
        pontos[3] = new Ponto(10.0f, -10.0f);

        gl.glClearColor(0.95f, 0.95f, 0.95f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable glad) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        glu.gluOrtho2D(-20.0f, 20.0f, -20.0f, 20.0f);

        linha(new Ponto(0.0f, 0.0f), new Ponto(10.0f, 0.0f), 1.0f, 0.0f, 0.0f);

        linha(new Ponto(0.0f, 0.0f), new Ponto(0.0f, 10.0f), 0.0f, 1.0f, 0.0f);

        poliedro();

        ponto(pontos[pontoControle]);

        splineBezier();

        gl.glFlush();
    }

    private void linha(Ponto p1, Ponto p2, float f1, float f2, float f3) {
        gl.glColor3f(f1, f2, f3);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(p1.getX(), p1.getY());
        gl.glVertex2f(p2.getX(), p2.getY());
        gl.glEnd();
    }

    private void poliedro() {
        gl.glColor3f(0f, 1f, 1f);
        gl.glLineWidth(2.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        for (int i = 0; i < 4; i++) {
            gl.glVertex2f(pontos[i].getX(), pontos[i].getY());
        }
        gl.glEnd();
    }

    private void ponto(Ponto p) {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glPointSize(10.0f);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(p.getX(), p.getY());
        gl.glEnd();
    }

    private void splineBezier() {
        float t;
        float funcao1;
        float funcao2;
        float funcao3;
        float funcao4;

        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glPointSize(2.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        for (int i = 0; i <= qtdPontosSpline; i++) {

            t = (float) i / qtdPontosSpline;

            funcao1 = (float) (Math.pow((1 - t), 3));
            funcao2 = (float) (3 * t * Math.pow((1.0f - t), 2.0f));
            funcao3 = (float) (3 * Math.pow(t, 2.0f) * (1.0f - t));
            funcao4 = (float) (Math.pow(t, 3.0f));

            gl.glVertex2d(getX(funcao1, funcao2, funcao3, funcao4), getY(funcao1, funcao2, funcao3, funcao4));
        }
        gl.glEnd();
    }

    private float getX(float funcao1, float funcao2, float funcao3, float funcao4) {
        return funcao1 * getPontoX(0) + funcao2 * getPontoX(1) + funcao3 * getPontoX(2) + funcao4 * getPontoX(3);
    }

    private float getY(float funcao1, float funcao2, float funcao3, float funcao4) {
        return funcao1 * getPontoY(0) + funcao2 * getPontoY(1) + funcao3 * getPontoY(2) + funcao4 * getPontoY(3);
    }

    private float getPontoX(int i) {
        return pontos[i].getX();//Obtem o valor do ponto x da posição conforme o incice passado
    }

    private float getPontoY(int i) {
        return pontos[i].getY();//Obtem o valor do ponto y da posição conforme o incice passado
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
        boolean desenhar = true;
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_1:
                pontoControle = 0;
                break;
            case KeyEvent.VK_2:
                pontoControle = 1;
                break;
            case KeyEvent.VK_3:
                pontoControle = 2;
                break;
            case KeyEvent.VK_4:
                pontoControle = 3;
                break;
            case KeyEvent.VK_E:
                if (getPontoX(pontoControle) > -20) {//verifica limite tela
                    pontos[pontoControle].setX(getPontoX(pontoControle) - 1);
                } else {
                    desenhar = false;
                }
                break;
            case KeyEvent.VK_D:
                if (getPontoX(pontoControle) < 20) {//verifica limite tela
                    pontos[pontoControle].setX(getPontoX(pontoControle) + 1);
                } else {
                    desenhar = false;
                }
                break;
            case KeyEvent.VK_C:
                if (getPontoY(pontoControle) < 20) {//verifica limite tela
                    pontos[pontoControle].setY(getPontoY(pontoControle) + 1);
                } else {
                    desenhar = false;
                }
                break;
            case KeyEvent.VK_B:
                if (getPontoY(pontoControle) > -20) {//verifica limite tela
                    pontos[pontoControle].setY(getPontoY(pontoControle) - 1);
                } else {
                    desenhar = false;
                }
                break;
            case KeyEvent.VK_Q:
                if (qtdPontosSpline > 1) {
                    qtdPontosSpline--;
                } else {
                    desenhar = false;
                }
                break;
            case KeyEvent.VK_W:
                if (qtdPontosSpline < 100) {//limita quantidade de pontos
                    qtdPontosSpline++;
                } else {
                    desenhar = false;
                }
                break;
            default:
                desenhar = false;
                break;
        }
        if (desenhar) {
            glDrawable.display();
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    public static void main(String[] args) {
        new ExeFrame("6", new Exe06());
    }
}
