package Random;

import java.util.Random;

public class RandomIntegers {

    public static void main(String[] args) {
        Random ramdomNumbers = new Random();
        int face;
        for (int i = 1; i <= 20; i++) {
            face = 1 + ramdomNumbers.nextInt(6);
            System.out.printf("%d ", face);
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }
}
