package Exe18;

public class Produto {

    private String produto;
    private double valor;

    public Produto(String produto, double valor) {
        this.produto = produto;
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }
}
