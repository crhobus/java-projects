package edu.furb.mavenprojectxml;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TesteSax extends DefaultHandler {

    private static final String TAG_NOME = "nome";
    private static final String TAG_CONTATO = "contato";
    private static final String TAG_EMAL = "email";
    private Contato contato;
    private List<Contato> listaContatos;
    private String valor;

    @Override
    public void startDocument() throws SAXException {
        //Iniciando o documento
        listaContatos = new ArrayList<Contato>();
    }

    @Override
    public void endDocument() throws SAXException {
        //Finalizando o documento
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //Iniciando elemento
        if (qName.equalsIgnoreCase(TAG_CONTATO)) {
            contato = new Contato();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //Finalizando elemento
        switch (qName.toLowerCase()) {
            case TAG_CONTATO: {
                listaContatos.add(contato);
                break;
            }
            case TAG_NOME: {
                contato.setNome(valor);
                break;
            }
            case TAG_EMAL: {
                contato.setEmail(valor);
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //Conteúdo
        valor = String.copyValueOf(ch, start, length).trim();
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public static void main(String[] args) {
        try {
            SAXParserFactory spd = SAXParserFactory.newInstance();
            SAXParser sp = spd.newSAXParser();

            TesteSax handler = new TesteSax();
            sp.parse("agenda.xml", handler);

            System.out.println("Contatos:");
            for (Contato contato : handler.getListaContatos()) {
                System.out.println("Contato: " + contato.getNome() + " email: " + contato.getEmail());
            }

        } catch (Exception ex) {
            Logger.getLogger(TesteSax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
