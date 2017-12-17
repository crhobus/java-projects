package OrdenacaoComparator;

import java.util.Comparator;

public class ComparatorTempo implements Comparator<Tempo> {

    public int compare(Tempo tempo1, Tempo tempo2) {
        int comparaHora = tempo1.getHora() - tempo2.getHora();
        if (comparaHora != 0) {
            return comparaHora;
        }
        int comparaMinuto = tempo1.getMinuto() - tempo2.getMinuto();
        if (comparaMinuto != 0) {
            return comparaMinuto;
        }
        int comparaSegundo = tempo1.getSegundo() - tempo2.getSegundo();
        return comparaSegundo;
    }
}
