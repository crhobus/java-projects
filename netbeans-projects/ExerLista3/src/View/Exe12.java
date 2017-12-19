package View;

public class Exe12 {

    public static void main(String[] args) {
        int[] x = {1, 3, 4, 50, 10};
        int[] y = new int[5];
        int j = 4;
        for (int i = 0; i < y.length; i++) {
            y[i] = x[j];
            j--;
        }
        System.out.println("Vetor X");
        System.out.print("[ ");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println("]");
        System.out.println("\nVetor Y");
        System.out.print("[ ");
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + " ");
        }
        System.out.println("]");
    }
}
