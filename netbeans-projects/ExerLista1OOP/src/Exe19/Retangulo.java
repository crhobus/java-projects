package Exe19;

public class Retangulo {

    private double altura;
    private double largura;

    public Retangulo(double altura, double largura) {
        this.altura = altura;
        this.largura = largura;
    }

    public String getArea() {
        return "Área: " + (largura * altura);
    }

    public String getPerimetro() {
        return "Peímetro: " + ((largura * 2) + (altura * 2));
    }
}
