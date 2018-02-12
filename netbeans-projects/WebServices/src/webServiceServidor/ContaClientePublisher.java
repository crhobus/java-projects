package webServiceServidor;

import javax.xml.ws.Endpoint;

public class ContaClientePublisher {

    public static void main(String[] args) {
        System.out.println("Web Service - Inicializado");
        ContaCliente cadasCliente = new ContaCliente();
        Endpoint.publish("http://localhost:5097/cliente", cadasCliente);
    }
}
