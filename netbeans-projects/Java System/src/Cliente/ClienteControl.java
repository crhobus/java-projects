package Cliente;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Cliente;

public class ClienteControl {

    private List<Cliente> listaCliente;
    private DAOFactory daoFactory;

    public ClienteControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setCliente(Cliente cliente) throws Exception {
        if (daoFactory.createClienteDAO().setCliente(cliente)) {
            return true;
        }
        return false;
    }

    public Cliente getCliente(String cpf) throws Exception {
        return daoFactory.createClienteDAO().getCliente(cpf);
    }

    public boolean removeCliente(String cpf) throws Exception {
        if (daoFactory.createClienteDAO().removeCliente(cpf)) {
            return true;
        }
        return false;
    }

    public boolean arqClieVazio() throws Exception {
        return daoFactory.createClienteDAO().arqClieVazio();
    }

    public int getProxCodClie() throws Exception {
        return daoFactory.createClienteDAO().getProxCodClie();
    }

    public int getQtdadeClieCadas() {
        return listaCliente.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Cliente cliente = listaCliente.get(linha);
        switch (coluna) {
            case 0:
                return cliente.getCodigo();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getRg();
            case 3:
                return cliente.getCpf();
            case 4:
                return cliente.getProfissao();
            case 5:
                return cliente.getEmpresa();
            case 6:
                return cliente.getFoneEmpresa();
            case 7:
                return cliente.getSexo();
            case 8:
                return cliente.getCep();
            case 9:
                return cliente.getEndereco();
            case 10:
                return cliente.getBairro();
            case 11:
                return cliente.getNumero();
            case 12:
                return cliente.getComplemento();
            case 13:
                return cliente.getCidade();
            case 14:
                return cliente.getEstado();
            case 15:
                return cliente.getFone();
            case 16:
                return cliente.getCelular1();
            case 17:
                return cliente.getEmail();
            default:
                return cliente.getDescricao();
        }
    }

    public void listaTodos() throws Exception {
        listaCliente = daoFactory.createClienteDAO().listCliente();
    }

    public List<Cliente> getLista() throws Exception {
        return daoFactory.createClienteDAO().listCliente();
    }
}
