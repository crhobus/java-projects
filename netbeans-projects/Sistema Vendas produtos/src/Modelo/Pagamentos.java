package Modelo;

import java.io.*;

public class Pagamentos implements Serializable {

    private String nomeClie, dataEmissao, condPagto, parcelasRes, situacao;
    private int codigo, codigoVen;
    private double descontos, total, valorParcelas, valorPago;

    public String getParcelasRes() {
        return parcelasRes;
    }

    public void setParcelasRes(String parcelasRes) {
        this.parcelasRes = parcelasRes;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoVen() {
        return codigoVen;
    }

    public void setCodigoVen(int codigoVen) {
        this.codigoVen = codigoVen;
    }

    public String getCondPagto() {
        return condPagto;
    }

    public void setCondPagto(String condPagto) {
        this.condPagto = condPagto;
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

    public String getNomeClie() {
        return nomeClie;
    }

    public void setNomeClie(String nomeClie) {
        this.nomeClie = nomeClie;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }
}
