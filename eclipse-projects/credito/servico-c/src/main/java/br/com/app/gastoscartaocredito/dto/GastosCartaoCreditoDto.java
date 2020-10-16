package br.com.app.gastoscartaocredito.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class GastosCartaoCreditoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O valor do gasto no cartão de crédito não pode ser vazio")
    @Positive(message = "Valor do gasto no cartão de crédito deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor do gasto no cartão de crédito inválido")
    private BigDecimal valor;

    @NotNull(message = "Data do gasto no cartão de crédito não pode ser vazio")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9]) (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])", message = "Data do gasto no cartão de crédito inválido")
    private String data;

    @NotNull(message = "O local do gasto no cartão de crédito não pode ser vazio")
    @Length(min = 1, max = 250, message = "Local do gasto no cartão de crédito deve conter entre 1 e 250 caracteres")
    private String local;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
