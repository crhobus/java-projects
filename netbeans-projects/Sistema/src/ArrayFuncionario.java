
import java.io.*;

public class ArrayFuncionario implements Serializable {//Serializable Trabalha com arquivo

    private String Nome, Funcao, Endereco, Bairro, Cidade, Estado, Email, DataAdmissao, CPF, RG, CEP, Fone, Celular, DataNascimento, Complemento, Sexo;
    private int Codigo, Numero;
    private float SalarioIncial, Aumento, Inss, SalarioTotal;

    ArrayFuncionario(String nome, String funcao, String endereco, String bairro, String cidade, String estado, String email, String dataadmissao, String cpf, String rg, String cep, String fone,
            String celular, String complemento, String sexo, String datanascimento, int codigo, int numero, float salarioinial, float aumento, float inss, float salariototal) {

        this.Nome = nome;
        this.Funcao = funcao;
        this.Endereco = endereco;
        this.Bairro = bairro;
        this.Cidade = cidade;
        this.Estado = estado;
        this.Email = email;
        this.DataAdmissao = dataadmissao;
        this.CPF = cpf;
        this.RG = rg;
        this.CEP = cep;
        this.Fone = fone;
        this.Celular = celular;
        this.Complemento = complemento;
        this.Sexo = sexo;
        this.DataNascimento = datanascimento;
        this.Codigo = codigo;
        this.Numero = numero;
        this.SalarioIncial = salarioinial;
        this.Aumento = aumento;
        this.Inss = inss;
        this.SalarioTotal = salariototal;
    }

    ArrayFuncionario() {
    }

    public float getAumento() {
        return Aumento;
    }

    public void setAumento(float Aumento) {
        this.Aumento = Aumento;
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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public String getDataAdmissao() {
        return DataAdmissao;
    }

    public void setDataAdmissao(String DataAdmissao) {
        this.DataAdmissao = DataAdmissao;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.DataNascimento = DataNascimento;
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

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String Funcao) {
        this.Funcao = Funcao;
    }

    public float getInss() {
        return Inss;
    }

    public void setInss(float Inss) {
        this.Inss = Inss;
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

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public float getSalarioIncial() {
        return SalarioIncial;
    }

    public void setSalarioIncial(float SalarioIncial) {
        this.SalarioIncial = SalarioIncial;
    }

    public float getSalarioTotal() {
        return SalarioTotal;
    }

    public void setSalarioTotal(float SalarioTotal) {
        this.SalarioTotal = SalarioTotal;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }
}
