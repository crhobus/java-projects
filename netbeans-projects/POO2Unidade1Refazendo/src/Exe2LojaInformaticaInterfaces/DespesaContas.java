package Exe2LojaInformaticaInterfaces;

public class DespesaContas implements LojaDespesa {

    private double valorDespesa;
    private String nomeDespesa;

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public double getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(double valorDespesa) {
        this.valorDespesa = valorDespesa;
    }
}
