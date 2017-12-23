package DBOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Bairro;
import Model.CEP;
import Model.Cidade;
import Model.Estado;

public class BairroDAO extends DBDAO {

    private Connection con;

    public BairroDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertBairro(Bairro bairro) throws Exception {
        try {
            if (isBairroCadastrado(bairro.getCodigo())) {
                throw new Exception("Não é possível sobrescrever este bairro");
            }
            int codBairro = getCadastrado("SELECT CODBAIRRO FROM TBBAIRRO WHERE LOWER(BAIRRO) = '" + bairro.getBairro().toLowerCase() + "'",
                    "Erro na leitura do bairro no sistema");// verifica se bairro está cadastrado
            if (codBairro != -1) {
                insertBairroHasCep(codBairro, bairro);
                return true;
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBBAIRRO (CODBAIRRO, BAIRRO) VALUES (?, ?)");
            int proxCodBairro = getProxCod("SELECT MAX(CODBAIRRO) FROM TBBAIRRO",
                    "Erro na leitura de bairros no sistema");// obtem próximo codigo do bairro
            stm.setInt(1, proxCodBairro);
            stm.setString(2, bairro.getBairro());
            stm.execute();// insere novo bairro
            insertBairroHasCep(proxCodBairro, bairro);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o bairro no sistema");
        }
    }

    private void insertBairroHasCep(int codBairro, Bairro bairro) throws SQLException {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBBAIRRO_HAS_TBCEP (COD_BAIRRO_CEP, CODBAIRRO, CODCEP) VALUES (?, ?, ?)");
            stm.setInt(1, bairro.getCodigo());// codigo que é gerado pelo método getProxCodBairro na tela de cadastro
            stm.setInt(2, codBairro);// cod bairro obtido através do select
            stm.setInt(3, bairro.getCep().getCodigo());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o bairro no sistema");
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
                "Erro na leitura dos bairros no sistema");
    }

    public int getProxCodBairro() throws SQLException {
        return getProxCod("SELECT MAX(COD_BAIRRO_CEP) FROM TBBAIRRO_HAS_TBCEP",
                "Erro na leitura dos bairros no sistema");
    }

    public List<Bairro> listBairros() throws SQLException {
        return getLista("SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                + "CE.CODESTADO, E.ESTADO FROM TBBAIRRO_HAS_TBCEP BC "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "ORDER BY BC.COD_BAIRRO_CEP");
    }

    private List<Bairro> getLista(String sql) throws SQLException {
        try {
            List<Bairro> lista = new ArrayList<Bairro>();
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getBairroAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de bairros no sistema");
        }
    }

    private Bairro getBairroAux(ResultSet rs) throws SQLException {
        try {
            Bairro bairro = new Bairro();
            bairro.setCodigo(rs.getInt(1));
            bairro.setBairro(rs.getString(2));
            CEP cep = new CEP();
            cep.setCodigo(rs.getInt(3));
            cep.setCep(rs.getString(4));
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(5));
            cidade.setCidade(rs.getString(6));
            cep.setCidade(cidade);
            Estado estado = new Estado();
            estado.setCodigo(rs.getInt(7));
            estado.setEstado(rs.getString(8));
            cidade.setEstado(estado);
            bairro.setCep(cep);
            return bairro;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do bairro no sistema");
        }
    }

    public List<Bairro> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("BC.COD_BAIRRO_CEP = " + n));
            case 1:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 3:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("LOWER(E.ESTADO) like '%" + s.toLowerCase() + "%'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT BC.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, C.CIDADE, "
                + "CE.CODESTADO, E.ESTADO FROM TBBAIRRO_HAS_TBCEP BC "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO E ON CE.CODESTADO = E.CODESTADO "
                + "WHERE " + condicao + " ORDER BY BC.COD_BAIRRO_CEP";
    }
}
