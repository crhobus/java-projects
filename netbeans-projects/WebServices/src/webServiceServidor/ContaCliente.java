package webServiceServidor;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ContaCliente {

    @WebMethod
    public String getCliente() {
        return "Caio Renan Hobus";
    }
}
