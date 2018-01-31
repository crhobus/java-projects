package model.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_funcionario", columnDefinition = "number(10)")
    private int cdFuncionario;

    @Column(name = "nm_funcionario", columnDefinition = "varchar2(100)", nullable = false)
    private String nmFuncionario;

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

    @Column(name = "nr_carteira_trabalho", columnDefinition = "varchar2(15)", nullable = false)
    private String nrCarteiraTrabalho;

    @Column(name = "nr_pis_pasep", columnDefinition = "varchar2(15)", nullable = false)
    private String nrPisPasep;

    @Column(name = "nr_rg", columnDefinition = "varchar2(12)", nullable = false)
    private String nrRg;

    @Column(name = "nr_cpf", columnDefinition = "varchar2(15)", nullable = false)
    private String nrCpf;

    @Column(name = "nr_cnh", columnDefinition = "varchar2(15)")
    private String nrCnh;

    @Column(name = "ds_cargo", columnDefinition = "varchar2(80)", nullable = false)
    private String dsCargo;

    @Column(name = "vl_salario", columnDefinition = "number(10,2)", nullable = false)
    private double vlSalario;

    @Column(name = "dt_admissao", columnDefinition = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtAdmissao;

    @Column(name = "dt_demissao", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private Date dtDemissao;

    @Column(name = "ds_observacao", columnDefinition = "varchar2(400)")
    private String dsObservacao;

    public int getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(int cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public String getDsCargo() {
        return dsCargo;
    }

    public void setDsCargo(String dsCargo) {
        this.dsCargo = dsCargo;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Date getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(Date dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public Date getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(Date dtDemissao) {
        this.dtDemissao = dtDemissao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNmFuncionario() {
        return nmFuncionario;
    }

    public void setNmFuncionario(String nmFuncionario) {
        this.nmFuncionario = nmFuncionario;
    }

    public String getNrCarteiraTrabalho() {
        return nrCarteiraTrabalho;
    }

    public void setNrCarteiraTrabalho(String nrCarteiraTrabalho) {
        this.nrCarteiraTrabalho = nrCarteiraTrabalho;
    }

    public String getNrCelular() {
        return nrCelular;
    }

    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    public String getNrCnh() {
        return nrCnh;
    }

    public void setNrCnh(String nrCnh) {
        this.nrCnh = nrCnh;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    public String getNrPisPasep() {
        return nrPisPasep;
    }

    public void setNrPisPasep(String nrPisPasep) {
        this.nrPisPasep = nrPisPasep;
    }

    public String getNrRg() {
        return nrRg;
    }

    public void setNrRg(String nrRg) {
        this.nrRg = nrRg;
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

    public double getVlSalario() {
        return vlSalario;
    }

    public void setVlSalario(double vlSalario) {
        this.vlSalario = vlSalario;
    }
}
