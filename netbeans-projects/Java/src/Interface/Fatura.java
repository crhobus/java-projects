package Interface;

public class Fatura implements APagar {

    private String numeroPeca;
    private String descricaoPeca;
    private int qtdade;
    private double precoItem;

    public Fatura(String numeroPeca, String descricaoPeca, int qtdade, double precoItem) {
        this.numeroPeca = numeroPeca;
        this.descricaoPeca = descricaoPeca;
        setQtdade(qtdade);
        setPrecoItem(precoItem);
    }

    public String getDescricaoPeca() {
        return descricaoPeca;
    }

    public void setDescricaoPeca(String descricaoPeca) {
        this.descricaoPeca = descricaoPeca;
    }

    public String getNumeroPeca() {
        return numeroPeca;
    }

    public void setNumeroPeca(String numeroPeca) {
        this.numeroPeca = numeroPeca;
    }

    public double getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(double precoItem) {
        this.precoItem = precoItem;
    }

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }

    @Override
    public String toString() {
        return String.format("%s: \n%s %s (%s) \n%s: %d \n%s $%,.2f", "fatura", "numero da peça", getNumeroPeca(),
                getDescricaoPeca(), "quantidade", getQtdade(), "preço por item", getPrecoItem());
    }

    public double getQtdadePagto() {
        return getQtdade() * getPrecoItem();
    }
}
