package n4;

public class Posicoes {

    private double posicaoPx;
    private double posicaoPz;

    public Posicoes(double posicaoPx, double posicaoPz) {
        this.posicaoPx = posicaoPx;
        this.posicaoPz = posicaoPz;
    }

    public double getPosicaoPx() {
        return posicaoPx;
    }

    public void setPosicaoPx(double posicaoPx) {
        this.posicaoPx = posicaoPx;
    }

    public double getPosicaoPz() {
        return posicaoPz;
    }

    public void setPosicaoPz(double posicaoPz) {
        this.posicaoPz = posicaoPz;
    }
}
