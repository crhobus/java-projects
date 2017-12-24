package projeto.corba.sistemaBancarioCorba;

import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import projeto.rmi.rmi.RMIInterface;
//import projeto.rmi.rmi.RMIInterface;

public class ServidorCorba {

    public ServidorCorba(String[] args) {
        try {
            // Cria e inicializa o ORB
            ORB orb = ORB.init(args, null);

            // Cria a implementação e registra no ORB
            SistemaBancarioImpl banco = new SistemaBancarioImpl(getConnection(), getRMIInterface("localhost"));

            // Ativa o POA
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Pega a referência do servidor
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(banco);
            InterfaceCorba href = InterfaceCorbaHelper.narrow(ref);

            // Obtém uma referência para o servidor de nomes
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Registra o servidor no servico de nomes
            String name = "SistemaBancario";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Servidor aguardando requisicoes ....");

            // Aguarda chamadas dos clientes
            orb.run();
        } catch (Exception e) {
            System.err.println("ERRO: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("Encerrando o Servidor.");

    }

    public RMIInterface getRMIInterface(String ipServidorRMI) {
        try {
            return (RMIInterface) Naming.lookup("//" + ipServidorRMI + "/RMIInterface");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível conectar-se com o servidor RMI");
        }
        return null;
    }

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "syscorba", "key50950");
        } catch (Exception ex) {
            System.out.println("Não foi possível conectar-se com o banco de dados");
        }
        return null;
    }
}
