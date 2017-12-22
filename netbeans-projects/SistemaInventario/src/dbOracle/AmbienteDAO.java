package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Ambiente;

public class AmbienteDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Ambiente> ambientes;

    public AmbienteDAO(Connection con) {
        super(con);
        this.con = con;
        ambientes = new HashMap<Integer, Ambiente>();
    }

    public boolean insertAmbiente(Ambiente ambiente) throws Exception {
        if (isAmbienteCadastrado(ambiente.getCodigo())) {
            return updateAmbiente(ambiente);
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBAMBIENTE (CODAMBIENTE, AMBIENTE) VALUES (?, ?)");
            stm.setInt(1, ambiente.getCodigo());
            stm.setString(2, ambiente.getAmbiente());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o ambiente no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateAmbiente(Ambiente ambiente) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBAMBIENTE SET AMBIENTE = ? WHERE CODAMBIENTE = ?");
            stm.setString(1, ambiente.getAmbiente());
            stm.setInt(2, ambiente.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do ambiente no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isAmbienteCadastrado(int codAmbiente) throws SQLException {
        return isCadastrado("SELECT CODAMBIENTE FROM TBAMBIENTE WHERE CODAMBIENTE = "
                + codAmbiente, "Erro na leitura do ambiente no sistema");
    }

    public int getAmbienteCadastrado(String ambiente) throws SQLException {
        return getCadastrado("SELECT CODAMBIENTE FROM TBAMBIENTE WHERE LOWER(AMBIENTE) = '"
                + ambiente.toLowerCase() + "'", "Erro na leitura do ambiente no sistema");
    }

    public boolean isAmbienteVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBAMBIENTE",
                "Erro na leitura de ambientes no sistema");
    }

    public int getProxCodAmbiente() throws SQLException {
        return getProxCod("SELECT MAX(CODAMBIENTE) FROM TBAMBIENTE",
                "Erro na leitura de ambientes no sistema");
    }

    public boolean deleteAmbiente(int codAmbiente) throws SQLException {
        return delete("DELETE FROM TBAMBIENTE WHERE CODAMBIENTE = " + codAmbiente,
                "Não foi possível excluir o ambiente do sistema");
    }

    public void listAmbientes() throws SQLException {
        addAmbientesMapa("SELECT * FROM TBAMBIENTE ORDER BY CODAMBIENTE");
    }

    public void listAmbientesCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addAmbientesMapa(getSql("CODAMBIENTE = " + n));
                break;
            default:
                addAmbientesMapa(getSql("LOWER(AMBIENTE) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT * FROM TBAMBIENTE WHERE " + condicao + " ORDER BY CODAMBIENTE";
    }

    private void addAmbientesMapa(String sql) throws SQLException {
        ambientes.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                ambientes.put(linha, getAmbienteAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de ambientes no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Ambiente getAmbienteAux(ResultSet rs) throws SQLException {
        try {
            Ambiente ambiente = new Ambiente();
            ambiente.setCodigo(rs.getInt(1));
            ambiente.setAmbiente(rs.getString(2));
            return ambiente;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do ambiente no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        ambientes.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Ambiente ambiente = ambientes.get(linha);
        switch (coluna) {
            case 0:
                return ambiente.getCodigo();
            default:
                return ambiente.getAmbiente();
        }
    }

    public int getQtdadeAmbientesCadas() {
        return ambientes.size();
    }

    public Ambiente getAmbienteMapa(int linha) {
        return ambientes.get(linha);
    }
}
