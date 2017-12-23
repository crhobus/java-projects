package Control.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DBOracle.ClienteDAO;
import Model.Cliente;

public class ClienteControl {

    private List<Cliente> listaClie;
    private ClienteDAO clienteDAO;

    public ClienteControl(Connection con) {
        clienteDAO = new ClienteDAO(con);
    }

    public boolean insertClie(Cliente cliente) throws SQLException {
        return clienteDAO.insertClie(cliente);
    }

    public boolean updateCliente(Cliente cliente) throws SQLException {
        return clienteDAO.updateCliente(cliente);
    }

    public boolean isClieCadastrado(int codClie) throws SQLException {
        return clienteDAO.isClieCadastrado(codClie);
    }

    public int getClieCadastrado(String cpfCnpj, int codClie) throws SQLException {
        return clienteDAO.getClieCadastrado(cpfCnpj, codClie);
    }

    public int getClieCadastrado(String cpfCnpj) throws SQLException {
        return clienteDAO.getClieCadastrado(cpfCnpj);
    }

    public boolean isClieVazio() throws SQLException {
        return clienteDAO.isClieVazio();
    }

    public int getProxCodClie() throws SQLException {
        return clienteDAO.getProxCodClie();
    }

    public boolean deleteClie(int codClie) throws SQLException {
        return clienteDAO.deleteClie(codClie);
    }

    public int getQtdadeClieCadas() {
        return listaClie.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Cliente clie = listaClie.get(linha);
        switch (coluna) {
            case 0:
                return clie.getCodigo();
            case 1:
                return clie.getNome();
            case 2:
                return clie.getCpf();
            case 3:
                return clie.getRg();
            case 4:
                return clie.getEndereco().getEndereco();
            case 5:
                return clie.getEndereco().getBairro().getBairro();
            case 6:
                return clie.getNumero();
            case 7:
                return clie.getEndereco().getBairro().getCep().getCep();
            case 8:
                return clie.getEndereco().getBairro().getCep().getCidade().getCidade();
            case 9:
                return clie.getEndereco().getBairro().getCep().getCidade().getEstado().getEstado();
            default:
                return clie.getFone();
        }
    }

    public void limparLista() {
        listaClie.clear();
    }

    public void listarClie() throws SQLException {
        listaClie = clienteDAO.listClie();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaClie = clienteDAO.getLista(coluna, s, n);
    }

    public Cliente getListaPosicao(int posicao) {
        return listaClie.get(posicao);
    }
}
