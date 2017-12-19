package FornecedorDAO;

import java.util.List;

import Modelo.Fornecedor;

public interface FornecedorDAO {

    public boolean setFornecedor(Fornecedor fornecedor) throws Exception;

    public Fornecedor getFornecedor(String nome) throws Exception;

    public boolean removeFornecedor(String nome) throws Exception;

    public List<Fornecedor> listFornecedor() throws Exception;

    public boolean arqFornVazio() throws Exception;

    public int getProxCodForn() throws Exception;
}
