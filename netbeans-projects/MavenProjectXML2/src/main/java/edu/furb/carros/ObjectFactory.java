package edu.furb.carros;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Carro_QNAME = new QName("", "carro");

    public ObjectFactory() {
    }

    public Carro createCarro() {
        return new Carro();
    }

    public Motorista createMotorista() {
        return new Motorista();
    }

    @XmlElementDecl(namespace = "", name = "carro")
    public JAXBElement<Carro> createCarro(Carro value) {
        return new JAXBElement<Carro>(_Carro_QNAME, Carro.class, null, value);
    }
}
