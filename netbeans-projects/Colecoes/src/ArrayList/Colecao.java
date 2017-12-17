package ArrayList;

import java.util.*;

public class Colecao {

    private String nomes[] = {"Marcia", "Ivelise", "Ruth"};

    public Colecao() {
        List<String> lista = new ArrayList<String>();
        lista.add("Marlise");
        for (int i = 0; i < nomes.length; i++) {
            lista.add(nomes[i]);
        }
        lista.add("Herta");
        System.out.print("\nO ArrayList: ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
        removerNomes(lista);
        lista.add("Heloisa");
        System.out.println("\n\nArrayList Final: ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
        System.out.println();
    }

    public void removerNomes(Collection lista) {
        Iterator dedo = lista.iterator();
        while (dedo.hasNext()) {
            if (dedo.next() instanceof String) {
                dedo.remove();
            }
        }
    }

    public static void main(String[] args) {
        new Colecao();
    }
}
