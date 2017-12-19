package Exe9;

public class Numero {

    private int num1, num2, num3;

    public Numero(int num1, int num2, int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public String getValoresOrdCrescente() {
        int aux = 0;
        for (int i = 0; i < 2; i++) {
            if (num1 > num2) {
                aux = num1;
                num1 = num2;
                num2 = aux;
            }
            if (num2 > num3) {
                aux = num2;
                num2 = num3;
                num3 = aux;
            }
        }
        return "[ " + num1 + " - " + num2 + " - " + num3 + " ]";
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum3() {
        return num3;
    }
}
