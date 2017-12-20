package Lanchonete;

public class Palmito extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Palmito(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " palmito";
    }

    @Override
    public double custo() {
        return .56 + lanchonete.custo();
    }
}
