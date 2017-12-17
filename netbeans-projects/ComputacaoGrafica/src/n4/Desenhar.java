package n4;

import com.sun.opengl.util.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Desenhar implements GLEventListener, KeyListener {

    private GL gl;
    private GLU glu;
    private GLUT glut;
    private GLAutoDrawable glDrawable;
    private double xEye, yEye, zEye;
    private double xCenter, yCenter, zCenter;
    private final double xUp = 0.0f, yUp = 1.0f, zUp = 0.0f;
    private float aspectRatio;
    private Mundo mundo;
    private final int qtLinhas = 10;
    private final int qtColunas = 25;
    private int indexObjMundo = -1;//inicializa com -1 pois cada vez que chama o método proximoObj() incrementa 1
    private double posicaoPx = 4.5;
    private double posicaoPz = 24.5;
    private boolean fim;
    private Frame frame;
    private int tipoObjetoEmCena;
    private Random random;
    private int novoTipoObjeto;

    public Desenhar(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        glDrawable.setGL(new DebugGL(gl));

        mundo = new Mundo();
        random = new Random();
        proximoObj();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        xEye = 2.0f;
        yEye = 12.0f;
        zEye = -3.0f;
        xCenter = 5.0f;
        yCenter = 0.0f;
        zCenter = 7.0f;

        float posLight[] = {5.0f, 5.0f, 10.0f, 0.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0);
        gl.glEnable(GL.GL_LIGHT0);

        gl.glEnable(GL.GL_CULL_FACE);
        gl.glEnable(GL.GL_DEPTH_TEST);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        desenhaTabuleiro();
        for (ObjetoGrafico objeto : mundo.getObjetosGrafico()) {
            tipoObjetoEmCena = objeto.getTipoObjeto();
            switch (tipoObjetoEmCena) {
                case 1:
                    objeto.desenhaCubo(gl, glut);
                    break;
                case 2:
                    objeto.desenhaT(gl, glut);
                    break;
                case 3:
                    objeto.desenhaI(gl, glut);
                    break;
            }
        }

        gl.glFlush();
    }

    private void desenhaTabuleiro() {
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_LINES);
        for (int i = 0; i <= qtLinhas; i++) {//10 linha
            gl.glVertex3d(i, 0, 0);
            gl.glVertex3d(i, 0, qtColunas);
        }
        for (int i = 0; i <= qtColunas; i++) {//25 colunas
            gl.glVertex3d(0, 0, i);
            gl.glVertex3d(qtLinhas, 0, i);
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        aspectRatio = (float) height / (float) width;
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, aspectRatio, 5, 60);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(xEye, yEye, zEye, xCenter, yCenter, zCenter, xUp, yUp, zUp);
    }

    @Override
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_RIGHT://translação - seta - direita
                if (((novoTipoObjeto == 2
                        && posicaoPx > 1.5)
                        || ((novoTipoObjeto == 1
                        || novoTipoObjeto == 3)
                        && posicaoPx > 0.5))
                        && !mundo.verificaPosicaoOcupada((posicaoPx - 1), posicaoPz, tipoObjetoEmCena)
                        && !fim) {
                    posicaoPx--;
                    setPosicaoPx(posicaoPx);
                }
                break;
            case KeyEvent.VK_LEFT://translação - seta - esquerda
                if (((novoTipoObjeto == 2
                        && posicaoPx < 8.5)
                        || ((novoTipoObjeto == 1
                        || novoTipoObjeto == 3)
                        && posicaoPx < 9.5))
                        && !mundo.verificaPosicaoOcupada((posicaoPx + 1), posicaoPz, tipoObjetoEmCena)
                        && !fim) {
                    posicaoPx++;
                    setPosicaoPx(posicaoPx);
                }
                break;
            case KeyEvent.VK_DOWN://translação - seta - baixo
                if (((novoTipoObjeto == 2
                        && posicaoPz > 1.5)
                        || (novoTipoObjeto == 1
                        && posicaoPz > 0.5)
                        || (novoTipoObjeto == 3
                        && posicaoPz > 2.5))
                        && !mundo.verificaPosicaoOcupada(posicaoPx, (posicaoPz - 1), tipoObjetoEmCena)
                        && !fim) {
                    posicaoPz--;
                    setPosicaoPz(posicaoPz);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        //
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        //
    }

    public int getNovoTipoObjeto() {
        return novoTipoObjeto;
    }

    public int getTipoObjetoEmCena() {
        return tipoObjetoEmCena;
    }

    public Mundo getMundo() {
        return mundo;
    }

    public double getPosicaoPz() {
        return posicaoPz;
    }

    public void setPosicaoPz(double posicaoPz) {
        this.posicaoPz = posicaoPz;
        mundo.setPosicaoPz(posicaoPz, indexObjMundo);
        redesenhar();
    }

    public double getPosicaoPx() {
        return posicaoPx;
    }

    public void setPosicaoPx(double posicaoPx) {
        this.posicaoPx = posicaoPx;
        mundo.setPosicaoPx(posicaoPx, indexObjMundo);
        redesenhar();
    }

    public void redesenhar() {
        glDrawable.display();
    }

    public void proximoObj() {
        novoTipoObjeto = random.nextInt(3) + 1;
        if (!mundo.verificaPosicaoOcupada(4.5, 24.5, tipoObjetoEmCena)) {
            mundo.finalizaObjetoAnterior(posicaoPx, posicaoPz);
            frame.setPontos(indexObjMundo + 1);
            posicaoPx = 4.5;
            posicaoPz = 24.5;
            indexObjMundo++;
            mundo.addObjetoGrafico(new ObjetoGrafico(), posicaoPx, posicaoPz, indexObjMundo, novoTipoObjeto);
            ThreadMoveObjeto thread = new ThreadMoveObjeto(this);
            thread.start();
        } else {
            fim = true;
        }
    }
}
