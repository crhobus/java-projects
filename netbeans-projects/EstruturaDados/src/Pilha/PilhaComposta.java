package Pilha;

import ListaVinculada.EmptyListException;
import ListaVinculada.Lista;

public class PilhaComposta {

    private Lista pilhaLista;

    public PilhaComposta() {
        pilhaLista = new Lista("pilha");
    }

    public void push(Object objeto) {
        pilhaLista.inserirComeco(objeto);
    }

    public Object pop() throws EmptyListException {
        return pilhaLista.removerComeco();
    }

    public boolean vazio() {
        return pilhaLista.vazio();
    }

    public void imprimir() {
        pilhaLista.imprimir();
    }
}
