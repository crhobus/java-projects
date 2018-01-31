package model.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_usuario", columnDefinition = "number(10)")
    private int cdUsuario;

    @Column(name = "nm_usuario", columnDefinition = "varchar2(25)", nullable = false, unique = true)
    private String nmUsuario;

    @Column(name = "ds_senha", columnDefinition = "varchar2(40)", nullable = false)
    private String dsSenha;

    @Column(name = "ds_email", columnDefinition = "varchar2(80)", nullable = false)
    private String dsEmail;

    @Column(name = "dt_atualizacao", columnDefinition = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_tipo_usuario", columnDefinition = "number(10)", nullable = false)
    private TipoUsuario tipoUsuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_usuario", columnDefinition = "number(10)")
    private List<Perfil> perfis;

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
