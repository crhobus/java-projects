package envoltoriaConvexa;

public class Ponto implements Comparable<Ponto> {

    private int x, y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Ponto ponto) {
        if (this.getX() == ponto.getX()) {
            return 0;
        }
        if (this.getX() < ponto.getX()) {
            return -1;
        }
        return 1;
    }
}