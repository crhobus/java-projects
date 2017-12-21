package Exe2LojaInformaticaInterfaces;

public class DespesaEntrega implements LojaDespesa {

    private int km;
    private String entrega;

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getNomeDespesa() {
        return entrega;
    }

    public double getValorDespesa() {
        return km * 1.50;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getKm() {
        return km;
    }
}
