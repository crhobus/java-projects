package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Cep;
import model.Cidade;

public class CepDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Cep> ceps;

    public CepDAO(Connection con) {
        super(con);
        this.con = con;
        ceps = new HashMap<Integer, Cep>();
    }

    public boolean insertCep(Cep cep) throws Exception {
        if (isCepCadastrado(cep.getCodigo())) {
            throw new Exception("Não é possível sobrescrever o cep\nEntre com um novo cep");
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCEP (CODCEP, CEP, CODCIDADE) VALUES (?, ?, ?)");
            stm.setInt(1, cep.getCodigo());
            stm.setString(2, cep.getCep());
            stm.setInt(3, cep.getCidade().getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o cep no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getCepCadastrado(String cep) throws SQLException {
        return getCadastrado("SELECT CODCEP FROM TBCEP WHERE CEP = '" + cep
                + "'", "Erro na leitura do cep no sistema");
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

    public void listCeps() throws SQLException {
        addCepsMapa("SELECT CEP.CODCEP, CEP.CEP, CEP.CODCIDADE, C.CIDADE FROM TBCEP CEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "ORDER BY CEP.CODCEP");
    }

    public void listCepsCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addCepsMapa(getSql("CEP.CODCEP = " + n));
                break;
            case 1:
                addCepsMapa(getSql("CEP.CEP = '" + s + "'"));
                break;
            default:
                addCepsMapa(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT CEP.CODCEP, CEP.CEP, CEP.CODCIDADE, C.CIDADE FROM TBCEP CEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "WHERE " + condicao + " ORDER BY CEP.CODCEP";
    }

    private void addCepsMapa(String sql) throws SQLException {
        ceps.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                ceps.put(linha, getCepAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de ceps no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
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
            cep.setCodigo(rs.getInt(1));
            cep.setCep(rs.getString(2));
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(3));
            cidade.setCidade(rs.getString(4));
            cep.setCidade(cidade);
            return cep;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do cep no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        ceps.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Cep cep = ceps.get(linha);
        switch (coluna) {
            case 0:
                return cep.getCodigo();
            case 1:
                return cep.getCep();
            default:
                return cep.getCidade().getCidade();
        }
    }

    public int getQtdadeCepsCadas() {
        return ceps.size();
    }

    public Cep getCepMapa(int linha) {
        return ceps.get(linha);
    }
}
