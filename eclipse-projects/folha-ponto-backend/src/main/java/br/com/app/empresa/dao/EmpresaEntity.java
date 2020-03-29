package br.com.app.empresa.dao;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.infra.database.EntityBase;

@Entity
@Table(name = "empresa")
public class EmpresaEntity extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome", length = 120, nullable = false)
    private String nome;

    @Column(name = "cnpj", length = 14, unique = true, nullable = false)
    private String cnpj;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FuncionarioEntity> funcionarios;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public List<FuncionarioEntity> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioEntity> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void addFuncionario(FuncionarioEntity funcionario) {
        if (funcionarios == null) {
            funcionarios = new ArrayList<>();
        }
        funcionarios.add(funcionario);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EmpresaEntity other = (EmpresaEntity) obj;
        if (cnpj == null) {
            if (other.cnpj != null) return false;
        } else if (!cnpj.equals(other.cnpj)) return false;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (funcionarios == null) {
            if (other.funcionarios != null) return false;
        } else if (!funcionarios.equals(other.funcionarios)) return false;
        if (getId() != other.getId()) return false;
        if (nome == null) {
            if (other.nome != null) return false;
        } else if (!nome.equals(other.nome)) return false;
        return true;
    }

}
