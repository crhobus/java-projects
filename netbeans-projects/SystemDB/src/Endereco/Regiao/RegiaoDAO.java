package Endereco.Regiao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Regiao;
import Principal.DBDAO;

public class RegiaoDAO extends DBDAO {

    private Connection con;

    public RegiaoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertRegiao(Regiao regiao) throws Exception {
        if (isRegiaoCadastrado(regiao.getCodRegiao())) {
            throw new Exception("Não é possível realizar esta operação");
        }
        int codRegiao = getCadastrado("SELECT CODREGIAO FROM TBREGIAO WHERE LOWER(REGIAO) = '" + regiao.getRegiao().toLowerCase() + "'",
                "Erro na leitura da região no sistema");// verifica se região está cadastrada
        if (codRegiao != -1) {
            insertRegiaoHasPais(codRegiao, regiao);
            return true;
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBREGIAO (CODREGIAO, REGIAO) VALUES (?, ?)");
            int proxCodRegiao = getProxCod("SELECT MAX(CODREGIAO) FROM TBREGIAO",
                    "Erro na leitura de regiões no sistema");// obtem próximo codigo da região
            stm.setInt(1, proxCodRegiao);
            stm.setString(2, regiao.getRegiao());
            stm.execute();// insere nova região
            insertRegiaoHasPais(proxCodRegiao, regiao);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a região no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private void insertRegiaoHasPais(int codRegiao, Regiao regiao) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBREGIAO_HAS_TBPAIS (COD_REGIAO_PAIS, CODREGIAO, CODPAIS) VALUES (?, ?, ?)");
            stm.setInt(1, regiao.getCodRegiao());// codigo que é gerado pelo método getProxCodRegiao na tela de cadastro
            stm.setInt(2, codRegiao);// cod região obtido através do select
            stm.setInt(3, regiao.getCodPais());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a região no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getRegiaoCadastrada(String regiao, int codPais) throws SQLException {
        return getCadastrado("SELECT R.CODREGIAO FROM TBREGIAO R "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON R.CODREGIAO = RP.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "WHERE LOWER(R.REGIAO) = '" + regiao.toLowerCase() + "' AND P.CODPAIS = " + codPais,
                "Erro na leitura da região no sistema");
    }

    public boolean isRegiaoCadastrado(int codRegiao) throws SQLException {
        return isCadastrado("SELECT COD_REGIAO_PAIS FROM TBREGIAO_HAS_TBPAIS WHERE COD_REGIAO_PAIS = "
                + codRegiao, "Erro na leitura da região no sistema");
    }

    public boolean isRegiaoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBREGIAO_HAS_TBPAIS",
                "Erro na leitura de regiões no sistema");
    }

    public int getProxCodRegiao() throws SQLException {
        return getProxCod("SELECT MAX(COD_REGIAO_PAIS) FROM TBREGIAO_HAS_TBPAIS",
                "Erro na leitura de regiões no sistema");
    }

    public List<Regiao> listRegiaoes() throws SQLException {
        return getLista("SELECT RP.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBREGIAO_HAS_TBPAIS RP "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "ORDER BY RP.COD_REGIAO_PAIS");
    }

    private List<Regiao> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Regiao> lista = new ArrayList<Regiao>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getRegiaoAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de regiões no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Regiao getRegiaoAux(ResultSet rs) throws SQLException {
        try {
            Regiao regiao = new Regiao();
            regiao.setCodRegiao(rs.getInt(1));
            regiao.setRegiao(rs.getString(2));
            regiao.setCodPais(rs.getInt(3));
            regiao.setPais(rs.getString(4));
            return regiao;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da região no sistema");
        }
    }

    public List<Regiao> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT RP.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBREGIAO_HAS_TBPAIS RP "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE RP.COD_REGIAO_PAIS = " + n);
            case 1:
                return getLista("SELECT RP.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBREGIAO_HAS_TBPAIS RP "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(R.REGIAO) like '%" + s.toLowerCase() + "%' ORDER BY RP.COD_REGIAO_PAIS");
            default:
                return getLista("SELECT RP.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBREGIAO_HAS_TBPAIS RP "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(P.PAIS) like '%" + s.toLowerCase() + "%' ORDER BY RP.COD_REGIAO_PAIS");
        }
    }
}
