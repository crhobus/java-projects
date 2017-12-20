package Lanchonete;

public class PratoCasa extends Lanchonete {

    public PratoCasa() {
        setDescricao("1 Porção de feijão com arroz:");
    }

    @Override
    public double custo() {
        return 10.89;
    }
}
