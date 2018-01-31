package edu.furb.pagamento;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "formaPgo",
    "valor",
    "quitado"
})
@XmlRootElement(name = "titulo")
public class Titulo {

    @XmlElement(name = "forma_pgo", required = true)
    protected String formaPgo;
    @XmlElement(required = true)
    protected BigDecimal valor;
    protected boolean quitado;
    @XmlAttribute(name = "nr_titulo")
    protected BigInteger nrTitulo;

    public Titulo() {
    }

    public Titulo(String formaPgo, BigDecimal valor, boolean quitado, BigInteger nrTitulo) {
        this.formaPgo = formaPgo;
        this.valor = valor;
        this.quitado = quitado;
        this.nrTitulo = nrTitulo;
    }

    public String getFormaPgo() {
        return formaPgo;
    }

    public void setFormaPgo(String value) {
        this.formaPgo = value;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal value) {
        this.valor = value;
    }

    public boolean isQuitado() {
        return quitado;
    }

    public void setQuitado(boolean value) {
        this.quitado = value;
    }

    public BigInteger getNrTitulo() {
        return nrTitulo;
    }

    public void setNrTitulo(BigInteger value) {
        this.nrTitulo = value;
    }

    @Override
    public String toString() {
        return "Titulo{" + "formaPgo=" + formaPgo + ", valor=" + valor + ", quitado=" + quitado + ", nrTitulo=" + nrTitulo + '}';
    }
}
