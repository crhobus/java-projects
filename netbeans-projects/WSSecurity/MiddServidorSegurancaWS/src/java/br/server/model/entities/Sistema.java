package br.server.model.entities;

/*
 * Document   : Sistema.java
 * Created on : 25/05/2013, 14:29:10
 * Author     : Caio
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sistema")
public class Sistema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nr_sequencia", columnDefinition = "number(10)")
    private long nrSequencia;

    @Column(name = "nm_sistema", columnDefinition = "varchar2(80)", nullable = false, unique = true)
    private String nmSistema;

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

    public String getNmSistema() {
        return nmSistema;
    }

    public void setNmSistema(String nmSistema) {
        this.nmSistema = nmSistema;
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
        hash = 79 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 79 * hash + (this.nmSistema != null ? this.nmSistema.hashCode() : 0);
        hash = 79 * hash + (this.nmUsuarioAtualizacao != null ? this.nmUsuarioAtualizacao.hashCode() : 0);
        hash = 79 * hash + (this.dtAtualizacao != null ? this.dtAtualizacao.hashCode() : 0);
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
        final Sistema other = (Sistema) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if ((this.nmSistema == null) ? (other.nmSistema != null) : !this.nmSistema.equals(other.nmSistema)) {
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