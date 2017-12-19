package SomaNumeros;

import java.util.ArrayList;

public class SomaNumeros2 {

    public static void main(String[] args) {
        Integer integers[] = {1, 2, 3, 4, 5};
        ArrayList<Integer> listaInteger = new ArrayList<Integer>();
        for (Integer elemento : integers) {
            listaInteger.add(elemento);
        }
        System.out.printf("Lista de integer: %s\n", listaInteger);
        System.out.printf("Total dos elementos na lista de integer: %.0f\n\n", soma(listaInteger));

        Double doubles[] = {1.1, 3.3, 5.5};
        ArrayList<Double> listaDouble = new ArrayList<Double>();
        for (Double elemento : doubles) {
            listaDouble.add(elemento);
        }
        System.out.printf("Lista de double: %s\n", listaDouble);
        System.out.printf("Total dos elementos na lista de double: %.1f\n\n", soma(listaDouble));

        Number numbers[] = {1, 2.4, 3, 4.1};
        ArrayList<Number> listaNumber = new ArrayList<Number>();
        for (Number elemento : numbers) {
            listaNumber.add(elemento);
        }
        System.out.printf("Lista de number: %s\n", listaNumber);
        System.out.printf("Total dos elementos na lista de number: %.1f\n\n", soma(listaNumber));
    }

    private static double soma(ArrayList<? extends Number> lista) {
        double total = 0;
        for (Number elemento : lista) {
            total += elemento.doubleValue();
        }
        return total;
    }
}
