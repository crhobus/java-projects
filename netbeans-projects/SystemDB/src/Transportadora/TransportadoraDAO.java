package Transportadora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Transportadora;
import Principal.DBDAO;

public class TransportadoraDAO extends DBDAO {

    private Connection con;

    public TransportadoraDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertTrans(Transportadora transportadora) throws SQLException {
        if (isTransCadastrado(transportadora.getCodTrans())) {
            return updateTrans(transportadora);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(transportadora);
            stm = con.prepareStatement("INSERT INTO TBTRANSPORTADORA (CODTRANS, CODPESSOA, "
                    + "FAX, FRETE) VALUES (?, ?, ?, ?)");
            stm.setInt(1, transportadora.getCodTrans());
            stm.setInt(2, codPessoa);
            stm.setString(3, transportadora.getFax());
            stm.setDouble(4, transportadora.getFrete());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a transportadora no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertPessoa(Transportadora transportadora) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, "
                    + "NOME, CPFCNPJ, RGIE, INSCMUN, NUMERO, REFERENCIA, FONE, EMAIL, MSN, DESCRICAO, COD_ENDERECO_BAI_CEP) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de transportadoras no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(transportadora.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(transportadora.getUltAlteracao().getTime()));
            stm.setString(4, transportadora.getNome());
            stm.setString(5, transportadora.getCpfCnpj());
            stm.setString(6, transportadora.getRgIe());
            stm.setString(7, transportadora.getInscMunicipal());
            stm.setInt(8, transportadora.getNumero());
            stm.setString(9, transportadora.getReferencia());
            stm.setString(10, transportadora.getFone());
            stm.setString(11, transportadora.getEmail());
            stm.setString(12, transportadora.getMsn());
            stm.setString(13, transportadora.getDescricao());
            stm.setInt(14, transportadora.getCodEndereco());
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a transportadora no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateTrans(Transportadora transportadora) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = updatePessoa(transportadora);
            stm = con.prepareStatement("UPDATE TBTRANSPORTADORA SET CODPESSOA = ?,"
                    + "FAX = ?, FRETE = ? WHERE CODTRANS = ?");
            stm.setInt(1, codPessoa);
            stm.setString(2, transportadora.getFax());
            stm.setDouble(3, transportadora.getFrete());
            stm.setInt(4, transportadora.getCodTrans());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da transportadora no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updatePessoa(Transportadora transportadora) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBTRANSPORTADORA WHERE CODTRANS = "
                    + transportadora.getCodTrans(), "Erro na leitura da transportadora no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, "
                    + "NOME = ?, CPFCNPJ = ?, RGIE = ?, INSCMUN = ?, NUMERO = ?, REFERENCIA = ?, FONE = ?, EMAIL = ?, "
                    + "MSN = ?, DESCRICAO = ?, COD_ENDERECO_BAI_CEP = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(transportadora.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(transportadora.getUltAlteracao().getTime()));
            stm.setString(3, transportadora.getNome());
            stm.setString(4, transportadora.getCpfCnpj());
            stm.setString(5, transportadora.getRgIe());
            stm.setString(6, transportadora.getInscMunicipal());
            stm.setInt(7, transportadora.getNumero());
            stm.setString(8, transportadora.getReferencia());
            stm.setString(9, transportadora.getFone());
            stm.setString(10, transportadora.getEmail());
            stm.setString(11, transportadora.getMsn());
            stm.setString(12, transportadora.getDescricao());
            stm.setInt(13, transportadora.getCodEndereco());
            stm.setInt(14, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da transportadora no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isTransCadastrado(int codTrans) throws SQLException {
        return isCadastrado("SELECT CODTRANS FROM TBTRANSPORTADORA WHERE CODTRANS = "
                + codTrans, "Erro na leitura da transportadora no sistema");
    }

    public int getTransCadastrado(String cnpj, int codTrans) throws SQLException {
        return getCadastrado("SELECT CODTRANS FROM TBTRANSPORTADORA T "
                + "INNER JOIN TBPESSOA P ON T.CODPESSOA = P.CODPESSOA "
                + "WHERE P.CPFCNPJ = '" + cnpj + "' AND T.CODTRANS = "
                + codTrans, "Erro na leitura da transportadora no sistema");
    }

    public int getTransCadastrado(String cnpj) throws SQLException {
        return getCadastrado("SELECT CODPESSOA FROM TBPESSOA WHERE CPFCNPJ = '"
                + cnpj + "'", "Erro na leitura da transportadora no sistema");
    }

    public boolean isTransVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBTRANSPORTADORA",
                "Erro na leitura de transportadoras no sistema");
    }

    public int getProxCodTrans() throws SQLException {
        return getProxCod("SELECT MAX(CODTRANS) FROM TBTRANSPORTADORA",
                "Erro na leitura de transportadoras no sistema");
    }

    public boolean deleteTrans(int codTrans) throws SQLException {
        int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBTRANSPORTADORA WHERE CODTRANS = "
                + codTrans, "Erro na leitura da transportadora no sistema");// obtém o codigo da pessoa
        delete("DELETE FROM TBTRANSPORTADORA WHERE CODTRANS = " + codTrans,
                "Não foi possível excluir a transportadora do sistema");
        return delete("DELETE FROM TBPESSOA WHERE CODPESSOA = " + codPessoa,
                "Não foi possível excluir a transportadora do sistema");
    }

