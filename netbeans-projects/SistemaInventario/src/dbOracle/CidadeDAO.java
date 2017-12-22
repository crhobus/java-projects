package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Cidade;

public class CidadeDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Cidade> cidades;

    public CidadeDAO(Connection con) {
        super(con);
        this.con = con;
        cidades = new HashMap<Integer, Cidade>();
    }

    public boolean insertCidade(Cidade cidade) throws Exception {
        if (isCidadeCadastrada(cidade.getCodigo())) {
            throw new Exception("Não é possível sobrescrever a cidade\nEntre com uma nova cidade");
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCIDADE (CODCIDADE, CIDADE) VALUES (?, ?)");
            stm.setInt(1, cidade.getCodigo());
            stm.setString(2, cidade.getCidade());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a cidade no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isCidadeCadastrada(int codCidade) throws SQLException {
        return isCadastrado("SELECT CODCIDADE FROM TBCIDADE WHERE CODCIDADE = "
                + codCidade, "Erro na leitura da cidade no sistema");
    }

    public int getCidadeCadastrada(String cidade) throws SQLException {
        return getCadastrado("SELECT CODCIDADE FROM TBCIDADE WHERE LOWER(CIDADE) = '"
                + cidade.toLowerCase() + "'", "Erro na leitura da cidade no sistema");
    }

    public boolean isCidadeVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCIDADE",
                "Erro na leitura de cidades no sistema");
    }

    public int getProxCodCidade() throws SQLException {
        return getProxCod("SELECT MAX(CODCIDADE) FROM TBCIDADE",
                "Erro na leitura de cidades no sistema");
    }

    public void listCidades() throws SQLException {
        addCidadesMapa("SELECT * FROM TBCIDADE ORDER BY CODCIDADE");
    }

    public void listCidadesCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addCidadesMapa(getSql("CODCIDADE = " + n));
                break;
            default:
                addCidadesMapa(getSql("LOWER(CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT * FROM TBCIDADE WHERE " + condicao + " ORDER BY CODCIDADE";
    }

    private void addCidadesMapa(String sql) throws SQLException {
        cidades.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                cidades.put(linha, getCidadeAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de cidades no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
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
            cidade.setCodigo(rs.getInt(1));
            cidade.setCidade(rs.getString(2));
            return cidade;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da cidade no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        cidades.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Cidade cidade = cidades.get(linha);
        switch (coluna) {
            case 0:
                return cidade.getCodigo();
            default:
                return cidade.getCidade();
        }
    }

    public int getQtdadeCidadesCadas() {
        return cidades.size();
    }

    public Cidade getCidadeMapa(int linha) {
        return cidades.get(linha);
    }
}
