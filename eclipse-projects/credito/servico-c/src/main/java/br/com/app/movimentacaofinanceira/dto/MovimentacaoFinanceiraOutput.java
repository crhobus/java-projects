package br.com.app.movimentacaofinanceira.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimentacaoFinanceiraOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal valor;

    private OperacaoEnum operacao;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OperacaoEnum getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoEnum operacao) {
        this.operacao = operacao;
    }

}
