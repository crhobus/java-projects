package PilhaGenerica;

import java.util.EmptyStackException;

public class TesteTipoBruto {

    private Double doubleElementos[] = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
    private Integer intElementos[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    public void testePilha() {
        Pilha pilhaTipoBruto1 = new Pilha(6);
        Pilha pilhaTipoBruto2 = new Pilha<Double>(6);
        Pilha<Integer> pilhaInteger = new Pilha(11);
        testePush("PilhaTipoBruto1", pilhaTipoBruto1, doubleElementos);
        testePop("PilhaTipoBruto1", pilhaTipoBruto1);
        testePush("PilhaTipoBruto2", pilhaTipoBruto2, doubleElementos);
        testePop("PilhaTipoBruto2", pilhaTipoBruto2);
        testePush("PilhaInteger", pilhaInteger, intElementos);
        testePop("PilhaInteger", pilhaInteger);
    }

    public <T> void testePush(String nome, Pilha<T> pilha, T elementos[]) {
        try {
            System.out.printf("\nInserindo elementos na %s\n", nome);
            for (T elemento : elementos) {
                System.out.printf("%s ", elemento);
                pilha.push(elemento);
            }
        } catch (PilhaCheiaException ex) {
            System.out.println();
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
            System.out.println();
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TesteTipoBruto teste = new TesteTipoBruto();
        teste.testePilha();
    }
}
