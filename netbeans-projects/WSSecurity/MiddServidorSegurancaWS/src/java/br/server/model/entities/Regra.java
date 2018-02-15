package br.server.model.entities;

/*
 * Document   : Regra.java
 * Created on : 25/08/2013, 14:34:18
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
@Table(name = "regra")
public class Regra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nr_sequencia", columnDefinition = "number(10)")
    private long nrSequencia;

    @Column(name = "nm_regra", columnDefinition = "varchar2(80)", nullable = false, unique = true)
    private String nmRegra;

    @Column(name = "cd_regra_comunic", columnDefinition = "varchar2(20)", nullable = false, unique = true)
    private String cdRegraComunic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_sistema", columnDefinition = "number(10)", nullable = false)
    private Sistema sistema;

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

    public String getNmRegra() {
        return nmRegra;
    }

    public void setNmRegra(String nmRegra) {
        this.nmRegra = nmRegra;
    }

    public String getCdRegraComunic() {
        return cdRegraComunic;
    }

    public void setCdRegraComunic(String cdRegraComunic) {
        this.cdRegraComunic = cdRegraComunic;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
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
        hash = 73 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 73 * hash + (this.nmRegra != null ? this.nmRegra.hashCode() : 0);
        hash = 73 * hash + (this.cdRegraComunic != null ? this.cdRegraComunic.hashCode() : 0);
        hash = 73 * hash + (this.sistema != null ? this.sistema.hashCode() : 0);
        hash = 73 * hash + (this.nmUsuarioAtualizacao != null ? this.nmUsuarioAtualizacao.hashCode() : 0);
        hash = 73 * hash + (this.dtAtualizacao != null ? this.dtAtualizacao.hashCode() : 0);
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
        final Regra other = (Regra) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if ((this.nmRegra == null) ? (other.nmRegra != null) : !this.nmRegra.equals(other.nmRegra)) {
            return false;
        }
        if ((this.cdRegraComunic == null) ? (other.cdRegraComunic != null) : !this.cdRegraComunic.equals(other.cdRegraComunic)) {
            return false;
        }
        if (this.sistema != other.sistema && (this.sistema == null || !this.sistema.equals(other.sistema))) {
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