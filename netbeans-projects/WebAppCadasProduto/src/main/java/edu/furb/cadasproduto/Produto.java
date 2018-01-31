package edu.furb.cadasproduto;

import java.math.BigDecimal;

public class Produto {

    private long id;
    private String nome;
    private String unidadeMedida;
    private BigDecimal preco;

    public Produto() {}

    public Produto(long id, String nome, String unidadeMedida, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
