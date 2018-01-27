package Departamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Departamento;
import Seguranca.Seguranca;

public class DepartamentoControl {

    private List<Departamento> listaDep;
    private DepartamentoDAO departamentoDAO;
    private Seguranca seguranca;

    public DepartamentoControl(Connection con, Seguranca seguranca) {
        this.seguranca = seguranca;
        departamentoDAO = new DepartamentoDAO(con);
    }

    public boolean insertDep(Departamento departamento) throws SQLException {
        return departamentoDAO.insertDep(departamento);
    }

    public boolean isDepCadastrado(int codDepartamento) throws SQLException {
        return departamentoDAO.isDepCadastrado(codDepartamento);
    }

    public int getDepCadastrado(String departamento) throws SQLException {
        return departamentoDAO.getDepCadastrado(departamento);
    }

    public boolean isDepVazio() throws SQLException {
        return departamentoDAO.isDepVazio();
    }

    public int getProxCodDep() throws SQLException {
        return departamentoDAO.getProxCodDep();
    }

    public boolean deleteDep(int codDepartamento) throws SQLException {
        return departamentoDAO.deleteDep(codDepartamento);
    }

    public int getQtdadeDepCadas() {
        return listaDep.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Departamento departamento = listaDep.get(linha);
        switch (coluna) {
            case 0:
                return departamento.getCodDepartamento();
            default:
                try {
                    return new String(seguranca.decriptarAssimetricamente(departamento.getDepartamento(), false));
                } catch (Exception ex) {
                    return "";
                }
        }
    }

    public void limparLista() {
        listaDep.clear();
    }

    public void listarDepartamentos() throws SQLException {
        listaDep = departamentoDAO.listDep();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaDep = departamentoDAO.getLista(coluna, s, n);
    }

    public Departamento getListaPosicao(int posicao) {
        return listaDep.get(posicao);
    }
}
