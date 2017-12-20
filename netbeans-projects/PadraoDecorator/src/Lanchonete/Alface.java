package Lanchonete;

public class Alface extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Alface(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " alface";
    }

    @Override
    public double custo() {
        return .9 + lanchonete.custo();
    }
}
