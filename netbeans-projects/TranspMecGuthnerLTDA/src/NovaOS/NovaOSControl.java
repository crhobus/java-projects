package NovaOS;

import java.sql.Connection;
import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.OrdemServico;

public class NovaOSControl {

    private DAOFactory daoFactory;
    private Connection con;

    public NovaOSControl(DAOFactory daoFactory, Connection con) {
        this.daoFactory = daoFactory;
        this.con = con;
    }

    public boolean setOrdemServico(OrdemServico ordemServico) throws Exception {
        if (daoFactory.createNovaOSDAO().setOrdemServico(ordemServico, con)) {
            return true;
        }
        return false;
    }

    public boolean updateOrdemServico(OrdemServico ordemServico) throws Exception {
        if (daoFactory.createNovaOSDAO().updateOrdemServico(ordemServico, con)) {
            return true;
        }
        return false;
    }

    public OrdemServico getOrdemServico(int numero) throws Exception {
        return daoFactory.createNovaOSDAO().getOrdemServico(numero, con);
    }

    public boolean isOSVazio() throws Exception {
        return daoFactory.createNovaOSDAO().isOSVazio(con);
    }

    public int getProxCodOS() throws Exception {
        return daoFactory.createNovaOSDAO().getProxCodOS(con);
    }

    public List<OrdemServico> getLista() throws Exception {
        return daoFactory.createNovaOSDAO().listOrdemServicos(con);
    }
}
