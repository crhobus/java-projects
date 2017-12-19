package Exe4;

import java.util.Comparator;

public class Comparador implements Comparator<Candidato> {

    @Override
    public int compare(Candidato c1, Candidato c2) {
        return c1.getNumVotos() - c2.getNumVotos();
    }
}
