package model;

import java.util.Date;

public class ItemChamada extends Codigo {

    private int qtdade;
    private String descricao;
    private double valor;
    private Date dataItem;
    private Chamada chamada;

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataItem() {
        return dataItem;
    }

    public void setDataItem(Date dataItem) {
        this.dataItem = dataItem;
    }

    public Chamada getChamada() {
        return chamada;
    }

    public void setChamada(Chamada chamada) {
        this.chamada = chamada;
    }
}
