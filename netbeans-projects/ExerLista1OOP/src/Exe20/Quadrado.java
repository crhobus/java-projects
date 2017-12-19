package Exe20;

public class Quadrado {

    private double lado1, lado2, lado3, lado4;

    public Quadrado(double lado1, double lado2, double lado3, double lado4) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.lado4 = lado4;
    }

    public String isQuadrado() {
        if (lado1 == lado2 && lado1 == lado3 && lado1 == lado4) {
            return "Objeto analisado é um quadrado";
        } else {
            return "Objeto informado não é um quadrado";
        }
    }

    public double getLado1() {
        return lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public double getLado3() {
        return lado3;
    }

    public double getLado4() {
        return lado4;
    }
}
