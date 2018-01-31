package edu.furb.pagamento;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pagamento", propOrder = {
    "titulo",
    "cliente"
})
public class Pagamento {

    @XmlElement(required = true)
    protected List<Titulo> titulo;
    @XmlElement(required = true)
    protected String cliente;

    public Pagamento() {
    }

    public Pagamento(String cliente) {
        this.cliente = cliente;
    }

    public List<Titulo> getTitulo() {
        if (titulo == null) {
            titulo = new ArrayList<Titulo>();
        }
        return this.titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String value) {
        this.cliente = value;
    }

    @Override
    public String toString() {
        String titulos = "";
        for (Titulo t : titulo) {
            titulos += t;
        }
        return "Pagamento{" + "cliente=" + cliente + " - " + titulos + '}';
    }
}
