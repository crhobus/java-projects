package Endereco.Bairro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Bairro;
import Principal.DBDAO;

public class BairroDAO extends DBDAO {

    private Connection con;

    public BairroDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertBairro(Bairro bairro) throws Exception {
        if (isBairroCadastrado(bairro.getCodBairro())) {
            throw new Exception("Não é possível realizar esta operação");
        }
        int codBairro = getCadastrado("SELECT CODBAIRRO FROM TBBAIRRO WHERE LOWER(BAIRRO) = '" + bairro.getBairro().toLowerCase() + "'",
                "Erro na leitura do bairro no sistema");// verifica se bairro está cadastrado
        if (codBairro != -1) {
            insertBairroHasCep(codBairro, bairro);
            return true;
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBBAIRRO (CODBAIRRO, BAIRRO) VALUES (?, ?)");
            int proxCodBairro = getProxCod("SELECT MAX(CODBAIRRO) FROM TBBAIRRO",
                    "Erro na leitura de bairros no sistema");// obtem próximo codigo do bairro
            stm.setInt(1, proxCodBairro);
            stm.setString(2, bairro.getBairro());
            stm.execute();// insere novo bairro
            insertBairroHasCep(proxCodBairro, bairro);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o bairro no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private void insertBairroHasCep(int codBairro, Bairro bairro) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBBAIRRO_HAS_TBCEP (COD_BAIRRO_CEP, CODBAIRRO, CODCEP) VALUES (?, ?, ?)");
            stm.setInt(1, bairro.getCodBairro());// codigo que é gerado pelo método getProxCodBairro na tela de cadastro
            stm.setInt(2, codBairro);// cod bairro obtido através do select
            stm.setInt(3, bairro.getCodCep());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o bairro no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getBairroCadastrado(String bairro, int codCep) throws SQLException {
        return getCadastrado("SELECT B.CODBAIRRO FROM TBBAIRRO B "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON B.CODBAIRRO = BC.CODBAIRRO "
                + "INNER JOIN TBCEP CE ON BC.CODCEP = CE.CODCEP "
                + "WHERE LOWER(B.BAIRRO) = '" + bairro.toLowerCase() + "' AND CE.CODCEP = " + codCep,
                "Erro na leitura do bairro no sistema");
    }

    public boolean isBairroCadastrado(int codBairro) throws SQLException {
        return isCadastrado("SELECT COD_BAIRRO_CEP FROM TBBAIRRO_HAS_TBCEP WHERE COD_BAIRRO_CEP = "
                + codBairro, "Erro na leitura do bairro no sistema");
    }

    public boolean isBairroVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBBAIRRO_HAS_TBCEP",
                "Erro na leitura dos bairros no sistema");
    }

    public int getProxCodBairro() throws SQLException {
        return getProxCod("SELECT MAX(COD_BAIRRO_CEP) FROM TBBAIRRO_HAS_TBCEP",
                "Erro na leitura dos bairros no sistema");
    }

    public List<Bairro> listBairros() throws SQLException {
        return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "ORDER BY BC.COD_BAIRRO_CEP");
    }

    private List<Bairro> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Bairro> lista = new ArrayList<Bairro>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getBairroAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de bairros no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Bairro getBairroAux(ResultSet rs) throws SQLException {
        try {
            Bairro bairro = new Bairro();
            bairro.setCodBairro(rs.getInt(1));
            bairro.setBairro(rs.getString(2));
            bairro.setCodCep(rs.getInt(3));
            bairro.setCep(rs.getString(4));
            bairro.setCodCidade(rs.getInt(5));
            bairro.setCidade(rs.getString(6));
            bairro.setCodEstado(rs.getInt(7));
            bairro.setEstado(rs.getString(8));
            bairro.setCodRegiao(rs.getInt(9));
            bairro.setRegiao(rs.getString(10));
            bairro.setCodPais(rs.getInt(11));
            bairro.setPais(rs.getString(12));
            return bairro;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do bairro no sistema");
        }
    }

    public List<Bairro> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE BC.COD_BAIRRO_CEP = " + n);
            case 1:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%' ORDER BY BC.COD_BAIRRO_CEP");
            case 2:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE CEP.CEP = '" + s + "' ORDER BY BC.COD_BAIRRO_CEP");
            case 3:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%' ORDER BY BC.COD_BAIRRO_CEP");
            case 4:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%' ORDER BY BC.COD_BAIRRO_CEP");
            case 5:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(R.REGIAO) like '%" + s.toLowerCase() + "%' ORDER BY BC.COD_BAIRRO_CEP");
            default:
                return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                        + "CE.CODESTADO, E.ESTADO, E.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBBAIRRO_HAS_TBCEP BC "
                        + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                        + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                        + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                        + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                        + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                        + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON E.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                        + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                        + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                        + "WHERE LOWER(P.PAIS) like '%" + s.toLowerCase() + "%' ORDER BY BC.COD_BAIRRO_CEP");
        }
    }
}
