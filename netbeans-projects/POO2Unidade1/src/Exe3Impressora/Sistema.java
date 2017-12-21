package Exe3Impressora;

import java.util.*;

public class Sistema {

    public static void main(String[] args) {
        ArrayList<String> frases = new ArrayList<String>();
        frases.add("Java é a linguagem de programação mais bacana.");
        frases.add("Esse exercício é mole.");

        Impressora<A4> a4Imp = new Impressora<A4>(3);
        Impressora<Carta> cartaImp = new Impressora<Carta>(4);

        FabricaFolhas<A4> fabricaA4 = new FabricaFolhasA4();
        FabricaFolhas<Carta> fabricaCarta = new FabricaFolhasCarta();

        cartaImp.setBandeja(fabricaCarta.criar(7));
        cartaImp.imprimir(frases);

        frases.add("Java é a linguagem de programaão mais bacana.");
        frases.add("Esse exercício é mole.");

        a4Imp.setBandeja(fabricaA4.criar(7));
        a4Imp.imprimir(frases);
    }
}
