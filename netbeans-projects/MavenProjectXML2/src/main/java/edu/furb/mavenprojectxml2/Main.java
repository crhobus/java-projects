package edu.furb.mavenprojectxml2;

import edu.furb.carros.Carro;
import edu.furb.carros.Motorista;
import edu.furb.carros.ObjectFactory;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {

    public static void main(String[] args) {
        try {
            Carro carro1 = new Carro();
            carro1.setNome("Brasilia");
            carro1.getMotoristas().add(new Motorista("João"));
            carro1.getMotoristas().add(new Motorista("Caio"));

            JAXBContext context = JAXBContext.newInstance("edu.furb.carros");
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            JAXBElement<Carro> element = new ObjectFactory().createCarro(carro1);
            m.marshal(element, System.out);

            Unmarshaller unm = context.createUnmarshaller();
            File file = new File("carros.xml");
            element = (JAXBElement<Carro>) unm.unmarshal(file);

            Carro carro2 = element.getValue();
            System.out.println("\nCarro: " + carro2);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
