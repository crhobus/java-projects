
import java.io.*;

public class ArrayTransportadora implements Serializable {//Serializable Trabalha com arquivo

    private String Nome, Endereco, Bairro, Cidade, Estado, Email, CEP, Fone;
    private int Codigo, Numero;

    ArrayTransportadora(String nome, String endereco, String bairro, String cidade, String estado, String email,
            String cep, String fone, int codigo, int numero) {

        this.Nome = nome;
        this.Endereco = endereco;
        this.Bairro = bairro;
        this.Cidade = cidade;
        this.Estado = estado;
        this.Email = email;
        this.CEP = cep;
        this.Fone = fone;
        this.Codigo = codigo;
        this.Numero = numero;
    }

    ArrayTransportadora() {
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFone() {
        return Fone;
    }

    public void setFone(String Fone) {
        this.Fone = Fone;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }
}
