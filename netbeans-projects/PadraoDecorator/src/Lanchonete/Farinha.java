package Lanchonete;

public class Farinha extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Farinha(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " farinha";
    }

    @Override
    public double custo() {
        return 0.75 + lanchonete.custo();
    }
}
