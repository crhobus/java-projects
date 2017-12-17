package LinkedList;

import java.util.*;

public class Colecao {

    private String cidades[] = {"Blumenau", "Timbó", "Indaial"};
    private String cidades2[] = {"Presidente Getúlio", "Ibirama", "Brusque"};

    public Colecao() {
        List<String> lista1 = new LinkedList<String>();
        List<String> lista2 = new LinkedList<String>();
        for (int i = 0; i < cidades.length; i++) {
            lista1.add(cidades[i]);
            lista2.add(cidades2[i]);
        }
        System.out.print("LISTA1: ");
        mostraLista(lista1);
        lista1.addAll(lista1);
        lista2 = null;
        System.out.print("\nLISTA1 APOS ADIÇÃO: ");
        mostraLista(lista1);
        retira(lista1);
        System.out.print("\nLISTA1 DEPOIS DA RETIRADA: ");
        mostraLista(lista1);
        System.out.print("\n");
    }

    public void mostraLista(List<String> lista) {
        for (String s : lista) {
            System.out.printf("%s ", s);
        }

    }

    public void retira(List<String> lista) {
        lista.subList(1, 3).clear();
    }

    public static void main(String[] args) {
        new Colecao();
    }
}
