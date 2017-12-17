package AlgoritimosAddAllFrequencyDisjoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Algoritimos {

    private String cores[] = {"Vermelho", "Branco", "Amarelo", "Azul"};
    private List<String> lista;
    private Vector<String> vetor = new Vector<String>();

    public Algoritimos() {
        lista = Arrays.asList(cores);
        vetor.add("Preto");
        vetor.add("Marrom");
        vetor.add("Verde");
        System.out.println("Antes de addAll o vetor contém: ");
        for (String s : vetor) {
            System.out.printf("%s ", s);
        }
        Collections.addAll(vetor, cores);//adiciona todos de cores em  vetor
        System.out.println("\nDepois de addAll o vetor contém: ");
        for (String s : vetor) {
            System.out.printf("%s ", s);
        }
        int frequencia = Collections.frequency(vetor, "Branco");
        System.out.printf("\n\nFrequencia do Branco no vetor: %d\n", frequencia);
        boolean disjunto = Collections.disjoint(lista, vetor);//Verifica se há elementos em comum
        System.out.printf("\nLista e vetor %s elementos em comun\n", (disjunto ? "não tem" : "tem"));
    }

    public static void main(String[] args) {
        new Algoritimos();
    }
}
