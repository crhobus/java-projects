package Model;

public class Funcionario {

    private String nome;
    private int idade, horaTrabalhada, horaTrabalhada50, horaTrabalhada100;
    private String cargo;
    private double valorHora, salarioLiquido;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getHoraTrabalhada() {
        return horaTrabalhada;
    }

    public void setHoraTrabalhada(int horaTrabalhada) {
        this.horaTrabalhada = horaTrabalhada;
    }

    public int getHoraTrabalhada50() {
        return horaTrabalhada50;
    }

    public void setHoraTrabalhada50(int horaTrabalhada50) {
        this.horaTrabalhada50 = horaTrabalhada50;
    }

    public int getHoraTrabalhada100() {
        return horaTrabalhada100;
    }

    public void setHoraTrabalhada100(int horaTrabalhada100) {
        this.horaTrabalhada100 = horaTrabalhada100;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }
}
