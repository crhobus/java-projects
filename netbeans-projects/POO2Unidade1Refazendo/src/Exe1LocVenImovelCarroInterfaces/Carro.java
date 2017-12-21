package Exe1LocVenImovelCarroInterfaces;

public class Carro implements ItemLocavel, ItemVenda {

    private String placa;
    private double valorLocacao, valorBase;

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getValorBase() {
        return valorBase;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
