package main;

import factory.PizzaPeperoniFactory;
import factory.PizzaQueijoFactory;
import factory.PizzariaFactory;

public class Controller {

    private final PizzariaFactory[] factories = {new PizzaPeperoniFactory(), new PizzaQueijoFactory()};

    public void criarPizza(int sabor) {

        //Pega uma fabrica generica (n�o importa se � a Peperoni ou de queijo).
        PizzariaFactory factory = factories[sabor];

        //"constroi" a pizza
        factory.fazerPizza();

    }
}
