package CollectionVector;

import java.util.NoSuchElementException;
import java.util.Vector;

public class CollectionVector {

    private static final String cores[] = {"Vermelho", "Branco", "Azul", "Preto", "Marrom", "Amarelo", "Verde", "Azul", "Roxo", "Ciano", "Lilaz"};

    public CollectionVector() {
        Vector<String> vetor = new Vector<String>();
        imprimirVetor(vetor);
        for (String cor : cores) {
            vetor.add(cor);
        }
        imprimirVetor(vetor);
        try {
            System.out.printf("Primeiro elemento: %s\n", vetor.firstElement());//Retorna uma referencia ao primeiro elemento do Vector
            System.out.printf("Ultimo elemento: %s\n", vetor.lastElement());//Retorna uma referencia ao último elemento do Vector
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
        if (vetor.contains("Azul")) {//Verifica se o vector contém azul
            System.out.printf("\n\"Azul\" Encontrado no índice %d\n\n", vetor.indexOf("Azul"));//Retorna o índice que contém a String azul
        } else {
            System.out.println("\n\"Azul\" não encontrado\n");
        }
        vetor.remove("Azul");
        System.out.println("\"Azul\" foi removido");
        imprimirVetor(vetor);
        if (vetor.contains("Azul")) {
            System.out.printf("\"Azul\" Encontrado no índice %d\n", vetor.indexOf("Azul"));
        } else {
            System.out.println("\"Azul\" não encontrado");
        }
        System.out.printf("\nTamanho: %d\nCapacidade: %d\n", vetor.size(), vetor.capacity());//size() retorna oo tamanho do vector, e capacity() retorna a capacidade maxima do vector, o vector inicia com 10 quando encher esses 10 ele aumenta internamente seu vetor em mais 10
    }

    private void imprimirVetor(Vector<String> vetor) {
        if (vetor.isEmpty()) {
            System.out.print("Vetor está vazio");
        } else {
            System.out.print("Vetor contém: ");
            for (String elemento : vetor) {
                System.out.printf("%s ", elemento);
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        new CollectionVector();
    }
}
