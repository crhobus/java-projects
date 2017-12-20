package factory;

import product.Pizza;

public abstract class PizzariaFactory {

    /**
     * Factory Method, deixa a decis�o de qual pizza criar para as sub-classes.
     *
     * @return Pizza
     */
    public abstract Pizza createPizza();

    /**
     * A forma de fazer a pizza (ou, as etapas da constru��o do produto) � igual
     * n�o importa o sabor da pizza.
     */
    public void fazerPizza() {

        //chamada para a factory method.
        Pizza pizza = createPizza();

        pizza.preparar();
        pizza.assar();
        pizza.cortar();
        pizza.embalar();
    }
}
