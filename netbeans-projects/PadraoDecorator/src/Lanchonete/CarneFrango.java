package Lanchonete;

public class CarneFrango extends DecoradorCondimento {

    private Lanchonete lanchonete;

    public CarneFrango(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }

    @Override
    public String getDescricao() {
        return lanchonete.getDescricao() + " carne de frango";
    }

    @Override
    public double custo() {
        return 4.36 + lanchonete.custo();
    }
}
