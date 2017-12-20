package Lanchonete;

public class Milho extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Milho(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " milho";
    }

    @Override
    public double custo() {
        return .5 + lanchonete.custo();
    }
}
