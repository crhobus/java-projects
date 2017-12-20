package CadastroPedido;

public class Produto {

    private String nome;
    private double peso, valorUnitario;

    public Produto(String nome, double peso, double valorUnitario) {
        this.nome = nome;
        this.peso = peso;
        this.valorUnitario = valorUnitario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return nome + ", Valor Unitário: " + valorUnitario + ", Peso Unitario: " + peso;
    }
}
