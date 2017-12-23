package Control.Endereco.CEP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DBOracle.CEPDAO;
import Model.CEP;

public class CEPControl {

    private List<CEP> listaCep;
    private CEPDAO cepDAO;

    public CEPControl(Connection con) {
        cepDAO = new CEPDAO(con);
    }

    public boolean insertCep(CEP cep) throws Exception {
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
        CEP cep = listaCep.get(linha);
        switch (coluna) {
            case 0:
                return cep.getCodigo();
            case 1:
                return cep.getCep();
            case 2:
                return cep.getCidade().getCidade();
            default:
                return cep.getCidade().getEstado().getEstado();
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

    public CEP getListaPosicao(int posicao) {
        return listaCep.get(posicao);
    }
}
