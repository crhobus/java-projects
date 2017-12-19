package DAOFactory;

import SetorDAO.SetorDAO;
import SetorDAO.SetorTexto;
import TransportadoraDAO.TransportadoraDAO;
import TransportadoraDAO.TransportadotaTexto;
import UsuarioDAO.UsuarioDAO;
import UsuarioDAO.UsuarioTexto;
import VendedorDAO.VendedorDAO;
import VendedorDAO.VendedorTexto;
import CidadeDAO.CidadeDAO;
import CidadeDAO.CidadeTexto;
import ClienteDAO.ClienteDAO;
import ClienteDAO.ClienteTexto;
import FornecedorDAO.FornecedorDAO;
import FornecedorDAO.FornecedorTexto;
import FuncionarioDAO.FuncionarioDAO;
import FuncionarioDAO.FuncionarioTexto;

public class TextoFactory extends DAOFactory {

    @Override
    public ClienteDAO createClienteDAO() {
        return new ClienteTexto();
    }

    @Override
    public UsuarioDAO createUsuarioDAO() {
        return new UsuarioTexto();
    }

    @Override
    public CidadeDAO createCidadeDAO() {
        return new CidadeTexto();
    }

    @Override
    public SetorDAO createSetorDAO() {
        return new SetorTexto();
    }

    @Override
    public FuncionarioDAO createFuncionarioDAO() {
        return new FuncionarioTexto();
    }

    @Override
    public VendedorDAO createVendedorDAO() {
        return new VendedorTexto();
    }

    @Override
    public FornecedorDAO createFornecedorDAO() {
        return new FornecedorTexto();
    }

    @Override
    public TransportadoraDAO createTransportadoraDAO() {
        return new TransportadotaTexto();
    }

    @Override
    public String toString() {
        return "Texto";
    }
}
