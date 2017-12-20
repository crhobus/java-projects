package Lanchonete;

public class Mussarela extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public Mussarela(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " mussarela";
    }

    @Override
    public double custo() {
        return .14 + lanchonete.custo();
    }
}
