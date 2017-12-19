package exercicios.trabalho;

public final class Porco extends Animal {

    static int nrPorcos = 0;

    public Porco(int i) {
        super(i);
        nrPorcos++;
    }

    public float getPeso() {
        return 100;
    }

    public String toString() {
        return "sou porco (" + id + ") de um total de " + nrPorcos;
    }
}
