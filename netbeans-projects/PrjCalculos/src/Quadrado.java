
public class Quadrado {

    private int lado;

    public Quadrado(int num) {
        lado = num;
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
