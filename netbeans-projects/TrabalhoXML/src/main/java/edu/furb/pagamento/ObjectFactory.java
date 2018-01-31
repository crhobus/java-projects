package edu.furb.pagamento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Pagamento_QNAME = new QName("", "pagamento");

    public ObjectFactory() {
    }

    public Titulo createTitulo() {
        return new Titulo();
    }

    public Pagamento createPagamento() {
        return new Pagamento();
    }

    @XmlElementDecl(namespace = "", name = "pagamento")
    public JAXBElement<Pagamento> createPagamento(Pagamento value) {
        return new JAXBElement<Pagamento>(_Pagamento_QNAME, Pagamento.class, null, value);
    }
}
