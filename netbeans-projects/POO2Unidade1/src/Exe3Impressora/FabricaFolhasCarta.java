package Exe3Impressora;

import java.util.*;

public class FabricaFolhasCarta implements FabricaFolhas<Carta> {

    public ArrayList<Carta> criar(int qtas) {
        ArrayList<Carta> l = new ArrayList<Carta>();
        for (int i = 0; i < qtas; i++) {
            l.add(new Carta());
        }
        return l;
    }
}
