package br.server.model.entities;

/*
 * Document   : Recurso.java
 * Created on : 03/09/2013, 20:56:10
 * Author     : Caio
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recurso")
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nr_sequencia", columnDefinition = "number(10)")
    private long nrSequencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_regra", columnDefinition = "number(10)", nullable = false)
    private Regra regra;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nr_seq_recurso", columnDefinition = "number(10)", nullable = false)
    private List<RecursoPermissao> recursoPermissoes = new ArrayList<RecursoPermissao>();

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

    public Regra getRegra() {
        return regra;
    }

    public void setRegra(Regra regra) {
        this.regra = regra;
    }

    public List<RecursoPermissao> getRecursoPermissoes() {
        return recursoPermissoes;
    }

    public void setRecursoPermissoes(List<RecursoPermissao> recursoPermissoes) {
        this.recursoPermissoes = recursoPermissoes;
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
        int hash = 3;
        hash = 53 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 53 * hash + (this.regra != null ? this.regra.hashCode() : 0);
        hash = 53 * hash + (this.recursoPermissoes != null ? this.recursoPermissoes.hashCode() : 0);
        hash = 53 * hash + (this.nmUsuarioAtualizacao != null ? this.nmUsuarioAtualizacao.hashCode() : 0);
        hash = 53 * hash + (this.dtAtualizacao != null ? this.dtAtualizacao.hashCode() : 0);
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
        final Recurso other = (Recurso) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if (this.regra != other.regra && (this.regra == null || !this.regra.equals(other.regra))) {
            return false;
        }
        if (this.recursoPermissoes != other.recursoPermissoes && (this.recursoPermissoes == null || !this.recursoPermissoes.equals(other.recursoPermissoes))) {
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