package Lanchonete;

public class Suco extends Lanchonete {

    public Suco() {
        setDescricao("Suco:\nCondimentos:");
    }

    @Override
    public double custo() {
        return 3.87;
    }
}
