package projeto.rpc.servidor;

import netbula.ORPC.rpc_err;

public class SistemaServidorRPC {

    public static void main(String[] args) {
        try {
            new ServidorRPC().run();
        } catch (rpc_err ex) {
            System.out.println("Erro na conexão com o servidor RPC");
        }
    }
}
