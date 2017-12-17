package ArrayList2;

import java.util.*;

public class Colecao {

    public static void main(String[] args) {
        String algo = "Num ";
        int aux;
        List cadastro = new ArrayList();
        for (aux = 9; aux >= 0; aux--) {
            cadastro.add(algo + aux);
        }
        System.out.print("SITUACAO ORIGINAL: ");
        for (aux = 0; aux < cadastro.size(); aux++) {
            System.out.print(cadastro.get(aux) + " ");
        }
        cadastro.remove(2);
        System.out.println("\nSITUAÇÃO APÓS REMOÇÃO: ");
        for (aux = 0; aux < cadastro.size(); aux++) {
            System.out.println(cadastro.get(aux) + " ");
        }
        Collections.sort(cadastro);
        System.out.print("\nAGORA ORDENADO: ");
        for (aux = 0; aux < cadastro.size(); aux++) {
            System.out.print(cadastro.get(aux) + " ");
        }
        Iterator interator = cadastro.iterator();
        while (interator.hasNext()) {
            if (interator.next() instanceof String) {
                interator.remove();
            }
        }
        System.out.print("\n\nAGORA O FINAL: ");
        for (aux = 0; aux < cadastro.size(); aux++) {
            System.out.print(cadastro.get(aux) + " ");
        }
        System.out.print("\n");
    }
}
