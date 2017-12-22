package dbOracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import model.Ambiente;
import model.Empresa;
import model.Fornecedor;
import model.Patrimonio;

public class PatrimonioDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Patrimonio> patrimonios;

    public PatrimonioDAO(Connection con) {
        super(con);
        this.con = con;
        patrimonios = new HashMap<Integer, Patrimonio>();
    }

    public boolean insertPatrimonio(Patrimonio patrimonio) throws SQLException {
        if (isPatrimonioCadastrado(patrimonio.getCodigo())) {
            return updatePatrimonio(patrimonio);
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPATRIMONIO (CODPATRIMONIO, DATACADAS, ULTALTERACAO, DESCRICAO, "
                    + "DATAAQUISICAO, NUMNOTAFISCAL, NUMSERIE, MESESGARANTIA, VALOR, SITUACAOBEM, CODAMBIENTE, CODFORN, "
                    + "CODEMP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, patrimonio.getCodigo());
            stm.setTimestamp(2, new Timestamp(patrimonio.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(patrimonio.getUltAlteracao().getTime()));
            stm.setString(4, patrimonio.getDescricao());
            stm.setDate(5, new Date(patrimonio.getDataAquisicao().getTime()));
            stm.setInt(6, patrimonio.getNumNotaFiscal());
            stm.setString(7, patrimonio.getNumSerie());
            stm.setInt(8, patrimonio.getMesesGarantia());
            stm.setDouble(9, patrimonio.getValor());
            stm.setInt(10, patrimonio.getSituacaoBem());
            stm.setInt(11, patrimonio.getAmbiente().getCodigo());
            stm.setInt(12, patrimonio.getFornecedor().getCodigo());
            stm.setInt(13, patrimonio.getEmpresa().getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o patrimonio no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updatePatrimonio(Patrimonio patrimonio) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBPATRIMONIO SET DATACADAS = ?, ULTALTERACAO = ?, DESCRICAO = ?, "
                    + "DATAAQUISICAO = ?, NUMNOTAFISCAL = ?, NUMSERIE = ?, MESESGARANTIA = ?, VALOR = ?, SITUACAOBEM = ?, "
                    + "CODAMBIENTE = ?, CODFORN = ?, CODEMP = ? WHERE CODPATRIMONIO = ?");
            stm.setTimestamp(1, new Timestamp(patrimonio.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(patrimonio.getUltAlteracao().getTime()));
            stm.setString(3, patrimonio.getDescricao());
            stm.setDate(4, new Date(patrimonio.getDataAquisicao().getTime()));
            stm.setInt(5, patrimonio.getNumNotaFiscal());
            stm.setString(6, patrimonio.getNumSerie());
            stm.setInt(7, patrimonio.getMesesGarantia());
            stm.setDouble(8, patrimonio.getValor());
            stm.setInt(9, patrimonio.getSituacaoBem());
            stm.setInt(10, patrimonio.getAmbiente().getCodigo());
            stm.setInt(11, patrimonio.getFornecedor().getCodigo());
            stm.setInt(12, patrimonio.getEmpresa().getCodigo());
            stm.setInt(13, patrimonio.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do patrimonio no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isPatrimonioCadastrado(int codPatrimonio) throws SQLException {
        return isCadastrado("SELECT CODPATRIMONIO FROM TBPATRIMONIO WHERE CODPATRIMONIO = "
                + codPatrimonio, "Erro na leitura do patrimonio no sistema");
    }

    public int getPatrimonioCadastrado(String numSerie, int codPatrimonio) throws SQLException {
        return getCadastrado("SELECT CODPATRIMONIO FROM TBPATRIMONIO WHERE NUMSERIE = '"
                + numSerie + "' AND CODPATRIMONIO = " + codPatrimonio, "Erro na leitura do patrimonio no sistema");
    }

    public int getPatrimonioCadastrado(String numSerie) throws SQLException {
        return getCadastrado("SELECT CODPATRIMONIO FROM TBPATRIMONIO WHERE NUMSERIE = '"
                + numSerie + "'", "Erro na leitura do patrimonio no sistema");
    }

    public boolean isPatrimonioVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBPATRIMONIO",
                "Erro na leitura de patrimonios no sistema");
    }

    public int getProxCodPatrimonio() throws SQLException {
        return getProxCod("SELECT MAX(CODPATRIMONIO) FROM TBPATRIMONIO",
                "Erro na leitura de patrimonios no sistema");
    }

    public boolean deletePatrimonio(int codPatrimonio) throws SQLException {
        return delete("DELETE FROM TBPATRIMONIO WHERE CODPATRIMONIO = "
                + codPatrimonio, "Não foi possível excluir o patrimonio do sistema");
    }

    public void listPatrimonios() throws SQLException {
        addPatrimoniosMapa("SELECT P.CODPATRIMONIO, P.DATACADAS, P.ULTALTERACAO, P.DESCRICAO, P.CODAMBIENTE, "
                + "A.AMBIENTE, P.DATAAQUISICAO, P.NUMNOTAFISCAL, P.NUMSERIE, P.MESESGARANTIA, P.VALOR, "
                + "P.CODFORN, PF.NOME, P.CODEMP, PE.NOME, P.SITUACAOBEM FROM TBPATRIMONIO P "
                + "INNER JOIN TBAMBIENTE A ON P.CODAMBIENTE = A.CODAMBIENTE "
                + "INNER JOIN TBFORNECEDOR F ON P.CODFORN = F.CODFORN "
                + "INNER JOIN TBPESSOA PF ON F.CODPESSOA = PF.CODPESSOA "
                + "INNER JOIN TBEMPRESA E ON P.CODEMP = E.CODEMP "
                + "INNER JOIN TBPESSOA PE ON E.CODPESSOA = PE.CODPESSOA "
                + "ORDER BY P.CODPATRIMONIO");
    }

    public void listPatrimoniosCondicao(final int coluna, final String s, final int n, final double d) throws SQLException {
        switch (coluna) {
            case 0:
                addPatrimoniosMapa(getSql("P.CODPATRIMONIO = " + n));
                break;
            case 1:
                addPatrimoniosMapa(getSql("LOWER(P.DESCRICAO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addPatrimoniosMapa(getSql("P.NUMSERIE = '" + s + "'"));
                break;
            case 3:
                addPatrimoniosMapa(getSql("LOWER(A.AMBIENTE) like '%" + s.toLowerCase() + "%'"));
                break;
            case 4:
                addPatrimoniosMapa(getSql("P.VALOR = " + d));
                break;
            case 5:
                addPatrimoniosMapa(getSql("LOWER(PF.NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            case 6:
                addPatrimoniosMapa(getSql("LOWER(PE.NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            default:
                if (s.equalsIgnoreCase("Ativo")) {
                    addPatrimoniosMapa(getSql("P.SITUACAOBEM = 1"));
                    break;
                } else {
                    if (s.equalsIgnoreCase("Com Defeito")) {
                        addPatrimoniosMapa(getSql("P.SITUACAOBEM = 2"));
                        break;
                    } else {
                        if (s.equalsIgnoreCase("Conserto")) {
                            addPatrimoniosMapa(getSql("P.SITUACAOBEM = 3"));
                            break;
                        } else {
                            if (s.equalsIgnoreCase("Inativo")) {
                                addPatrimoniosMapa(getSql("P.SITUACAOBEM = 4"));
                                break;
                            }
                        }
                    }
                }
        }
    }

    private String getSql(String condicao) {
        return "SELECT P.CODPATRIMONIO, P.DATACADAS, P.ULTALTERACAO, P.DESCRICAO, P.CODAMBIENTE, "
                + "A.AMBIENTE, P.DATAAQUISICAO, P.NUMNOTAFISCAL, P.NUMSERIE, P.MESESGARANTIA, P.VALOR, "
                + "P.CODFORN, PF.NOME, P.CODEMP, PE.NOME, P.SITUACAOBEM FROM TBPATRIMONIO P "
                + "INNER JOIN TBAMBIENTE A ON P.CODAMBIENTE = A.CODAMBIENTE "
                + "INNER JOIN TBFORNECEDOR F ON P.CODFORN = F.CODFORN "
                + "INNER JOIN TBPESSOA PF ON F.CODPESSOA = PF.CODPESSOA "
                + "INNER JOIN TBEMPRESA E ON P.CODEMP = E.CODEMP "
                + "INNER JOIN TBPESSOA PE ON E.CODPESSOA = PE.CODPESSOA "
                + "WHERE " + condicao + " ORDER BY P.CODPATRIMONIO";
    }

    private void addPatrimoniosMapa(String sql) throws SQLException {
        patrimonios.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                patrimonios.put(linha, getPatrimonioAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de patrimonios no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Patrimonio getPatrimonioAux(ResultSet rs) throws SQLException {
        try {
            Patrimonio patrimonio = new Patrimonio();
            patrimonio.setCodigo(rs.getInt(1));
            patrimonio.setDataCadastro(rs.getTimestamp(2));
            patrimonio.setUltAlteracao(rs.getTimestamp(3));
            patrimonio.setDescricao(rs.getString(4));
            Ambiente ambiente = new Ambiente();
            ambiente.setCodigo(rs.getInt(5));
            ambiente.setAmbiente(rs.getString(6));
            patrimonio.setAmbiente(ambiente);
            patrimonio.setDataAquisicao(rs.getDate(7));
            patrimonio.setNumNotaFiscal(rs.getInt(8));
            patrimonio.setNumSerie(rs.getString(9));
            patrimonio.setMesesGarantia(rs.getInt(10));
            patrimonio.setValor(rs.getDouble(11));
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCodigo(rs.getInt(12));
            fornecedor.setNome(rs.getString(13));
            patrimonio.setFornecedor(fornecedor);
            Empresa empresa = new Empresa();
            empresa.setCodigo(rs.getInt(14));
            empresa.setNome(rs.getString(15));
            patrimonio.setEmpresa(empresa);
            patrimonio.setSituacaoBem(rs.getInt(16));
            return patrimonio;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do patrimonio no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        patrimonios.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Patrimonio patrimonio = patrimonios.get(linha);
        switch (coluna) {
            case 0:
                return patrimonio.getCodigo();
            case 1:
                return patrimonio.getDescricao();
            case 2:
                return patrimonio.getNumSerie();
            case 3:
                return patrimonio.getAmbiente().getAmbiente();
            case 4:
                return NumberFormat.getCurrencyInstance().format(patrimonio.getValor());
            case 5:
                return patrimonio.getFornecedor().getNome();
            case 6:
                return patrimonio.getEmpresa().getNome();
            default:
                switch (patrimonio.getSituacaoBem()) {
                    case 1:
                        return "Ativo";
                    case 2:
                        return "Com Defeito";
                    case 3:
                        return "Conserto";
                    default:
                        return "Inativo";
                }
        }
    }

    public int getQtdadePatrimoniosCadas() {
        return patrimonios.size();
    }

    public Patrimonio getPatrimonioMapa(int linha) {
        return patrimonios.get(linha);
    }
}
