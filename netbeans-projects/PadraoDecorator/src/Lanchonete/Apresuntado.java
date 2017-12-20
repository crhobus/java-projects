package Lanchonete;

public class Apresuntado extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Apresuntado(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " apresuntado";
    }

    @Override
    public double custo() {
        return .17 + lanchonete.custo();
    }
}
