package br.furb.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_grupo", columnDefinition = "number(10)")
    private long id;
    
    @Column(name = "cd_codigo", columnDefinition = "varchar2(30)", nullable = false)
    private String cogigo;
    
    @Column(name = "ds_nome", columnDefinition = "varchar2(50)", nullable = false)
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo")
    private List<Permissao> permissoes;

    public String getCogigo() {
        return cogigo;
    }

    public void setCogigo(String cogigo) {
        this.cogigo = cogigo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
