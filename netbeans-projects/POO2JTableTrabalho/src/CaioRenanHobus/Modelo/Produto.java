package CaioRenanHobus.Modelo;

public class Produto {

    private String nome, unidade, adicionar, remover;
    private double quantidade;

    public String getAdicionar() {
        return adicionar;
    }

    public void setAdicionar(String adicionar) {
        this.adicionar = adicionar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getRemover() {
        return remover;
    }

    public void setRemover(String remover) {
        this.remover = remover;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
