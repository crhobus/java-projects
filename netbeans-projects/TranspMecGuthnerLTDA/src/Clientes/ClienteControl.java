package Clientes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Cliente;

public class ClienteControl {

    private List<Cliente> listaCliente;
    private DAOFactory daoFactory;
    private Connection con;

    public ClienteControl(DAOFactory daoFactory, Connection con) {
        this.daoFactory = daoFactory;
        this.con = con;
    }

    public boolean setCliente(Cliente cliente) throws Exception {
        if (daoFactory.createClientesDAO().setCliente(cliente, con)) {
            return true;
        }
        return false;
    }

    public Cliente getCliente(String cpfCNPJ) throws Exception {
        return daoFactory.createClientesDAO().getCliente(cpfCNPJ, con);
    }

    public boolean removeCliente(String cpfCNPJ) throws Exception {
        if (daoFactory.createClientesDAO().removeCliente(cpfCNPJ, con)) {
            return true;
        }
        return false;
    }

    public boolean isClieVazio() throws Exception {
        return daoFactory.createClientesDAO().isClieVazio(con);
    }

    public int getProxCodClie() throws Exception {
        return daoFactory.createClientesDAO().getProxCodClie(con);
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
                return cliente.getTipoPessoa();
            case 3:
                return cliente.getCpfCNPJ();
            case 4:
                return cliente.getRg();
            case 5:
                return cliente.getIe();
            case 6:
                return cliente.getEndereco();
            case 7:
                return cliente.getBairro();
            case 8:
                return cliente.getNumero();
            case 9:
                return cliente.getCidade();
            case 10:
                return cliente.getUf();
            case 11:
                return cliente.getFone();
            case 12:
                return cliente.getCelular1();
            default:
                return cliente.geteMail();
        }
    }

    public void listaTodos() throws Exception {
        listaCliente = daoFactory.createClientesDAO().listClientes(con);
    }

    public void limparLista() {
        listaCliente.clear();
    }

    public void getListaClieCod(int codigo) {
        for (Cliente cliente : listaCliente) {
            if (codigo == cliente.getCodigo()) {
                listaCliente.clear();
                listaCliente.add(cliente);
                return;
            }
        }
    }

    public void getListaClieNome(String nome) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (nome.equalsIgnoreCase(cliente.getNome())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieTPess(String tipopessoa) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (tipopessoa.equalsIgnoreCase(cliente.getTipoPessoa())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieCPFCNPJ(String cpfCnpj) {
        for (Cliente cliente : listaCliente) {
            if (cpfCnpj.equalsIgnoreCase(cliente.getCpfCNPJ())) {
                listaCliente.clear();
                listaCliente.add(cliente);
                return;
            }
        }
    }

    public void getListaClieRG(String rg) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (rg.equalsIgnoreCase(cliente.getRg())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieIE(String ie) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (ie.equalsIgnoreCase(cliente.getIe())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieEnde(String endereco) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (endereco.equalsIgnoreCase(cliente.getEndereco())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieBairro(String bairro) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (bairro.equalsIgnoreCase(cliente.getBairro())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieNum(int numero) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (numero == cliente.getNumero()) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieCid(String cidade) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (cidade.equalsIgnoreCase(cliente.getCidade())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieUF(String uf) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (uf.equalsIgnoreCase(cliente.getUf())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieFone(String fone) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (fone.equalsIgnoreCase(cliente.getFone())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieCel(String celular) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (celular.equalsIgnoreCase(cliente.getCelular1())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }

    public void getListaClieMail(String mail) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaCliente) {
            if (mail.equalsIgnoreCase(cliente.geteMail())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaCliente.clear();
            listaCliente = listaAux;
        }
    }
}
