package Exe4;

public class Moeda {

    private double umCentavo, cincoCentavos, dezCentavos, vinteCincoCentavos,
            cinquentaCentavos, umReal;

    public double valorEconomizado() {
        return umCentavo + cincoCentavos + dezCentavos + vinteCincoCentavos + cinquentaCentavos + umReal;
    }

    public double getUmCentavo() {
        return umCentavo;
    }

    public void setUmCentavo(int qtdade) {
        this.umCentavo = qtdade * 0.01;
    }

    public double getCincoCentavos() {
        return cincoCentavos;
    }

    public void setCincoCentavos(int qtdade) {
        this.cincoCentavos = qtdade * 0.05;
    }

    public double getDezCentavos() {
        return dezCentavos;
    }

    public void setDezCentavos(int qtdade) {
        this.dezCentavos = qtdade * 0.1;
    }

    public double getVinteCincoCentavos() {
        return vinteCincoCentavos;
    }

    public void setVinteCincoCentavos(int qtdade) {
        this.vinteCincoCentavos = qtdade * 0.25;
    }

    public double getCinquentaCentavos() {
        return cinquentaCentavos;
    }

    public void setCinquentaCentavos(int qtdade) {
        this.cinquentaCentavos = qtdade * 0.5;
    }

    public double getUmReal() {
        return umReal;
    }

    public void setUmReal(int qtdade) {
        this.umReal = qtdade;
    }
}
