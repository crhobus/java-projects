package Endereco.Bairro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Bairro;

public class BairroControl {

    private List<Bairro> listaBairro;
    private BairroDAO bairroDAO;

    public BairroControl(Connection con) {
        bairroDAO = new BairroDAO(con);
    }

    public boolean insertBairro(Bairro bairro) throws Exception {
        return bairroDAO.insertBairro(bairro);
    }

    public int getBairroCadastrado(String bairro, int codCep) throws SQLException {
        return bairroDAO.getBairroCadastrado(bairro, codCep);
    }

    public boolean isBairroCadastrado(int codBairro) throws SQLException {
        return bairroDAO.isBairroCadastrado(codBairro);
    }

    public boolean isBairroVazio() throws SQLException {
        return bairroDAO.isBairroVazio();
    }

    public int getProxCodBairro() throws SQLException {
        return bairroDAO.getProxCodBairro();
    }

    public int getQtdadeBairroCadas() {
        return listaBairro.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Bairro bairro = listaBairro.get(linha);
        switch (coluna) {
            case 0:
                return bairro.getCodBairro();
            case 1:
                return bairro.getBairro();
            case 2:
                return bairro.getCep();
            case 3:
                return bairro.getCidade();
            case 4:
                return bairro.getEstado();
            case 5:
                return bairro.getRegiao();
            default:
                return bairro.getPais();
        }
    }

    public void limparLista() {
        listaBairro.clear();
    }

    public void listarBairros() throws SQLException {
        listaBairro = bairroDAO.listBairros();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaBairro = bairroDAO.getLista(coluna, s, n);
    }

    public Bairro getListaPosicao(int posicao) {
        return listaBairro.get(posicao);
    }
}
