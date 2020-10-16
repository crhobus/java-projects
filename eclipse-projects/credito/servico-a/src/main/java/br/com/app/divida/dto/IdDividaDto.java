package br.com.app.divida.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class IdDividaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "O ID da dívida não pode ser vazio")
    private Long id;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
