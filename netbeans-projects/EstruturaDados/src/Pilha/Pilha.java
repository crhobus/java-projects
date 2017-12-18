package Pilha;

import ListaVinculada.EmptyListException;
import ListaVinculada.Lista;

public class Pilha extends Lista {

    public Pilha() {
        super("Pilha");
    }

    public void push(Object objeto) {
        inserirComeco(objeto);
    }

    public Object pop() throws EmptyListException {
        return removerComeco();
    }
}
