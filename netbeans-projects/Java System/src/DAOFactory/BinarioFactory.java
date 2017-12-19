package DAOFactory;

import SetorDAO.SetorBinario;
import SetorDAO.SetorDAO;
import TransportadoraDAO.TransportadoraBinario;
import TransportadoraDAO.TransportadoraDAO;
import UsuarioDAO.UsuarioBinario;
import UsuarioDAO.UsuarioDAO;
import VendedorDAO.VendedorBinario;
import VendedorDAO.VendedorDAO;
import CidadeDAO.CidadeBinario;
import CidadeDAO.CidadeDAO;
import ClienteDAO.ClienteBinario;
import ClienteDAO.ClienteDAO;
import FornecedorDAO.FornecedorBinario;
import FornecedorDAO.FornecedorDAO;
import FuncionarioDAO.FuncionarioBinario;
import FuncionarioDAO.FuncionarioDAO;

public class BinarioFactory extends DAOFactory {

    @Override
    public ClienteDAO createClienteDAO() {
        return new ClienteBinario();
    }

    @Override
    public UsuarioDAO createUsuarioDAO() {
        return new UsuarioBinario();
    }

    @Override
    public CidadeDAO createCidadeDAO() {
        return new CidadeBinario();
    }

    @Override
    public SetorDAO createSetorDAO() {
        return new SetorBinario();
    }

    @Override
    public FuncionarioDAO createFuncionarioDAO() {
        return new FuncionarioBinario();
    }

    @Override
    public VendedorDAO createVendedorDAO() {
        return new VendedorBinario();
    }

    @Override
    public FornecedorDAO createFornecedorDAO() {
        return new FornecedorBinario();
    }

    @Override
    public TransportadoraDAO createTransportadoraDAO() {
        return new TransportadoraBinario();
    }

    @Override
    public String toString() {
        return "Binario";
    }
}
