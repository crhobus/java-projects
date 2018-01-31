package NovaOSDAO;

import java.sql.Connection;
import java.util.List;

import Modelo.ItensOS;

public interface ItemOSDAO {

    public boolean setItensOS(ItensOS itensOS, Connection con) throws Exception;

    public List<ItensOS> listItensOSs(int codigoOrdemServico, Connection con) throws Exception;

    public int getProxCodItenOS(Connection con) throws Exception;
}
