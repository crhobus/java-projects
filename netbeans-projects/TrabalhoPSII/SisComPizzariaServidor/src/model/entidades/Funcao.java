package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "funcao")
public class Funcao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_funcao", columnDefinition = "number(10)")
    private int cdFuncao;

    @Column(name = "nm_funcao", columnDefinition = "varchar2(40)", nullable = false, unique = true)
    private String nmFuncao;

    public int getCdFuncao() {
        return cdFuncao;
    }

    public void setCdFuncao(int cdFuncao) {
        this.cdFuncao = cdFuncao;
    }

    public String getNmFuncao() {
        return nmFuncao;
    }

    public void setNmFuncao(String nmFuncao) {
        this.nmFuncao = nmFuncao;
    }
}
