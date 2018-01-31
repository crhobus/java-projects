package br.com.baseDados.estrutura.dados;

public class Registro {

    private String segmento, cdTabela, qtColunas;
    private String[] tamanho, informacao;

    public String getCdTabela() {
        return cdTabela;
    }

    public void setCdTabela(String cdTabela) {
        this.cdTabela = cdTabela;
    }

    public String[] getInformacao() {
        return informacao;
    }

    public void setInformacao(String[] informacao) {
        this.informacao = informacao;
    }

    public String getQtColunas() {
        return qtColunas;
    }

    public void setQtColunas(String qtColunas) {
        this.qtColunas = qtColunas;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String[] getTamanho() {
        return tamanho;
    }

    public void setTamanho(String[] tamanho) {
        this.tamanho = tamanho;
    }
}
