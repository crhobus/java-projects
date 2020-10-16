package br.com.app.rendapessoafisica.dao;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.infra.database.EntityBase;

@Entity
@Table(name = "renda_pessoa_fisica")
@SequenceGenerator(name = "SEQUENCE_BASE", sequenceName = "renda_pessoa_fisica_seq", allocationSize = 1)
public class RendaPessoaFisicaEntity extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "renda", nullable = false)
    private BigDecimal renda;

    @Column(name = "fonte", length = 250, nullable = false)
    private String fonte;

    @Column(name = "data_renda", nullable = false)
    private LocalDate dataRenda;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public LocalDate getDataRenda() {
        return dataRenda;
    }

    public void setDataRenda(LocalDate dataRenda) {
        this.dataRenda = dataRenda;
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
        result = prime * result + ((dataRenda == null) ? 0 : dataRenda.hashCode());
        result = prime * result + ((fonte == null) ? 0 : fonte.hashCode());
        result = prime * result + idade;
        result = prime * result + ((renda == null) ? 0 : renda.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RendaPessoaFisicaEntity other = (RendaPessoaFisicaEntity) obj;
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
        if (dataRenda == null) {
            if (other.dataRenda != null) return false;
        } else if (!dataRenda.equals(other.dataRenda)) return false;
        if (fonte == null) {
            if (other.fonte != null) return false;
        } else if (!fonte.equals(other.fonte)) return false;
        if (idade != other.idade) return false;
        if (renda == null) {
            if (other.renda != null) return false;
        } else if (!renda.equals(other.renda)) return false;
        return true;
    }

}
