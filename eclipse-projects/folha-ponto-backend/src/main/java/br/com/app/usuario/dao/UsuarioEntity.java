package br.com.app.usuario.dao;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.infra.database.EntityBase;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

@Entity
@Table(name = "usuario")
public class UsuarioEntity extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "email", length = 150, unique = true, nullable = false)
    private String email;

    @Column(name = "senha", length = 60, nullable = false)
    private String senha;

    @Column(name = "situacao", nullable = false)
    private int situacao;

    @Column(name = "perfil", nullable = false)
    private int perfil;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public SituacaoUserEnum getSituacao() {
        return SituacaoUserEnum.getKey(situacao);
    }

    public void setSituacao(SituacaoUserEnum situacao) {
        this.situacao = situacao.getValue();
    }

    public PerfilEnum getPerfil() {
        return PerfilEnum.getKey(perfil);
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil.getValue();
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
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + perfil;
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + situacao;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UsuarioEntity other = (UsuarioEntity) obj;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (funcionario == null) {
            if (other.funcionario != null) return false;
        } else if (!funcionario.equals(other.funcionario)) return false;
        if (getId() != other.getId()) return false;
        if (perfil != other.perfil) return false;
        if (senha == null) {
            if (other.senha != null) return false;
        } else if (!senha.equals(other.senha)) return false;
        if (situacao != other.situacao) return false;
        return true;
    }

}
