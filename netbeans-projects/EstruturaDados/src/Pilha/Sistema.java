package Pilha;

import ListaVinculada.EmptyListException;

public class Sistema {

    public static void main(String[] args) {
        Pilha pilha = new Pilha();
        pilha.push(-1);
        pilha.imprimir();
        pilha.push(0);
        pilha.imprimir();
        pilha.push(1);
        pilha.imprimir();
        pilha.push(5);
        pilha.imprimir();
        try {
            Object removeObjeto = null;
            while (true) {
                removeObjeto = pilha.pop();
                System.out.printf("%s removido da pilha\n", removeObjeto);
                pilha.imprimir();
            }
        } catch (EmptyListException ex) {
            ex.printStackTrace();
        }
    }
}
