package br.com.app.movimentacaofinanceira.dao;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.infra.database.EntityBase;
import br.com.app.movimentacaofinanceira.dto.OperacaoEnum;

@Entity
@Table(name = "movimentacao_financeira")
@SequenceGenerator(name = "SEQUENCE_BASE", sequenceName = "movimentacao_financeira_seq", allocationSize = 1)
public class MovimentacaoFinanceiraEntity extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "descricao", length = 250, nullable = false)
    private String descricao;

    @Column(name = "data_movimentacao", nullable = false)
    private Instant dataMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "operacao", nullable = false)
    private OperacaoEnum operacao;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Instant dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public OperacaoEnum getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoEnum operacao) {
        this.operacao = operacao;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((dataMovimentacao == null) ? 0 : dataMovimentacao.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((operacao == null) ? 0 : operacao.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MovimentacaoFinanceiraEntity other = (MovimentacaoFinanceiraEntity) obj;
        if (getId() != other.getId()) return false;
        if (cpf == null) {
            if (other.cpf != null) return false;
        } else if (!cpf.equals(other.cpf)) return false;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (dataMovimentacao == null) {
            if (other.dataMovimentacao != null) return false;
        } else if (!dataMovimentacao.equals(other.dataMovimentacao)) return false;
        if (descricao == null) {
            if (other.descricao != null) return false;
        } else if (!descricao.equals(other.descricao)) return false;
        if (operacao != other.operacao) return false;
        if (valor == null) {
            if (other.valor != null) return false;
        } else if (!valor.equals(other.valor)) return false;
        return true;
    }

}
