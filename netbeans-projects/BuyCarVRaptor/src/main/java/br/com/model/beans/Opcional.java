package br.com.model.beans;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Opcional {

    private Long nrSequencia;
    @NotNull(message = "{opcional.nome}")
    private String nome;
    private String descricao;
    @NotNull(message = "{opcional.valor}")
    @DecimalMin(value = "1.0", message = "{opcional.valor.min}")
    private BigDecimal valor;

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
