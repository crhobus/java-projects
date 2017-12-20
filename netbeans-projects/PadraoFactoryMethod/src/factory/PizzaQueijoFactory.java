package factory;

import product.Pizza;
import product.PizzaQueijo;

public class PizzaQueijoFactory extends PizzariaFactory {

    @Override
    public Pizza createPizza() {
        return new PizzaQueijo();
    }
}
