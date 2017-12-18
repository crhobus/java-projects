package Arvore;

import java.util.Random;

public class Sistema {

    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        int valor;
        Random numRandom = new Random();
        System.out.println("Inserindo os seguintes valores: ");
        for (int i = 1; i <= 10; i++) {
            valor = numRandom.nextInt(100);
            System.out.print(valor + " ");
            arvore.inserirNo(valor);
        }
        System.out.println("\n\nPercorendo a arvore em pré-ordem:");
        arvore.preOrdem();
        System.out.println("\n\nPercorendo a arvore em ordem:");
        arvore.ordem();
        System.out.println("\n\nPercorendo a arvore em pós-ordem:");
        arvore.posOrdem();
        System.out.println();
    }
}
