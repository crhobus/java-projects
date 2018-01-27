package Endereco.CEP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cep;
import Principal.DBDAO;

public class CepDAO extends DBDAO {

    private Connection con;

    public CepDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertCep(Cep cep) throws Exception {
        if (isCepCadastrado(cep.getCodCep())) {
            throw new Exception("Não é possível realizar esta operação");
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCEP (CODCEP, COD_CIDADE_ESTADO, CEP) VALUES (?, ?, ?)");
            stm.setInt(1, cep.getCodCep());
            stm.setInt(2, cep.getCodCidade());
            stm.setString(3, cep.getCep());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o cep no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException e) {
            }
        }
    }

    public int getCepCadastrado(String cep) throws SQLException {
        return getCadastrado("SELECT CODCEP FROM TBCEP WHERE CEP = " + "'"
                + cep + "'", "Erro na leitura do cep no sistema");
    }

    public boolean isCepCadastrado(int codCep) throws SQLException {
        return isCadastrado("SELECT CODCEP FROM TBCEP WHERE CODCEP = " + codCep,
                "Erro na leitura do cep no sistema");
    }

    public boolean isCepVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCEP",
                "Erro na leitura dos cep no sistema");
    }

    public int getProxCodCep() throws SQLException {
        return getProxCod("SELECT MAX(CODCEP) FROM TBCEP",
                "Erro na leitura dos cep no sistema");
    }

    public List<Cep> listCeps() throws SQLException {
        return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "ORDER BY CEP.CODCEP");
    }

    private List<Cep> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Cep> lista = new ArrayList<Cep>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getCepAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura dos cep no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Cep getCepAux(ResultSet rs) throws SQLException {
        try {
            Cep cep = new Cep();
            cep.setCodCep(rs.getInt(1));
            cep.setCep(rs.getString(2));
            cep.setCodCidade(rs.getInt(3));
            cep.setCidade(rs.getString(4));
            cep.setCodEstado(rs.getInt(5));
            cep.setEstado(rs.getString(6));
            cep.setCodRegiao(rs.getInt(7));
            cep.setRegiao(rs.getString(8));
            cep.setCodPais(rs.getInt(9));
            cep.setPais(rs.getString(10));
            return cep;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do cep no sistema");
        }
    }

    public List<Cep> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                        + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE CEP.CODCEP = " + n);
            case 1:
                return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                        + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE CEP.CEP = '" + s + "' ORDER BY CEP.CODCEP");
            case 2:
                return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                        + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%' ORDER BY CEP.CODCEP");
            case 3:
                return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                        + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%' ORDER BY CEP.CODCEP");
            case 4:
                return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                        + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(R.REGIAO) like '%" + s.toLowerCase() + "%' ORDER BY CEP.CODCEP");
            default:
                return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, "
                        + "E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCEP CEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(P.PAIS) like '%" + s.toLowerCase() + "%' ORDER BY CEP.CODCEP");
        }
    }
}
