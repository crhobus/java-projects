package Modelo;

import java.util.Date;

public class OrdemServico {

    private int numeroOs, numeroSerieApa, tempo;
    private String nomeAtendente, situacao, nomeApa, marcaApa, modeloApa,
            corApa, assistenciaApa, acessoriosApa, condPagto, formaPagto,
            cpfCnpjClie, nomeOPRealizada, horaInicial, horaFinal, descricaoApa;
    private Date dataGeracao, ultAlteracao, data;
    private double subTotal, descontos, total, juros, parcelasMes,
            valorPorHora;
    private boolean orcamentoApa;

    public int getNumeroOs() {
        return numeroOs;
    }

    public void setNumeroOs(int numeroOs) {
        this.numeroOs = numeroOs;
    }

    public int getNumeroSerieApa() {
        return numeroSerieApa;
    }

    public void setNumeroSerieApa(int numeroSerieApa) {
        this.numeroSerieApa = numeroSerieApa;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNomeApa() {
        return nomeApa;
    }

    public void setNomeApa(String nomeApa) {
        this.nomeApa = nomeApa;
    }

    public String getMarcaApa() {
        return marcaApa;
    }

    public void setMarcaApa(String marcaApa) {
        this.marcaApa = marcaApa;
    }

    public String getModeloApa() {
        return modeloApa;
    }

    public void setModeloApa(String modeloApa) {
        this.modeloApa = modeloApa;
    }

    public String getCorApa() {
        return corApa;
    }

    public void setCorApa(String corApa) {
        this.corApa = corApa;
    }

    public String getAssistenciaApa() {
        return assistenciaApa;
    }

    public void setAssistenciaApa(String assistenciaApa) {
        this.assistenciaApa = assistenciaApa;
    }

    public String getAcessoriosApa() {
        return acessoriosApa;
    }

    public void setAcessoriosApa(String acessoriosApa) {
        this.acessoriosApa = acessoriosApa;
    }

    public String getCondPagto() {
        return condPagto;
    }

    public void setCondPagto(String condPagto) {
        this.condPagto = condPagto;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public String getCpfCnpjClie() {
        return cpfCnpjClie;
    }

    public void setCpfCnpjClie(String cpfCnpjClie) {
        this.cpfCnpjClie = cpfCnpjClie;
    }

    public String getNomeOPRealizada() {
        return nomeOPRealizada;
    }

    public void setNomeOPRealizada(String nomeOPRealizada) {
        this.nomeOPRealizada = nomeOPRealizada;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDescricaoApa() {
        return descricaoApa;
    }

    public void setDescricaoApa(String descricaoApa) {
        this.descricaoApa = descricaoApa;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Date getUltAlteracao() {
        return ultAlteracao;
    }

    public void setUltAlteracao(Date ultAlteracao) {
        this.ultAlteracao = ultAlteracao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getParcelasMes() {
        return parcelasMes;
    }

    public void setParcelasMes(double parcelasMes) {
        this.parcelasMes = parcelasMes;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public boolean isOrcamentoApa() {
        return orcamentoApa;
    }

    public void setOrcamentoApa(boolean orcamentoApa) {
        this.orcamentoApa = orcamentoApa;
    }
}
