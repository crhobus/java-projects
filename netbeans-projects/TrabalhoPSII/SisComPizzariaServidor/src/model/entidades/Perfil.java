package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_perfil", columnDefinition = "number(10)")
    private int cdPerfil;

    @Column(name = "ie_alterar_dados", columnDefinition = "varchar2(1)", nullable = false)
    private String ieAlterarDados;//S ou N

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_funcao", columnDefinition = "number(10)", nullable = false)
    private Funcao funcao;

    public int getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(int cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public String getIeAlterarDados() {
        return ieAlterarDados;
    }

    public void setIeAlterarDados(String ieAlterarDados) {
        this.ieAlterarDados = ieAlterarDados;
    }
}
