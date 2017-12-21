
public class Triangulo_Equilatero {

    private double lado;

    public Triangulo_Equilatero(double num) {
        lado = num;
    }

    public double altura() {
        double result;
        result = lado * Math.sqrt(3) / 2;
        return result;
    }

    public double area() {
        double result;
        result = lado * altura() / 2;
        return result;
    }

    public double perimetro() {
        double result;
        result = 3 * lado;
        return result;
    }
}
