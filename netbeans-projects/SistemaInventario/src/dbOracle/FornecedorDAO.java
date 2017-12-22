package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import model.Bairro;
import model.Cep;
import model.Cidade;
import model.Endereco;
import model.Fornecedor;

public class FornecedorDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Fornecedor> fornecedores;

    public FornecedorDAO(Connection con) {
        super(con);
        this.con = con;
        fornecedores = new HashMap<Integer, Fornecedor>();
    }

    public boolean insertForn(Fornecedor fornecedor) throws SQLException {
        if (isFornCadastrado(fornecedor.getCodigo())) {
            return updateForn(fornecedor);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(fornecedor);
            stm = con.prepareStatement("INSERT INTO TBFORNECEDOR (CODFORN, EMAIL, HOMEPAGE, CODPESSOA) "
                    + "VALUES (?, ?, ?, ?)");
            stm.setInt(1, fornecedor.getCodigo());
            stm.setString(2, fornecedor.getEmail());
            stm.setString(3, fornecedor.getHomePage());
            stm.setInt(4, codPessoa);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o fornecedor no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertPessoa(Fornecedor fornecedor) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, NOME, "
                    + "CPFCNPJ, RGIE, NUMERO, CONTATO, COD_ENDERECO_BAI_CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de fornecedores no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(fornecedor.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(fornecedor.getUltAlteracao().getTime()));
            stm.setString(4, fornecedor.getNome());
            stm.setString(5, fornecedor.getCpfCnpj());
            stm.setString(6, fornecedor.getRgIe());
            stm.setInt(7, fornecedor.getNumero());
            stm.setString(8, fornecedor.getContato());
            stm.setInt(9, fornecedor.getEndereco().getCodigo());
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o fornecedor no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
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
            stm = con.prepareStatement("UPDATE TBFORNECEDOR SET EMAIL = ?, HOMEPAGE = ?, CODPESSOA = ? WHERE CODFORN = ?");
            stm.setString(1, fornecedor.getEmail());
            stm.setString(2, fornecedor.getHomePage());
            stm.setInt(3, codPessoa);
            stm.setInt(4, fornecedor.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do fornecedor no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
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
            int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBFORNECEDOR WHERE CODFORN = " + fornecedor.getCodigo(),
                    "Erro na leitura do fornecedor no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, NOME = ?, "
                    + "CPFCNPJ = ?, RGIE = ?, NUMERO = ?, CONTATO = ?, COD_ENDERECO_BAI_CEP = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(fornecedor.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(fornecedor.getUltAlteracao().getTime()));
            stm.setString(3, fornecedor.getNome());
            stm.setString(4, fornecedor.getCpfCnpj());
            stm.setString(5, fornecedor.getRgIe());
            stm.setInt(6, fornecedor.getNumero());
            stm.setString(7, fornecedor.getContato());
            stm.setInt(8, fornecedor.getEndereco().getCodigo());
            stm.setInt(9, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do fornecedor no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
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

    public void listFornecedores() throws SQLException {
        addFornecedoresMapa("SELECT F.CODFORN, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, "
                + "P.COD_ENDERECO_BAI_CEP, E.ENDERECO, B.BAIRRO, P.NUMERO, CEP.CEP, C.CIDADE, "
                + "P.CONTATO, F.EMAIL, F.HOMEPAGE FROM TBFORNECEDOR F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO E ON EB.CODENDERECO = E.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "ORDER BY F.CODFORN");
    }

    public void listFornecedoresCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addFornecedoresMapa(getSql("F.CODFORN = " + n));
                break;
            case 1:
                addFornecedoresMapa(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addFornecedoresMapa(getSql("P.CPFCNPJ = '" + s + "'"));
                break;
            case 3:
                addFornecedoresMapa(getSql("P.RGIE = '" + s + "'"));
                break;
            case 4:
                addFornecedoresMapa(getSql("LOWER(E.ENDERECO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 5:
                addFornecedoresMapa(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 6:
                addFornecedoresMapa(getSql("P.NUMERO = " + n));
                break;
            case 7:
                addFornecedoresMapa(getSql("CEP.CEP = '" + s + "'"));
                break;
            case 8:
                addFornecedoresMapa(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
            case 9:
                addFornecedoresMapa(getSql("LOWER(F.EMAIL) like '%" + s.toLowerCase() + "%'"));
                break;
            default:
                addFornecedoresMapa(getSql("P.CONTATO = '" + s + "'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT F.CODFORN, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, "
                + "P.COD_ENDERECO_BAI_CEP, E.ENDERECO, B.BAIRRO, P.NUMERO, CEP.CEP, C.CIDADE, "
                + "P.CONTATO, F.EMAIL, F.HOMEPAGE FROM TBFORNECEDOR F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO E ON EB.CODENDERECO = E.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "WHERE " + condicao + " ORDER BY F.CODFORN";
    }

    private void addFornecedoresMapa(String sql) throws SQLException {
        fornecedores.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                fornecedores.put(linha, getFornecedorAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de fornecedores no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Fornecedor getFornecedorAux(ResultSet rs) throws SQLException {
        try {
            Fornecedor forn = new Fornecedor();
            forn.setCodigo(rs.getInt(1));
            forn.setDataCadastro(rs.getTimestamp(2));
            forn.setUltAlteracao(rs.getTimestamp(3));
            forn.setNome(rs.getString(4));
            forn.setCpfCnpj(rs.getString(5));
            forn.setRgIe(rs.getString(6));
            Endereco endereco = new Endereco();
            endereco.setCodigo(rs.getInt(7));
            endereco.setEndereco(rs.getString(8));
            Bairro bairro = new Bairro();
            bairro.setBairro(rs.getString(9));
            forn.setNumero(rs.getInt(10));
            Cep cep = new Cep();
            cep.setCep(rs.getString(11));
            Cidade cidade = new Cidade();
            cidade.setCidade(rs.getString(12));
            cep.setCidade(cidade);
            bairro.setCep(cep);
            endereco.setBairro(bairro);
            forn.setEndereco(endereco);
            forn.setContato(rs.getString(13));
            forn.setEmail(rs.getString(14));
            forn.setHomePage(rs.getString(15));
            return forn;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do fornecedor no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        fornecedores.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Fornecedor forn = fornecedores.get(linha);
        switch (coluna) {
            case 0:
                return forn.getCodigo();
            case 1:
                return forn.getNome();
            case 2:
                return forn.getCpfCnpj();
            case 3:
                return forn.getRgIe();
            case 4:
                return forn.getEndereco().getEndereco();
            case 5:
                return forn.getEndereco().getBairro().getBairro();
            case 6:
                return forn.getNumero();
            case 7:
                return forn.getEndereco().getBairro().getCep().getCep();
            case 8:
                return forn.getEndereco().getBairro().getCep().getCidade().getCidade();
            case 9:
                return forn.getEmail();
            default:
                return forn.getContato();
        }
    }

    public int getQtdadeFornecedoresCadas() {
        return fornecedores.size();
    }

    public Fornecedor getFornecedorMapa(int linha) {
        return fornecedores.get(linha);
    }
}
