package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sabor")
public class Sabor implements Serializable {

    // Código
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_sabor", columnDefinition = "number(10)")
    private int cdSabor;
    
    // Nome
    @Column(name = "nm_sabor", nullable = false, columnDefinition = "varchar2(50)")
    private String nmSabor;
    
    // Descriçao
    @Column(name = "ds_sabor", nullable = false, columnDefinition = "varchar2(250)")
    private String dsSabor;
    
    // Tipo
    @OneToOne
    @JoinColumn(name = "cd_tipo", nullable = false, columnDefinition = "number(10)")
    private TipoCardapio cdTipo;

    public int getCdSabor() {
        return cdSabor;
    }

    public void setCdSabor(int cdSabor) {
        this.cdSabor = cdSabor;
    }

    public String getDsSabor() {
        return dsSabor;
    }

    public void setDsSabor(String dsSabor) {
        this.dsSabor = dsSabor;
    }

    public String getNmSabor() {
        return nmSabor;
    }

    public void setNmSabor(String nmSabor) {
        this.nmSabor = nmSabor;
    }

    public TipoCardapio getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(TipoCardapio cdTipo) {
        this.cdTipo = cdTipo;
    }


}
