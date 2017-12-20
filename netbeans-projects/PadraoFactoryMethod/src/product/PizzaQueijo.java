package product;

public class PizzaQueijo extends Pizza {

    @Override
    public String getNome() {
        return getClass().getSimpleName();
    }
}
