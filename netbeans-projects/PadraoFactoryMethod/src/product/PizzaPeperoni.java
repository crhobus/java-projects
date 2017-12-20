package product;

public class PizzaPeperoni extends Pizza {

    @Override
    public String getNome() {
        return getClass().getSimpleName();
    }

    @Override
    public void assar() {
        System.out.println("Assando por 10 min.");
    }
}
