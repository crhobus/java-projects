package CadastroPedido;

public class ItemPedido {

    private int qtdade;
    private Produto produto;

    public double getPesoItem() {
        return produto.getPeso() * qtdade;
    }

    public double getValorItem() {
        return produto.getValorUnitario() * qtdade;
    }

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
