package NovaOSDAO;

import java.sql.Connection;
import java.util.List;

import Modelo.OrdemServico;

public interface NovaOSDAO {

    public boolean setOrdemServico(OrdemServico ordemServico, Connection con) throws Exception;

    public OrdemServico getOrdemServico(int numero, Connection con) throws Exception;

    public List<OrdemServico> listOrdemServicos(Connection con) throws Exception;

    public boolean isOSVazio(Connection con) throws Exception;

    public int getProxCodOS(Connection con) throws Exception;

    public boolean updateOrdemServico(OrdemServico ordemServico, Connection con) throws Exception;
}
