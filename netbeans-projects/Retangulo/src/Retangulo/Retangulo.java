package Retangulo;

public class Retangulo {

    private int ldmenor;
    private int ldmaior;

    public Retangulo(int lado1, int lado2) {
        if (lado1 < lado2) {
            ldmenor = lado1;
            ldmaior = lado2;
        } else {
            ldmenor = lado2;
            ldmaior = lado1;
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
