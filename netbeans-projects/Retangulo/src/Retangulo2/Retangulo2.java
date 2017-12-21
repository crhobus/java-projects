package Retangulo2;

public class Retangulo2 {

    private int ldmenor;
    private int ldmaior;

    public Retangulo2(int num1, int num2) {
        if (num1 < num2) {
            ldmenor = num1;
            ldmaior = num2;
        } else {
            ldmenor = num2;
            ldmaior = num1;
        }
    }

    public int area() {
        int resultado;
        resultado = ldmaior * ldmenor;
        return resultado;
    }

    public int perimetro() {
        int resultado;
        resultado = (2 * ldmaior) + (2 * ldmenor);
        return resultado;
    }

    public boolean ehRetangulo() {
        boolean volta;
        volta = true;
        if (ldmenor == ldmaior) {
            volta = false;
        }
        return volta;
    }
}
