package Exe3Impressora;

import java.util.*;

public class FabricaFolhasA4 implements FabricaFolhas<A4> {

    public ArrayList<A4> criar(int qtas) {
        ArrayList<A4> l = new ArrayList<A4>();
        for (int i = 0; i < qtas; i++) {
            l.add(new A4());
        }
        return l;
    }
}
