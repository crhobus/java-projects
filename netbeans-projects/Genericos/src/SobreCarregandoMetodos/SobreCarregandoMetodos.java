package SobreCarregandoMetodos;

public class SobreCarregandoMetodos {

    public static void imprimirVetor(Integer[] vetor) {
        for (Integer elemento : vetor) {
            System.out.printf("%s ", elemento);
        }
        System.out.println();
    }

    public static void imprimirVetor(Double[] vetor) {
        for (Double elemento : vetor) {
            System.out.printf("%s ", elemento);
        }
        System.out.println();
    }

    public static void imprimirVetor(Character[] vetor) {
        for (Character elemento : vetor) {
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
