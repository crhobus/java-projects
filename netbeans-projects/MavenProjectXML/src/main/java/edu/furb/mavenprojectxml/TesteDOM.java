package edu.furb.mavenprojectxml;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TesteDOM {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("agenda.xml");

            Element raiz = doc.getDocumentElement();
            System.out.println("O element raiz é: " + raiz.getNodeName());

            NodeList contatos = raiz.getElementsByTagName("contato");

            for (int i = 0; i < contatos.getLength(); i++) {
                Element contato = (Element) contatos.item(i);
                String nome = contato.getElementsByTagName("nome").item(0).getFirstChild().getNodeValue();
                String email = contato.getElementsByTagName("email").item(0).getFirstChild().getNodeValue();

                System.out.println("Nome: " + nome + " Email: " + email);
            }

        } catch (Exception ex) {
            Logger.getLogger(TesteDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
