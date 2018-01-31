package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_tipo_usuario", columnDefinition = "number(10)")
    private int cdTipoUsuario;

    @Column(name = "nm_tipo_usuario", columnDefinition = "varchar2(20)", nullable = false, unique = true)
    private String nmTipoUsuario;

    public int getCdTipoUsuario() {
        return cdTipoUsuario;
    }

    public void setCdTipoUsuario(int cdTipoUsuario) {
        this.cdTipoUsuario = cdTipoUsuario;
    }

    public String getNmTipoUsuario() {
        return nmTipoUsuario;
    }

    public void setNmTipoUsuario(String nmTipoUsuario) {
        this.nmTipoUsuario = nmTipoUsuario;
    }
}
