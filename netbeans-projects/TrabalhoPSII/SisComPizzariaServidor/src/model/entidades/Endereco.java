package model.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_endereco", columnDefinition = "number(10)")
    private int cdEndereco;

    @Column(name = "ds_endereco", columnDefinition = "varchar2(120)", nullable = false)
    private String dsEndereco;

    @Column(name = "nm_bairro", columnDefinition = "varchar2(60)", nullable = false)
    private String nmBairro;

    @Column(name = "nr_cep", columnDefinition = "varchar2(9)", nullable = false)
    private String nrCep;

    @Column(name = "nr_residencia", columnDefinition = "number(10)", nullable = false)
    private int nrResidencia;

    @Column(name = "nm_cidade", columnDefinition = "varchar2(60)", nullable = false)
    private String nmCidade;

    @Column(name = "ds_referencia", columnDefinition = "varchar2(90)")
    private String dsReferencia;

    public int getCdEndereco() {
        return cdEndereco;
    }

    public void setCdEndereco(int cdEndereco) {
        this.cdEndereco = cdEndereco;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsReferencia() {
        return dsReferencia;
    }

    public void setDsReferencia(String dsReferencia) {
        this.dsReferencia = dsReferencia;
    }

    public String getNmBairro() {
        return nmBairro;
    }

    public void setNmBairro(String nmBairro) {
        this.nmBairro = nmBairro;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public String getNrCep() {
        return nrCep;
    }

    public void setNrCep(String nrCep) {
        this.nrCep = nrCep;
    }

    public int getNrResidencia() {
        return nrResidencia;
    }

    public void setNrResidencia(int nrResidencia) {
        this.nrResidencia = nrResidencia;
    }
}
