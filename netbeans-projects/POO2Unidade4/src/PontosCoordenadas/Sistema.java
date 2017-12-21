package PontosCoordenadas;

import java.util.*;

public class Sistema {

    public static void main(String[] args) {
        Arquivo arquivo = new Arquivo();
        try {
            Set<Pontos> listaPontos;
            listaPontos = arquivo.lerPontos();
            System.out.println("Abaixo todos os pontos cadastrados");
            for (Pontos pontos : listaPontos) {
                System.out.println("Nome: " + pontos.getNome());
                System.out.println("X: " + pontos.getX());
                System.out.println("Y: " + pontos.getY());
            }
            System.out.println();
            List<Coordenadas> listaCoordenadas = new ArrayList<Coordenadas>();
            listaCoordenadas = arquivo.lerCoordenadas();
            System.out.println("Abaixo todas as coordenadas cadastradas");
            for (Coordenadas coord : listaCoordenadas) {
                System.out.println("X: " + coord.getX() + " Y: " + coord.getY());
            }
            System.out.println();
            System.out.println("Abaixo o nome dos pontos mais próximos da coordenada");
            for (Coordenadas coord : listaCoordenadas) {
                System.out.println("X: " + coord.getX() + " Y: " + coord.getY());
                SortedSet<Pontos> sort = new TreeSet<Pontos>(new PontosComparator(coord.getX(), coord.getY()));
                sort.addAll(listaPontos);
                int n = listaPontos.size();
                if (n > 2) {
                    n = 3;
                }
                for (int i = 0; i < n; i++) {
                    System.out.println(sort.first().getNome());
                    sort.remove(sort.first());
                }
            }
        } catch (Exception ex) {
        }
    }
}
