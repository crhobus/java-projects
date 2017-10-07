package br.com.model.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Pedido {

    private Long nrSequencia;
    private Cliente cliente;
    private Veiculo veiculo;
    private List<ItemPedido> itensPedido;
    @NotNull(message = "{pedido.dtEmissao}")
    private Date dtEmissao;
    @NotNull(message = "{pedido.valorVeiculo}")
    @DecimalMin(value = "1.0", message = "{pedido.valorVeiculo.min}")
    private BigDecimal valorVeiculo;
    @NotNull(message = "{pedido.subTotal}")
    @DecimalMin(value = "1.0", message = "{pedido.subTotal.min}")
    private BigDecimal subTotal;
    @NotNull(message = "{pedido.desconto}")
    private BigDecimal desconto;
    @NotNull(message = "{pedido.valorTotal}")
    @DecimalMin(value = "1.0", message = "{pedido.valorTotal.min}")
    private BigDecimal valorTotal;
    @NotNull(message = "{pedido.condicaoPagto}")
    private String condicaoPagto;
    @NotNull(message = "{pedido.situacao}")
    private String situacao;

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public BigDecimal getValorVeiculo() {
        return valorVeiculo;
    }

    public void setValorVeiculo(BigDecimal valorVeiculo) {
        this.valorVeiculo = valorVeiculo;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCondicaoPagto() {
        return condicaoPagto;
    }

    public void setCondicaoPagto(String condicaoPagto) {
        this.condicaoPagto = condicaoPagto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
