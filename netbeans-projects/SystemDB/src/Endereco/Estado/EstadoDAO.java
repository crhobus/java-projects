package Endereco.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Estado;
import Principal.DBDAO;

public class EstadoDAO extends DBDAO {

    private Connection con;

    public EstadoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertEstado(Estado estado) throws Exception {
        if (isEstadoCadastrado(estado.getCodEstado())) {
            throw new Exception("Não é possível realizar esta operação");
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBESTADO (CODESTADO, COD_REGIAO_PAIS, ESTADO) VALUES (?, ?, ?)");
            stm.setInt(1, estado.getCodEstado());
            stm.setInt(2, estado.getCodRegiao());
            stm.setString(3, estado.getEstado());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o estado no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getEstadoCadastrado(String estado) throws SQLException {
        return getCadastrado("SELECT CODESTADO FROM TBESTADO WHERE LOWER(ESTADO) = " + "'" + estado.toLowerCase() + "'",
                "Erro na leitura do estado no sistema");
    }

    public boolean isEstadoCadastrado(int codEstado) throws SQLException {
        return isCadastrado("SELECT CODESTADO FROM TBESTADO WHERE CODESTADO = "
                + codEstado, "Erro na leitura do estado no sistema");
    }

    public boolean isEstadoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBESTADO",
                "Erro na leitura de estados no sistema");
    }

    public int getProxCodEstado() throws SQLException {
        return getProxCod("SELECT MAX(CODESTADO) FROM TBESTADO",
                "Erro na leitura de estados no sistema");
    }

    public List<Estado> listEstados() throws SQLException {
        return getLista("SELECT E.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBESTADO E "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "ORDER BY E.CODESTADO");
    }

    private List<Estado> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Estado> lista = new ArrayList<Estado>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getEstadoAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de estados no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Estado getEstadoAux(ResultSet rs) throws SQLException {
        try {
            Estado estado = new Estado();
            estado.setCodEstado(rs.getInt(1));
            estado.setEstado(rs.getString(2));
            estado.setCodRegiao(rs.getInt(3));
            estado.setRegiao(rs.getString(4));
            estado.setCodPais(rs.getInt(5));
            estado.setPais(rs.getString(6));
            return estado;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do estado no sistema");
        }
    }

    public List<Estado> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT E.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBESTADO E "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE E.CODESTADO = " + n);
            case 1:
                return getLista("SELECT E.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBESTADO E "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%' ORDER BY E.CODESTADO");
            case 2:
                return getLista("SELECT E.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBESTADO E "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(R.REGIAO) like '%" + s.toLowerCase() + "%' ORDER BY E.CODESTADO");
            default:
                return getLista("SELECT E.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBESTADO E "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(P.PAIS) like '%" + s.toLowerCase() + "%' ORDER BY E.CODESTADO");
        }
    }
}
