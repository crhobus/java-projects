package Lanchonete;

public class Cafe extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Cafe(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " café";
    }

    @Override
    public double custo() {
        return .20 + lanchonete.custo();
    }
}
