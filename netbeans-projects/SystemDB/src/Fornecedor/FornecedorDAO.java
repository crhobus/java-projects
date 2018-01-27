package Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Fornecedor;
import Principal.DBDAO;

public class FornecedorDAO extends DBDAO {

    private Connection con;

    public FornecedorDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertForn(Fornecedor fornecedor) throws SQLException {
        if (isFornCadastrado(fornecedor.getCodForn())) {
            return updateForn(fornecedor);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(fornecedor);
            stm = con.prepareStatement("INSERT INTO TBFORNECEDOR (CODFORN, CODPESSOA, SIGLA, "
                    + "TIPOFORNECEDOR, COMISSAO, HOMEPAGE, FAX, NOMECONTATO, FONECONTATO, COMPRAMINIMA, COMPRAMAXIMA) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, fornecedor.getCodForn());
            stm.setInt(2, codPessoa);
            stm.setBytes(3, fornecedor.getSigla());
            stm.setString(4, fornecedor.getTipoFornecedor());
            stm.setDouble(5, fornecedor.getComissao());
            stm.setString(6, fornecedor.getHomePage());
            stm.setString(7, fornecedor.getFax());
            stm.setString(8, fornecedor.getNomeContato());
            stm.setString(9, fornecedor.getFoneContato());
            stm.setDouble(10, fornecedor.getCompraMinima());
            stm.setDouble(11, fornecedor.getCompraMaxima());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o fornecedor no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException e) {
            }
        }
    }

