package Lanchonete;

public class Hamburguer extends DecoradorCondimento {

    private Lanchonete Lanchonete;

    public Hamburguer(Lanchonete Lanchonete) {
        this.Lanchonete = Lanchonete;
    }

    @Override
    public String getDescricao() {
        return Lanchonete.getDescricao() + " hamburguer";
    }

    @Override
    public double custo() {
        return 2.78 + Lanchonete.custo();
    }
}
