package br.com.app.security.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class JwtAuthenticationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Senha não pode ser vazia")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
