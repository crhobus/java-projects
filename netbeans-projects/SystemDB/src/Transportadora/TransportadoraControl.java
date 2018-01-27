package Transportadora;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Transportadora;

public class TransportadoraControl {

    private List<Transportadora> listaTrans;
    private TransportadoraDAO transportadoraDAO;

    public TransportadoraControl(Connection con) {
        transportadoraDAO = new TransportadoraDAO(con);
    }

    public boolean insertTrans(Transportadora transportadora) throws SQLException {
        return transportadoraDAO.insertTrans(transportadora);
    }

    public boolean updateTrans(Transportadora transportadora) throws SQLException {
        return transportadoraDAO.updateTrans(transportadora);
    }

    public boolean isTransCadastrado(int codTrans) throws SQLException {
        return transportadoraDAO.isTransCadastrado(codTrans);
    }

    public int getTransCadastrado(String cnpj, int codTrans) throws SQLException {
        return transportadoraDAO.getTransCadastrado(cnpj, codTrans);
    }

    public int getTransCadastrado(String cnpj) throws SQLException {
        return transportadoraDAO.getTransCadastrado(cnpj);
    }

    public boolean isTransVazio() throws SQLException {
        return transportadoraDAO.isTransVazio();
    }

    public int getProxCodTrans() throws SQLException {
        return transportadoraDAO.getProxCodTrans();
    }

    public boolean deleteTrans(int codTrans) throws SQLException {
        return transportadoraDAO.deleteTrans(codTrans);
    }

    public int getQtdadeTransCadas() {
        return listaTrans.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Transportadora trans = listaTrans.get(linha);
        switch (coluna) {
            case 0:
                return trans.getCodTrans();
            case 1:
                return trans.getNome();
            case 2:
                return trans.getCpfCnpj();
            case 3:
                return trans.getRgIe();
            case 4:
                return trans.getInscMunicipal();
            case 5:
                return trans.getEndereco();
            case 6:
                return trans.getBairro();
            case 7:
                return trans.getNumero();
            case 8:
                return trans.getCep();
            case 9:
                return trans.getCidade();
            case 10:
                return trans.getEstado();
            case 11:
                return trans.getEmail();
            case 12:
                return trans.getFone();
            default:
                return trans.getFrete();
        }
    }

    public void limparLista() {
        listaTrans.clear();
    }

    public void listarTrans() throws SQLException {
        listaTrans = transportadoraDAO.listTrans();
    }

    public void getLista(final int coluna, final String s, final int n, final double d) throws SQLException {
        listaTrans = transportadoraDAO.getLista(coluna, s, n, d);
    }

    public Transportadora getListaPosicao(int posicao) {
        return listaTrans.get(posicao);
    }
}
