package edu.furb.mavenprojectxml2;

import edu.furb.carros.Carro;
import edu.furb.carros.Motorista;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class MainJackson {

    public static void main(String[] args) {
        try {
            Carro carro1 = new Carro();
            carro1.setNome("Brasilia");
            carro1.getMotoristas().add(new Motorista("João"));
            carro1.getMotoristas().add(new Motorista("Caio"));

            ObjectMapper mapper = new ObjectMapper();
            AnnotationIntrospector intro = new JaxbAnnotationIntrospector();
            mapper.getDeserializationConfig().setAnnotationIntrospector(intro);
            mapper.getSerializationConfig().setAnnotationIntrospector(intro);

            mapper.writeValue(System.out, carro1);

            Carro carro = mapper.readValue(new File("carros.json"), Carro.class);

            System.out.println("Carro: " + carro);

        } catch (IOException ex) {
            Logger.getLogger(MainJackson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
