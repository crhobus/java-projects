package br.furb.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "funcionalidade")
public class Funcionalidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_funcionalidade", columnDefinition = "number(10)")
    private long id;
    
    @Column(name = "cd_codigo", columnDefinition = "varchar2(30)", nullable = false)
    private String codigo;
    
    @Column(name = "ds_funcionalidade", columnDefinition = "varchar2(200)", nullable = false)
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionalidade")
    private List<Permissao> permissoes;
    
    @OneToMany()
    @JoinColumn(name = "id_funcionalidade", columnDefinition = "number(10)", nullable = false)
    private List<Funcionalidade> funcionalidades;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionalidade> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
