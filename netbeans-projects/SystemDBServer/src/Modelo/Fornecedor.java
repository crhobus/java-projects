package Modelo;

public class Fornecedor extends Pessoa {

    private int codForn;
    private String tipoFornecedor, homePage, fax, nomeContato,
            foneContato;
    private double comissao, compraMinima, compraMaxima;
    private byte[] sigla;

    public int getCodForn() {
        return codForn;
    }

    public void setCodForn(int codForn) {
        this.codForn = codForn;
    }

    public String getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(String tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getFoneContato() {
        return foneContato;
    }

    public void setFoneContato(String foneContato) {
        this.foneContato = foneContato;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getCompraMinima() {
        return compraMinima;
    }

    public void setCompraMinima(double compraMinima) {
        this.compraMinima = compraMinima;
    }

    public double getCompraMaxima() {
        return compraMaxima;
    }

    public void setCompraMaxima(double compraMaxima) {
        this.compraMaxima = compraMaxima;
    }

    public byte[] getSigla() {
        return sigla;
    }

    public void setSigla(byte[] sigla) {
        this.sigla = sigla;
    }
}
