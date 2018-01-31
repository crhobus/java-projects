package DAOFactory;

import NovaOSDAO.ItemOSDAO;
import NovaOSDAO.ItemOSFirebird;
import NovaOSDAO.NovaOSDAO;
import NovaOSDAO.NovaOSFirebird;
import VeiculosDAO.VeiculoFirebird;
import VeiculosDAO.VeiculosDAO;
import ClientesDAO.ClienteFirebird;
import ClientesDAO.ClientesDAO;
import FuncionarioDAO.FuncionarioDAO;
import FuncionarioDAO.FuncionarioFirebird;

public class FirebirdFactory extends DAOFactory {

    @Override
    public ClientesDAO createClientesDAO() {
        return new ClienteFirebird();
    }

    @Override
    public VeiculosDAO createVeiculosDAO() {
        return new VeiculoFirebird();
    }

    @Override
    public FuncionarioDAO createFuncionarioDAO() {
        return new FuncionarioFirebird();
    }

    @Override
    public NovaOSDAO createNovaOSDAO() {
        return new NovaOSFirebird();
    }

    @Override
    public ItemOSDAO createItemOSDAO() {
        return new ItemOSFirebird();
    }
}
