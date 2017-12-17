package addArrayListHashSet;

import java.util.*;

public class Conjunto {

    private String cidades[] = {"Blumenau,", "Timbó,", "Indaial,", "Lontras,", "Presidente Getúlio,", "Pomerode,",
        "Timbó,", "Indaial,", "Lontras,", "Taió,", "Brusque,", "Guabiruba,", "Gaspar,", "Lontras,", "Brusque,", "Guabiruba,",
        "Massaranduba,", "Guaramirim"};

    public Conjunto() {
        List<String> lista = new ArrayList(Arrays.asList(cidades));
        System.out.print("ARRAY DE CIDADES: ");
        for (String s : cidades) {
            System.out.printf("%s ", s);
        }
        System.out.print("\nARRAYLIST COM AS CIDADES: ");
        for (String s : lista) {
            System.out.printf("%s ", s);
        }
        imprimirHashSet(lista);
    }

    public void imprimirHashSet(Collection<String> colecao) {
        Set<String> set = new HashSet<String>(colecao);
        Iterator iterator = set.iterator();
        System.out.print("\n\nAGORA O CONJUNTO: \n");
        while (iterator.hasNext()) {
            System.out.print("" + iterator.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Conjunto();
    }
}
