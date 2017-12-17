package n3;

import java.awt.KeyboardFocusManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Desenhar implements GLEventListener, MouseListener, MouseMotionListener {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private double xMinOrtho, xMaxOrtho, yMinOrtho, yMaxOrtho;
    private double xMinFrame, xMaxFrame, yMinFrame, yMaxFrame;
    private boolean edicao;
    private Mundo mundo;
    private int indexObjMundo;
    private boolean cenaCriada;

    public Desenhar(int xMaxFrame, int yMaxFrame) {
        this.xMinFrame = 0;
        this.xMaxFrame = xMaxFrame;
        this.yMinFrame = 0;
        this.yMaxFrame = yMaxFrame;
        this.edicao = false;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new CallbackTeclado(this));
        xMinOrtho = -30;
        xMaxOrtho = 30;
        yMinOrtho = -30;
        yMaxOrtho = 30;
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));
        mundo = new Mundo();
        mundo.addObjetoGrafico(new ObjetoGrafico());
        getObjetoGrafico().setDesenhaBBox(false);
        this.indexObjMundo = 0;

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        glu.gluOrtho2D(xMinOrtho, xMaxOrtho, yMinOrtho, yMaxOrtho);

        displaySRU();

        desenharObjetos();

        desenhaBBox();

        gl.glFlush();

        this.cenaCriada = true;
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        //
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
        //
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (!edicao) {
            getObjetoGrafico().addPonto(new Ponto(getXNormalizado(evt.getX()), -getYNormalizado(evt.getY()), 0, 1));
            getObjetoGrafico().addPonto(new Ponto(getXNormalizado(evt.getX()), -getYNormalizado(evt.getY()), 0, 1));
        } else {
            indexObjMundo = selecionar(evt);
            getObjetoGrafico().setDesenhaBBox(true);
        }
        redesenhar();
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        //
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        //
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        //
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
        if (cenaCriada
                && getObjetoGrafico().getListaPontos().size() > 0
                && !edicao) {
            getObjetoGrafico().atualizaUltimoPonto(getXNormalizado(evt.getX()), -getYNormalizado(evt.getY()));
            redesenhar();
        }
    }

    private void desenharObjetos() {
        for (ObjetoGrafico objeto : mundo.getObjetosGrafico()) {
            gl.glColor3d(objeto.getRgb()[0], objeto.getRgb()[1], objeto.getRgb()[2]);
            gl.glPushMatrix();
            gl.glMultMatrixd(objeto.getMatrix().getDate(), 0);
            gl.glPointSize(3.0f);
            gl.glBegin(objeto.getPrimitiva());
            for (Ponto ponto : objeto.getListaPontos()) {
                gl.glVertex2d(ponto.getX(), ponto.getY());
            }
            gl.glEnd();
            //aki chama desenharObjetos para cada filhos
            gl.glPopMatrix();
        }
    }

    public double getXNormalizado(double click) {
        double deltaD = xMaxOrtho - xMinOrtho;
        double deltaO = xMaxFrame - xMinFrame;
        return ((click - xMinFrame) * (deltaD / deltaO)) + xMinOrtho;
    }

    public double getYNormalizado(double click) {
        double deltaD = yMaxOrtho - yMinOrtho;
        double deltaO = yMaxFrame - yMinFrame;
        return ((click - yMinFrame) * (deltaD / deltaO)) + yMinOrtho;
    }

    public ObjetoGrafico getObjetoGrafico() {
        return mundo.getObjetoGrafico(indexObjMundo);
    }

    public void novoObjetoGrafico() {
        indexObjMundo = mundo.getPosUltimoObj() + 1;
        mundo.addObjetoGrafico(new ObjetoGrafico());
        edicao = false;
        getObjetoGrafico().setDesenhaBBox(false);
    }

    public void redesenhar() {
        glDrawable.display();
    }

    public void zoomIn() {
        if (xMinOrtho < -11
                && xMaxOrtho > 11
                && yMinOrtho < -11
                && yMaxOrtho > 11) {
            xMinOrtho++;
            xMaxOrtho--;
            yMinOrtho++;
            yMaxOrtho--;
        } else {
            xMinOrtho = -11;
            xMaxOrtho = 11;
            yMinOrtho = -11;
            yMaxOrtho = 11;
        }
    }

    public void zoomOut() {
        if (xMinOrtho > -414
                && xMaxOrtho < 414
                && yMinOrtho > -414
                && yMaxOrtho < 414) {
            xMinOrtho--;
            xMaxOrtho++;
            yMinOrtho--;
            yMaxOrtho++;
        } else {
            xMinOrtho = -414;
            xMaxOrtho = 414;
            yMinOrtho = -414;
            yMaxOrtho = 414;
        }
    }

    public void deslocamentoHorizontalPositivo() {
        yMaxOrtho += 2;
        yMinOrtho += 2;
    }

    public void deslocamentoVerticalPositivo() {
        xMaxOrtho += 2;
        xMinOrtho += 2;
    }

    public void deslocamentoHorizontalNegativo() {
        yMaxOrtho -= 2;
        yMinOrtho -= 2;
    }

    public void deslocamentoVerticalNegativo() {
        xMaxOrtho -= 2;
        xMinOrtho -= 2;
    }

    public void displaySRU() {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(-20.0f, 0.0f);
        gl.glVertex2f(20.0f, 0.0f);
        gl.glEnd();
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.0f, -20.0f);
        gl.glVertex2f(0.0f, 20.0f);
        gl.glEnd();
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public void desenhaBBox() {
        if (getObjetoGrafico().isDesenhaBBox()) {
            gl.glColor3d(0.0, 1.0, 1.0);
            gl.glPointSize(5.0f);
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2d(getObjetoGrafico().getxMin(), getObjetoGrafico().getyMax());
            gl.glVertex2d(getObjetoGrafico().getxMax(), getObjetoGrafico().getyMax());
            gl.glVertex2d(getObjetoGrafico().getxMax(), getObjetoGrafico().getyMin());
            gl.glVertex2d(getObjetoGrafico().getxMin(), getObjetoGrafico().getyMin());
            gl.glEnd();

            gl.glColor3d(0.0, 1.0, 1.0);
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2d(getObjetoGrafico().getxMin(), getObjetoGrafico().getyMax());
            gl.glVertex2d(getObjetoGrafico().getxMax(), getObjetoGrafico().getyMax());
            gl.glVertex2d(getObjetoGrafico().getxMax(), getObjetoGrafico().getyMin());
            gl.glVertex2d(getObjetoGrafico().getxMin(), getObjetoGrafico().getyMin());
            gl.glEnd();
        }
    }

    public void removerObjeto() {
        if (edicao) {
            mundo.removeObjetoGrafico(indexObjMundo);
            redesenhar();
            edicao = false;
            novoObjetoGrafico();
        }
    }

    public int selecionar(MouseEvent evt) {
        for (ObjetoGrafico objeto : this.mundo.getObjetosGrafico()) {
            Ponto pSel = new Ponto(getXNormalizado(evt.getX()), -getYNormalizado(evt.getX()), 0, 1);
            if (pSel.getY() > objeto.getyMin() && pSel.getY() > objeto.getyMax()
                    && pSel.getX() > objeto.getxMin() && pSel.getX() < objeto.getxMax()) {
                if (!ehInterno(objeto, pSel)) {
                    return this.mundo.getObjetosGrafico().indexOf(objeto);
                }
            }
        }
        return 1;
    }

    private boolean ehInterno(ObjetoGrafico pol, Ponto pSel) {
        int n = 0;
        ArrayList<Ponto> pontos = pol.getListaPontos();
        for (int i = 0; i < pontos.size() - 1; i++) {
            Ponto p = pontoDeInterseccao(pontos.get(i), pontos.get(i + 1), pSel);
            if (p != null) {
                double intX = p.getX();
                double intY = p.getY();//ponto de intersecção do lado pi-pi+1 com a reta L
                if (pontos.get(i).getY() != pontos.get(i + 1).getY()) {
                    if (intX == pSel.getX()) {
                        break;
                    } else {
                        if ((intX > pSel.getX())
                                && (intY > (pontos.get(i).getY() < pontos.get(i + 1).getY() ? pontos.get(i).getY() : pontos.get(i + 1).getY()))
                                && (intY <= (pontos.get(i).getY() > pontos.get(i + 1).getY() ? pontos.get(i).getY() : pontos.get(i + 1).getY()))) {
                            n++;
                        }
                    }
                } else {
                    if (pSel.getY() == pontos.get(i).getY()
                            && (pSel.getX() > (pontos.get(i).getX() < pontos.get(i + 1).getX() ? pontos.get(i).getX() : pontos.get(i + 1).getX()))
                            && (pSel.getX() <= (pontos.get(i).getX() > pontos.get(i + 1).getX() ? pontos.get(i).getX() : pontos.get(i + 1).getX()))) {
                        //psel sestá sobre o lado horizontal pi-pi+1 PARE
                        break;
                    }
                }
            }
        }
        if (n % 2 != 0) {
            return true;
        }
        return false;
    }

    private Ponto pontoDeInterseccao(Ponto p1, Ponto p2, Ponto pSel) {
        double t = (pSel.getY() - p1.getY()) / (p2.getY() - p1.getY());
        if (t > 0 && t < 1) {
            double x = p1.getX() + (p2.getX() - p1.getX()) * t;
            return new Ponto(x, pSel.getY(), 0, 1);
        }
        return null;
    }
}
