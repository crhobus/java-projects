package AlgoritimoShuffle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaralhoCartas {

    private List<Carta> lista;

    public BaralhoCartas() {
        Carta[] baralho = new Carta[52];
        int cont = 0;
        for (Carta.Naipe naipe : Carta.Naipe.values()) {
            for (Carta.Face face : Carta.Face.values()) {
                baralho[cont] = new Carta(face, naipe);
                cont++;
            }
        }
        lista = Arrays.asList(baralho);//O metodo static asList da classe Arrays obtem uma visualizaçao List do array baralho
        Collections.shuffle(lista);//Embaralha as cartas
    }

    public void imprimirCartas() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%-20s%s", lista.get(i), ((i + 1) % 2 == 0) ? "\n" : "\t");
        }
    }

    public static void main(String[] args) {
        BaralhoCartas barCartas = new BaralhoCartas();
        barCartas.imprimirCartas();
    }
}
