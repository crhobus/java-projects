package Endereco.Pais;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Pais;

public class PaisControl {

    private List<Pais> listaPais;
    private PaisDAO paisDAO;

    public PaisControl(Connection con) {
        paisDAO = new PaisDAO(con);
    }

    public boolean insertPais(Pais pais) throws Exception {
        return paisDAO.insertPais(pais);
    }

    public int getPaisCadastrado(String pais) throws SQLException {
        return paisDAO.getPaisCadastrado(pais);
    }

    public boolean isPaisCadastrado(int codPais) throws SQLException {
        return paisDAO.isPaisCadastrado(codPais);
    }

    public boolean isPaisVazio() throws SQLException {
        return paisDAO.isPaisVazio();
    }

    public int getProxCodPais() throws SQLException {
        return paisDAO.getProxCodPais();
    }

    public int getQtdadePaisCadas() {
        return listaPais.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Pais pais = listaPais.get(linha);
        switch (coluna) {
            case 0:
                return pais.getCodPais();
            default:
                return pais.getPais();
        }
    }

    public void limparLista() {
        listaPais.clear();
    }

    public void listarPaises() throws SQLException {
        listaPais = paisDAO.listPaises();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaPais = paisDAO.getLista(coluna, s, n);
    }

    public Pais getListaPosicao(int posicao) {
        return listaPais.get(posicao);
    }
}
