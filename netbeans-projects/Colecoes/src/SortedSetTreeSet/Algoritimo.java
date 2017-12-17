package SortedSetTreeSet;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class Algoritimo {

    private static final String cores[] = {"Amarelo", "Verde", "Preto", "Cinza", "Branco", "Laranja", "Vermelho", "Azul", "Preto"};
    private static final String aparelhos[] = {"TV", "Notebook", "Computador", "DVD", "MP3", "Home Theater"};

    public Algoritimo() {
        SortedSet<String> arvore = new TreeSet<String>(Arrays.asList(cores));//Ordena os elementos, e elimina os duplicados
        SortedSet<String> arvoreAparelhos = new TreeSet<String>(Arrays.asList(aparelhos));
        arvore.add("Amarelo");
        arvore.add("Rosa");
        arvore.add("Azul-Claro");
        arvore.add("Lilás");
        System.out.println("Sorted Set cores: ");
        imprimirSet(arvore);
        System.out.println("\nSorted Set aparelhos: ");
        imprimirSet(arvoreAparelhos);
        System.out.print("\nheadSet (\"Laranja\"):  ");
        imprimirSet(arvore.headSet("Laranja"));//Obtem o subconjunto do TreeSet em que cada elemento é menor que Laranja
        System.out.print("tailSet (\"Laranja\"):  ");//Obtem o subconjunto do TreeSet em que cada elemento é maior que Laranja
        imprimirSet(arvore.tailSet("Laranja"));
        System.out.printf("Primeiro: %s\n", arvore.first());
        System.out.printf("Ultimo: %s\n", arvore.last());
        arvore.addAll(arvoreAparelhos);
        System.out.println("\nSorted Set após ter inserido todos os aparelhos com as cores: ");
        imprimirSet(arvore);
        arvore.remove("TV");
        System.out.println("\nSorted Set após ter removido TV: ");
        imprimirSet(arvore);
        arvore.removeAll(arvoreAparelhos);
        System.out.println("\nSorted Set após ter removido todos os aparelhos das cores: ");
        imprimirSet(arvore);
        System.out.println("\nSorted Set aparelhos: ");
        imprimirSet(arvoreAparelhos);
    }

    private void imprimirSet(SortedSet<String> arvore) {
        if (arvore.isEmpty()) {
            System.out.print("A arvore está vazia");
        } else {
            for (String s : arvore) {
                System.out.printf("%s ", s);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Algoritimo();
    }
}
