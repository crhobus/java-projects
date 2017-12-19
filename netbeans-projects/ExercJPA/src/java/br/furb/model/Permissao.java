package br.furb.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "permissao", uniqueConstraints = @UniqueConstraint(columnNames = {"id_grupo", "id_funcionalidade"}))
public class Permissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_permissao", columnDefinition = "number(10)")
    private int id;
    @Column(name = "ie_visualizar", columnDefinition = "varchar2(1)", nullable = false)
    private String visualizar;
    
    @Column(name = "ie_inserir", columnDefinition = "varchar2(1)", nullable = false)
    private String inserir;
    
    @Column(name = "ie_editar", columnDefinition = "varchar2(1)", nullable = false)
    private String editar;
    
    @Column(name = "ie_excluir", columnDefinition = "varchar2(1)", nullable = false)
    private String excluir;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionalidade", columnDefinition = "number(10)", nullable = false)
    private Funcionalidade funcionalidade;

    public String getEditar() {
        return editar;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

    public String getExcluir() {
        return excluir;
    }

    public void setExcluir(String excluir) {
        this.excluir = excluir;
    }

    public Funcionalidade getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInserir() {
        return inserir;
    }

    public void setInserir(String inserir) {
        this.inserir = inserir;
    }

    public String getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(String visualizar) {
        this.visualizar = visualizar;
    }
}
