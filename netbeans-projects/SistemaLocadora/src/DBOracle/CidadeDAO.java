package DBOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Cidade;
import Model.Estado;

public class CidadeDAO extends DBDAO {

    private Connection con;

    public CidadeDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertCidade(Cidade cidade) throws Exception {
        try {
            if (isCidadeCadastrada(cidade.getCodigo())) {
                throw new Exception("Não é possível sobrescrever esta cidade");
            }
            int codCidade = getCadastrado("SELECT CODCIDADE FROM TBCIDADE WHERE LOWER(CIDADE) = " + "'" + cidade.getCidade().toLowerCase() + "'",
                    "Erro na leitura da cidade no sistema");// verifica se cidade está cadastrada
            if (codCidade != -1) {
                insertCidadeHasEstado(codCidade, cidade);
                return true;
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBCIDADE (CODCIDADE, CIDADE) VALUES (?, ?)");
            int proxCodCidade = getProxCod("SELECT MAX(CODCIDADE) FROM TBCIDADE",
                    "Erro na leitura de cidades no sistema");// obtem próximo codigo da cidade
            stm.setInt(1, proxCodCidade);
            stm.setString(2, cidade.getCidade());
            stm.execute();// insere nova cidade
            insertCidadeHasEstado(proxCodCidade, cidade);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a cidade no sistema");
        }
    }

    private void insertCidadeHasEstado(int codCidade, Cidade cidade) throws SQLException {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBCIDADE_HAS_TBESTADO (COD_CIDADE_ESTADO, CODCIDADE, CODESTADO) VALUES (?, ?, ?)");
            stm.setInt(1, cidade.getCodigo());// codigo que é gerado pelo método getProxCodCidade na tela de cadastro
            stm.setInt(2, codCidade);// cod cidade obtido através do select
            stm.setInt(3, cidade.getEstado().getCodigo());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a cidade no sistema");
        }
    }

    public int getCidadeCadastrada(String cidade, int codEstado) throws SQLException {
        return getCadastrado("SELECT C.CODCIDADE FROM TBCIDADE C "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON C.CODCIDADE = CE.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "WHERE LOWER(C.CIDADE) = '" + cidade.toLowerCase()
                + "' AND E.CODESTADO = " + codEstado,
                "Erro na leitura da cidade no sistema");
    }

    public boolean isCidadeCadastrada(int codCidade) throws SQLException {
        return isCadastrado("SELECT COD_CIDADE_ESTADO FROM TBCIDADE_HAS_TBESTADO WHERE COD_CIDADE_ESTADO = "
                + codCidade, "Erro na leitura da cidade no sistema");
    }

    public boolean isCidadeVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCIDADE_HAS_TBESTADO",
                "Erro na leitura de cidades no sistema");
    }

    public int getProxCodCidade() throws SQLException {
        return getProxCod("SELECT MAX(COD_CIDADE_ESTADO) FROM TBCIDADE_HAS_TBESTADO",
                "Erro na leitura de cidades no sistema");
    }

    public List<Cidade> listCidades() throws SQLException {
        return getLista("SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO "
                + "FROM TBCIDADE_HAS_TBESTADO CE "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "ORDER BY CE.COD_CIDADE_ESTADO");
    }

    private List<Cidade> getLista(String sql) throws SQLException {
        try {
            List<Cidade> lista = new ArrayList<Cidade>();
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getCidadeAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Erro na leitura de cidades no sistema");
        }
    }

    private Cidade getCidadeAux(ResultSet rs) throws SQLException {
        try {
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(1));
            cidade.setCidade(rs.getString(2));
            Estado estado = new Estado();
            estado.setCodigo(rs.getInt(3));
            estado.setEstado(rs.getString(4));
            cidade.setEstado(estado);
            return cidade;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Erro na leitura da cidade no sistema");
        }
    }

    public List<Cidade> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("CE.COD_CIDADE_ESTADO = " + n));
            case 1:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT CE.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO "
                + "FROM TBCIDADE_HAS_TBESTADO CE "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "WHERE " + condicao + " ORDER BY CE.COD_CIDADE_ESTADO";
    }
}
