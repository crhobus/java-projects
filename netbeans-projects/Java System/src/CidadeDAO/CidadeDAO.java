package CidadeDAO;

import java.util.List;

import Modelo.Cidade;

public interface CidadeDAO {

    public boolean setCidade(Cidade cidade) throws Exception;

    public Cidade getCidade(String cid) throws Exception;

    public boolean removeCid(String cid) throws Exception;

    public List<Cidade> listCidade() throws Exception;

    public boolean arqCidVazio() throws Exception;

    public int getProxCodCid() throws Exception;
}
