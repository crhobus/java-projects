package Modelo;

import java.util.Date;

public class Vendedor {

    private int codigo;
    private String cpf, celularEmpresa, emailVendedor, descricao, homePage,
            outasConsideracoes;
    private Date dataCadas, ultAlteracao;
    private double comissao, salarioLiquido;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelularEmpresa() {
        return celularEmpresa;
    }

    public void setCelularEmpresa(String celularEmpresa) {
        this.celularEmpresa = celularEmpresa;
    }

    public String getEmailVendedor() {
        return emailVendedor;
    }

    public void setEmailVendedor(String emailVendedor) {
        this.emailVendedor = emailVendedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getOutasConsideracoes() {
        return outasConsideracoes;
    }

    public void setOutasConsideracoes(String outasConsideracoes) {
        this.outasConsideracoes = outasConsideracoes;
    }

    public Date getDataCadas() {
        return dataCadas;
    }

    public void setDataCadas(Date dataCadas) {
        this.dataCadas = dataCadas;
    }

    public Date getUltAlteracao() {
        return ultAlteracao;
    }

    public void setUltAlteracao(Date ultAlteracao) {
        this.ultAlteracao = ultAlteracao;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }
}
