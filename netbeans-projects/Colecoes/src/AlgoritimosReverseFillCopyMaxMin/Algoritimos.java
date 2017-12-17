package AlgoritimosReverseFillCopyMaxMin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Algoritimos {

    private Character letras[] = {'P', 'C', 'M', 'A', 'M', 'O', 'W', 'D', 'M', 'Q'};
    private Character copiaLetras[];
    private List<Character> lista;
    private List<Character> copiaLista;

    public Algoritimos() {
        lista = Arrays.asList(letras);//lista é uma visualização de letras
        copiaLetras = new Character[letras.length];
        copiaLista = Arrays.asList(copiaLetras);
        System.out.println("Lista inicial: ");
        saida(lista);
        Collections.reverse(lista);//Inverte a ordem da lista
        System.out.println("\nLista invertida: ");
        saida(lista);
        Collections.copy(copiaLista, lista);//Copia os elementos da list para copiaLista
        System.out.println("\nLista copiada: ");
        saida(copiaLista);
        Collections.fill(lista, 'R');//Modifica toda lista para R
        System.out.println("\nLista preenchida com R: ");
        saida(lista);
    }

    private void saida(List<Character> lista) {
        for (Character elemento : lista) {
            System.out.printf("%s ", elemento);
        }
        System.out.printf("\nMáximo: %s", Collections.max(lista));//Localiza o maior elemento
        System.out.printf(" Minimo: %s\n", Collections.min(lista));//Localiza o menor elemento
    }

    public static void main(String[] args) {
        new Algoritimos();
    }
}
