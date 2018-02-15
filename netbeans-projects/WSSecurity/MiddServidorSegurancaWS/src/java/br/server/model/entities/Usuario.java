package br.server.model.entities;

/*
 * Document   : Usuario.java
 * Created on : 25/05/2013, 14:21:05
 * Author     : Caio
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nr_sequencia", columnDefinition = "number(10)")
    private long nrSequencia;

    @Column(name = "nm_pessoa", columnDefinition = "varchar2(80)", nullable = false)
    private String nmPessoa;

    @Column(name = "nm_usuario", columnDefinition = "varchar2(25)", nullable = false, unique = true)
    private String nmUsuario;

    @Column(name = "ds_senha", columnDefinition = "varchar2(32)", nullable = false)
    private String dsSenha;

    @Column(name = "ds_email", columnDefinition = "varchar2(80)", nullable = false)
    private String dsEmail;

    @Column(name = "ie_ativo", columnDefinition = "varchar2(1)", nullable = false)
    private boolean ieAtivo;

    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "usuario_permissao",
               uniqueConstraints = {@UniqueConstraint(columnNames = {"nr_seq_usuario", "ds_permissao"})},
               joinColumns = {@JoinColumn(name = "nr_seq_usuario")})
    @Column(name = "ds_permissao", columnDefinition = "varchar2(25)", nullable = false)
    private Set<String> dsPermissao = new HashSet<String>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nr_seq_usuario", columnDefinition = "number(10)")
    private List<Recurso> recursos = new ArrayList<Recurso>();

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

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public boolean isIeAtivo() {
        return ieAtivo;
    }

    public void setIeAtivo(boolean ieAtivo) {
        this.ieAtivo = ieAtivo;
    }

    public Set<String> getDsPermissao() {
        return dsPermissao;
    }

    public void setDsPermissao(Set<String> dsPermissao) {
        this.dsPermissao = dsPermissao;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
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
        hash = 73 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 73 * hash + (this.nmPessoa != null ? this.nmPessoa.hashCode() : 0);
        hash = 73 * hash + (this.nmUsuario != null ? this.nmUsuario.hashCode() : 0);
        hash = 73 * hash + (this.dsSenha != null ? this.dsSenha.hashCode() : 0);
        hash = 73 * hash + (this.dsEmail != null ? this.dsEmail.hashCode() : 0);
        hash = 73 * hash + (this.ieAtivo ? 1 : 0);
        hash = 73 * hash + (this.dsPermissao != null ? this.dsPermissao.hashCode() : 0);
        hash = 73 * hash + (this.recursos != null ? this.recursos.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if ((this.nmPessoa == null) ? (other.nmPessoa != null) : !this.nmPessoa.equals(other.nmPessoa)) {
            return false;
        }
        if ((this.nmUsuario == null) ? (other.nmUsuario != null) : !this.nmUsuario.equals(other.nmUsuario)) {
            return false;
        }
        if ((this.dsSenha == null) ? (other.dsSenha != null) : !this.dsSenha.equals(other.dsSenha)) {
            return false;
        }
        if ((this.dsEmail == null) ? (other.dsEmail != null) : !this.dsEmail.equals(other.dsEmail)) {
            return false;
        }
        if (this.ieAtivo != other.ieAtivo) {
            return false;
        }
        if (this.dsPermissao != other.dsPermissao && (this.dsPermissao == null || !this.dsPermissao.equals(other.dsPermissao))) {
            return false;
        }
        if (this.recursos != other.recursos && (this.recursos == null || !this.recursos.equals(other.recursos))) {
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