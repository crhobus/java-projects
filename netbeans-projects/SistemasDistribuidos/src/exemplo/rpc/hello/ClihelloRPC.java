package exemplo.rpc.hello;

import netbula.ORPC.*;

public class ClihelloRPC {

    static public void main(String args[]) {
        try {
            String servhost = args.length <= 0 ? "localhost" : args[0];

            hello_cln _hello = new hello_cln(servhost, "tcp");
            System.out.println("Conectado a " + servhost);

            String msg = "hello world";
            for (int i = 0; i < 5; i++) {
                System.out.println("Enviando: " + msg + " " + i);
                String reply = _hello.sendHello(msg + " " + i);
                System.out.println("Retorno: " + reply);
            }
        } catch (rpc_err e) {
            System.out.println("rpc: " + e.toString());
        }
    }
}
