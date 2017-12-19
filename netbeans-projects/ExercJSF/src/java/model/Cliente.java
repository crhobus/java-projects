package model;

import java.util.Date;

public class Cliente {

    private String nome;
    private boolean masc;
    private Date nascimento;
    private String email;
    private String escolaridade;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public boolean isMasc() {
        return masc;
    }

    public void setMasc(boolean masc) {
        this.masc = masc;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome + " " + masc + " "
                + nascimento + " " + email + " "
                + escolaridade;
    }
}