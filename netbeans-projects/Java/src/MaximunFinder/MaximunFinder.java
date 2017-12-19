package MaximunFinder;

import java.util.Scanner;

public class MaximunFinder {

    public void determineMaximun() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter three floating-point values separated by spaces: ");
        double number1 = input.nextDouble();
        double number2 = input.nextDouble();
        double number3 = input.nextDouble();

        double returlt = maximun(number1, number2, number3);
        System.out.println("Maximun is: " + returlt);
    }

    public double maximun(double x, double y, double z) {
        double maximunValue = x;
        if (y > maximunValue) {
            maximunValue = y;
        }
        if (z > maximunValue) {
            maximunValue = z;
        }
        return maximunValue;
    }
}
