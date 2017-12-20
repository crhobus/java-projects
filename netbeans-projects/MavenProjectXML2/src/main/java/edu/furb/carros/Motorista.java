package edu.furb.carros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Motorista", propOrder = {
    "nome"
})
public class Motorista {

    @XmlElement(required = true)
    protected String nome;

    public Motorista() {
    }

    public Motorista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String value) {
        this.nome = value;
    }

    @Override
    public String toString() {
        return "Motorista{" + "nome=" + nome + '}';
    }
}
