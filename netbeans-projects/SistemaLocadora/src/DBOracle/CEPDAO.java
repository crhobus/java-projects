package DBOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CEP;
import Model.Cidade;
import Model.Estado;

public class CEPDAO extends DBDAO {

    private Connection con;

    public CEPDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertCep(CEP cep) throws Exception {
        try {
            if (isCepCadastrado(cep.getCodigo())) {
                throw new Exception("Não é possível sobrescrever este CEP");
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBCEP (CODCEP, COD_CIDADE_ESTADO, CEP) VALUES (?, ?, ?)");
            stm.setInt(1, cep.getCodigo());
            stm.setInt(2, cep.getCidade().getCodigo());
            stm.setString(3, cep.getCep());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o CEP no sistema");
        }
    }

    public int getCepCadastrado(String cep) throws SQLException {
        return getCadastrado("SELECT CODCEP FROM TBCEP WHERE CEP = " + "'"
                + cep + "'", "Erro na leitura do CEP no sistema");
    }

    public boolean isCepCadastrado(int codCep) throws SQLException {
        return isCadastrado("SELECT CODCEP FROM TBCEP WHERE CODCEP = " + codCep,
                "Erro na leitura do CEP no sistema");
    }

    public boolean isCepVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCEP",
                "Erro na leitura dos CEPs no sistema");
    }

    public int getProxCodCep() throws SQLException {
        return getProxCod("SELECT MAX(CODCEP) FROM TBCEP",
                "Erro na leitura dos CEPs no sistema");
    }

    public List<CEP> listCeps() throws SQLException {
        return getLista("SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO FROM TBCEP CEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "ORDER BY CEP.CODCEP");
    }

    private List<CEP> getLista(String sql) throws SQLException {
        try {
            List<CEP> lista = new ArrayList<CEP>();
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getCepAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura dos CEPs no sistema");
        }
    }

    private CEP getCepAux(ResultSet rs) throws SQLException {
        try {
            CEP cep = new CEP();
            cep.setCodigo(rs.getInt(1));
            cep.setCep(rs.getString(2));
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(3));
            cidade.setCidade(rs.getString(4));
            Estado estado = new Estado();
            estado.setCodigo(rs.getInt(5));
            estado.setEstado(rs.getString(6));
            cidade.setEstado(estado);
            cep.setCidade(cidade);
            return cep;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do CEPs no sistema");
        }
    }

    public List<CEP> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("CEP.CODCEP = " + n));
            case 1:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 2:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT CEP.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, CE.CODESTADO, E.ESTADO FROM TBCEP CEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "WHERE " + condicao + " ORDER BY CE.COD_CIDADE_ESTADO";
    }
}
