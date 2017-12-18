package Fila;

import ListaVinculada.EmptyListException;

public class Sistema {

    public static void main(String[] args) {
        Fila fila = new Fila();
        fila.insere(-1);
        fila.imprimir();
        fila.insere(5);
        fila.imprimir();
        try {
            Object removerObjeto = null;
            removerObjeto = fila.remove();
            System.out.printf("%s removido da fila\n", removerObjeto);
            fila.imprimir();
        } catch (EmptyListException ex) {
            ex.printStackTrace();
        }
        fila.insere(9);
        fila.imprimir();
        fila.insere(1);
        fila.imprimir();
        try {
            Object removerObjeto = null;
            removerObjeto = fila.remove();
            System.out.printf("%s removido da fila\n", removerObjeto);
            fila.imprimir();
        } catch (EmptyListException ex) {
            ex.printStackTrace();
        }
    }
}
