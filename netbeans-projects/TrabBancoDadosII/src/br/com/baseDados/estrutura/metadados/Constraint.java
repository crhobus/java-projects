package br.com.baseDados.estrutura.metadados;

public class Constraint {

    private int cdConstraint, tipo, nrColunas;
    private String nmConstraint;
    private int[] cdColunaConstraintTabela;
    private CabecalhoTabela tabelaReferenciada;
    private Coluna[] colunasReferenciada;

    public int[] getCdColunaConstraintTabela() {
        return cdColunaConstraintTabela;
    }

    public void setCdColunaConstraintTabela(int[] cdColunaConstraintTabela) {
        this.cdColunaConstraintTabela = cdColunaConstraintTabela;
    }

    public int getCdConstraint() {
        return cdConstraint;
    }

    public void setCdConstraint(int cdConstraint) {
        this.cdConstraint = cdConstraint;
    }

    public Coluna[] getColunasReferenciada() {
        return colunasReferenciada;
    }

    public void setColunasReferenciada(Coluna[] colunasReferenciada) {
        this.colunasReferenciada = colunasReferenciada;
    }

    public String getNmConstraint() {
        return nmConstraint;
    }

    public void setNmConstraint(String nmConstraint) {
        this.nmConstraint = nmConstraint;
    }

    public int getNrColunas() {
        return nrColunas;
    }

    public void setNrColunas(int nrColunas) {
        this.nrColunas = nrColunas;
    }

    public CabecalhoTabela getTabelaReferenciada() {
        return tabelaReferenciada;
    }

    public void setTabelaReferenciada(CabecalhoTabela tabelaReferenciada) {
        this.tabelaReferenciada = tabelaReferenciada;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
