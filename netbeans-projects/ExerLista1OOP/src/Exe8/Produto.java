package Exe8;

public class Produto {

    private int codigo, quantidade;
    private String produto, marca;
    private double valor;

    public Produto(int codigo, String produto, String marca, int quantidade, double valor) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.marca = marca;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public String getMarca() {
        return marca;
    }

    public double getValor() {
        return valor;
    }
}
