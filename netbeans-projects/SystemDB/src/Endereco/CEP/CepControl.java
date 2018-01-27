package Endereco.CEP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Cep;

public class CepControl {

    private List<Cep> listaCep;
    private CepDAO cepDAO;

    public CepControl(Connection con) {
        cepDAO = new CepDAO(con);
    }

    public boolean insertCep(Cep cep) throws Exception {
        return cepDAO.insertCep(cep);
    }

    public int getCepCadastrado(String cep) throws SQLException {
        return cepDAO.getCepCadastrado(cep);
    }

    public boolean isCepCadastrado(int codCep) throws SQLException {
        return cepDAO.isCepCadastrado(codCep);
    }

    public boolean isCepVazio() throws SQLException {
        return cepDAO.isCepVazio();
    }

    public int getProxCodCep() throws SQLException {
        return cepDAO.getProxCodCep();
    }

    public int getQtdadeCepCadas() {
        return listaCep.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Cep cep = listaCep.get(linha);
        switch (coluna) {
            case 0:
                return cep.getCodCep();
            case 1:
                return cep.getCep();
            case 2:
                return cep.getCidade();
            case 3:
                return cep.getEstado();
            case 4:
                return cep.getRegiao();
            default:
                return cep.getPais();
        }
    }

    public void limparLista() {
        listaCep.clear();
    }

    public void listarCeps() throws SQLException {
        listaCep = cepDAO.listCeps();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaCep = cepDAO.getLista(coluna, s, n);
    }

    public Cep getListaPosicao(int posicao) {
        return listaCep.get(posicao);
    }
}
