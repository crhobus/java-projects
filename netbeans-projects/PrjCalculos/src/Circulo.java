
public class Circulo {

    private double Raio;

    public Circulo(double num) {
        Raio = num;
    }

    public double area() {
        double result;
        result = Math.PI * Raio * Raio;
        return result;
    }

    public double perimetro() {
        double result;
        result = 2 * Math.PI * Raio;
        return result;
    }
}
