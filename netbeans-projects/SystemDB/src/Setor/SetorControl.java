package Setor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Setor;

public class SetorControl {

    private List<Setor> listaSetor;
    private SetorDAO setorDAO;

    public SetorControl(Connection con) {
        setorDAO = new SetorDAO(con);
    }

    public boolean insertSetor(Setor setor) throws SQLException {
        return setorDAO.insertSetor(setor);
    }

    public boolean isSetorCadastrado(int codSetor) throws SQLException {
        return setorDAO.isSetorCadastrado(codSetor);
    }

    public int getSetorCadastrado(String setor) throws SQLException {
        return setorDAO.getSetorCadastrado(setor);
    }

    public boolean isSetorVazio() throws SQLException {
        return setorDAO.isSetorVazio();
    }

    public int getProxCodSetor() throws SQLException {
        return setorDAO.getProxCodSetor();
    }

    public boolean deleteSetor(int codSetor) throws SQLException {
        return setorDAO.deleteSetor(codSetor);
    }

    public int getQtdadeSetorCadas() {
        return listaSetor.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Setor setor = listaSetor.get(linha);
        switch (coluna) {
            case 0:
                return setor.getCodSetor();
            default:
                return setor.getSetor();
        }
    }

    public void limparLista() {
        listaSetor.clear();
    }

    public void listarSetores() throws SQLException {
        listaSetor = setorDAO.listSetor();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaSetor = setorDAO.getLista(coluna, s, n);
    }

    public Setor getListaPosicao(int posicao) {
        return listaSetor.get(posicao);
    }
}
