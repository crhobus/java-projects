package UsandoToArray;

import java.util.Arrays;
import java.util.LinkedList;

public class UsandoToArray {

    public UsandoToArray() {
        String cores[] = {"Preto", "Azul", "Amarelo"};
        LinkedList<String> linked = new LinkedList<String>(Arrays.asList(cores));//retorna uma visualização do array como uma List, e depois inicializar LinkedList com List
        linked.addLast("Vermelho");//Adiciona-o como ultimo da lista
        linked.add("Rosa");//adiciona ao final
        linked.add(3, "Verde");//adiciona no terceiro índice
        linked.addFirst("Ciano");//adiona como primeiro item
        cores = linked.toArray(new String[linked.size()]);//método List toArray para obter um array String de linked, o array é uma cópia dos elementos da lista, se modificar o conteúdo do array não modifica a lista
        System.out.println("Cores: ");
        for (String cor : cores) {
            System.out.printf("%s ", cor);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new UsandoToArray();
    }
}
