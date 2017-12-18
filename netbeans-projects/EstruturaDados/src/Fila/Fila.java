package Fila;

import ListaVinculada.EmptyListException;
import ListaVinculada.Lista;

public class Fila {

    private Lista filaLista;

    public Fila() {
        filaLista = new Lista("Fila");
    }

    public void insere(Object object) {
        filaLista.inserirFinal(object);
    }

    public Object remove() throws EmptyListException {
        return filaLista.removerComeco();
    }

    public boolean vazio() {
        return filaLista.vazio();
    }

    public void imprimir() {
        filaLista.imprimir();
    }
}
