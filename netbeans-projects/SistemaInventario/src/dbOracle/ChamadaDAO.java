package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import model.Ambiente;
import model.Chamada;
import model.Defeito;
import model.Funcionario;
import model.Patrimonio;

public class ChamadaDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Chamada> chamadas;

    public ChamadaDAO(Connection con) {
        super(con);
        this.con = con;
        chamadas = new HashMap<Integer, Chamada>();
    }

    public boolean insertChamada(Chamada chamada) throws Exception {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCHAMADA (NUMCHAMADA, DATAREALIZACAO, SITUACAO, "
                    + "NUMOCORRENCIAS, CODAMBIENTE, CODPATRIMONIO, CODFUNC, CODDEFEITO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, chamada.getNumeroCha());
            stm.setTimestamp(2, new Timestamp(chamada.getDataRealizacao().getTime()));
            stm.setString(3, chamada.getSituacao());
            stm.setInt(4, chamada.getNumOcorrencias());
            stm.setInt(5, chamada.getAmbiente().getCodigo());
            stm.setInt(6, chamada.getPatrimonio().getCodigo());
            stm.setInt(7, chamada.getFuncionario().getCodigo());
            stm.setInt(8, chamada.getDefeito().getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a chamada no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateSituacaoChamada(String situacao, int numChamada) throws Exception {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBCHAMADA SET SITUACAO = ? WHERE NUMCHAMADA = ?");
            stm.setString(1, situacao);
            stm.setInt(2, numChamada);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da chamada no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getQtdadePatrimonio(int codPatrimonio) throws SQLException {
        return getCadastrado("SELECT COUNT(*) FROM TBCHAMADA WHERE CODPATRIMONIO = "
                + codPatrimonio, "Erro na leitura da chamada no sistema");
    }

    public boolean isChamadaVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCHAMADA",
                "Erro na leitura de chamadas no sistema");
    }

    public int getProxCodChamada() throws SQLException {
        return getProxCod("SELECT MAX(NUMCHAMADA) FROM TBCHAMADA",
                "Erro na leitura de chamadas no sistema");
    }

    public String getHistoricoDefeito(int codPatrimonio) throws SQLException {
        String defeitos = "";
        ResultSet rs = null;
        try {
            rs = con.prepareStatement("SELECT D.DEFEITO FROM TBCHAMADA C "
                    + "INNER JOIN TBDEFEITO D ON C.CODDEFEITO = D.CODDEFEITO "
                    + "WHERE C.CODPATRIMONIO = " + codPatrimonio + " ORDER BY C.NUMCHAMADA").executeQuery();
            while (rs.next()) {
                defeitos += rs.getString(1) + "\n";
            }
            return defeitos;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da chamada no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void listChamadas() throws SQLException {
        addChamadasMapa("SELECT C.NUMCHAMADA, C.DATAREALIZACAO, C.SITUACAO, C.CODFUNC, "
                + "PF.NOME, C.CODPATRIMONIO, P.DESCRICAO, C.CODAMBIENTE, A.AMBIENTE, "
                + "C.CODDEFEITO, D.DEFEITO, C.NUMOCORRENCIAS FROM TBCHAMADA C "
                + "INNER JOIN TBFUNCIONARIO F ON C.CODFUNC = F.CODFUNC "
                + "INNER JOIN TBPESSOA PF ON F.CODPESSOA = PF.CODPESSOA "
                + "INNER JOIN TBPATRIMONIO P ON C.CODPATRIMONIO = P.CODPATRIMONIO "
                + "INNER JOIN TBAMBIENTE A ON C.CODAMBIENTE = A.CODAMBIENTE "
                + "INNER JOIN TBDEFEITO D ON C.CODDEFEITO = D.CODDEFEITO "
                + "ORDER BY C.NUMCHAMADA");
    }

    public void listChamadasCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addChamadasMapa(getSql("C.NUMCHAMADA = " + n));
                break;
            case 1:
                addChamadasMapa(getSql("LOWER(C.SITUACAO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addChamadasMapa(getSql("LOWER(PF.NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            case 3:
                addChamadasMapa(getSql("LOWER(P.DESCRICAO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 4:
                addChamadasMapa(getSql("LOWER(A.AMBIENTE) like '%" + s.toLowerCase() + "%'"));
                break;
            default:
                addChamadasMapa(getSql("C.NUMOCORRENCIAS = " + n));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT C.NUMCHAMADA, C.DATAREALIZACAO, C.SITUACAO, C.CODFUNC, "
                + "PF.NOME, C.CODPATRIMONIO, P.DESCRICAO, C.CODAMBIENTE, A.AMBIENTE, "
                + "C.CODDEFEITO, D.DEFEITO, C.NUMOCORRENCIAS FROM TBCHAMADA C "
                + "INNER JOIN TBFUNCIONARIO F ON C.CODFUNC = F.CODFUNC "
                + "INNER JOIN TBPESSOA PF ON F.CODPESSOA = PF.CODPESSOA "
                + "INNER JOIN TBPATRIMONIO P ON C.CODPATRIMONIO = P.CODPATRIMONIO "
                + "INNER JOIN TBAMBIENTE A ON C.CODAMBIENTE = A.CODAMBIENTE "
                + "INNER JOIN TBDEFEITO D ON C.CODDEFEITO = D.CODDEFEITO "
                + "WHERE " + condicao + " ORDER BY C.NUMCHAMADA";
    }

    private void addChamadasMapa(String sql) throws SQLException {
        chamadas.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                chamadas.put(linha, getChamadaAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de chamadas no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Chamada getChamadaAux(ResultSet rs) throws SQLException {
        try {
            Chamada chamada = new Chamada();
            chamada.setNumeroCha(rs.getInt(1));
            chamada.setDataRealizacao(rs.getTimestamp(2));
            chamada.setSituacao(rs.getString(3));
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(rs.getInt(4));
            funcionario.setNome(rs.getString(5));
            chamada.setFuncionario(funcionario);
            Patrimonio patrimonio = new Patrimonio();
            patrimonio.setCodigo(rs.getInt(6));
            patrimonio.setDescricao(rs.getString(7));
            chamada.setPatrimonio(patrimonio);
            Ambiente ambiente = new Ambiente();
            ambiente.setCodigo(rs.getInt(8));
            ambiente.setAmbiente(rs.getString(9));
            chamada.setAmbiente(ambiente);
            Defeito defeito = new Defeito();
            defeito.setCodigo(rs.getInt(10));
            defeito.setDefeito(rs.getString(11));
            chamada.setDefeito(defeito);
            chamada.setNumOcorrencias(rs.getInt(12));
            return chamada;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da chamada no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        chamadas.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Chamada chamada = chamadas.get(linha);
        switch (coluna) {
            case 0:
                return chamada.getNumeroCha();
            case 1:
                return chamada.getSituacao();
            case 2:
                return chamada.getFuncionario().getNome();
            case 3:
                return chamada.getPatrimonio().getDescricao();
            case 4:
                return chamada.getAmbiente().getAmbiente();
            default:
                return chamada.getNumOcorrencias();
        }
    }

    public int getQtdadeChamadasCadas() {
        return chamadas.size();
    }

    public Chamada getChamadaMapa(int linha) {
        return chamadas.get(linha);
    }
}
