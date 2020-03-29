package br.com.app.lancamento.dao;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.infra.database.EntityBase;
import br.com.app.lancamento.dto.TipoEnum;

@Entity
@Table(name = "lancamento")
public class LancamentoEntity extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "data", nullable = false)
    private Instant data;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "localizacao", length = 150)
    private String localizacao;

    @Column(name = "tipo", nullable = false)
    private int tipo;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario", foreignKey = @ForeignKey(name = "lancamento_funcionario_fk"), referencedColumnName = "id", nullable = false)
    private FuncionarioEntity funcionario;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public TipoEnum getTipo() {
        return TipoEnum.getKey(tipo);
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo.getValue();
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public FuncionarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioEntity funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
        result = prime * result + tipo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        LancamentoEntity other = (LancamentoEntity) obj;
        if (data == null) {
            if (other.data != null) return false;
        } else if (!data.equals(other.data)) return false;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (descricao == null) {
            if (other.descricao != null) return false;
        } else if (!descricao.equals(other.descricao)) return false;
        if (funcionario == null) {
            if (other.funcionario != null) return false;
        } else if (!funcionario.equals(other.funcionario)) return false;
        if (getId() != other.getId()) return false;
        if (localizacao == null) {
            if (other.localizacao != null) return false;
        } else if (!localizacao.equals(other.localizacao)) return false;
        if (tipo != other.tipo) return false;
        return true;
    }

}
