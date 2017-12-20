package Lanchonete;

public class Pepino extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Pepino(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " pepino";
    }

    @Override
    public double custo() {
        return .26 + lanchonete.custo();
    }
}
