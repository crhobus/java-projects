package Modelo;

import java.io.*;

public class Venda implements Serializable {

    private String dataEmissao, situacao, condPagto, formaPagto, descricao, endedrecoEnt, bairroEnt, cidadeEnt, estadoEnt, contatoEnt;
    private int codigo, codigoVendedor, codigoCliente, codigoItemVendas, numeroEnt;
    private double subTotal, descontos, total;

    public String getBairroEnt() {
        return bairroEnt;
    }

    public void setBairroEnt(String bairroEnt) {
        this.bairroEnt = bairroEnt;
    }

    public String getCidadeEnt() {
        return cidadeEnt;
    }

    public void setCidadeEnt(String cidadeEnt) {
        this.cidadeEnt = cidadeEnt;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoItemVendas() {
        return codigoItemVendas;
    }

    public void setCodigoItemVendas(int codigoItemVendas) {
        this.codigoItemVendas = codigoItemVendas;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getCondPagto() {
        return condPagto;
    }

    public void setCondPagto(String condPagto) {
        this.condPagto = condPagto;
    }

    public String getContatoEnt() {
        return contatoEnt;
    }

    public void setContatoEnt(String contatoEnt) {
        this.contatoEnt = contatoEnt;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndedrecoEnt() {
        return endedrecoEnt;
    }

    public void setEndedrecoEnt(String endedrecoEnt) {
        this.endedrecoEnt = endedrecoEnt;
    }

    public String getEstadoEnt() {
        return estadoEnt;
    }

    public void setEstadoEnt(String estadoEnt) {
        this.estadoEnt = estadoEnt;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public int getNumeroEnt() {
        return numeroEnt;
    }

    public void setNumeroEnt(int numeroEnt) {
        this.numeroEnt = numeroEnt;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
