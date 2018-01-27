package Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Endereco;

public class EnderecoControl {

    private List<Endereco> listaEndereco;
    private EnderecoDAO enderecoDAO;

    public EnderecoControl(Connection con) {
        enderecoDAO = new EnderecoDAO(con);
    }

    public boolean insertEndereco(Endereco endereco) throws SQLException {
        return enderecoDAO.insertEndereco(endereco);
    }

    public int getEnderecoCadastrado(String endereco, int codBairro) throws SQLException {
        return enderecoDAO.getEnderecoCadastrado(endereco, codBairro);
    }

    public boolean isEnderecoVazio() throws SQLException {
        return enderecoDAO.isEnderecoVazio();
    }

    public int getProxCodEndereco() throws SQLException {
        return enderecoDAO.getProxCodEndereco();
    }

    public int getQtdadeEnderecoCadas() {
        return listaEndereco.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Endereco endereco = listaEndereco.get(linha);
        switch (coluna) {
            case 0:
                return endereco.getCodEndereco();
            case 1:
                return endereco.getEndereco();
            case 2:
                return endereco.getBairro();
            case 3:
                return endereco.getCep();
            case 4:
                return endereco.getCidade();
            case 5:
                return endereco.getEstado();
            case 6:
                return endereco.getRegiao();
            default:
                return endereco.getPais();
        }
    }

    public void limparLista() {
        listaEndereco.clear();
    }

    public void listarEnderecos() throws SQLException {
        listaEndereco = enderecoDAO.listEnderecos();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaEndereco = enderecoDAO.getLista(coluna, s, n);
    }

    public Endereco getListaPosicao(int posicao) {
        return listaEndereco.get(posicao);
    }
}
