package SetorDAO;

import java.util.List;

import Modelo.Setor;

public interface SetorDAO {

    public boolean setSetor(Setor setor) throws Exception;

    public Setor getSetor(String s) throws Exception;

    public boolean removeSetor(String setor) throws Exception;

    public List<Setor> listSetor() throws Exception;

    public boolean arqSetorVazio() throws Exception;

    public int getProxCodSetor() throws Exception;
}
