package Exe6;

public class Salario {

    private int numHoras, numHorasExtras50, numHorasExtras100;
    private double valorHora;

    public double getSalario() {
        return (numHoras * valorHora) + ((valorHora * 1.5) * numHorasExtras50) + ((valorHora * 2) * numHorasExtras100);
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public int getNumHorasExtras50() {
        return numHorasExtras50;
    }

    public void setNumHorasExtras50(int numHorasExtras50) {
        this.numHorasExtras50 = numHorasExtras50;
    }

    public int getNumHorasExtras100() {
        return numHorasExtras100;
    }

    public void setNumHorasExtras100(int numHorasExtras100) {
        this.numHorasExtras100 = numHorasExtras100;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }
}
