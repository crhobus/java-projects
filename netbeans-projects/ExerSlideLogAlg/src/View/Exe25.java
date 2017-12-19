package View;

public class Exe25 {

    public static void main(String[] args) {
        System.out.println("Tabuadas de 1 a 10");
        for (int i = 1; i <= 10; i++) {
            System.out.println("\nTabuada de " + i);
            for (int y = 1; y <= 10; y++) {
                System.out.println(i + " x " + y + " = " + (i * y));
            }
        }
    }
}
