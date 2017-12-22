package Modelo;

import java.util.Date;

public class Produto {

    private int codigo, numeroSerie, quantidade, mesGarantia;
    private String nome, marca, modelo, descricao, acessorios, cor;
    private Date dataCadastro, ultAlteracao;
    private double precoUnitCompra, precoUnitVenda, percentualLucro,
            impostosUnit, valorTotal, descontos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getMesGarantia() {
        return mesGarantia;
    }

    public void setMesGarantia(int mesGarantia) {
        this.mesGarantia = mesGarantia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String acessorios) {
        this.acessorios = acessorios;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public double getPrecoUnitCompra() {
        return precoUnitCompra;
    }

    public void setPrecoUnitCompra(double precoUnitCompra) {
        this.precoUnitCompra = precoUnitCompra;
    }

    public double getPrecoUnitVenda() {
        return precoUnitVenda;
    }

    public void setPrecoUnitVenda(double precoUnitVenda) {
        this.precoUnitVenda = precoUnitVenda;
    }

    public double getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    public double getImpostosUnit() {
        return impostosUnit;
    }

    public void setImpostosUnit(double impostosUnit) {
        this.impostosUnit = impostosUnit;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }
}