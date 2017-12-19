package DAOFactory;

import SetorDAO.SetorDAO;
import TransportadoraDAO.TransportadoraDAO;
import UsuarioDAO.UsuarioDAO;
import VendedorDAO.VendedorDAO;
import CidadeDAO.CidadeDAO;
import ClienteDAO.ClienteDAO;
import FornecedorDAO.FornecedorDAO;
import FuncionarioDAO.FuncionarioDAO;

public abstract class DAOFactory {

    public abstract ClienteDAO createClienteDAO();

    public abstract UsuarioDAO createUsuarioDAO();

    public abstract CidadeDAO createCidadeDAO();

    public abstract SetorDAO createSetorDAO();

    public abstract FuncionarioDAO createFuncionarioDAO();

    public abstract VendedorDAO createVendedorDAO();

    public abstract FornecedorDAO createFornecedorDAO();

    public abstract TransportadoraDAO createTransportadoraDAO();
}
