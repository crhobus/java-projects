package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Defeito;

public class DefeitoDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Defeito> defeitos;

    public DefeitoDAO(Connection con) {
        super(con);
        this.con = con;
        defeitos = new HashMap<Integer, Defeito>();
    }

    public boolean insertDefeito(Defeito defeito) throws Exception {
        if (isDefeitoCadastrado(defeito.getCodigo())) {
            return updateDefeito(defeito);
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBDEFEITO (CODDEFEITO, DEFEITO) VALUES (?, ?)");
            stm.setInt(1, defeito.getCodigo());
            stm.setString(2, defeito.getDefeito());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o defeito no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateDefeito(Defeito defeito) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBDEFEITO SET DEFEITO = ? WHERE CODDEFEITO = ?");
            stm.setString(1, defeito.getDefeito());
            stm.setInt(2, defeito.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do defeito no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isDefeitoCadastrado(int codDefeito) throws SQLException {
        return isCadastrado("SELECT CODDEFEITO FROM TBDEFEITO WHERE CODDEFEITO = "
                + codDefeito, "Erro na leitura do defeito no sistema");
    }

    public int getDefeitoCadastrado(String defeito) throws SQLException {
        return getCadastrado("SELECT CODDEFEITO FROM TBDEFEITO WHERE LOWER(DEFEITO) = '"
                + defeito.toLowerCase() + "'", "Erro na leitura do defeito no sistema");
    }

    public boolean isDefeitoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBDEFEITO",
                "Erro na leitura de defeitos no sistema");
    }

    public int getProxCodDefeito() throws SQLException {
        return getProxCod("SELECT MAX(CODDEFEITO) FROM TBDEFEITO",
                "Erro na leitura de defeitos no sistema");
    }

    public boolean deleteDefeito(int codDefeito) throws SQLException {
        return delete("DELETE FROM TBDEFEITO WHERE CODDEFEITO = " + codDefeito,
                "Não foi possível excluir o defeito do sistema");
    }

    public void listDefeitos() throws SQLException {
        addDefeitosMapa("SELECT * FROM TBDEFEITO ORDER BY CODDEFEITO");
    }

    public void listDefeitosCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addDefeitosMapa(getSql("CODDEFEITO = " + n));
                break;
            default:
                addDefeitosMapa(getSql("LOWER(DEFEITO) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT * FROM TBDEFEITO WHERE " + condicao + " ORDER BY CODDEFEITO";
    }

    private void addDefeitosMapa(String sql) throws SQLException {
        defeitos.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                defeitos.put(linha, getDefeitoAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de defeitos no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Defeito getDefeitoAux(ResultSet rs) throws SQLException {
        try {
            Defeito defeito = new Defeito();
            defeito.setCodigo(rs.getInt(1));
            defeito.setDefeito(rs.getString(2));
            return defeito;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do defeito no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        defeitos.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Defeito defeito = defeitos.get(linha);
        switch (coluna) {
            case 0:
                return defeito.getCodigo();
            default:
                return defeito.getDefeito();
        }
    }

    public int getQtdadeDefeitosCadas() {
        return defeitos.size();
    }

    public Defeito getDefeitoMapa(int linha) {
        return defeitos.get(linha);
    }
}
