package Exe1LocVenImovelCarroInterfaces;

public class Imovel implements ItemLocavel, ItemVenda {

    private String endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
