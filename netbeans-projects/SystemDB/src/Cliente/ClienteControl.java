package Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Cliente;

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
                return clie.getCodClie();
            case 1:
                return clie.getNome();
            case 2:
                return clie.getTipoPessoa();
            case 3:
                return clie.getCpfCnpj();
            case 4:
                return clie.getRgIe();
            case 5:
                return clie.getEndereco();
            case 6:
                return clie.getBairro();
            case 7:
                return clie.getNumero();
            case 8:
                return clie.getCep();
            case 9:
                return clie.getCidade();
            case 10:
                return clie.getEstado();
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
