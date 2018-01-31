package DAOFactory;

import NovaOSDAO.ItemOSDAO;
import NovaOSDAO.NovaOSDAO;
import VeiculosDAO.VeiculosDAO;
import ClientesDAO.ClientesDAO;
import FuncionarioDAO.FuncionarioDAO;

public abstract class DAOFactory {

    public abstract ClientesDAO createClientesDAO();

    public abstract VeiculosDAO createVeiculosDAO();

    public abstract FuncionarioDAO createFuncionarioDAO();

    public abstract NovaOSDAO createNovaOSDAO();

    public abstract ItemOSDAO createItemOSDAO();

    public static DAOFactory getFactory(int persistenciaDados) {
        if (persistenciaDados == 1) {
            return new FirebirdFactory();
        }
        return null;
    }
}
