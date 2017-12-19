package View;

public class Exe25 {

    public static void main(String[] args) {
        System.out.println("Exibindo de 0 a 128, sendo que o número seguinte sempre será o dobro");
        int num = 0;
        System.out.println(num);
        while (num != 128) {
            if (num < 2) {
                num = num + 2;
            } else {
                num = num * 2;
            }
            System.out.println(num);
        }
    }
}
