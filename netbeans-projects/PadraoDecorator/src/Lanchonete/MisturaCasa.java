package Lanchonete;

public class MisturaCasa extends Lanchonete {

    public MisturaCasa() {
        setDescricao("Café mistura da casa:\nCondimentos:");
    }

    @Override
    public double custo() {
        return .89;
    }
}
