
import java.io.*;

public class ArrayUsuario implements Serializable {//Serializable Trabalha com arquivo

    private String Nome;
    private int Codigo, Permissao, Senha;

    ArrayUsuario(String nome, int senha, int codigo, int permissao) {

        this.Nome = nome;
        this.Senha = senha;
        this.Codigo = codigo;
        this.Permissao = permissao;
    }

    ArrayUsuario() {
    }

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
}
