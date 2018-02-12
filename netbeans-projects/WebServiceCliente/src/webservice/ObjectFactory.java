
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetHelloWordResponse_QNAME = new QName("http://servidor/", "getHelloWordResponse");
    private final static QName _GetListaCidades_QNAME = new QName("http://servidor/", "getListaCidades");
    private final static QName _GetHelloWord_QNAME = new QName("http://servidor/", "getHelloWord");
    private final static QName _SomaResponse_QNAME = new QName("http://servidor/", "somaResponse");
    private final static QName _GetListaCidadesResponse_QNAME = new QName("http://servidor/", "getListaCidadesResponse");
    private final static QName _Soma_QNAME = new QName("http://servidor/", "soma");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListaCidades }
     * 
     */
    public GetListaCidades createGetListaCidades() {
        return new GetListaCidades();
    }

    /**
     * Create an instance of {@link GetHelloWord }
     * 
     */
    public GetHelloWord createGetHelloWord() {
        return new GetHelloWord();
    }

    /**
     * Create an instance of {@link GetHelloWordResponse }
     * 
     */
    public GetHelloWordResponse createGetHelloWordResponse() {
        return new GetHelloWordResponse();
    }

    /**
     * Create an instance of {@link GetListaCidadesResponse }
     * 
     */
    public GetListaCidadesResponse createGetListaCidadesResponse() {
        return new GetListaCidadesResponse();
    }

    /**
     * Create an instance of {@link Soma }
     * 
     */
    public Soma createSoma() {
        return new Soma();
    }

    /**
     * Create an instance of {@link SomaResponse }
     * 
     */
    public SomaResponse createSomaResponse() {
        return new SomaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHelloWordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "getHelloWordResponse")
    public JAXBElement<GetHelloWordResponse> createGetHelloWordResponse(GetHelloWordResponse value) {
        return new JAXBElement<GetHelloWordResponse>(_GetHelloWordResponse_QNAME, GetHelloWordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaCidades }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "getListaCidades")
    public JAXBElement<GetListaCidades> createGetListaCidades(GetListaCidades value) {
        return new JAXBElement<GetListaCidades>(_GetListaCidades_QNAME, GetListaCidades.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHelloWord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "getHelloWord")
    public JAXBElement<GetHelloWord> createGetHelloWord(GetHelloWord value) {
        return new JAXBElement<GetHelloWord>(_GetHelloWord_QNAME, GetHelloWord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SomaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "somaResponse")
    public JAXBElement<SomaResponse> createSomaResponse(SomaResponse value) {
        return new JAXBElement<SomaResponse>(_SomaResponse_QNAME, SomaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaCidadesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "getListaCidadesResponse")
    public JAXBElement<GetListaCidadesResponse> createGetListaCidadesResponse(GetListaCidadesResponse value) {
        return new JAXBElement<GetListaCidadesResponse>(_GetListaCidadesResponse_QNAME, GetListaCidadesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Soma }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "soma")
    public JAXBElement<Soma> createSoma(Soma value) {
        return new JAXBElement<Soma>(_Soma_QNAME, Soma.class, null, value);
    }

}
