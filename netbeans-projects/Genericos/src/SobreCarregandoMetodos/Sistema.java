package SobreCarregandoMetodos;

public class Sistema {

    public static <E> void imprimirVetor(E[] vetor) {
        for (E elemento : vetor) {
            System.out.printf("%s ", elemento);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] integerVetor = {1, 2, 3, 4, 5, 6};
        Double[] doubleVetor = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        Character[] characterVetor = {'H', 'E', 'L', 'L', 'O'};

        System.out.println("O vetor de inteiros contem: ");
        imprimirVetor(integerVetor);
        System.out.println("O vetor de double contem: ");
        imprimirVetor(doubleVetor);
        System.out.println("O vetor de caracteres contem: ");
        imprimirVetor(characterVetor);
    }
}
