package Endereco.Regiao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Regiao;

public class RegiaoControl {

    private List<Regiao> listaRegiao;
    private RegiaoDAO regiaoDAO;

    public RegiaoControl(Connection con) {
        regiaoDAO = new RegiaoDAO(con);
    }

    public boolean insertRegiao(Regiao regiao) throws Exception {
        return regiaoDAO.insertRegiao(regiao);
    }

    public int getRegiaoCadastrada(String regiao, int codPais) throws SQLException {
        return regiaoDAO.getRegiaoCadastrada(regiao, codPais);
    }

    public boolean isRegiaoCadastrado(int codRegiao) throws SQLException {
        return regiaoDAO.isRegiaoCadastrado(codRegiao);
    }

    public boolean isRegiaoVazio() throws SQLException {
        return regiaoDAO.isRegiaoVazio();
    }

    public int getProxCodRegiao() throws SQLException {
        return regiaoDAO.getProxCodRegiao();
    }

    public int getQtdadeRegiaoCadas() {
        return listaRegiao.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Regiao regiao = listaRegiao.get(linha);
        switch (coluna) {
            case 0:
                return regiao.getCodRegiao();
            case 1:
                return regiao.getRegiao();
            default:
                return regiao.getPais();
        }
    }

    public void limparLista() {
        listaRegiao.clear();
    }

    public void listarRegioes() throws SQLException {
        listaRegiao = regiaoDAO.listRegiaoes();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaRegiao = regiaoDAO.getLista(coluna, s, n);
    }

    public Regiao getListaPosicao(int posicao) {
        return listaRegiao.get(posicao);
    }
}
