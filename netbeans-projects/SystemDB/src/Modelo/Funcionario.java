package Modelo;

import java.io.Serializable;
import java.util.Date;

public class Funcionario extends Pessoa implements Serializable {

    private int codFunc, diaPagto;
    private String numContrato, tipoContrato, numPis, numCartHabilitacao,
            tituloEleitor, numCartTrabalho, certReservista, cargo, turno,
            horaInicial, horaFinal, banco, numConta;
    private Date dataAdmissao, dataDemissao;
    private double salario;
    private boolean ativo;
    private Setor setor;
    private Departamento departamento;

    public int getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(int codFunc) {
        this.codFunc = codFunc;
    }

    public int getDiaPagto() {
        return diaPagto;
    }

    public void setDiaPagto(int diaPagto) {
        this.diaPagto = diaPagto;
    }

    public String getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getNumPis() {
        return numPis;
    }

    public void setNumPis(String numPis) {
        this.numPis = numPis;
    }

    public String getNumCartHabilitacao() {
        return numCartHabilitacao;
    }

    public void setNumCartHabilitacao(String numCartHabilitacao) {
        this.numCartHabilitacao = numCartHabilitacao;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getNumCartTrabalho() {
        return numCartTrabalho;
    }

    public void setNumCartTrabalho(String numCartTrabalho) {
        this.numCartTrabalho = numCartTrabalho;
    }

    public String getCertReservista() {
        return certReservista;
    }

    public void setCertReservista(String certReservista) {
        this.certReservista = certReservista;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}