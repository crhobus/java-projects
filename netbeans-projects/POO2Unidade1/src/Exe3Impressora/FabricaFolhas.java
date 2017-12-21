package Exe3Impressora;

import java.util.*;

public interface FabricaFolhas<T extends Folha> {

    public abstract ArrayList<T> criar(int qtas);
}
