package envoltoriaConvexa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnvoltoriaConvexa {

    private List<Ponto> pontos = new ArrayList<Ponto>();

    public void addPonto(Ponto pontos) {
        this.pontos.add(pontos);
    }

    public List<Ponto> getEnvoltoriaConvexa() {
        Collections.sort(pontos);
        List<Ponto> listaSup = new ArrayList<Ponto>();
        List<Ponto> listaInf = new ArrayList<Ponto>();
        listaSup.add(pontos.get(0));
        listaSup.add(pontos.get(1));
        for (int i = 2; i < pontos.size(); i++) {
            listaSup.add(pontos.get(i));
            while (listaSup.size() > 2 && getProdutoVetorial(listaSup.get(listaSup.size() - 3), listaSup.get(listaSup.size() - 2), listaSup.get(listaSup.size() - 1)) >= 0) {
                listaSup.remove(listaSup.size() - 2);
            }
        }
        listaInf.add(pontos.get(pontos.size() - 1));
        listaInf.add(pontos.get(pontos.size() - 2));
        for (int i = pontos.size() - 3; i >= 0; i--) {
            listaInf.add(pontos.get(i));
            while (listaInf.size() > 2 && getProdutoVetorial(listaInf.get(listaInf.size() - 3), listaInf.get(listaInf.size() - 2), listaInf.get(listaInf.size() - 1)) >= 0) {
                listaInf.remove(listaInf.size() - 2);
            }
        }
        listaInf.remove(0);
        listaInf.remove(listaInf.size() - 1);
        listaSup.addAll(listaInf);
        return listaSup;
    }

    private int getProdutoVetorial(Ponto p1, Ponto p2, Ponto p3) {
        return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
    }

    public String getPontosMaiorDistancia(List<Ponto> pontos) {
        Ponto p1 = null, p2 = null;
        double maiorDistancia = Double.MIN_VALUE, distCalculada;
        for (Ponto ponto1 : pontos) {
            for (Ponto ponto2 : pontos) {
                if (ponto1 != ponto2) {
                    distCalculada = geDistanciaEuclidiana(ponto1, ponto2);
                    if (distCalculada > maiorDistancia) {
                        p1 = ponto1;
                        p2 = ponto2;
                        maiorDistancia = distCalculada;
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
