package model;

import java.util.Date;

public class Patrimonio extends Codigo {

    private int numNotaFiscal, mesesGarantia, situacaoBem;
    private String descricao, numSerie;
    private Date dataCadastro, ultAlteracao, dataAquisicao;
    private double valor;
    private Ambiente ambiente;
    private Fornecedor fornecedor;
    private Empresa empresa;

    public int getNumNotaFiscal() {
        return numNotaFiscal;
    }

    public void setNumNotaFiscal(int numNotaFiscal) {
        this.numNotaFiscal = numNotaFiscal;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    }

    public int getSituacaoBem() {
        return situacaoBem;
    }

    public void setSituacaoBem(int situacaoBem) {
        this.situacaoBem = situacaoBem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getUltAlteracao() {
        return ultAlteracao;
    }

    public void setUltAlteracao(Date ultAlteracao) {
        this.ultAlteracao = ultAlteracao;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
