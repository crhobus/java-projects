package Endereco.Cidade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Cidade;

public class CidadeControl {

    private List<Cidade> listaCidade;
    private CidadeDAO cidadeDAO;

    public CidadeControl(Connection con) {
        cidadeDAO = new CidadeDAO(con);
    }

    public boolean insertCidade(Cidade cidade) throws Exception {
        return cidadeDAO.insertCidade(cidade);
    }

    public int getCidadeCadastrada(String cidade, int codEstado) throws SQLException {
        return cidadeDAO.getCidadeCadastrada(cidade, codEstado);
    }

    public boolean isCidadeCadastrada(int codCidade) throws SQLException {
        return cidadeDAO.isCidadeCadastrada(codCidade);
    }

    public boolean isCidadeVazio() throws SQLException {
        return cidadeDAO.isCidadeVazio();
    }

    public int getProxCodCidade() throws SQLException {
        return cidadeDAO.getProxCodCidade();
    }

    public int getQtdadeCidadeCadas() {
        return listaCidade.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Cidade cidade = listaCidade.get(linha);
        switch (coluna) {
            case 0:
                return cidade.getCodCidade();
            case 1:
                return cidade.getCidade();
            case 2:
                return cidade.getEstado();
            case 3:
                return cidade.getRegiao();
            default:
                return cidade.getPais();
        }
    }

    public void limparLista() {
        listaCidade.clear();
    }

    public void listarCidades() throws SQLException {
        listaCidade = cidadeDAO.listCidades();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaCidade = cidadeDAO.getLista(coluna, s, n);
    }

    public Cidade getListaPosicao(int posicao) {
        return listaCidade.get(posicao);
    }
}
