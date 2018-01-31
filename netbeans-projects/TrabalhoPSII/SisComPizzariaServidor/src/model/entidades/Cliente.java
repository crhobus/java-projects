package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_cliente", columnDefinition = "number(10)")
    private int cdCliente;

    @Column(name = "nmCliente", columnDefinition = "varchar2(100)", nullable = false)
    private String nmCliente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_usuario", columnDefinition = "number(10)", nullable = false)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_endereco", columnDefinition = "number(10)", nullable = false)
    private Endereco endereco;

    @Column(name = "nr_tefefone", columnDefinition = "varchar2(14)")
    private String nrTefefone;

    @Column(name = "nr_celular", columnDefinition = "varchar2(14)")
    private String nrCelular;

    @Column(name = "ds_observacao", columnDefinition = "varchar2(400)")
    private String dsObservacao;

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getNrCelular() {
        return nrCelular;
    }

    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    public String getNrTefefone() {
        return nrTefefone;
    }

    public void setNrTefefone(String nrTefefone) {
        this.nrTefefone = nrTefefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
