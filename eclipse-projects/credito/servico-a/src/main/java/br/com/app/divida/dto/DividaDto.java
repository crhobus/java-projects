package br.com.app.divida.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class DividaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "A descrição da dívida não pode ser vazia")
    @Length(min = 1, max = 250, message = "A descrição da dívida deve conter entre 1 e 250 caracteres")
    private String descricao;

    @NotNull(message = "O valor da dívida não pode ser vazia")
    @Positive(message = "Valor da dívida deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor da dívida inválida")
    private BigDecimal valor;

    @NotNull(message = "Data da dívida não pode ser vazia")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9])", message = "Data da dívida inválida")
    private String dataVencimento;

    @NotNull(message = "O status da dívida deve ser informada")
    private QuitadoEnum quitado;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public QuitadoEnum getQuitado() {
        return quitado;
    }

    public void setQuitado(QuitadoEnum quitado) {
        this.quitado = quitado;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
