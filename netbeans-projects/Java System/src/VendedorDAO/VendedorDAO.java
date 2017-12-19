package VendedorDAO;

import java.util.List;

import Modelo.Vendedor;

public interface VendedorDAO {

    public boolean setVendedor(Vendedor vendedor) throws Exception;

    public Vendedor getVendedor(String cpf) throws Exception;

    public boolean removeVendedor(String cpf) throws Exception;

    public List<Vendedor> listVendedor() throws Exception;

    public boolean arqVenVazio() throws Exception;

    public int getProxCodVen() throws Exception;
}
