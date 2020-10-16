package br.com.app.rastreioevtspessoafisica.dao;

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
import br.com.app.rastreioevtspessoafisica.dto.PossuiDividaEnum;

@Entity
@Table(name = "rastreio_evts_pessoa_fisica")
@SequenceGenerator(name = "SEQUENCE_BASE", sequenceName = "rastreio_evts_pessoa_fisica_seq", allocationSize = 1)
public class RastreioEvtsPessoaFisicaEntity extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "data_ultima_consulta_credito", nullable = false)
    private Instant dataUltimaConsultaCredito;

    @Column(name = "local_ultima_consulta_credito", length = 250, nullable = false)
    private String localUltimaConsultaCredito;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "possui_divida", nullable = false)
    private PossuiDividaEnum possuiDivida;

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

    public Instant getDataUltimaConsultaCredito() {
        return dataUltimaConsultaCredito;
    }

    public void setDataUltimaConsultaCredito(Instant dataUltimaConsultaCredito) {
        this.dataUltimaConsultaCredito = dataUltimaConsultaCredito;
    }

    public String getLocalUltimaConsultaCredito() {
        return localUltimaConsultaCredito;
    }

    public void setLocalUltimaConsultaCredito(String localUltimaConsultaCredito) {
        this.localUltimaConsultaCredito = localUltimaConsultaCredito;
    }

    public PossuiDividaEnum getPossuiDivida() {
        return possuiDivida;
    }

    public void setPossuiDivida(PossuiDividaEnum possuiDivida) {
        this.possuiDivida = possuiDivida;
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
        result = prime * result + ((dataUltimaConsultaCredito == null) ? 0 : dataUltimaConsultaCredito.hashCode());
        result = prime * result + ((localUltimaConsultaCredito == null) ? 0 : localUltimaConsultaCredito.hashCode());
        result = prime * result + ((possuiDivida == null) ? 0 : possuiDivida.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RastreioEvtsPessoaFisicaEntity other = (RastreioEvtsPessoaFisicaEntity) obj;
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
        if (dataUltimaConsultaCredito == null) {
            if (other.dataUltimaConsultaCredito != null) return false;
        } else if (!dataUltimaConsultaCredito.equals(other.dataUltimaConsultaCredito)) return false;
        if (localUltimaConsultaCredito == null) {
            if (other.localUltimaConsultaCredito != null) return false;
        } else if (!localUltimaConsultaCredito.equals(other.localUltimaConsultaCredito)) return false;
        if (possuiDivida != other.possuiDivida) return false;
        return true;
    }

}
