package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Bairro;
import model.Cep;
import model.Cidade;
import model.Endereco;

public class EnderecoDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Endereco> enderecos;

    public EnderecoDAO(Connection con) {
        super(con);
        this.con = con;
        enderecos = new HashMap<Integer, Endereco>();
    }

    public boolean insertEndereco(Endereco endereco) throws SQLException {
        if (isEnderecoCadastrado(endereco.getCodigo())) {
            return updateEndereco(endereco);
        }
        int codEndereco = getCadastrado("SELECT CODENDERECO FROM TBENDERECO WHERE LOWER(ENDERECO) = '"
                + endereco.getEndereco().toLowerCase() + "'", "Erro na leitura do endereço no sistema");// verifica se endereço já está cadastrado
        if (codEndereco != -1) {
            insertEnderecoHasBaiCep(codEndereco, endereco);
            return true;
        }
        insertEnderecoHasBaiCep(insertNovoEndereco(endereco), endereco);
        return true;
    }

    private void insertEnderecoHasBaiCep(int codEndereco, Endereco endereco) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBENDERECO_HAS_TBBAI_CEP (COD_ENDERECO_BAI_CEP, CODENDERECO, COD_BAIRRO_CEP) VALUES (?, ?, ?)");
            stm.setInt(1, endereco.getCodigo());// codigo que é gerado pelo método getProxCodEndereco na tela de cadastro
            stm.setInt(2, codEndereco);// cod endereço obtido através do select
            stm.setInt(3, endereco.getBairro().getCodigo());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endereço no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertNovoEndereco(Endereco endereco) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBENDERECO (CODENDERECO, ENDERECO) VALUES (?, ?)");
            int proxCodEndereco = getProxCod("SELECT MAX(CODENDERECO) FROM TBENDERECO",
                    "Erro na leitura de endereços no sistema");// obtem próximo codigo do endereço
            stm.setInt(1, proxCodEndereco);
            stm.setString(2, endereco.getEndereco());
            stm.execute();// insere novo endereço
            return proxCodEndereco;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endereço no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateEndereco(Endereco endereco) throws SQLException {
        int codAntigoEnd = getCadastrado("SELECT CODENDERECO FROM TBENDERECO_HAS_TBBAI_CEP "
                + "WHERE COD_ENDERECO_BAI_CEP = " + endereco.getCodigo(),
                "Erro na leitura do endereço no sistema");// Retorna o codigo do endereço antigo.
        int codNovoEnd = getCadastrado("SELECT CODENDERECO FROM TBENDERECO WHERE LOWER(ENDERECO) = '"
                + endereco.getEndereco().toLowerCase() + "'", "Erro na leitura do endereço no sistema");// Retorna o codigo do endereço novo.
        if (codNovoEnd == -1) {// verifica se novo endereço ja cadastrado
            int proxCodEndereco = insertNovoEndereco(endereco);// adiciona novo endereço
            updateEnderecoHasBaiCep(proxCodEndereco, endereco.getCodigo());// atualiza tabela com novo endereço
            deleteEndereco(codAntigoEnd);
        } else {
            updateEnderecoHasBaiCep(codNovoEnd, endereco.getCodigo());
            deleteEndereco(codAntigoEnd);
        }
        return true;
    }

    private void updateEnderecoHasBaiCep(int codEnderecoNovo, int codEnderecoHasBaiCep) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBENDERECO_HAS_TBBAI_CEP SET CODENDERECO = ? WHERE COD_ENDERECO_BAI_CEP = ?");
            stm.setInt(1, codEnderecoNovo);
            stm.setInt(2, codEnderecoHasBaiCep);
            stm.execute();// atualiza a tabela TBENDERECO_HAS_TBBAI_CEP
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar o endereço no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private void deleteEndereco(int codAntigoEnd) throws SQLException {
        int qtdade = getCadastrado("SELECT COUNT(*) FROM TBENDERECO_HAS_TBBAI_CEP "
                + "WHERE CODENDERECO = " + codAntigoEnd, "Erro na leitura do endereço no sistema");
        if (qtdade == 0) {
            delete("DELETE FROM TBENDERECO WHERE CODENDERECO = " + codAntigoEnd,
                    "Erro ao atualizar os dados do endereço no sistema");
        }
    }

    private boolean isEnderecoCadastrado(int codEndereco) throws SQLException {
        return isCadastrado("SELECT COD_ENDERECO_BAI_CEP FROM TBENDERECO_HAS_TBBAI_CEP WHERE COD_ENDERECO_BAI_CEP = "
                + codEndereco, "Erro na leitura do endereço no sistema");
    }

    public int getEnderecoCadastrado(String endereco, int codBairro) throws SQLException {
        return getCadastrado("SELECT E.CODENDERECO FROM TBENDERECO E "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON E.CODENDERECO = EB.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "WHERE LOWER(E.ENDERECO) = '" + endereco.toLowerCase() + "' AND BC.COD_BAIRRO_CEP = "
                + codBairro, "Erro na leitura do endereço no sistema");
    }

    public boolean isEnderecoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBENDERECO_HAS_TBBAI_CEP",
                "Erro na leitura dos endereços no sistema");
    }

    public int getProxCodEndereco() throws SQLException {
        return getProxCod("SELECT MAX(COD_ENDERECO_BAI_CEP) FROM TBENDERECO_HAS_TBBAI_CEP",
                "Erro na leitura dos endereços no sistema");
    }

    public void listEnderecos() throws SQLException {
        addEnderecosMapa("SELECT EB.COD_ENDERECO_BAI_CEP, E.ENDERECO, EB.COD_BAIRRO_CEP, B.BAIRRO, "
                + "BC.CODCEP, CEP.CEP, CEP.CODCIDADE, C.CIDADE FROM TBENDERECO_HAS_TBBAI_CEP EB "
                + "INNER JOIN TBENDERECO E ON EB.CODENDERECO = E.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "ORDER BY EB.COD_ENDERECO_BAI_CEP");
    }

    public void listEnderecosCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addEnderecosMapa(getSql("EB.COD_ENDERECO_BAI_CEP = " + n));
                break;
            case 1:
                addEnderecosMapa(getSql("LOWER(E.ENDERECO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addEnderecosMapa(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 3:
                addEnderecosMapa(getSql("CEP.CEP = '" + s + "'"));
                break;
            default:
                addEnderecosMapa(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT EB.COD_ENDERECO_BAI_CEP, E.ENDERECO, EB.COD_BAIRRO_CEP, B.BAIRRO, "
                + "BC.CODCEP, CEP.CEP, CEP.CODCIDADE, C.CIDADE FROM TBENDERECO_HAS_TBBAI_CEP EB "
                + "INNER JOIN TBENDERECO E ON EB.CODENDERECO = E.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "WHERE " + condicao + " ORDER BY EB.COD_ENDERECO_BAI_CEP";
    }

    private void addEnderecosMapa(String sql) throws SQLException {
        enderecos.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                enderecos.put(linha, getEnderecoAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de endereços no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Endereco getEnderecoAux(ResultSet rs) throws SQLException {
        try {
            Endereco endereco = new Endereco();
            endereco.setCodigo(rs.getInt(1));
            endereco.setEndereco(rs.getString(2));
            Bairro bairro = new Bairro();
            bairro.setCodigo(rs.getInt(3));
            bairro.setBairro(rs.getString(4));
            Cep cep = new Cep();
            cep.setCodigo(rs.getInt(5));
            cep.setCep(rs.getString(6));
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(7));
            cidade.setCidade(rs.getString(8));
            cep.setCidade(cidade);
            bairro.setCep(cep);
            endereco.setBairro(bairro);
            return endereco;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do endereço no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        enderecos.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Endereco endereco = enderecos.get(linha);
        switch (coluna) {
            case 0:
                return endereco.getCodigo();
            case 1:
                return endereco.getEndereco();
            case 2:
                return endereco.getBairro().getBairro();
            case 3:
                return endereco.getBairro().getCep().getCep();
            default:
                return endereco.getBairro().getCep().getCidade().getCidade();
        }
    }

    public int getQtdadeEnderecosCadas() {
        return enderecos.size();
    }

    public Endereco getEnderecoMapa(int linha) {
        return enderecos.get(linha);
    }
}
