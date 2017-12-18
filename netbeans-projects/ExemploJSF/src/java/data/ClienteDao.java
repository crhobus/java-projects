package data;

import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private List<Cliente> clientes = new ArrayList<Cliente>();

    public ClienteDao() {
        Cliente cli = new Cliente();
        cli.setNome("Caio");
        cli.setTelefone("444");
        cli.setCelular("999");
        clientes.add(cli);
        cli = new Cliente();
        cli.setNome("Renan");
        cli.setTelefone("333");
        cli.setCelular("888");
        clientes.add(cli);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
