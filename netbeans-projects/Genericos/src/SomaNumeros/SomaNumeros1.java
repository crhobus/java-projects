package SomaNumeros;

import java.util.ArrayList;

public class SomaNumeros1 {

    public static void main(String[] args) {
        Number numeros[] = {1, 2.4, 3, 4.1};
        ArrayList<Number> ListaNumeros = new ArrayList<Number>();
        for (Number elemento : numeros) {
            ListaNumeros.add(elemento);
        }
        System.out.printf("Lista de números: %s\n", ListaNumeros);
        System.out.printf("Total dos elementos na lista de números: %.1f\n", soma(ListaNumeros));
    }

    private static double soma(ArrayList<Number> lista) {
        double total = 0;
        for (Number elemento : lista) {
            total += elemento.doubleValue();
        }
        return total;
    }
}
