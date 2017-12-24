/** HelloClient.java **/
package exemplo.rmi;

import java.rmi.Naming;

public class HelloClient {

    public static void main(String[] args) {
        try {
            //HelloWorld obj = (HelloWorld) Naming.lookup("//" + args[0] + "/HelloWorld");
            //HelloWorld obj = (HelloWorld) Naming.lookup("//201.54.202.25/HelloWorld");
            HelloWorld obj = (HelloWorld) Naming.lookup("//localhost/HelloWorld");
            System.out.println("Mensagem do Servidor: " + obj.hello());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
