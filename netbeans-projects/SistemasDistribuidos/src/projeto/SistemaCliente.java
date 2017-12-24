package projeto;

public class SistemaCliente {

    public static void main(String[] args) {
        String[] str = {"-ORBInitialHost", "localhost"};
        new Cliente("localhost", "localhost", str);
    }
}
