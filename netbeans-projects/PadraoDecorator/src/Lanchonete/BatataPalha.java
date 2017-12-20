package Lanchonete;

public class BatataPalha extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public BatataPalha(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " batata palha";
    }

    @Override
    public double custo() {
        return .30 + lanchonete.custo();
    }
}
