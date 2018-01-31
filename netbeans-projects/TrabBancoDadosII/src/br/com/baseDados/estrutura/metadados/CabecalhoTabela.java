package br.com.baseDados.estrutura.metadados;

public class CabecalhoTabela {

    private int cdTabela, qtColunas, qtConstraints, qtIndices;
    private String nmTabela;

    public int getCdTabela() {
        return cdTabela;
    }

    public void setCdTabela(int cdTabela) {
        this.cdTabela = cdTabela;
    }

    public String getNmTabela() {
        return nmTabela;
    }

    public void setNmTabela(String nmTabela) {
        this.nmTabela = nmTabela;
    }

    public int getQtColunas() {
        return qtColunas;
    }

    public void setQtColunas(int qtColunas) {
        this.qtColunas = qtColunas;
    }

    public int getQtConstraints() {
        return qtConstraints;
    }

    public void setQtConstraints(int qtConstraints) {
        this.qtConstraints = qtConstraints;
    }

    public int getQtIndices() {
        return qtIndices;
    }

    public void setQtIndices(int qtIndices) {
        this.qtIndices = qtIndices;
    }
}
