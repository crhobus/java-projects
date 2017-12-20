package main;

import factory.PizzaPeperoniFactory;
import factory.PizzaQueijoFactory;
import factory.PizzariaFactory;

public class Main {

    public static void main(String[] args) {

        PizzariaFactory[] factories = {new PizzaPeperoniFactory(), new PizzaQueijoFactory()};

        //Pedido de uma pizza de peperoni
        PizzariaFactory fabrica = factories[0];

        fabrica.fazerPizza();
    }
}
