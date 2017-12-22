package model;

import java.util.Date;

public class Chamada {

    private int numeroCha, numOcorrencias;
    private String situacao;
    private Date dataRealizacao;
    private Ambiente ambiente;
    private Patrimonio patrimonio;
    private Funcionario funcionario;
    private Defeito defeito;

    public int getNumeroCha() {
        return numeroCha;
    }

    public void setNumeroCha(int numeroCha) {
        this.numeroCha = numeroCha;
    }

    public int getNumOcorrencias() {
        return numOcorrencias;
    }

    public void setNumOcorrencias(int numOcorrencias) {
        this.numOcorrencias = numOcorrencias;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Defeito getDefeito() {
        return defeito;
    }

    public void setDefeito(Defeito defeito) {
        this.defeito = defeito;
    }
}
