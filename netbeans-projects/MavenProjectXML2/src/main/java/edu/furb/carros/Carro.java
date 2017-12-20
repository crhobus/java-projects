package edu.furb.carros;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Carro", propOrder = {
    "nome",
    "portas",
    "motoristas"
})
public class Carro {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement
    protected int portas;
    @XmlElement
    protected List<Motorista> motoristas;

    public String getNome() {
        return nome;
    }

    public void setNome(String value) {
        this.nome = value;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int value) {
        this.portas = value;
    }

    public List<Motorista> getMotoristas() {
        if (motoristas == null) {
            motoristas = new ArrayList<Motorista>();
        }
        return this.motoristas;
    }

    @Override
    public String toString() {
        String m = "";
        for (int i = 0; i < motoristas.size(); i++) {
            m += "Motorista " + i;
            m += "\n" + motoristas.get(i) + "\n";
        }
        return "Carro{" + "nome=" + nome + ", portas=" + portas + ", motoristas=" + m + '}';
    }
}
