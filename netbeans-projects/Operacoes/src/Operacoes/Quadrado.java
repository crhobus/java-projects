package Operacoes;


public class Quadrado {

    private int lado;

    Quadrado(int numero) {
        lado = numero;
    }

    public int area() {
        int calcula;
        calcula = lado * lado;
        return calcula;
    }

    public int perimetro() {
        int resultado;
        resultado = 4 * lado;
        return resultado;
    }
}
