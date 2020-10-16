package br.com.app.movimentacaofinanceira.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class MovimentacaoFinanceiraDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O valor da movimentação financeira não pode ser vazio")
    @Positive(message = "Valor da movimentação financeira deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor da movimentação financeira inválida")
    private BigDecimal valor;

    @NotNull(message = "A descrição da movimentação financeira não pode ser vazia")
    @Length(min = 1, max = 250, message = "A descrição da movimentação financeira deve conter entre 1 e 250 caracteres")
    private String descricao;

    @NotNull(message = "Data da movimentação financeira não pode ser vazio")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9]) (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])", message = "Data da movimentação financeira inválida")
    private String dataMovimentacao;

    @NotNull(message = "O tipo de operação da movimentação financeira deve ser informada")
    private OperacaoEnum operacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public OperacaoEnum getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoEnum operacao) {
        this.operacao = operacao;
    }

}
