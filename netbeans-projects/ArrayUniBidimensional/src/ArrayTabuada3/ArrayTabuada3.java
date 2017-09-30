package ArrayTabuada3;

public class ArrayTabuada3 {

    public static void main(String[] args) {
        int meuVetor[], i, soma = 0, mult5 = 0;
        meuVetor = new int[10];
        for (i = 0; i < 10; i++) {
            meuVetor[i] = (i + 1) * 3;
            System.out.println("\nVeja o Conteúdo do vetor: " + meuVetor[i]);
        }
        for (i = 0; i < 10; i++) {
            soma += meuVetor[i];
        }
        for (i = 1; i < 10; i = i + 2) {
            if ((meuVetor[i] % 5) == 0) {
                mult5++;
            }
        }
        System.out.println("Somatorio: " + soma);
        System.out.println("Multiplos de 5: " + mult5);
    }
}
