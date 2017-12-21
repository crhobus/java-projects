package Exe2LojaInformaticaInterfaces;

public class DespesaSalarios implements LojaDespesa {

    private String nome;
    private double salario;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDespesa() {
        return nome;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getValorDespesa() {
        return salario;
    }
}
