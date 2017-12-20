package Lanchonete;

public class Acucar extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Acucar(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " açucar";
    }

    @Override
    public double custo() {
        return .15 + lanchonete.custo();
    }
}
