package Modelo;

import java.util.Date;

public class OrdemServico {

    private int codigo;
    private String nomeVendedor, situacao, placaVei, condPagto, formaPagto,
            cpfCnpjClie, nomeOPRealizada, horaInicial, horaFinal, tempo,
            descricao;
    private Date dataGeracao, ultAlteracao, data;
    private double subTotal, descontos, total, juros, parcelasMes,
            valorPorHora;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPlacaVei() {
        return placaVei;
    }

    public void setPlacaVei(String placaVei) {
        this.placaVei = placaVei;
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

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