    public List<Transportadora> listTrans() throws SQLException {
        return getLista("SELECT T.CODTRANS, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, "
                + "P.INSCMUN, P.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, P.NUMERO, CEP.CEP, "
                + "C.CIDADE, ES.ESTADO, R.REGIAO, P.REFERENCIA, P.EMAIL, P.MSN, P.FONE, T.FAX, "
                + "T.FRETE, P.DESCRICAO FROM TBTRANSPORTADORA T "
                + "INNER JOIN TBPESSOA P ON T.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON ES.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "ORDER BY T.CODTRANS");
    }

    private List<Transportadora> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Transportadora> lista = new ArrayList<Transportadora>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getTransAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de transportadoras no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    private Transportadora getTransAux(ResultSet rs) throws SQLException {
        try {
            Transportadora trans = new Transportadora();
            trans.setCodTrans(rs.getInt(1));
            trans.setDataCadastro(rs.getTimestamp(2));
            trans.setUltAlteracao(rs.getTimestamp(3));
            trans.setNome(rs.getString(4));
            trans.setCpfCnpj(rs.getString(5));
            trans.setRgIe(rs.getString(6));
            trans.setInscMunicipal(rs.getString(7));
            trans.setCodEndereco(rs.getInt(8));
            trans.setEndereco(rs.getString(9));
            trans.setBairro(rs.getString(10));
            trans.setNumero(rs.getInt(11));
            trans.setCep(rs.getString(12));
            trans.setCidade(rs.getString(13));
            trans.setEstado(rs.getString(14));
            trans.setRegiao(rs.getString(15));
            trans.setReferencia(rs.getString(16));
            trans.setEmail(rs.getString(17));
            trans.setMsn(rs.getString(18));
            trans.setFone(rs.getString(19));
            trans.setFax(rs.getString(20));
            trans.setFrete(rs.getDouble(21));
            trans.setDescricao(rs.getString(22));
            return trans;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da transportadora no sistema");
        }
    }

    public List<Transportadora> getLista(final int coluna, final String s, final int n, final double d) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("T.CODTRANS = " + n));
            case 1:
                return getLista(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("P.CPFCNPJ = '" + s + "'"));
            case 3:
                return getLista(getSql("P.RGIE = '" + s + "'"));
            case 4:
                return getLista(getSql("P.INSCMUN = '" + s + "'"));
            case 5:
                return getLista(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
            case 6:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 7:
                return getLista(getSql("P.NUMERO = " + n));
            case 8:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 9:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            case 10:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
            case 11:
                return getLista(getSql("LOWER(P.EMAIL) like '%" + s.toLowerCase() + "%'"));
            case 12:
                return getLista(getSql("P.FONE = '" + s + "'"));
            default:
                return getLista(getSql("T.FRETE = " + d));
        }
    }

    private String getSql(String condicao) {
        return "SELECT T.CODTRANS, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, "
                + "P.INSCMUN, P.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, P.NUMERO, CEP.CEP, "
                + "C.CIDADE, ES.ESTADO, R.REGIAO, P.REFERENCIA, P.EMAIL, P.MSN, P.FONE, T.FAX, "
                + "T.FRETE, P.DESCRICAO FROM TBTRANSPORTADORA T "
                + "INNER JOIN TBPESSOA P ON T.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON ES.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "WHERE " + condicao + " ORDER BY T.CODTRANS";
    }
}
