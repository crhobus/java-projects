package br.com.app.empresa.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public class EmpresaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "Nome da empresa não pode ser vazia")
    @Length(min = 5, max = 120, message = "Nome da empresa deve conter entre 5 e 120 caracteres")
    private String nome;

    @NotNull(message = "CNPJ da empresa não pode ser vazio")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
