package ws.cliente;

import br.ws.calculadora.servico.CalcWS;
import br.ws.calculadora.servico.CalcWSService;
import javax.xml.ws.BindingProvider;
/**
 *
 * @author Caio
 */
public class ClienteWS {

    private static final String WS_URL = "http://localhost:8084/CalculadoraWS/CalcWS?wsdl";

    public static void main(String[] args) {
        CalcWSService service = new CalcWSService();
        CalcWS port = service.getCalcWSPort();

        //*****************UserName & Password ********************************
//        Map<String, List<String>> headers = new HashMap<String, List<String>>();
//        headers.put("Username", Collections.singletonList("SystemWS"));
//        headers.put("Password", Collections.singletonList("key50100"));

        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);
//        ((BindingProvider) port).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        //*********************************************************************

        System.out.println("Soma: " + port.add(24.0, 21.0));
        System.out.println("Subtração: " + port.sub(24, 21.0));
        System.out.println("Multiplicação: " + port.mult(24.0, 21.0));
        System.out.println("Divisão: " + port.div(24.0, 21.0));
    }
}
