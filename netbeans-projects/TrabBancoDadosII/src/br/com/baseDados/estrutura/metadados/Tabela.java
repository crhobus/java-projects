package br.com.baseDados.estrutura.metadados;

public class Tabela {

    private CabecalhoTabela cabecalhoTabela;
    private Coluna[] colunas;
    private Constraint[] constraints;
    private Indice[] indices;

    public CabecalhoTabela getCabecalhoTabela() {
        return cabecalhoTabela;
    }

    public void setCabecalhoTabela(CabecalhoTabela cabecalhoTabela) {
        this.cabecalhoTabela = cabecalhoTabela;
    }

    public Coluna[] getColunas() {
        return colunas;
    }

    public void setColunas(Coluna[] colunas) {
        this.colunas = colunas;
    }

    public Constraint[] getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraint[] constraints) {
        this.constraints = constraints;
    }

    public Indice[] getIndices() {
        return indices;
    }

    public void setIndices(Indice[] indices) {
        this.indices = indices;
    }
}
