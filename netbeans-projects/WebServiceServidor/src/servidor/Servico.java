package servidor;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Servico {

    @WebMethod
    public String getHelloWord(String cliente) {
        return "Hello Word " + cliente;
    }

    @WebMethod
    public double soma(double valor1, double valor2) {
        return valor1 + valor2;
    }

    @WebMethod
    public List<String> getListaCidades() {
        ArrayList cidades = new ArrayList();
        cidades.add("Indaial");
        cidades.add("Blumenau");
        cidades.add("Florianópolis");
        cidades.add("Navegantes");
        cidades.add("Balneário Camboriú");
        return cidades;
    }
}
