package Endereco.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Pais;
import Principal.DBDAO;

public class PaisDAO extends DBDAO {

    private Connection con;

    public PaisDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertPais(Pais pais) throws Exception {
        if (isPaisCadastrado(pais.getCodPais())) {
            throw new Exception("Não é possível realizar esta operação");
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPAIS (CODPAIS, PAIS) VALUES (?, ?)");
            stm.setInt(1, pais.getCodPais());
            stm.setString(2, pais.getPais());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o país no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getPaisCadastrado(String pais) throws SQLException {
        return getCadastrado("SELECT CODPAIS FROM TBPAIS WHERE LOWER(PAIS) = '"
                + pais.toLowerCase() + "'", "Erro na leitura do país no sistema");
    }

    public boolean isPaisCadastrado(int codPais) throws SQLException {
        return isCadastrado("SELECT CODPAIS FROM TBPAIS WHERE CODPAIS = "
                + codPais, "Erro na leitura do país no sistema");
    }

    public boolean isPaisVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBPAIS",
                "Erro na leitura de países no sistema");
    }

    public int getProxCodPais() throws SQLException {
        return getProxCod("SELECT MAX(CODPAIS) FROM TBPAIS",
                "Erro na leitura de países no sistema");
    }

    public List<Pais> listPaises() throws SQLException {
        return getLista("SELECT * FROM TBPAIS ORDER BY CODPAIS");
    }

    private List<Pais> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Pais> lista = new ArrayList<Pais>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getPaisAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de países no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Pais getPaisAux(ResultSet rs) throws SQLException {
        try {
            Pais pais = new Pais();
            pais.setCodPais(rs.getInt("CODPAIS"));
            pais.setPais(rs.getString("PAIS"));
            return pais;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do país no sistema");
        }
    }

    public List<Pais> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT * FROM TBPAIS WHERE CODPAIS = " + n);
            default:
                return getLista("SELECT * FROM TBPAIS WHERE LOWER(PAIS) like '%"
                        + s.toLowerCase() + "%'");
        }
    }
}
