package Endereco.Estado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Estado;

public class EstadoControl {

    private List<Estado> listaEstado;
    private EstadoDAO estadoDAO;

    public EstadoControl(Connection con) {
        estadoDAO = new EstadoDAO(con);
    }

    public boolean insertEstado(Estado estado) throws Exception {
        return estadoDAO.insertEstado(estado);
    }

    public int getEstadoCadastrado(String estado) throws SQLException {
        return estadoDAO.getEstadoCadastrado(estado);
    }

    public boolean isEstadoCadastrado(int codEstado) throws SQLException {
        return estadoDAO.isEstadoCadastrado(codEstado);
    }

    public boolean isEstadoVazio() throws SQLException {
        return estadoDAO.isEstadoVazio();
    }

    public int getProxCodEstado() throws SQLException {
        return estadoDAO.getProxCodEstado();
    }

    public int getQtdadeEstadoCadas() {
        return listaEstado.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Estado estado = listaEstado.get(linha);
        switch (coluna) {
            case 0:
                return estado.getCodEstado();
            case 1:
                return estado.getEstado();
            case 2:
                return estado.getRegiao();
            default:
                return estado.getPais();
        }
    }

    public void limparLista() {
        listaEstado.clear();
    }

    public void listarEstados() throws SQLException {
        listaEstado = estadoDAO.listEstados();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaEstado = estadoDAO.getLista(coluna, s, n);
    }

    public Estado getListaPosicao(int posicao) {
        return listaEstado.get(posicao);
    }
}
