package Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Endereco;
import Principal.DBDAO;

public class EnderecoDAO extends DBDAO {

    private Connection con;

    public EnderecoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertEndereco(Endereco endereco) throws SQLException {
        try {
            if (isEnderecoCadastrado(endereco.getCodEndereco())) {
                return updateEndereco(endereco);
            }
            int codEndereco = getCadastrado("SELECT CODENDERECO FROM TBENDERECO WHERE LOWER(ENDERECO) = '"
                    + endereco.getEndereco().toLowerCase() + "'", "Erro na leitura do endereço no sistema");// verifica se endereço está cadastrado
            if (codEndereco != -1) {
                insertEnderecoHasBaiCep(codEndereco, endereco);
                return true;
            }
            insertEnderecoHasBaiCep(insertNovoEndereco(endereco), endereco);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endereço no sistema");
        }
    }

    private void insertEnderecoHasBaiCep(int codEndereco, Endereco endereco) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBENDERECO_HAS_TBBAI_CEP (COD_ENDERECO_BAI_CEP, CODENDERECO, COD_BAIRRO_CEP) VALUES (?, ?, ?)");
            stm.setInt(1, endereco.getCodEndereco());// codigo que é gerado pelo método getProxCodEndereco na tela de cadastro
            stm.setInt(2, codEndereco);// cod endereço obtido através do select
            stm.setInt(3, endereco.getCodBairro());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endereço no sistema");
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
            throw new SQLException("Erro ao salvar o endereço no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateEndereco(Endereco endereco) throws SQLException {
        try {
            int codAntigoEnd = getCadastrado("SELECT CODENDERECO FROM TBENDERECO_HAS_TBBAI_CEP "
                    + "WHERE COD_ENDERECO_BAI_CEP = " + endereco.getCodEndereco(),
                    "Erro na leitura do endereço no sistema");// Retorna o codigo do endereço antigo.
            int codNovoEnd = getCadastrado("SELECT CODENDERECO FROM TBENDERECO "
                    + "WHERE LOWER(ENDERECO) = '" + endereco.getEndereco().toLowerCase() + "'",
                    "Erro na leitura do endereço no sistema");// Retorna o codigo do endereço novo.
            if (codNovoEnd == -1) {//verifica se novo endereço ja cadastrado
                int proxCodEndereco = insertNovoEndereco(endereco);//adiciona novo endereço
                updateEnderecoHasBaiCep(proxCodEndereco, endereco.getCodEndereco());//atualiza tabela com novo endereço
                deleteEndereco(codAntigoEnd);
            } else {
                updateEnderecoHasBaiCep(codNovoEnd, endereco.getCodEndereco());
                deleteEndereco(codAntigoEnd);
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do endereço no sistema");
        }
    }

    private void updateEnderecoHasBaiCep(int codEnderecoNovo, int codEnderecoHasBaiCep) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBENDERECO_HAS_TBBAI_CEP SET CODENDERECO = ? WHERE COD_ENDERECO_BAI_CEP = ?");
            stm.setInt(1, codEnderecoNovo);
            stm.setInt(2, codEnderecoHasBaiCep);
            stm.execute();// atualiza a tabela TBENDERECO_HAS_TBBAI_CEP
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do endereço no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private void deleteEndereco(int codAntigoEnd) throws SQLException {
        try {
            int qtdade = getCadastrado("SELECT COUNT(*) FROM TBENDERECO_HAS_TBBAI_CEP "
                    + "WHERE CODENDERECO = " + codAntigoEnd, "Erro na leitura do endereço no sistema");
            if (qtdade == 0) {
                delete("DELETE FROM TBENDERECO WHERE CODENDERECO = "
                        + codAntigoEnd, "Erro ao atualizar os dados do endereço no sistema");
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do endereço no sistema");
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

    public List<Endereco> listEnderecos() throws SQLException {
        return getLista("SELECT EB.COD_ENDERECO_BAI_CEP, EN.ENDERECO, EB.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, "
                + "C.CIDADE, CE.CODESTADO, ES.ESTADO, ES.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBENDERECO_HAS_TBBAI_CEP EB "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON ES.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "ORDER BY EB.COD_ENDERECO_BAI_CEP");
    }

    private List<Endereco> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Endereco> lista = new ArrayList<Endereco>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getEnderecoAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de endereços no sistema");
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
            endereco.setCodEndereco(rs.getInt(1));
            endereco.setEndereco(rs.getString(2));
            endereco.setCodBairro(rs.getInt(3));
            endereco.setBairro(rs.getString(4));
            endereco.setCodCep(rs.getInt(5));
            endereco.setCep(rs.getString(6));
            endereco.setCodCidade(rs.getInt(7));
            endereco.setCidade(rs.getString(8));
            endereco.setCodEstado(rs.getInt(9));
            endereco.setEstado(rs.getString(10));
            endereco.setCodRegiao(rs.getInt(11));
            endereco.setRegiao(rs.getString(12));
            endereco.setCodPais(rs.getInt(13));
            endereco.setPais(rs.getString(14));
            return endereco;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do endereço no sistema");
        }
    }

    public List<Endereco> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("EB.COD_ENDERECO_BAI_CEP = " + n));
            case 1:
                return getLista(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 3:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 4:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            case 5:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
            case 6:
                return getLista(getSql("LOWER(R.REGIAO) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("LOWER(P.PAIS) like '%" + s.toLowerCase() + "%'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT EB.COD_ENDERECO_BAI_CEP, EN.ENDERECO, EB.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, "
                + "C.CIDADE, CE.CODESTADO, ES.ESTADO, ES.COD_REGIAO_PAIS, R.REGIAO, RP.CODPAIS, P.PAIS FROM TBENDERECO_HAS_TBBAI_CEP EB "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON ES.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "INNER JOIN TBPAIS P ON RP.CODPAIS = P.CODPAIS "
                + "WHERE " + condicao + " ORDER BY EB.COD_ENDERECO_BAI_CEP";
    }
}
