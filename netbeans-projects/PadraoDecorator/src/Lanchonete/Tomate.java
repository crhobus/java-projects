package Lanchonete;

public class Tomate extends DecoradorCondimento {

    private Lanchonete Lanchonete;

    public Tomate(Lanchonete Lanchonete) {
        this.Lanchonete = Lanchonete;
    }

    @Override
    public String getDescricao() {
        return Lanchonete.getDescricao() + " tomate";
    }

    @Override
    public double custo() {
        return 0.54 + Lanchonete.custo();
    }
}
