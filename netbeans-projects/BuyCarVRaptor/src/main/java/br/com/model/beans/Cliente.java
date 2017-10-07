package br.com.model.beans;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class Cliente {

    private Long nrSequencia;
    @NotNull(message = "{cliente.tipoPessoa}")
    private String tipoPessoa;
    private String cpf;
    private String cnpj;
    @NotNull(message = "{cliente.nome}")
    private String nome;
    @NotNull(message = "{cliente.telefone}")
    private String telefone;
    @Email(message = "{cliente.email}")
    private String email;
    @NotNull(message = "{cliente.endereco}")
    private String endereco;
    @NotNull(message = "{cliente.bairro}")
    private String bairro;
    @NotNull(message = "{cliente.numero}")
    @Min(value = 1, message = "{cliente.numero.intervalo}")
    @Max(value = 999999, message = "{cliente.numero.intervalo}")
    private Integer numero;
    @NotNull(message = "{cliente.cep}")
    private String cep;
    @NotNull(message = "{cliente.cidade}")
    private String cidade;
    @NotNull(message = "{cliente.estado}")
    private String estado;

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
