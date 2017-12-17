package n4;

import java.util.ArrayList;
import java.util.List;

public class Mundo {

    private List<ObjetoGrafico> objetosGrafico;
    private Ponto p;
    private Transform t;
    private ObjetoGrafico objetoGrafico;
    private int cor = 1;

    public Mundo() {
        objetosGrafico = new ArrayList<>();
    }

    public void addObjetoGrafico(ObjetoGrafico objetoGrafico, double x, double z, int index, int tipoObjeto) {
        this.objetoGrafico = objetoGrafico;
        objetosGrafico.add(this.objetoGrafico);
        switch (cor) {
            case 1:
                this.objetoGrafico.setRed();
                break;
            case 2:
                this.objetoGrafico.setGreen();
                break;
            case 3:
                this.objetoGrafico.setBlue();
                break;
            case 4:
                this.objetoGrafico.setCian();
                break;
            case 5:
                this.objetoGrafico.setYellow();
                break;
        }
        cor++;
        if (cor == 6) {
            cor = 1;
        }
        this.objetoGrafico.setTipoObjeto(tipoObjeto);
        p = new Ponto(x, 0.5, z, 1);
        t = new Transform();
        t.makeTranslation(p);
        objetosGrafico.get(index).setMatrix(t);
    }

    public List<ObjetoGrafico> getObjetosGrafico() {
        return objetosGrafico;
    }

    public void setPosicaoPx(double posicaoPx, int index) {
        p.setX(posicaoPx);
        t.makeTranslation(p);
        objetosGrafico.get(index).setMatrix(t);
    }

    public void setPosicaoPz(double posicaoPz, int index) {
        p.setZ(posicaoPz);
        t.makeTranslation(p);
        objetosGrafico.get(index).setMatrix(t);
    }

    public void finalizaObjetoAnterior(double posicaoPx, double posicaoPz) {
        if (objetoGrafico != null) {
            switch (objetoGrafico.getTipoObjeto()) {
                case 1:
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx, posicaoPz));
                    break;
                case 2:
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx, posicaoPz));
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx + 1, posicaoPz));
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx - 1, posicaoPz));
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx, posicaoPz - 1));
                    break;
                case 3:
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx, posicaoPz));
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx, posicaoPz - 1));
                    this.objetoGrafico.addPosicoes(new Posicoes(posicaoPx, posicaoPz - 2));
                    break;
            }
        }
    }

    public boolean verificaPosicaoOcupada(double posicaoPx, double posicaoPz, int tipoObjetoEmCena) {
        for (ObjetoGrafico obj : objetosGrafico) {
            if (!obj.getPosicoes().isEmpty()) {
                switch (tipoObjetoEmCena) {
                    case 1:
                        for (Posicoes posicoes : obj.getPosicoes()) {
                            if (posicoes.getPosicaoPx() == posicaoPx
                                    && posicoes.getPosicaoPz() == posicaoPz) {
                                return true;
                            }
                        }
                        break;
                    case 2:
                        for (Posicoes posicoes : obj.getPosicoes()) {
                            if ((posicoes.getPosicaoPx() == posicaoPx
                                    && posicoes.getPosicaoPz() == posicaoPz)
                                    || (posicoes.getPosicaoPx() == posicaoPx + 1
                                    && posicoes.getPosicaoPz() == posicaoPz)
                                    || (posicoes.getPosicaoPx() == posicaoPx - 1
                                    && posicoes.getPosicaoPz() == posicaoPz)
                                    || (posicoes.getPosicaoPx() == posicaoPx
                                    && posicoes.getPosicaoPz() == posicaoPz - 1)) {
                                return true;
                            }
                        }
                        break;
                    case 3:
                        for (Posicoes posicoes : obj.getPosicoes()) {
                            if ((posicoes.getPosicaoPx() == posicaoPx
                                    && posicoes.getPosicaoPz() == posicaoPz)
                                    || (posicoes.getPosicaoPx() == posicaoPx
                                    && posicoes.getPosicaoPz() == posicaoPz - 1)
                                    || (posicoes.getPosicaoPx() == posicaoPx
                                    && posicoes.getPosicaoPz() == posicaoPz - 2)) {
                                return true;
                            }
                        }
                        break;
                }
            }
        }
        return false;
    }
}
