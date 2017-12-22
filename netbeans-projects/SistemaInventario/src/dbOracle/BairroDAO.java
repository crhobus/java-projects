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

public class BairroDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Bairro> bairros;

    public BairroDAO(Connection con) {
        super(con);
        this.con = con;
        bairros = new HashMap<Integer, Bairro>();
    }

    public boolean insertBairro(Bairro bairro) throws Exception {
        if (isBairroCadastrado(bairro.getCodigo())) {
            throw new Exception("Não é possível sobrescrever o bairro\nEntre com um novo bairro");
        }
        int codBairro = getCadastrado("SELECT CODBAIRRO FROM TBBAIRRO WHERE LOWER(BAIRRO) = '"
                + bairro.getBairro().toLowerCase() + "'", "Erro na leitura do bairro no sistema");// verifica se bairro ja está cadastrado
        if (codBairro != -1) {
            insertBairroHasCep(codBairro, bairro);
            return true;
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBBAIRRO (CODBAIRRO, BAIRRO) VALUES (?, ?)");
            int proxCodBairro = getProxCod("SELECT MAX(CODBAIRRO) FROM TBBAIRRO",
                    "Erro na leitura de bairros no sistema");// obtem próximo codigo do bairro
            stm.setInt(1, proxCodBairro);
            stm.setString(2, bairro.getBairro());
            stm.execute();// insere novo bairro
            insertBairroHasCep(proxCodBairro, bairro);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o bairro no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private void insertBairroHasCep(int codBairro, Bairro bairro) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBBAIRRO_HAS_TBCEP (COD_BAIRRO_CEP, CODBAIRRO, CODCEP) VALUES (?, ?, ?)");
            stm.setInt(1, bairro.getCodigo());// codigo que é gerado pelo método getProxCodBairro na tela de cadastro
            stm.setInt(2, codBairro);// cod bairro obtido através do select
            stm.setInt(3, bairro.getCep().getCodigo());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o bairro no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public int getBairroCadastrado(String bairro, int codCep) throws SQLException {
        return getCadastrado("SELECT B.CODBAIRRO FROM TBBAIRRO B "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON B.CODBAIRRO = BC.CODBAIRRO "
                + "INNER JOIN TBCEP CE ON BC.CODCEP = CE.CODCEP "
                + "WHERE LOWER(B.BAIRRO) = '" + bairro.toLowerCase() + "' AND CE.CODCEP = " + codCep,
                "Erro na leitura do bairro no sistema");
    }

    public boolean isBairroCadastrado(int codBairro) throws SQLException {
        return isCadastrado("SELECT COD_BAIRRO_CEP FROM TBBAIRRO_HAS_TBCEP WHERE COD_BAIRRO_CEP = "
                + codBairro, "Erro na leitura do bairro no sistema");
    }

    public boolean isBairroVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBBAIRRO_HAS_TBCEP",
                "Erro na leitura de bairros no sistema");
    }

    public int getProxCodBairro() throws SQLException {
        return getProxCod("SELECT MAX(COD_BAIRRO_CEP) FROM TBBAIRRO_HAS_TBCEP",
                "Erro na leitura de bairros no sistema");
    }

    public void listBairros() throws SQLException {
        addBairrosMapa("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, "
                + "CEP.CODCIDADE, C.CIDADE FROM TBBAIRRO_HAS_TBCEP BC "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "ORDER BY BC.COD_BAIRRO_CEP");
    }

    public void listBairrosCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addBairrosMapa(getSql("BC.COD_BAIRRO_CEP = " + n));
                break;
            case 1:
                addBairrosMapa(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addBairrosMapa(getSql("CEP.CEP = '" + s + "'"));
                break;
            default:
                addBairrosMapa(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, "
                + "CEP.CODCIDADE, C.CIDADE FROM TBBAIRRO_HAS_TBCEP BC "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "WHERE " + condicao + " ORDER BY BC.COD_BAIRRO_CEP";
    }

    private void addBairrosMapa(String sql) throws SQLException {
        bairros.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                bairros.put(linha, getBairroAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de bairros no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Bairro getBairroAux(ResultSet rs) throws SQLException {
        try {
            Bairro bairro = new Bairro();
            bairro.setCodigo(rs.getInt(1));
            bairro.setBairro(rs.getString(2));
            Cep cep = new Cep();
            cep.setCodigo(rs.getInt(3));
            cep.setCep(rs.getString(4));
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(5));
            cidade.setCidade(rs.getString(6));
            cep.setCidade(cidade);
            bairro.setCep(cep);
            return bairro;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do bairro no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        bairros.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Bairro bairro = bairros.get(linha);
        switch (coluna) {
            case 0:
                return bairro.getCodigo();
            case 1:
                return bairro.getBairro();
            case 2:
                return bairro.getCep().getCep();
            default:
                return bairro.getCep().getCidade().getCidade();
        }
    }

    public int getQtdadeBairrosCadas() {
        return bairros.size();
    }

    public Bairro getBairroMapa(int linha) {
        return bairros.get(linha);
    }
}
