package exemplo.rpc.hello;

import netbula.ORPC.*;

class helloSrv extends hello_svcb {

    public String sendHello(String in_arg) {

        System.out.println("Recebido do cliente: " + in_arg);
        return "Recebido pelo servidor: " + in_arg;
    }
}

public class SrvhelloRPC {

    static public void main(String args[]) {
        try {
            new helloSrv().run();
        } catch (rpc_err e) {
            System.out.println("Falha no servidor:" + e.toString());
        }
    }
}
