package br.furb.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario", columnDefinition = "number(10)")
    private long id;
    
    @Column(name = "ds_login", columnDefinition = "varchar2(30)", unique = true, nullable = false)
    private String login;
    
    @Column(name = "ds_senha", columnDefinition = "varchar2(30)", nullable = false)
    private String senha;
    
    @Column(name = "ds_nome", columnDefinition = "varchar2(80)", nullable = false)
    private String nome;
    
    @Column(name = "ds_email", columnDefinition = "varchar2(110)", nullable = false)
    private String email;
    
    @ManyToOne()
    @JoinColumn(name = "id_grupo", columnDefinition = "number(10)", nullable = false)
    private Grupo grupo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
