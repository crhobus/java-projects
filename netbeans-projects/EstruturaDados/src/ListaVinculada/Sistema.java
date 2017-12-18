package ListaVinculada;

import java.util.Random;

public class Sistema {

    public static void main(String[] args) {
        Lista lista = new Lista();
        Random numRandom = new Random();
        for(int i = 0; i < 100; i++){
            lista.inserirComeco(numRandom.nextInt(500000));
        }
        lista.imprimir();
        /*lista.inserirComeco(-1);
        lista.imprimir();
        lista.inserirComeco(0);
        lista.imprimir();
        lista.inserirFinal(1);
        lista.imprimir();
        lista.inserirFinal(5);
        lista.imprimir();*/
        try {
            Object removerObjeto = lista.removerComeco();
            System.out.printf("%s removido\n", removerObjeto);
            lista.imprimir();
            removerObjeto = lista.removerComeco();
            System.out.printf("%s removido\n", removerObjeto);
            lista.imprimir();
            removerObjeto = lista.removerUltimo();
            System.out.printf("%s removido\n", removerObjeto);
            lista.imprimir();
            removerObjeto = lista.removerUltimo();
            System.out.printf("%s removido\n", removerObjeto);
            lista.imprimir();
        } catch (EmptyListException ex) {
            ex.printStackTrace();
        }
    }
}
