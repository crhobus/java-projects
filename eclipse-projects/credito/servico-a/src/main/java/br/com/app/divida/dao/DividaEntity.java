package br.com.app.divida.dao;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.divida.dto.QuitadoEnum;
import br.com.app.infra.database.EntityBase;
import br.com.app.pessoafisica.dao.PessoaFisicaEntity;

@Entity
@Table(name = "divida")
@SequenceGenerator(name = "SEQUENCE_BASE", sequenceName = "divida_seq", allocationSize = 1)
public class DividaEntity extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "descricao", length = 250, nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "quitado", nullable = false)
    private QuitadoEnum quitado;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_fisica", foreignKey = @ForeignKey(name = "pessoa_fisica_divida_fk"), referencedColumnName = "id", nullable = false)
    private PessoaFisicaEntity pessoaFisica;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public QuitadoEnum getQuitado() {
        return quitado;
    }

    public void setQuitado(QuitadoEnum quitado) {
        this.quitado = quitado;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public PessoaFisicaEntity getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((pessoaFisica == null) ? 0 : pessoaFisica.hashCode());
        result = prime * result + ((quitado == null) ? 0 : quitado.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        DividaEntity other = (DividaEntity) obj;
        if (getId() != other.getId()) return false;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (dataVencimento == null) {
            if (other.dataVencimento != null) return false;
        } else if (!dataVencimento.equals(other.dataVencimento)) return false;
        if (descricao == null) {
            if (other.descricao != null) return false;
        } else if (!descricao.equals(other.descricao)) return false;
        if (pessoaFisica == null) {
            if (other.pessoaFisica != null) return false;
        } else if (!pessoaFisica.equals(other.pessoaFisica)) return false;
        if (quitado != other.quitado) return false;
        if (valor == null) {
            if (other.valor != null) return false;
        } else if (!valor.equals(other.valor)) return false;
        return true;
    }

}
