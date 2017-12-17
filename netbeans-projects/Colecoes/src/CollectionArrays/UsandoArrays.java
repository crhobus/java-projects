package CollectionArrays;

import java.util.Arrays;
import java.util.Random;

public class UsandoArrays {

    private int intArray[];
    private double doubleArray[];
    private int intArrayCheio[], intArrayCopia[];

    public UsandoArrays(int numMax, int tamIntArray, int tamIntArrayCheio, int tamDoubleArray, int preenche) {
        Random numRandom = new Random();
        intArray = new int[tamIntArray];
        for (int i = 0; i < tamIntArray; i++) {
            intArray[i] = numRandom.nextInt(numMax);
        }
        doubleArray = new double[tamDoubleArray];
        for (int i = 0; i < tamDoubleArray; i++) {
            doubleArray[i] = numRandom.nextDouble();
        }
        intArrayCheio = new int[tamIntArrayCheio];
        intArrayCopia = new int[intArray.length];
        Arrays.fill(intArrayCheio, preenche);//Preenche o vetor com preenche
        Arrays.sort(doubleArray);//ordena o array em ordem crescente
        Arrays.sort(intArray);
        System.arraycopy(intArray, 0, intArrayCopia, 0, intArray.length);//Copia o int array para intArrayCopia
    }

    public void imprimirArrays() {
        System.out.print("doubleArray: ");
        for (double doubleValor : doubleArray) {
            System.out.printf("%.1f ", doubleValor);
        }
        System.out.print("\nintArray: ");
        for (int intValor : intArray) {
            System.out.printf("%d ", intValor);
        }
        System.out.print("\nintArrayCheio: ");
        for (int intValor : intArrayCheio) {
            System.out.printf("%d ", intValor);
        }
        System.out.print("\nintArrayCopia: ");
        for (int intValor : intArrayCopia) {
            System.out.printf("%d ", intValor);
        }
        System.out.println("\n");
    }

    public int pesquisarArrayInt(int valor) {
        return Arrays.binarySearch(intArray, valor);//Pesquisa o valor em intArray
    }

    public void imprimirArrayIgual() {
        boolean igual = Arrays.equals(intArray, intArrayCopia);//verifica se os array são iguais e retorna true ou false
        System.out.printf("intArray %s intArrayCopia\n", igual ? "==" : "!=");
        igual = Arrays.equals(intArray, intArrayCheio);
        System.out.printf("intArray %s intArrayCheio\n", igual ? "==" : "!=");
    }

    public static void main(String[] args) {
        UsandoArrays usandoArrays = new UsandoArrays(100, 80, 30, 50, 18);
        usandoArrays.imprimirArrays();
        usandoArrays.imprimirArrayIgual();
        int valor = 64;
        int localizacao = usandoArrays.pesquisarArrayInt(valor);
        if (localizacao >= 0) {
            System.out.printf("O número %d foi encontrado na posição %d no intArray\n", valor, localizacao);
        } else {
            System.out.printf("O número %d não foi encontrado\n", valor);
        }
        valor = 21;
        localizacao = usandoArrays.pesquisarArrayInt(valor);
        if (localizacao >= 0) {
            System.out.printf("O número %d foi encontrado na posição %d no intArray\n", valor, localizacao);
        } else {
            System.out.printf("O número %d não foi encontrado\n", valor);
        }
    }
}
