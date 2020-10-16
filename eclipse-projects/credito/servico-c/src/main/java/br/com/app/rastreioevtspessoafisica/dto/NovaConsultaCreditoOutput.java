package br.com.app.rastreioevtspessoafisica.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraOutput;

public class NovaConsultaCreditoOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;

    private String nome;

    private String endereco;

    private String dataNascimento;

    private PossuiDividaEnum possuiDivida;

    private List<RendaPessoaFisicaDto> ultimosRegistrosRenda;

    private BigDecimal valorEmBens;

    private Map<String, List<MovimentacaoFinanceiraOutput>> ultimasMovimentacoesFinanceiras;

    private Map<String, BigDecimal> ultimosGastosCartaoCredito;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public PossuiDividaEnum getPossuiDivida() {
        return possuiDivida;
    }

    public void setPossuiDivida(PossuiDividaEnum possuiDivida) {
        this.possuiDivida = possuiDivida;
    }

    public List<RendaPessoaFisicaDto> getUltimosRegistrosRenda() {
        return ultimosRegistrosRenda;
    }

    public void setUltimosRegistrosRenda(List<RendaPessoaFisicaDto> ultimosRegistrosRenda) {
        this.ultimosRegistrosRenda = ultimosRegistrosRenda;
    }

    public BigDecimal getValorEmBens() {
        return valorEmBens;
    }

    public void setValorEmBens(BigDecimal valorEmBens) {
        this.valorEmBens = valorEmBens;
    }

    public Map<String, List<MovimentacaoFinanceiraOutput>> getUltimasMovimentacoesFinanceiras() {
        return ultimasMovimentacoesFinanceiras;
    }

    public void setUltimasMovimentacoesFinanceiras(Map<String, List<MovimentacaoFinanceiraOutput>> ultimasMovimentacoesFinanceiras) {
        this.ultimasMovimentacoesFinanceiras = ultimasMovimentacoesFinanceiras;
    }

    public Map<String, BigDecimal> getUltimosGastosCartaoCredito() {
        return ultimosGastosCartaoCredito;
    }

    public void setUltimosGastosCartaoCredito(Map<String, BigDecimal> ultimosGastosCartaoCredito) {
        this.ultimosGastosCartaoCredito = ultimosGastosCartaoCredito;
    }

}