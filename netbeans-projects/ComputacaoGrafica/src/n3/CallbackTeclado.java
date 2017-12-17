package n3;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL;

public class CallbackTeclado implements KeyEventDispatcher {

    private Desenhar desenhar;

    public CallbackTeclado(Desenhar desenhar) {
        this.desenhar = desenhar;
    }

    public ObjetoGrafico getObjetoGrafico() {
        return desenhar.getObjetoGrafico();
    }

    public Transform getMatrix() {
        return getObjetoGrafico().getMatrix();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent evt) {
        if (evt.getID() == KeyEvent.KEY_PRESSED) {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_N://inicia um novo objeto gráfico
                    desenhar.novoObjetoGrafico();
                    break;
                case KeyEvent.VK_O://zoon out
                    desenhar.zoomOut();
                    break;
                case KeyEvent.VK_I://zoon in
                    desenhar.zoomIn();
                    break;
                case KeyEvent.VK_C://modo criacao
                    desenhar.setEdicao(false);
                    break;
                case KeyEvent.VK_E://Modo edicao
                    desenhar.setEdicao(true);
                    break;
            }

            if (evt.isControlDown()) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_H://deslocamento horizontal +
                        desenhar.deslocamentoHorizontalPositivo();
                        break;
                    case KeyEvent.VK_V://deslocamento vertical +
                        desenhar.deslocamentoVerticalPositivo();
                        break;
                }
            } else if (evt.isAltDown()) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_H://deslocamento horizontal -
                        desenhar.deslocamentoHorizontalNegativo();
                        break;
                    case KeyEvent.VK_V://deslocamento vertical -
                        desenhar.deslocamentoVerticalNegativo();
                        break;
                }
            }

            if (getObjetoGrafico() != null) {//só altera os valores quando existir um objeto
                Ponto ponto = new Ponto();
                double lX;
                double lY;
                Transform matrixTranslate = new Transform();
                Transform matrixTranslateInverse = new Transform();

                double sX;
                double sY;
                Transform matrixScale = new Transform();

                Transform matrixRotate = new Transform();

                Transform matrixGlobal = new Transform();


                if (evt.isShiftDown()) {
                    switch (evt.getKeyCode()) {
                        case KeyEvent.VK_A://escala - aumenta o objeto
                            lX = -getObjetoGrafico().getPontoXCentroObj();
                            lY = -getObjetoGrafico().getPontoYCentroObj();
                            ponto.setX(lX);
                            ponto.setY(lY);
                            matrixTranslate.makeTranslation(ponto);

                            sX = 1.2;
                            sY = 1.2;
                            matrixScale.makeScale(sX, sY, 1.0);

                            lX = getObjetoGrafico().getPontoXCentroObj();
                            lY = getObjetoGrafico().getPontoYCentroObj();
                            ponto.setX(lX);
                            ponto.setY(lY);
                            matrixTranslateInverse.makeTranslation(ponto);

                            matrixGlobal = matrixGlobal.transformMatrix(matrixTranslateInverse);
                            matrixGlobal = matrixGlobal.transformMatrix(matrixScale);
                            matrixGlobal = matrixGlobal.transformMatrix(matrixTranslate);

                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixGlobal));
                            break;
                        case KeyEvent.VK_D://escala - diminui o objeto
                            lX = -getObjetoGrafico().getPontoXCentroObj();
                            lY = -getObjetoGrafico().getPontoYCentroObj();
                            ponto.setX(lX);
                            ponto.setY(lY);
                            matrixTranslate.makeTranslation(ponto);

                            sX = 0.8;
                            sY = 0.8;
                            matrixScale.makeScale(sX, sY, 1.0);

                            lX = getObjetoGrafico().getPontoXCentroObj();
                            lY = getObjetoGrafico().getPontoYCentroObj();
                            ponto.setX(lX);
                            ponto.setY(lY);
                            matrixTranslateInverse.makeTranslation(ponto);

                            matrixGlobal = matrixGlobal.transformMatrix(matrixTranslateInverse);
                            matrixGlobal = matrixGlobal.transformMatrix(matrixScale);
                            matrixGlobal = matrixGlobal.transformMatrix(matrixTranslate);

                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixGlobal));
                            break;
                    }
                } else {
                    switch (evt.getKeyCode()) {
                        case KeyEvent.VK_0:
                            getObjetoGrafico().setPrimitiva(GL.GL_POINTS);
                            break;
                        case KeyEvent.VK_1:
                            getObjetoGrafico().setPrimitiva(GL.GL_LINES);
                            break;
                        case KeyEvent.VK_2:
                            getObjetoGrafico().setPrimitiva(GL.GL_LINE_LOOP);
                            break;
                        case KeyEvent.VK_3:
                            getObjetoGrafico().setPrimitiva(GL.GL_LINE_STRIP);
                            break;
                        case KeyEvent.VK_4:
                            getObjetoGrafico().setPrimitiva(GL.GL_TRIANGLES);
                            break;
                        case KeyEvent.VK_5:
                            getObjetoGrafico().setPrimitiva(GL.GL_TRIANGLE_FAN);
                            break;
                        case KeyEvent.VK_6:
                            getObjetoGrafico().setPrimitiva(GL.GL_TRIANGLE_STRIP);
                            break;
                        case KeyEvent.VK_7:
                            getObjetoGrafico().setPrimitiva(GL.GL_QUADS);
                            break;
                        case KeyEvent.VK_8:
                            getObjetoGrafico().setPrimitiva(GL.GL_QUAD_STRIP);
                            break;
                        case KeyEvent.VK_9:
                            getObjetoGrafico().setPrimitiva(GL.GL_POLYGON);
                            break;
                        case KeyEvent.VK_V://cor - Vermelho
                            getObjetoGrafico().setRed(1);
                            break;
                        case KeyEvent.VK_G://cor - Verde
                            getObjetoGrafico().setGreen(1);
                            break;
                        case KeyEvent.VK_A://cor - Azul
                            getObjetoGrafico().setBlue(1);
                            break;
                        case KeyEvent.VK_P://cor - Preto
                            getObjetoGrafico().setBlack();
                            break;
                        case KeyEvent.VK_R://rotação
                            lX = -getObjetoGrafico().getPontoXCentroObj();
                            lY = -getObjetoGrafico().getPontoYCentroObj();
                            ponto.setX(lX);
                            ponto.setY(lY);
                            matrixTranslate.makeTranslation(ponto);

                            matrixRotate.makeZRotation(Transform.RAS_DEG_TO_RAD * 10);

                            lX = getObjetoGrafico().getPontoXCentroObj();
                            lY = getObjetoGrafico().getPontoYCentroObj();
                            ponto.setX(lX);
                            ponto.setY(lY);
                            matrixTranslateInverse.makeTranslation(ponto);

                            matrixGlobal = matrixGlobal.transformMatrix(matrixTranslateInverse);
                            matrixGlobal = matrixGlobal.transformMatrix(matrixRotate);
                            matrixGlobal = matrixGlobal.transformMatrix(matrixTranslate);

                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixGlobal));
                            break;
                        case KeyEvent.VK_RIGHT://translação - seta - direita
                            lX = 2;
                            ponto.setX(lX);
                            matrixTranslate.makeTranslation(ponto);
                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixTranslate));
                            break;
                        case KeyEvent.VK_LEFT://translação - seta - esquerda
                            lX = -2;
                            ponto.setX(lX);
                            matrixTranslate.makeTranslation(ponto);
                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixTranslate));
                            break;
                        case KeyEvent.VK_UP://translação - seta - cima
                            lY = 2;
                            ponto.setY(lY);
                            matrixTranslate.makeTranslation(ponto);
                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixTranslate));
                            break;
                        case KeyEvent.VK_DOWN://translação - seta - baixo
                            lY = -2;
                            ponto.setY(lY);
                            matrixTranslate.makeTranslation(ponto);
                            getObjetoGrafico().setMatrix(getMatrix().transformMatrix(matrixTranslate));
                            break;
                        case KeyEvent.VK_DELETE://remove objeto ou vértice selecionado
                            desenhar.removerObjeto();
                            break;
                    }
                }
            }
            desenhar.redesenhar();
        }
        return false;
    }
}
