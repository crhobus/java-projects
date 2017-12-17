package n3;

import java.util.ArrayList;

public class ObjetoGrafico {

    private ArrayList<ObjetoGrafico> objetosFilhos;
    private ArrayList<Ponto> listaPontos;
    private int primitiva = 2;
    private double[] rgb;
    private Transform matrix;
    private double xMin, xMax, yMin, yMax;
    private boolean primeiroPonto;
    private boolean desenhaBBox;
    private double pontoXCentroObj;
    private double pontoYCentroObj;

    public ObjetoGrafico() {
        objetosFilhos = new ArrayList<>();
        listaPontos = new ArrayList<>();
        rgb = new double[3];
        matrix = new Transform();
        primeiroPonto = false;
    }

    public ArrayList<Ponto> getListaPontos() {
        return listaPontos;
    }

    public void addPonto(Ponto ponto) {
        this.listaPontos.add(ponto);
        atualizaXY();
    }

    public void atualizaXY() {
        for (Ponto p : listaPontos) {
            if (!primeiroPonto) {
                xMin = p.getX();
                xMax = p.getX();
                yMin = p.getY();
                yMax = p.getY();
                primeiroPonto = true;
            }
            if (p.getX() > xMax) {
                xMax = p.getX();
            }
            if (p.getX() < xMin) {
                xMin = p.getX();
            }
            if (p.getY() > yMax) {
                yMax = p.getY();
            }
            if (p.getY() < yMin) {
                yMin = p.getY();
            }
        }
        pontoXCentroObj = (xMax + xMin) / 2;
        pontoYCentroObj = (yMax + yMin) / 2;
    }

    public Transform getMatrix() {
        return matrix;
    }

    public void setMatrix(Transform matrix) {
        this.matrix = matrix;
    }

    public ArrayList<ObjetoGrafico> getObjetosFilhos() {
        return objetosFilhos;
    }

    public void addObjetosFilhos(ObjetoGrafico objetoFilho) {
        this.objetosFilhos.add(objetoFilho);
    }

    public int getPrimitiva() {
        return primitiva;
    }

    public void setPrimitiva(int primitiva) {
        this.primitiva = primitiva;
    }

    public void setRed(int red) {
        this.rgb[0] = red;
        this.rgb[1] = 0;
        this.rgb[2] = 0;
    }

    public void setGreen(int green) {
        this.rgb[0] = 0;
        this.rgb[1] = green;
        this.rgb[2] = 0;
    }

    public void setBlue(int blue) {
        this.rgb[0] = 0;
        this.rgb[1] = 0;
        this.rgb[2] = blue;
    }

    public void setBlack() {
        this.rgb[0] = 0;
        this.rgb[1] = 0;
        this.rgb[2] = 0;
    }

    public double[] getRgb() {
        return rgb;
    }

    public void atualizaUltimoPonto(double x, double y) {
        listaPontos.get(listaPontos.size() - 1).setX(x);
        listaPontos.get(listaPontos.size() - 1).setY(y);
    }

    public boolean isDesenhaBBox() {
        return desenhaBBox;
    }

    public void setDesenhaBBox(boolean desenhaBBox) {
        this.desenhaBBox = desenhaBBox;
    }

    public double getxMax() {
        return xMax;
    }

    public double getxMin() {
        return xMin;
    }

    public double getyMax() {
        return yMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getPontoXCentroObj() {
        return pontoXCentroObj;
    }

    public double getPontoYCentroObj() {
        return pontoYCentroObj;
    }
}
