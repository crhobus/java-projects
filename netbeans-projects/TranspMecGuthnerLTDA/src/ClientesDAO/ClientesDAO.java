package ClientesDAO;

import java.sql.Connection;
import java.util.List;

import Modelo.Cliente;

public interface ClientesDAO {

    public boolean setCliente(Cliente cliente, Connection con) throws Exception;

    public Cliente getCliente(String cpfCNPJ, Connection con) throws Exception;

    public boolean removeCliente(String cpfCNPJ, Connection con) throws Exception;

    public List<Cliente> listClientes(Connection con) throws Exception;

    public boolean isClieVazio(Connection con) throws Exception;

    public int getProxCodClie(Connection con) throws Exception;
}
