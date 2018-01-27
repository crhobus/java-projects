package Endereco.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cidade;
import Principal.DBDAO;

public class CidadeDAO extends DBDAO {

    private Connection con;

    public CidadeDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertCidade(Cidade cidade) throws Exception {
        if (isCidadeCadastrada(cidade.getCodCidade())) {
            throw new Exception("Não é possível realizar esta operação");
        }
        int codCidade = getCadastrado("SELECT CODCIDADE FROM TBCIDADE WHERE LOWER(CIDADE) = "
                + "'" + cidade.getCidade().toLowerCase() + "'",
                "Erro na leitura da cidade no sistema");// verifica se cidade está cadastrada
        if (codCidade != -1) {
            insertCidadeHasEstado(codCidade, cidade);
            return true;
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCIDADE (CODCIDADE, CIDADE) VALUES (?, ?)");
            int proxCodCidade = getProxCod("SELECT MAX(CODCIDADE) FROM TBCIDADE",
                    "Erro na leitura de cidades no sistema");// obtem próximo codigo da cidade
            stm.setInt(1, proxCodCidade);
            stm.setString(2, cidade.getCidade());
            stm.execute();// insere nova cidade
            insertCidadeHasEstado(proxCodCidade, cidade);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a cidade no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private void insertCidadeHasEstado(int codCidade, Cidade cidade) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCIDADE_HAS_TBESTADO (COD_CIDADE_ESTADO, CODCIDADE, CODESTADO) VALUES (?, ?, ?)");
            stm.setInt(1, cidade.getCodCidade());// codigo que é gerado pelo método getProxCodCidade na tela de cadastro
            stm.setInt(2, codCidade);// cod cidade obtido através do select
            stm.setInt(3, cidade.getCodEstado());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a cidade no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getCidadeCadastrada(String cidade, int codEstado) throws SQLException {
        return getCadastrado("SELECT C.CODCIDADE FROM TBCIDADE C "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON C.CODCIDADE = CE.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "WHERE LOWER(C.CIDADE) = '" + cidade.toLowerCase() + "' AND E.CODESTADO = " + codEstado,
                "Erro na leitura da cidade no sistema");
    }

    public boolean isCidadeCadastrada(int codCidade) throws SQLException {
        return isCadastrado("SELECT COD_CIDADE_ESTADO FROM TBCIDADE_HAS_TBESTADO WHERE COD_CIDADE_ESTADO = "
                + codCidade, "Erro na leitura da cidade no sistema");
    }

    public boolean isCidadeVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCIDADE_HAS_TBESTADO",
                "Erro na leitura de cidades no sistema");
    }

    public int getProxCodCidade() throws SQLException {
        return getProxCod("SELECT MAX(COD_CIDADE_ESTADO) FROM TBCIDADE_HAS_TBESTADO",
                "Erro na leitura de cidades no sistema");
    }

    public List<Cidade> listCidades() throws SQLException {
        return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, "
                + "R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCIDADE_HAS_TBESTADO CE "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "ORDER BY CE.COD_CIDADE_ESTADO");
    }

    private List<Cidade> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Cidade> lista = new ArrayList<Cidade>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getCidadeAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de cidades no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Cidade getCidadeAux(ResultSet rs) throws SQLException {
        try {
            Cidade cidade = new Cidade();
            cidade.setCodCidade(rs.getInt(1));
            cidade.setCidade(rs.getString(2));
            cidade.setCodEstado(rs.getInt(3));
            cidade.setEstado(rs.getString(4));
            cidade.setCodRegiao(rs.getInt(5));
            cidade.setRegiao(rs.getString(6));
            cidade.setCodPais(rs.getInt(7));
            cidade.setPais(rs.getString(8));
            return cidade;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da cidade no sistema");
        }
    }

    public List<Cidade> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, "
                        + "R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCIDADE_HAS_TBESTADO CE "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE CE.COD_CIDADE_ESTADO = " + n);
            case 1:
                return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, "
                        + "R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCIDADE_HAS_TBESTADO CE "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%' ORDER BY CE.COD_CIDADE_ESTADO");
            case 2:
                return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, "
                        + "R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCIDADE_HAS_TBESTADO CE "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%' ORDER BY CE.COD_CIDADE_ESTADO");
            case 3:
                return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, "
                        + "R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCIDADE_HAS_TBESTADO CE "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(R.REGIAO) like '%" + s.toLowerCase() + "%' ORDER BY CE.COD_CIDADE_ESTADO");
            default:
                return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, "
                        + "R.REGIAO, RP.CODPAIS, P.PAIS FROM TBCIDADE_HAS_TBESTADO CE "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(P.PAIS) like '%" + s.toLowerCase() + "%' ORDER BY CE.COD_CIDADE_ESTADO");
        }
    }
}
