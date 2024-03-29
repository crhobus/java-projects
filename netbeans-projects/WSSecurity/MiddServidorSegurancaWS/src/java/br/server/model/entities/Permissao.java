package br.server.model.entities;

/*
 * Document   : Permissao.java
 * Created on : 25/08/2013, 14:36:25
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
@Table(name = "permissao")
public class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nr_sequencia", columnDefinition = "number(10)")
    private long nrSequencia;

    @Column(name = "nm_permissao", columnDefinition = "varchar2(50)", nullable = false, unique = true)
    private String nmPermissao;

    @Column(name = "cd_permissao_comunic", columnDefinition = "varchar2(20)", nullable = false, unique = true)
    private String cdPermissaoComunic;

    @Column(name = "ds_permissao", columnDefinition = "varchar2(4000)")
    private String dsPermissao;

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

    public String getNmPermissao() {
        return nmPermissao;
    }

    public void setNmPermissao(String nmPermissao) {
        this.nmPermissao = nmPermissao;
    }

    public String getCdPermissaoComunic() {
        return cdPermissaoComunic;
    }

    public void setCdPermissaoComunic(String cdPermissaoComunic) {
        this.cdPermissaoComunic = cdPermissaoComunic;
    }

    public String getDsPermissao() {
        return dsPermissao;
    }

    public void setDsPermissao(String dsPermissao) {
        this.dsPermissao = dsPermissao;
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
        hash = 59 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 59 * hash + (this.nmPermissao != null ? this.nmPermissao.hashCode() : 0);
        hash = 59 * hash + (this.cdPermissaoComunic != null ? this.cdPermissaoComunic.hashCode() : 0);
        hash = 59 * hash + (this.dsPermissao != null ? this.dsPermissao.hashCode() : 0);
        hash = 59 * hash + (this.nmUsuarioAtualizacao != null ? this.nmUsuarioAtualizacao.hashCode() : 0);
        hash = 59 * hash + (this.dtAtualizacao != null ? this.dtAtualizacao.hashCode() : 0);
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
        final Permissao other = (Permissao) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if ((this.nmPermissao == null) ? (other.nmPermissao != null) : !this.nmPermissao.equals(other.nmPermissao)) {
            return false;
        }
        if ((this.cdPermissaoComunic == null) ? (other.cdPermissaoComunic != null) : !this.cdPermissaoComunic.equals(other.cdPermissaoComunic)) {
            return false;
        }
        if ((this.dsPermissao == null) ? (other.dsPermissao != null) : !this.dsPermissao.equals(other.dsPermissao)) {
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