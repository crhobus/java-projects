package Fornecedor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Fornecedor;

public class FornecedorControl {

    private List<Fornecedor> listaForn;
    private FornecedorDAO fornecedorDAO;

    public FornecedorControl(Connection con) {
        fornecedorDAO = new FornecedorDAO(con);
    }

    public boolean insertForn(Fornecedor fornecedor) throws SQLException {
        return fornecedorDAO.insertForn(fornecedor);
    }

    public boolean updateForn(Fornecedor fornecedor) throws SQLException {
        return fornecedorDAO.updateForn(fornecedor);
    }

    public boolean isFornCadastrado(int codForn) throws SQLException {
        return fornecedorDAO.isFornCadastrado(codForn);
    }

    public int getFornCadastrado(String cnpj, int codForn) throws SQLException {
        return fornecedorDAO.getFornCadastrado(cnpj, codForn);
    }

    public int getFornCadastrado(String cnpj) throws SQLException {
        return fornecedorDAO.getFornCadastrado(cnpj);
    }

    public boolean isFornVazio() throws SQLException {
        return fornecedorDAO.isFornVazio();
    }

    public int getProxCodForn() throws SQLException {
        return fornecedorDAO.getProxCodForn();
    }

    public boolean deleteForn(int codForn) throws SQLException {
        return fornecedorDAO.deleteForn(codForn);
    }

    public int getQtdadeFornCadas() {
        return listaForn.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Fornecedor forn = listaForn.get(linha);
        switch (coluna) {
            case 0:
                return forn.getCodForn();
            case 1:
                return forn.getNome();
            case 2:
                return forn.getCpfCnpj();
            case 3:
                return forn.getRgIe();
            case 4:
                return forn.getInscMunicipal();
            case 5:
                return forn.getTipoFornecedor();
            case 6:
                return forn.getEndereco();
            case 7:
                return forn.getBairro();
            case 8:
                return forn.getNumero();
            case 9:
                return forn.getCep();
            case 10:
                return forn.getCidade();
            case 11:
                return forn.getEstado();
            case 12:
                return forn.getEmail();
            default:
                return forn.getFone();
        }
    }

    public void limparLista() {
        listaForn.clear();
    }

    public void listarForn() throws SQLException {
        listaForn = fornecedorDAO.listForn();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaForn = fornecedorDAO.getLista(coluna, s, n);
    }

    public Fornecedor getListaPosicao(int posicao) {
        return listaForn.get(posicao);
    }
}
