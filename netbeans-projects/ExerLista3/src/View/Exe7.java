package View;

public class Exe7 {

    public static void main(String[] args) {
        int[] vet = {1, 3, 4, 50, 10, 47, 49, 10, 3, 10};
        System.out.println("Posição do elemento no vetor igual a 10");
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] == 10) {
                System.out.println("Posição: " + (i + 1));
            }
        }
    }
}
