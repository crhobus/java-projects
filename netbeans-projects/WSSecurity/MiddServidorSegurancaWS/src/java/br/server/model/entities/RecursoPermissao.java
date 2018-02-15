package br.server.model.entities;

/*
 * Document   : RecursoPermissao.java
 * Created on : 03/09/2013, 21:20:35
 * Author     : Caio
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recurso_permissao")
public class RecursoPermissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nr_sequencia", columnDefinition = "number(10)")
    private long nrSequencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_permissao", columnDefinition = "number(10)", nullable = false)
    private Permissao permissao;

    @Column(name = "ie_permissao", columnDefinition = "varchar2(1)", nullable = false)
    private boolean iePermissao;

    @Column(name = "nm_usuario_atualizacao", columnDefinition = "varchar2(25)", nullable = false)
    private String nmUsuarioAtualizacao;

    @Column(name = "dt_atualizacao", columnDefinition = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    public long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public boolean isIePermissao() {
        return iePermissao;
    }

    public void setIePermissao(boolean iePermissao) {
        this.iePermissao = iePermissao;
    }

    public String getNmUsuarioAtualizacao() {
        return nmUsuarioAtualizacao;
    }

    public void setNmUsuarioAtualizacao(String nmUsuarioAtualizacao) {
        this.nmUsuarioAtualizacao = nmUsuarioAtualizacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 31 * hash + (this.permissao != null ? this.permissao.hashCode() : 0);
        hash = 31 * hash + (this.iePermissao ? 1 : 0);
        hash = 31 * hash + (this.nmUsuarioAtualizacao != null ? this.nmUsuarioAtualizacao.hashCode() : 0);
        hash = 31 * hash + (this.dtAtualizacao != null ? this.dtAtualizacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecursoPermissao other = (RecursoPermissao) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if (this.permissao != other.permissao && (this.permissao == null || !this.permissao.equals(other.permissao))) {
            return false;
        }
        if (this.iePermissao != other.iePermissao) {
            return false;
        }
        if ((this.nmUsuarioAtualizacao == null) ? (other.nmUsuarioAtualizacao != null) : !this.nmUsuarioAtualizacao.equals(other.nmUsuarioAtualizacao)) {
            return false;
        }
        if (this.dtAtualizacao != other.dtAtualizacao && (this.dtAtualizacao == null || !this.dtAtualizacao.equals(other.dtAtualizacao))) {
            return false;
        }
        return true;
    }
}