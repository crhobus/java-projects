package Exe25;

public class Calcula {

    public String getCalcula() {
        int num = 0;
        String str = Integer.toString(num);
        while (num != 128) {
            if (num < 2) {
                num = num + 2;
            } else {
                num = num * 2;
            }
            str += "\n" + Integer.toString(num);
        }
        return str;
    }
}
