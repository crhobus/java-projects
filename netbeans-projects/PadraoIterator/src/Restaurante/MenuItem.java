package Restaurante;

public class MenuItem {

    private String nome, descricao;
    private boolean vegatariano;
    private double preco;

    public MenuItem(String nome, String descricao, boolean vegatariano, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.vegatariano = vegatariano;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isVegatariano() {
        return vegatariano;
    }

    public void setVegatariano(boolean vegatariano) {
        this.vegatariano = vegatariano;
    }
}
