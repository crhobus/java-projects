package Lanchonete;

public class Leite extends DecoradorCondimento {

    Lanchonete lanchonete;

    public Leite(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " leite";
    }

    @Override
    public double custo() {
        return 2.00 + lanchonete.custo();
    }
}
