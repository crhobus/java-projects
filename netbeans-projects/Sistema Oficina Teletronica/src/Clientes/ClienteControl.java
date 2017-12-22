package Clientes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;

public class ClienteControl {

    private List<Cliente> listaClientes;
    private ClienteDAO clienteDAO;

    public ClienteControl(Connection con) {
        clienteDAO = new ClienteDAO(con);
    }

    public boolean insertCliente(Cliente cliente) throws Exception {
        return clienteDAO.insertCliente(cliente);
    }

    public boolean updateCliente(Cliente cliente) throws Exception {
        return clienteDAO.updateCliente(cliente);
    }

    public Cliente selectCliente(String cpfCNPJ) throws Exception {
        return clienteDAO.selectCliente(cpfCNPJ);
    }

    public boolean deleteCliente(String cpfCNPJ) throws Exception {
        return clienteDAO.deleteCliente(cpfCNPJ);
    }

    public boolean isClieVazio() throws Exception {
        return clienteDAO.isClieVazio();
    }

    public int getProxCodClie() throws Exception {
        return clienteDAO.getProxCodClie();
    }

    public int getQtdadeClieCadas() {
        return listaClientes.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Cliente cliente = listaClientes.get(linha);
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

    public void listarClientes() throws Exception {
        listaClientes = clienteDAO.listClientes();
    }

    public void limparLista() {
        listaClientes.clear();
    }

    public Cliente getListaPosicao(int posicao) {
        return listaClientes.get(posicao);
    }

    public void getListaCod(int codigo) {
        for (Cliente cliente : listaClientes) {
            if (codigo == cliente.getCodigo()) {
                listaClientes.clear();
                listaClientes.add(cliente);
                return;
            }
        }
    }

    public void getListaNome(String nome) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (nome.equalsIgnoreCase(cliente.getNome())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaTPess(String tipopessoa) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (tipopessoa.equalsIgnoreCase(cliente.getTipoPessoa())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaCPFCNPJ(String cpfCnpj) {
        for (Cliente cliente : listaClientes) {
            if (cpfCnpj.equalsIgnoreCase(cliente.getCpfCNPJ())) {
                listaClientes.clear();
                listaClientes.add(cliente);
                return;
            }
        }
    }

    public void getListaRG(String rg) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (rg.equalsIgnoreCase(cliente.getRg())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaIE(String ie) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (ie.equalsIgnoreCase(cliente.getIe())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaEnde(String endereco) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (endereco.equalsIgnoreCase(cliente.getEndereco())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaBairro(String bairro) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (bairro.equalsIgnoreCase(cliente.getBairro())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaNum(int numero) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (numero == cliente.getNumero()) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaCid(String cidade) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (cidade.equalsIgnoreCase(cliente.getCidade())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaUF(String uf) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (uf.equalsIgnoreCase(cliente.getUf())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaFone(String fone) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (fone.equalsIgnoreCase(cliente.getFone())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaCel(String celular) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (celular.equalsIgnoreCase(cliente.getCelular1())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }

    public void getListaMail(String mail) {
        List<Cliente> listaAux = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if (mail.equalsIgnoreCase(cliente.geteMail())) {
                listaAux.add(cliente);
            }
        }
        if (!listaAux.isEmpty()) {
            listaClientes.clear();
            listaClientes = listaAux;
        }
    }
}
