package Modelo;

import java.io.*;
import java.util.*;

public class Usuario implements Serializable {

    private String Nome;
    private int Codigo, Permissao, Senha;
    private Date data;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getPermissao() {
        return Permissao;
    }

    public void setPermissao(int Permissao) {
        this.Permissao = Permissao;
    }

    public int getSenha() {
        return Senha;
    }

    public void setSenha(int Senha) {
        this.Senha = Senha;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
