package ClienteDAO;

import java.util.List;

import Modelo.Cliente;

public interface ClienteDAO {

    public boolean setCliente(Cliente cliente) throws Exception;

    public Cliente getCliente(String cpf) throws Exception;

    public boolean removeCliente(String cpf) throws Exception;

    public List<Cliente> listCliente() throws Exception;

    public boolean arqClieVazio() throws Exception;

    public int getProxCodClie() throws Exception;
}
