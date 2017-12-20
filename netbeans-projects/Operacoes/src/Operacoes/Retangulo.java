package Operacoes;


public class Retangulo {

    private int ldmenor;
    private int ldmaior;

    Retangulo(int numero1, int numero2) {
        if (ldmenor < ldmaior) {
            ldmenor = numero1;
            ldmaior = numero2;
        } else {
            ldmaior = numero2;
            ldmenor = numero1;
        }
    }

    public int area() {
        int calcula;
        calcula = ldmenor * ldmaior;
        return calcula;
    }

    public int perimetro() {
        int resultado;
        resultado = (2 * ldmenor) + (2 * ldmaior);
        return resultado;
    }
}
