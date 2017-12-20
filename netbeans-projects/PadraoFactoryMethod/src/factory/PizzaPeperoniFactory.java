package factory;

import product.Pizza;
import product.PizzaPeperoni;

public class PizzaPeperoniFactory extends PizzariaFactory {

    @Override
    public Pizza createPizza() {
        return new PizzaPeperoni();
    }
}
