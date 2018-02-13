package ws;

import br.bancoDados.CalcDB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Caio
 */
@WebService(name = "CalcWS",
            serviceName = "CalcWSService",
            portName = "CalcWSPort",
            targetNamespace = "http://servico.calculadora.ws.br/")
public class CalcWS extends CalcDB {

//    @Resource
//    WebServiceContext wsctx;

    @WebMethod(operationName = "add")
//    @RolesAllowed("teste")
    public double add(@WebParam(name = "i") double i, @WebParam(name = "y") double y) {

//        MessageContext mctx = wsctx.getMessageContext();
//
//        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
//        List userList = (List) http_headers.get("Username");
//        List passList = (List) http_headers.get("Password");
//
//        String username = "";
//        String password = "";
//
//        if (userList != null) {
//            //get username
//            username = userList.get(0).toString();
//        }
//
//        if (passList != null) {
//            //get password
//            password = passList.get(0).toString();
//        }
//
//        //Should validate username and password with database
//        if (username.equals("user") && password.equals("key50100")) {
            double result = i + y;
            insert(i, y, "Soma", result);
            return result;
//        } else {
//            return -1;
//        }
    }

    @WebMethod(operationName = "sub")
    public double sub(@WebParam(name = "i") double i, @WebParam(name = "y") double y) {
        double result = i - y;
        insert(i, y, "Subtração", result);
        return result;
    }

    @WebMethod(operationName = "mult")
    public double mult(@WebParam(name = "i") double i, @WebParam(name = "y") double y) {
        double result = i * y;
        insert(i, y, "Multiplicação", result);
        return result;
    }

    @WebMethod(operationName = "div")
    public double div(@WebParam(name = "i") double i, @WebParam(name = "y") double y) {
        double result = i / y;
        insert(i, y, "Divisão", result);
        return result;
    }
}