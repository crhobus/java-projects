package Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Setor;
import Principal.DBDAO;

public class SetorDAO extends DBDAO {

    private Connection con;

    public SetorDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertSetor(Setor setor) throws SQLException {
        if (isSetorCadastrado(setor.getCodSetor())) {
            return updateSetor(setor);
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBSETOR (CODSETOR, SETOR, SETORASSINADODIGITAL) VALUES (?, ?, ?)");
            stm.setInt(1, setor.getCodSetor());
            stm.setString(2, setor.getSetor());
            stm.setBytes(3, setor.getSetorAssinadoDigital());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o setor no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateSetor(Setor setor) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBSETOR SET SETOR = ?, SETORASSINADODIGITAL = ? WHERE CODSETOR = ?");
            stm.setString(1, setor.getSetor());
            stm.setBytes(2, setor.getSetorAssinadoDigital());
            stm.setInt(3, setor.getCodSetor());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do setor no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isSetorCadastrado(int codSetor) throws SQLException {
        return isCadastrado("SELECT CODSETOR FROM TBSETOR WHERE CODSETOR = "
                + codSetor, "Erro na leitura do setor no sistema");
    }

    public int getSetorCadastrado(String setor) throws SQLException {
        return getCadastrado("SELECT CODSETOR FROM TBSETOR WHERE LOWER(SETOR) = '"
                + setor.toLowerCase() + "'", "Erro na leitura do setor no sistema");
    }

    public boolean isSetorVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBSETOR",
                "Erro na leitura de setores no sistema");
    }

    public int getProxCodSetor() throws SQLException {
        return getProxCod("SELECT MAX(CODSETOR) FROM TBSETOR",
                "Erro na leitura de setores no sistema");
    }

    public boolean deleteSetor(int codSetor) throws SQLException {
        return delete("DELETE FROM TBSETOR WHERE CODSETOR = " + codSetor,
                "Não foi possível excluir o setor do sistema");
    }

    public List<Setor> listSetor() throws SQLException {
        return getLista("SELECT * FROM TBSETOR ORDER BY CODSETOR");
    }

    private List<Setor> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Setor> lista = new ArrayList<Setor>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getSetorAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de setores no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Setor getSetorAux(ResultSet rs) throws SQLException {
        try {
            Setor setor = new Setor();
            setor.setCodSetor(rs.getInt("CODSETOR"));
            setor.setSetor(rs.getString("SETOR"));
            setor.setSetorAssinadoDigital(rs.getBytes("SETORASSINADODIGITAL"));
            return setor;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do setor no sistema");
        }
    }

    public List<Setor> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT * FROM TBSETOR WHERE CODSETOR = " + n);
            default:
                return getLista("SELECT * FROM TBSETOR WHERE LOWER(SETOR) like '%"
                        + s.toLowerCase() + "%'");
        }
    }
}
