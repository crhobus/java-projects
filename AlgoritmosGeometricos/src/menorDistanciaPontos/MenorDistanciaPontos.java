package menorDistanciaPontos;

import java.util.ArrayList;
import java.util.List;

public class MenorDistanciaPontos {

    private List<Ponto> pontos = new ArrayList<Ponto>();

    public void addPonto(Ponto pontos) {
        this.pontos.add(pontos);
    }

    public String calcular() {
        Ponto p1 = null, p2 = null;
        double menorDistancia = Double.MAX_VALUE, distCalculada;
        for (Ponto ponto1 : pontos) {
            for (Ponto ponto2 : pontos) {
                if (ponto1 != ponto2) {
                    distCalculada = geDistanciaEuclidiana(ponto1, ponto2);
                    if (distCalculada < menorDistancia) {
                        p1 = ponto1;
                        p2 = ponto2;
                        menorDistancia = distCalculada;
                    }
                }
            }
        }
        return "(" + p1.getX() + "," + p1.getY() + ") e (" + p2.getX() + "," + p2.getY() + ")";
    }

    private double geDistanciaEuclidiana(Ponto ponto1, Ponto ponto2) {
        double x = Math.pow((ponto1.getX() - ponto2.getX()), 2);// Potencia - base, expoente
        double y = Math.pow((ponto1.getY() - ponto2.getY()), 2);// Potencia - base, expoente
        return Math.sqrt(x + y);// Raiz quadrada de x + y
    }
}
