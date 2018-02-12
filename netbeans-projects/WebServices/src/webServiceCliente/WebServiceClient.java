package webServiceCliente;

import webservice.ContaCliente;
import webservice.ContaClienteService;

public class WebServiceClient {

    public static void main(String[] args) {
        ContaClienteService contaClienteService = new ContaClienteService();

        ContaCliente proxy = contaClienteService.getContaClientePort();

        String nm = proxy.getCliente();

        System.out.println(nm);
    }
}
