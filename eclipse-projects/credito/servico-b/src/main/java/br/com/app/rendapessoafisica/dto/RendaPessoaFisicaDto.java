package br.com.app.rendapessoafisica.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class RendaPessoaFisicaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O valor da renda não pode ser vazio")
    @Positive(message = "O valor da renda deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor da renda inválida")
    private BigDecimal renda;

    @NotNull(message = "A fonte de renda não pode ser vazia")
    @Length(min = 1, max = 250, message = "A fonte de renda deve conter entre 1 e 250 caracteres")
    private String fonte;

    @NotNull(message = "Data da renda não pode ser vazia")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9])", message = "Data da renda inválida")
    private String dataRenda;

    private Integer idade;

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

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getDataRenda() {
        return dataRenda;
    }

    public void setDataRenda(String dataRenda) {
        this.dataRenda = dataRenda;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

}
