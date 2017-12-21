package Exe2PedidoVenda;

public class Produtos {

    private String produto;
    private int quantidade;
    private float peso, valorUnitario;

    public Produtos(String produto, int quantidade, float peso, float valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.peso = peso;
        this.valorUnitario = valorUnitario;
        MostraPrdutos();
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void MostraPrdutos() {
        System.out.println("Dados Sobre Produtos");
        System.out.println("Produto: " + produto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Peso: " + peso);
        System.out.println("Valor Unitario: " + valorUnitario);
        System.out.println("");
    }
}


