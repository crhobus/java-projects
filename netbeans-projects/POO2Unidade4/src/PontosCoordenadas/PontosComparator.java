package PontosCoordenadas;

import java.util.*;

public class PontosComparator implements Comparator<Pontos> {

    private int x, y;

    public PontosComparator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compare(Pontos p1, Pontos p2) {
        int pontoP1 = Math.abs(p1.getX() - x) + Math.abs(p1.getY() - y);
        int pontoP2 = Math.abs(p2.getX() - x) + Math.abs(p2.getY() - y);
        return pontoP1 - pontoP2;
    }
}
