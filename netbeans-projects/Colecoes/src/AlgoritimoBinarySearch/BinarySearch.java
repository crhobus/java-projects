package AlgoritimoBinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    private static final String cores[] = {"Vermelho", "Branco", "Azul", "Preto", "Amarelo", "Roxo", "Escuro", "Rosa", "Azul"};
    private List<String> lista;

    public BinarySearch() {
        lista = new ArrayList<String>(Arrays.asList(cores));
        Collections.sort(lista);
        System.out.printf("ArrayList ordenado: %s\n", lista);
    }

    private void pesquisa() {
        imprimirResultadoPesquisa(cores[3]);
        imprimirResultadoPesquisa(cores[0]);
        imprimirResultadoPesquisa(cores[7]);
        imprimirResultadoPesquisa("Marrom");
        imprimirResultadoPesquisa("Cinza");
        imprimirResultadoPesquisa("Verde");

    }

    private void imprimirResultadoPesquisa(String str) {
        int result = 0;
        System.out.printf("\nProcurando por: %s\n", str);
        result = Collections.binarySearch(lista, str);
        if (result >= 0) {
            System.out.printf("Encontrado no índice %d\n", result);
        } else {
            System.out.printf("Não encontrado (%d)\n", result);
        }
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        search.pesquisa();
    }
}
