
import java.io.*;

public class ArrayFornecedor implements Serializable {//Serializable Trabalha com arquivo

    private String Nome, Sigla, Comissao, Endereco, Bairro, Cidade, Estado, Email, Empresa, CNPJ, IE, CEP, Fone, Celular;
    private int Codigo, Numero;

    ArrayFornecedor(String nome, String sigla, String comissao, String endereco, String bairro, String cidade, String estado, String email, String empresa,
            String cnpj, String ie, String cep, String fone, String celular, int codigo, int numero) {

        this.Nome = nome;
        this.Sigla = sigla;
        this.Comissao = comissao;
        this.Endereco = endereco;
        this.Bairro = bairro;
        this.Cidade = cidade;
        this.Estado = estado;
        this.Email = email;
        this.Empresa = empresa;
        this.CNPJ = cnpj;
        this.IE = ie;
        this.CEP = cep;
        this.Fone = fone;
        this.Celular = celular;
        this.Codigo = codigo;
        this.Numero = numero;
    }

    ArrayFornecedor() {
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

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
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

    public String getComissao() {
        return Comissao;
    }

    public void setComissao(String Comissao) {
        this.Comissao = Comissao;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
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

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
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

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String Sigla) {
        this.Sigla = Sigla;
    }
}
