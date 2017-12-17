package AlgoritimoShuffle;

public class Carta {

    public static enum Face {As, Duque, Tres, Quatro, Cinco, Seis, Sete, Oito, Nove, Dez, Valete, Rainha, Rei};
    public static enum Naipe {Paus, Diamante, Coracao, Espada};
    private final Face face;
    private final Naipe naipe;

    public Carta(Face face, Naipe naipe) {
        this.face = face;
        this.naipe = naipe;
    }

    public Face getFace() {
        return face;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    @Override
    public String toString() {
        return String.format("%s de %s", face, naipe);
    }
}
