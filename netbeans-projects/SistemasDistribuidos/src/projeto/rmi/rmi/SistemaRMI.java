package projeto.rmi.rmi;

import java.rmi.Naming;

public class SistemaRMI {

    public static void main(String[] args) {
        try {
            ServidorRMI obj = new ServidorRMI("localhost", "localhost");
            Naming.rebind("//localhost/RMIInterface", obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
