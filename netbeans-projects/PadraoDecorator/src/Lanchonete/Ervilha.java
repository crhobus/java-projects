package Lanchonete;

public class Ervilha extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Ervilha(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " ervilha";
    }

    @Override
    public double custo() {
        return .7 + lanchonete.custo();
    }
}
