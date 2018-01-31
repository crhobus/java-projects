package Modelo;

import java.io.*;

public class Contatos implements Serializable {

    private String nome, fone1, fone2, fone3, email;
    private Boolean vip[];
    private int codigo;
    private boolean seleciona;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getFone3() {
        return fone3;
    }

    public void setFone3(String fone3) {
        this.fone3 = fone3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getSeleciona() {
        return seleciona;
    }

    public void setSeleciona(boolean seleciona) {
        this.seleciona = seleciona;
    }

    public Boolean[] getVip() {
        return vip;
    }

    public void setVip(Boolean[] vip) {
        this.vip = vip;
    }
}
