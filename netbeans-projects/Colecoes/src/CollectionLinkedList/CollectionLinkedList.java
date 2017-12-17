package CollectionLinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CollectionLinkedList {

    private static final String cores[] = {"Preto", "Amarelo", "Verde", "Azul", "Violeta", "Prata"};
    private static final String cores2[] = {"Ouro", "Branco", "Marrom", "Azul", "Cinza", "Prata"};

    public CollectionLinkedList() {
        List<String> lista1 = new LinkedList<String>();
        List<String> lista2 = new LinkedList<String>();
        for (String cor : cores) {
            lista1.add(cor);//Adiciona no LinkedList
        }
        for (String cor : cores2) {
            lista2.add(cor);//Adiciona no LinkedList
        }
        lista1.addAll(lista2);//addiciona todos os elementos da lista2 no final da lista1
        lista2 = null;
        imprimeLista(lista1);
        converterStringMaiuscula(lista1);
        imprimeLista(lista1);
        System.out.print("\nExcluindo elementos 4-6..");
        removeItens(lista1, 4, 7);
        imprimeLista(lista1);
        imprimeListaInvertida(lista1);
        System.out.println();
    }

    private void imprimeLista(List<String> lista) {
        System.out.println("\nLista: ");
        for (String cor : lista) {
            System.out.printf("%s ", cor);
        }
        System.out.println();
    }

    private void converterStringMaiuscula(List<String> lista) {
        ListIterator<String> iterator = lista.listIterator();//ListIterator: percore uma lista de trás para frente ou vice-versa
        while (iterator.hasNext()) {//verifica se a lista contém outro elemento
            String cor = iterator.next();//obtém a proxíma String na lista
            iterator.set(cor.toUpperCase());//o método String toUpperCase converte as letras para maiúscula da String e chama o método ListIterator set para substituir a String atual que iterator referencia pela String retornada pelo método toUpperCase
        }
    }

    private void removeItens(List<String> lista, int inicio, int fim) {
        lista.subList(inicio, fim).clear();//insere os elementos de um intervalo em uma subList e apos isso remove-os com o metodo clear()
    }

    private void imprimeListaInvertida(List<String> lista) {
        ListIterator<String> iterator = lista.listIterator(lista.size());//chama o método List listIterator com um arqumento que especifica a posição inicial (neste caso, o último elemento da lista) para obter um iterador que percore a lista de traz para frente ou vice-versa
        System.out.println("\nLista Invertida: ");
        while (iterator.hasPrevious()) {//hasPrevious() para determinar se há mais elementos  ao percores a lista de traz para frente
            System.out.printf("%s ", iterator.previous());//Obtem o elemento anterior da lista e o envia para a saída padrão
        }
    }

    public static void main(String[] args) {
        new CollectionLinkedList();
    }
}
