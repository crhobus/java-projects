package PilhaGenerica;

import java.util.EmptyStackException;

public class TestandoPilha1 {

    private double doubleElementos[] = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
    private int intElementos[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private Pilha<Double> pilhaDouble;
    private Pilha<Integer> pilhaInt;

    public void testePilha() {
        pilhaDouble = new Pilha<Double>(6);
        pilhaInt = new Pilha<Integer>(11);
        testePushDouble();
        testePopDouble();
        testePushInteger();
        testePopInteger();
    }

    private void testePushDouble() {
        try {
            System.out.println("\nInserindo elementos na pilhaDouble");
            for (double elemento : doubleElementos) {
                System.out.printf("%.1f ", elemento);
                pilhaDouble.push(elemento);
            }
        } catch (PilhaCheiaException ex) {
            System.err.println();
            ex.printStackTrace();
        }
    }

    private void testePopDouble() {
        try {
            System.out.println("\nRetirando elementos da pilhaDouble");
            double popValor;
            popValor = pilhaDouble.pop();
            System.out.printf("%.1f ", popValor);
        } catch (EmptyStackException ex) {
            System.err.println();
            ex.printStackTrace();
        }
    }

    private void testePushInteger() {
        try {
            System.out.println("\nInserindo elementos na pilhaInteger");
            for (int elemento : intElementos) {
                System.out.printf("%d ", elemento);
                pilhaInt.push(elemento);
            }
        } catch (PilhaCheiaException ex) {
            System.err.println();
            ex.printStackTrace();
        }
    }

    private void testePopInteger() {
        try {
            System.out.println("\nRetirando elementos da pilhaInteger");
            int popValor;
            popValor = pilhaInt.pop();
            System.out.printf("%d ", popValor);
        } catch (EmptyStackException ex) {
            System.err.println();
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestandoPilha1 teste = new TestandoPilha1();
        teste.testePilha();
    }
}
