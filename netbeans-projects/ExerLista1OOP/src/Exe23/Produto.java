package Exe23;

public class Produto {

    private int codigo;
    private String produto;
    private double preco;

    public Produto(int codigo, String produto, double preco) {
        this.codigo = codigo;
        this.produto = produto;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getProduto() {
        return produto;
    }

    public double getPreco() {
        return preco;
    }
}
