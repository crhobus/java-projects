package n3;

import java.util.ArrayList;
import java.util.List;

public class Mundo {

    private ArrayList<ObjetoGrafico> objetosGrafico;

    public Mundo() {
        objetosGrafico = new ArrayList<>();
    }

    public void addObjetoGrafico(ObjetoGrafico objetoGrafico) {
        objetosGrafico.add(objetoGrafico);
    }

    public List<ObjetoGrafico> getObjetosGrafico() {
        return objetosGrafico;
    }

    public ObjetoGrafico getObjetoGrafico(int indice) {
        return objetosGrafico.get(indice);
    }

    public void setObjetoGrafico(int indice, ObjetoGrafico objetoGrafico) {
        objetosGrafico.set(indice, objetoGrafico);
    }

    public void removeObjetoGrafico(int indice) {
        if (objetosGrafico.size() > 0) {
            objetosGrafico.remove(indice);
        }
    }

    public int getPosUltimoObj() {
        return objetosGrafico.size() - 1;
    }
}