    private int insertPessoa(Fornecedor fornecedor) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, "
                    + "NOME, CPFCNPJ, RGIE, INSCMUN, NUMERO, REFERENCIA, FONE, EMAIL, MSN, DESCRICAO, COD_ENDERECO_BAI_CEP) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de fornecedores no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(fornecedor.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(fornecedor.getUltAlteracao().getTime()));
            stm.setString(4, fornecedor.getNome());
            stm.setString(5, fornecedor.getCpfCnpj());
            stm.setString(6, fornecedor.getRgIe());
            stm.setString(7, fornecedor.getInscMunicipal());
            stm.setInt(8, fornecedor.getNumero());
            stm.setString(9, fornecedor.getReferencia());
            stm.setString(10, fornecedor.getFone());
            stm.setString(11, fornecedor.getEmail());
            stm.setString(12, fornecedor.getMsn());
            stm.setString(13, fornecedor.getDescricao());
            stm.setInt(14, fornecedor.getCodEndereco());
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o fornecedor no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateForn(Fornecedor fornecedor) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = updatePessoa(fornecedor);
            stm = con.prepareStatement("UPDATE TBFORNECEDOR SET CODPESSOA = ?, SIGLA = ?, "
                    + "TIPOFORNECEDOR = ?, COMISSAO = ?, HOMEPAGE = ?, FAX = ?, NOMECONTATO = ?, FONECONTATO = ?, "
                    + "COMPRAMINIMA = ?, COMPRAMAXIMA = ? WHERE CODFORN = ?");
            stm.setInt(1, codPessoa);
            stm.setBytes(2, fornecedor.getSigla());
            stm.setString(3, fornecedor.getTipoFornecedor());
            stm.setDouble(4, fornecedor.getComissao());
            stm.setString(5, fornecedor.getHomePage());
            stm.setString(6, fornecedor.getFax());
            stm.setString(7, fornecedor.getNomeContato());
            stm.setString(8, fornecedor.getFoneContato());
            stm.setDouble(9, fornecedor.getCompraMinima());
            stm.setDouble(10, fornecedor.getCompraMaxima());
            stm.setInt(11, fornecedor.getCodForn());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do fornecedor no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updatePessoa(Fornecedor fornecedor) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBFORNECEDOR WHERE CODFORN = "
                    + fornecedor.getCodForn(), "Erro na leitura do fornecedor no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, "
                    + "NOME = ?, CPFCNPJ = ?, RGIE = ?, INSCMUN = ?, NUMERO = ?, REFERENCIA = ?, FONE = ?, EMAIL = ?, "
                    + "MSN = ?, DESCRICAO = ?, COD_ENDERECO_BAI_CEP = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(fornecedor.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(fornecedor.getUltAlteracao().getTime()));
            stm.setString(3, fornecedor.getNome());
            stm.setString(4, fornecedor.getCpfCnpj());
            stm.setString(5, fornecedor.getRgIe());
            stm.setString(6, fornecedor.getInscMunicipal());
            stm.setInt(7, fornecedor.getNumero());
            stm.setString(8, fornecedor.getReferencia());
            stm.setString(9, fornecedor.getFone());
            stm.setString(10, fornecedor.getEmail());
            stm.setString(11, fornecedor.getMsn());
            stm.setString(12, fornecedor.getDescricao());
            stm.setInt(13, fornecedor.getCodEndereco());
            stm.setInt(14, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do fornecedor no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isFornCadastrado(int codForn) throws SQLException {
        return isCadastrado("SELECT CODFORN FROM TBFORNECEDOR WHERE CODFORN = "
                + codForn, "Erro na leitura do fornecedor no sistema");
    }

    public int getFornCadastrado(String cnpj, int codForn) throws SQLException {
        return getCadastrado("SELECT CODFORN FROM TBFORNECEDOR F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "WHERE P.CPFCNPJ = '" + cnpj + "' AND F.CODFORN = "
                + codForn, "Erro na leitura do fornecedor no sistema");
    }

    public int getFornCadastrado(String cnpj) throws SQLException {
        return getCadastrado("SELECT CODPESSOA FROM TBPESSOA WHERE CPFCNPJ = '"
                + cnpj + "'", "Erro na leitura do fornecedor no sistema");
    }

    public boolean isFornVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBFORNECEDOR",
                "Erro na leitura de fornecedores no sistema");
    }

    public int getProxCodForn() throws SQLException {
        return getProxCod("SELECT MAX(CODFORN) FROM TBFORNECEDOR",
                "Erro na leitura de fornecedores no sistema");
    }

    public boolean deleteForn(int codForn) throws SQLException {
        int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBFORNECEDOR WHERE CODFORN = " + codForn,
                "Erro na leitura do fornecedor no sistema");// obtém o codigo da pessoa
        delete("DELETE FROM TBFORNECEDOR WHERE CODFORN = " + codForn,
                "Não foi possível excluir o fornecedor do sistema");
        return delete("DELETE FROM TBPESSOA WHERE CODPESSOA = " + codPessoa,
                "Não foi possível excluir o fornecedor do sistema");
    }

    public List<Fornecedor> listForn() throws SQLException {
        return getLista("SELECT F.CODFORN, P.DATACADAS, P.ULTALTERACAO, P.NOME, F.SIGLA, P.CPFCNPJ, P.RGIE, P.INSCMUN, "
                + "F.TIPOFORNECEDOR, P.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, P.NUMERO, CEP.CEP, C.CIDADE, "
                + "ES.ESTADO, R.REGIAO, P.REFERENCIA, F.COMISSAO, P.EMAIL, P.MSN, F.HOMEPAGE, P.FONE, F.FAX, "
                + "F.NOMECONTATO, F.FONECONTATO, F.COMPRAMINIMA, F.COMPRAMAXIMA, P.DESCRICAO FROM TBFORNECEDOR F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
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
                + "ORDER BY F.CODFORN");
    }

    private List<Fornecedor> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Fornecedor> lista = new ArrayList<Fornecedor>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getFornAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de fornecedores no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Fornecedor getFornAux(ResultSet rs) throws SQLException {
        try {
            Fornecedor forn = new Fornecedor();
            forn.setCodForn(rs.getInt(1));
            forn.setDataCadastro(rs.getTimestamp(2));
            forn.setUltAlteracao(rs.getTimestamp(3));
            forn.setNome(rs.getString(4));
            forn.setSigla(rs.getBytes(5));
            forn.setCpfCnpj(rs.getString(6));
            forn.setRgIe(rs.getString(7));
            forn.setInscMunicipal(rs.getString(8));
            forn.setTipoFornecedor(rs.getString(9));
            forn.setCodEndereco(rs.getInt(10));
            forn.setEndereco(rs.getString(11));
            forn.setBairro(rs.getString(12));
            forn.setNumero(rs.getInt(13));
            forn.setCep(rs.getString(14));
            forn.setCidade(rs.getString(15));
            forn.setEstado(rs.getString(16));
            forn.setRegiao(rs.getString(17));
            forn.setReferencia(rs.getString(18));
            forn.setComissao(rs.getDouble(19));
            forn.setEmail(rs.getString(20));
            forn.setMsn(rs.getString(21));
            forn.setHomePage(rs.getString(22));
            forn.setFone(rs.getString(23));
            forn.setFax(rs.getString(24));
            forn.setNomeContato(rs.getString(25));
            forn.setFoneContato(rs.getString(26));
            forn.setCompraMinima(rs.getDouble(27));
            forn.setCompraMaxima(rs.getDouble(28));
            forn.setDescricao(rs.getString(29));
            return forn;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do fornecedor no sistema");
        }
    }

    public List<Fornecedor> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("F.CODFORN = " + n));
            case 1:
                return getLista(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("P.CPFCNPJ = '" + s + "'"));
            case 3:
                return getLista(getSql("P.RGIE = '" + s + "'"));
            case 4:
                return getLista(getSql("P.INSCMUN = '" + s + "'"));
            case 5:
                return getLista(getSql("LOWER(F.TIPOFORNECEDOR) like '%" + s.toLowerCase() + "%'"));
            case 6:
                return getLista(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
            case 7:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 8:
                return getLista(getSql("P.NUMERO = " + n));
            case 9:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 10:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            case 11:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
            case 12:
                return getLista(getSql("LOWER(P.EMAIL) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("P.FONE = '" + s + "'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT F.CODFORN, P.DATACADAS, P.ULTALTERACAO, P.NOME, F.SIGLA, P.CPFCNPJ, P.RGIE, P.INSCMUN, "
                + "F.TIPOFORNECEDOR, P.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, P.NUMERO, CEP.CEP, C.CIDADE, "
                + "ES.ESTADO, R.REGIAO, P.REFERENCIA, F.COMISSAO, P.EMAIL, P.MSN, F.HOMEPAGE, P.FONE, F.FAX, "
                + "F.NOMECONTATO, F.FONECONTATO, F.COMPRAMINIMA, F.COMPRAMAXIMA, P.DESCRICAO FROM TBFORNECEDOR F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
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
                + "WHERE " + condicao + " ORDER BY F.CODFORN";
    }
}
