package CollectionArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionArrayList {

    private static final String cores[] = {"Magenta", "Vermelho", "Branco", "Azul", "Ciano"};
    private static final String removeCores[] = {"Vermelho", "Branco", "Azul"};

    public CollectionArrayList() {
        List<String> lista = new ArrayList<String>();
        List<String> removeList = new ArrayList<String>();
        for (String cor : cores) {
            lista.add(cor);
        }
        for (String cor : removeCores) {
            removeList.add(cor);
        }
        System.out.println("ArrayList: ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%s ", lista.get(i));
        }
        removeCores(lista, removeList);
        System.out.println("\n\nArrayList depois de chamar o método removeCores(): ");
        for (String cor : lista) {
            System.out.printf("%s ", cor);
        }
        System.out.println();
    }

    private void removeCores(Collection<String> collection1, Collection<String> collection2) {
        Iterator<String> iterator = collection1.iterator();
        while (iterator.hasNext()) {
            if (collection2.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        new CollectionArrayList();
    }
}
