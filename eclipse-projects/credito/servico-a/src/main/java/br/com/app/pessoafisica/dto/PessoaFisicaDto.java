package br.com.app.pessoafisica.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class PessoaFisicaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Nome não pode ser vazio")
    @Length(min = 5, max = 120, message = "Nome deve conter entre 5 e 120 caracteres")
    private String nome;

    @NotNull(message = "Endereço não pode ser vazio")
    @Length(min = 5, max = 250, message = "Endereço deve conter entre 5 e 250 caracteres")
    private String endereco;

    @NotNull(message = "Data de nascimento não pode ser vazia")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9])", message = "Data de nascimento inválida")
    private String dataNascimento;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
