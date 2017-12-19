package PilhaGenerica;

import java.util.EmptyStackException;

public class TestandoPilha2 {

    private Double doubleElementos[] = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
    private Integer intElementos[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private Pilha<Double> pilhaDouble;
    private Pilha<Integer> pilhaInt;

    public void testePilha() {
        pilhaDouble = new Pilha<Double>(6);
        pilhaInt = new Pilha<Integer>(11);
        testePush("PilhaDouble", pilhaDouble, doubleElementos);
        testePop("PilhaDouble", pilhaDouble);
        testePush("PilhaInteger", pilhaInt, intElementos);
        testePop("PilhaInteger", pilhaInt);
    }

    public <T> void testePush(String nome, Pilha<T> pilha, T elementos[]) {
        try {
            System.out.printf("\nInserindo elementos na %s\n", nome);
            for (T elemento : elementos) {
                System.out.printf("%s ", elemento);
                pilha.push(elemento);
            }
        } catch (PilhaCheiaException ex) {
            System.err.println();
            ex.printStackTrace();
        }
    }

    public <T> void testePop(String nome, Pilha<T> pilha) {
        try {
            System.out.printf("\nRetirando elementos da %s\n", nome);
            T popValor;
            popValor = pilha.pop();
            System.out.printf("%s ", popValor);
        } catch (EmptyStackException ex) {
            System.err.println();
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestandoPilha2 teste = new TestandoPilha2();
        teste.testePilha();
    }
}
