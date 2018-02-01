package edu.furb.web.model;

import java.math.BigDecimal;

/**
 *
 * @author Caio
 */
public class Produto {

    private String codigo;
    private String nome;
    private BigDecimal preco;

    public Produto() {}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
