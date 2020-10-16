package br.com.app.bempessoafisica.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class BemPessoaFisicaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O nome do bem não pode ser vazio")
    @Length(min = 1, max = 250, message = "O nome do bem deve conter entre 1 e 250 caracteres")
    private String nome;

    @NotNull(message = "O valor do bem não pode ser vazio")
    @Positive(message = "Valor do bem deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor da renda inválida")
    private BigDecimal valor;

    @NotNull(message = "O endereço do bem não pode ser vazio")
    @Length(min = 1, max = 250, message = "O endereço do bem deve conter entre 1 e 250 caracteres")
    private String endereco;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
