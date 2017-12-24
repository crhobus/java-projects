package projeto.rmi.bradesco;

import java.rmi.Naming;

public class SistemaBradesco {

    public static void main(String[] args) {
        try {
            ServidorBradesco obj = new ServidorBradesco();
            Naming.rebind("//localhost/SistemaBancarioBradesco", obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
