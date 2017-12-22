
import java.io.*;

public class ArrayVendedor implements Serializable {//Serializable Trabalha com arquivo

    private String Nome, Comissao, Empresa, Endereco, Bairro, Email, CEP, Fone, Fax, IE, CNPJ, Setor, Cidade, Estado;
    private int Codigo, Numero;

    ArrayVendedor(String nome, String comissao, String empresa, String endereco, String bairro, String email, String cep, String fone, String fax, String ie,
            String cnpj, String setor, String cidade, String estado, int codigo, int numero) {

        this.Nome = nome;
        this.Comissao = comissao;
        this.Empresa = empresa;
        this.Endereco = endereco;
        this.Bairro = bairro;
        this.Numero = numero;
        this.Email = email;
        this.CEP = cep;
        this.Fone = fone;
        this.Fax = fax;
        this.IE = ie;
        this.CNPJ = cnpj;
        this.Setor = setor;
        this.Cidade = cidade;
        this.Estado = estado;
        this.Codigo = codigo;
    }

    ArrayVendedor() {
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

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
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

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String Setor) {
        this.Setor = Setor;
    }
}
