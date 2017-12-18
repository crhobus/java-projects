package edu.furb.easyboleto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "boleto")
@TableGenerator(name = "boleto_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "boletoseq", valueColumnName = "valor", allocationSize = 1)
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "boleto_seq", strategy = GenerationType.TABLE)
    @Column(name = "nr_boleto")
    private long nrBoleto;
    @Column(name = "aceite", length = 1)
    private String aceite;
    @Column(name = "vl_abatimento", nullable = false)
    private double vlAbatimento;
    @Column(name = "vl_multa", nullable = false)
    private double vlMulta;
    @Column(name = "vl_boleto", nullable = false)
    private double vlBoleto;
    @Column(name = "dt_emissao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEmissao;
    @Column(name = "dt_documento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDocumento;
    @Column(name = "dt_vencimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtVencimento;
    @Column(name = "nosso_numero", length = 20, nullable = false)
    private String nossoNumero;
    @Column(name = "especie_titulo", length = 2)
    private String especieTitulo;
    @Column(name = "linha_digitavel", length = 54)
    private String linhaDigitavel;
    @Column(name = "codigo_barras", length = 46)
    private String codigoBarras;
    @Column(name = "mensagem1", length = 60)
    private String mensagem1;
    @Column(name = "mensagem2", length = 60)
    private String mensagem2;
    @Column(name = "mensagem3", length = 60)
    private String mensagem3;
    @Column(name = "mensagem4", length = 60)
    private String mensagem4;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_conta", nullable = false)
    private Beneficiario beneficiario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_pagador", nullable = false)
    private Pagador pagador;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_banco", nullable = false)
    private Banco banco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_convenio_beneficiario", nullable = false)
    private ConvenioBeneficiario convenioBeneficiario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_especie_documento", nullable = false)
    private EspecieDocumento especieDocumento;

    public long getNrBoleto() {
        return nrBoleto;
    }

    public void setNrBoleto(long nrBoleto) {
        this.nrBoleto = nrBoleto;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public double getVlAbatimento() {
        return vlAbatimento;
    }

    public void setVlAbatimento(double vlAbatimento) {
        this.vlAbatimento = vlAbatimento;
    }

    public double getVlMulta() {
        return vlMulta;
    }

    public void setVlMulta(double vlMulta) {
        this.vlMulta = vlMulta;
    }

    public double getVlBoleto() {
        return vlBoleto;
    }

    public void setVlBoleto(double vlBoleto) {
        this.vlBoleto = vlBoleto;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public Date getDtDocumento() {
        return dtDocumento;
    }

    public void setDtDocumento(Date dtDocumento) {
        this.dtDocumento = dtDocumento;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getEspecieTitulo() {
        return especieTitulo;
    }

    public void setEspecieTitulo(String especieTitulo) {
        this.especieTitulo = especieTitulo;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getMensagem1() {
        return mensagem1;
    }

    public void setMensagem1(String mensagem1) {
        this.mensagem1 = mensagem1;
    }

    public String getMensagem2() {
        return mensagem2;
    }

    public void setMensagem2(String mensagem2) {
        this.mensagem2 = mensagem2;
    }

    public String getMensagem3() {
        return mensagem3;
    }

    public void setMensagem3(String mensagem3) {
        this.mensagem3 = mensagem3;
    }

    public String getMensagem4() {
        return mensagem4;
    }

    public void setMensagem4(String mensagem4) {
        this.mensagem4 = mensagem4;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Pagador getPagador() {
        return pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ConvenioBeneficiario getConvenioBeneficiario() {
        return convenioBeneficiario;
    }

    public void setConvenioBeneficiario(ConvenioBeneficiario convenioBeneficiario) {
        this.convenioBeneficiario = convenioBeneficiario;
    }

    public EspecieDocumento getEspecieDocumento() {
        return especieDocumento;
    }

    public void setEspecieDocumento(EspecieDocumento especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.nrBoleto ^ (this.nrBoleto >>> 32));
        hash = 47 * hash + Objects.hashCode(this.aceite);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.vlAbatimento) ^ (Double.doubleToLongBits(this.vlAbatimento) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.vlMulta) ^ (Double.doubleToLongBits(this.vlMulta) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.vlBoleto) ^ (Double.doubleToLongBits(this.vlBoleto) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.dtEmissao);
        hash = 47 * hash + Objects.hashCode(this.dtDocumento);
        hash = 47 * hash + Objects.hashCode(this.dtVencimento);
        hash = 47 * hash + Objects.hashCode(this.nossoNumero);
        hash = 47 * hash + Objects.hashCode(this.especieTitulo);
        hash = 47 * hash + Objects.hashCode(this.linhaDigitavel);
        hash = 47 * hash + Objects.hashCode(this.codigoBarras);
        hash = 47 * hash + Objects.hashCode(this.mensagem1);
        hash = 47 * hash + Objects.hashCode(this.mensagem2);
        hash = 47 * hash + Objects.hashCode(this.mensagem3);
        hash = 47 * hash + Objects.hashCode(this.mensagem4);
        hash = 47 * hash + Objects.hashCode(this.beneficiario);
        hash = 47 * hash + Objects.hashCode(this.pagador);
        hash = 47 * hash + Objects.hashCode(this.banco);
        hash = 47 * hash + Objects.hashCode(this.convenioBeneficiario);
        hash = 47 * hash + Objects.hashCode(this.especieDocumento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boleto other = (Boleto) obj;
        if (this.nrBoleto != other.nrBoleto) {
            return false;
        }
        if (!Objects.equals(this.aceite, other.aceite)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vlAbatimento) != Double.doubleToLongBits(other.vlAbatimento)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vlMulta) != Double.doubleToLongBits(other.vlMulta)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vlBoleto) != Double.doubleToLongBits(other.vlBoleto)) {
            return false;
        }
        if (!Objects.equals(this.dtEmissao, other.dtEmissao)) {
            return false;
        }
        if (!Objects.equals(this.dtDocumento, other.dtDocumento)) {
            return false;
        }
        if (!Objects.equals(this.dtVencimento, other.dtVencimento)) {
            return false;
        }
        if (!Objects.equals(this.nossoNumero, other.nossoNumero)) {
            return false;
        }
        if (!Objects.equals(this.especieTitulo, other.especieTitulo)) {
            return false;
        }
        if (!Objects.equals(this.linhaDigitavel, other.linhaDigitavel)) {
            return false;
        }
        if (!Objects.equals(this.codigoBarras, other.codigoBarras)) {
            return false;
        }
        if (!Objects.equals(this.mensagem1, other.mensagem1)) {
            return false;
        }
        if (!Objects.equals(this.mensagem2, other.mensagem2)) {
            return false;
        }
        if (!Objects.equals(this.mensagem3, other.mensagem3)) {
            return false;
        }
        if (!Objects.equals(this.mensagem4, other.mensagem4)) {
            return false;
        }
        if (!Objects.equals(this.beneficiario, other.beneficiario)) {
            return false;
        }
        if (!Objects.equals(this.pagador, other.pagador)) {
            return false;
        }
        if (!Objects.equals(this.banco, other.banco)) {
            return false;
        }
        if (!Objects.equals(this.convenioBeneficiario, other.convenioBeneficiario)) {
            return false;
        }
        if (!Objects.equals(this.especieDocumento, other.especieDocumento)) {
            return false;
        }
        return true;
    }
}
