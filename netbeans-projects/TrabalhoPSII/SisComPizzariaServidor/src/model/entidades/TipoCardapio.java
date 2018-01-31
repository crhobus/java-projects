package model.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tipo_cardapio")
public class TipoCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_tipo", columnDefinition = "number(10)", nullable = false)
    private int cdTipo;
    
    @Column(name = "ds_tipo", columnDefinition = "varchar2(100)", nullable = false)
    private String dsTipo;

    public int getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(int cdTipo) {
        this.cdTipo = cdTipo;
    }

    public String getDsTipo() {
        return dsTipo;
    }

    public void setDsTipo(String dsTipo) {
        this.dsTipo = dsTipo;
    }
}
