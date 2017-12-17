package OrdenacaoComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ordenacao {

    public void imprimirElementos() {
        List<Tempo> lista = new ArrayList<Tempo>();
        lista.add(new Tempo(6, 24, 34));
        lista.add(new Tempo(18, 14, 58));
        lista.add(new Tempo(6, 05, 34));
        lista.add(new Tempo(12, 14, 58));
        lista.add(new Tempo(6, 24, 22));
        lista.add(new Tempo(0, 0, 9));
        lista.add(new Tempo(23, 59, 59));
        System.out.printf("Lista: %s\n", lista);
        Collections.sort(lista, new ComparatorTempo());
        System.out.printf("Lista ordenada pelo tempo: %s\n", lista);
    }

    public static void main(String[] args) {
        Ordenacao sort = new Ordenacao();
        sort.imprimirElementos();
    }
}
