package servidor;

import javax.xml.ws.Endpoint;

public class ServicoPublisher {

    public static void main(String[] args) {
        System.out.println("Web Service - Inicializado");
        Endpoint.publish("http://localhost:8084/servico", new Servico());
    }
}
