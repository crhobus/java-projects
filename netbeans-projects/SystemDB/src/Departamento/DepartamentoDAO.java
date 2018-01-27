package Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Departamento;
import Principal.DBDAO;

public class DepartamentoDAO extends DBDAO {

    private Connection con;

    public DepartamentoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertDep(Departamento departamento) throws SQLException {
        if (isDepCadastrado(departamento.getCodDepartamento())) {
            return updateDep(departamento);
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBDEPARTAMENTO (CODDEPARTAMENTO, DEPARTAMENTO) VALUES (?, ?)");
            stm.setInt(1, departamento.getCodDepartamento());
            stm.setBytes(2, departamento.getDepartamento());

            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o departamento no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateDep(Departamento departamento) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBDEPARTAMENTO SET DEPARTAMENTO = ? WHERE CODDEPARTAMENTO = ?");
            stm.setBytes(1, departamento.getDepartamento());
            stm.setInt(2, departamento.getCodDepartamento());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do departamento no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isDepCadastrado(int codDepartamento) throws SQLException {
        return isCadastrado("SELECT CODDEPARTAMENTO FROM TBDEPARTAMENTO WHERE CODDEPARTAMENTO = "
                + codDepartamento, "Erro na leitura do departamento no sistema");
    }

    public int getDepCadastrado(String departamento) throws SQLException {
        return getCadastrado("SELECT CODDEPARTAMENTO FROM TBDEPARTAMENTO WHERE LOWER(DEPARTAMENTO) = '"
                + departamento.toLowerCase() + "'", "Erro na leitura do dertamento no sistema");
    }

    public boolean isDepVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBDEPARTAMENTO",
                "Erro na leitura de departamentos no sistema");
    }

    public int getProxCodDep() throws SQLException {
        return getProxCod("SELECT MAX(CODDEPARTAMENTO) FROM TBDEPARTAMENTO",
                "Erro na leitura de departamentos no sistema");
    }

    public boolean deleteDep(int codDepartamento) throws SQLException {
        return delete("DELETE FROM TBDEPARTAMENTO WHERE CODDEPARTAMENTO = "
                + codDepartamento, "Não foi possível excluir o departamento do sistema");
    }

    public List<Departamento> listDep() throws SQLException {
        return getLista("SELECT * FROM TBDEPARTAMENTO ORDER BY CODDEPARTAMENTO");
    }

    private List<Departamento> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Departamento> lista = new ArrayList<Departamento>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getDepAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de departamentos no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Departamento getDepAux(ResultSet rs) throws SQLException {
        try {
            Departamento departamento = new Departamento();
            departamento.setCodDepartamento(rs.getInt("CODDEPARTAMENTO"));
            departamento.setDepartamento(rs.getBytes("DEPARTAMENTO"));
            return departamento;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do departamento no sistema");
        }
    }

    public List<Departamento> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT * FROM TBDEPARTAMENTO WHERE CODDEPARTAMENTO = " + n);
            default:
                return getLista("SELECT * FROM TBDEPARTAMENTO WHERE LOWER(DEPARTAMENTO) like '%"
                        + s.toLowerCase() + "%'");
        }
    }
}
