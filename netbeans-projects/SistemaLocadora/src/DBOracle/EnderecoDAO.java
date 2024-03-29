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
import Model.Endereco;
import Model.Estado;

public class EnderecoDAO extends DBDAO {

    private Connection con;

    public EnderecoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertEndereco(Endereco endereco) throws SQLException {
        try {
            if (isEnderecoCadastrado(endereco.getCodigo())) {
                return updateEndereco(endereco);
            }
            int codEndereco = getCadastrado("SELECT CODENDERECO FROM TBENDERECO WHERE LOWER(ENDERECO) = '"
                    + endereco.getEndereco().toLowerCase() + "'", "Erro na leitura do endere�o no sistema");// verifica se endere�o est� cadastrado
            if (codEndereco != -1) {
                insertEnderecoHasBaiCep(codEndereco, endereco);
                return true;
            }
            insertEnderecoHasBaiCep(insertNovoEndereco(endereco), endereco);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endere�o no sistema");
        }
    }

    private void insertEnderecoHasBaiCep(int codEndereco, Endereco endereco) throws SQLException {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBENDERECO_HAS_TBBAI_CEP (COD_ENDERECO_BAI_CEP, CODENDERECO, COD_BAIRRO_CEP) VALUES (?, ?, ?)");
            stm.setInt(1, endereco.getCodigo());// codigo que � gerado pelo m�todo getProxCodEndereco na tela de cadastro
            stm.setInt(2, codEndereco);// cod endere�o obtido atrav�s do select
            stm.setInt(3, endereco.getBairro().getCodigo());
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endere�o no sistema");
        }
    }

    private int insertNovoEndereco(Endereco endereco) throws SQLException {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBENDERECO (CODENDERECO, ENDERECO) VALUES (?, ?)");
            int proxCodEndereco = getProxCod("SELECT MAX(CODENDERECO) FROM TBENDERECO",
                    "Erro na leitura de endere�os no sistema");// obtem pr�ximo codigo do endere�o
            stm.setInt(1, proxCodEndereco);
            stm.setString(2, endereco.getEndereco());
            stm.execute();// insere novo endere�o
            return proxCodEndereco;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o endere�o no sistema");
        }
    }

    private boolean updateEndereco(Endereco endereco) throws SQLException {
        try {
            int codAntigoEnd = getCadastrado("SELECT CODENDERECO FROM TBENDERECO_HAS_TBBAI_CEP "
                    + "WHERE COD_ENDERECO_BAI_CEP = " + endereco.getCodigo(),
                    "Erro na leitura do endere�o no sistema");// Retorna o codigo do endere�o antigo.
            int codNovoEnd = getCadastrado("SELECT CODENDERECO FROM TBENDERECO "
                    + "WHERE LOWER(ENDERECO) = '" + endereco.getEndereco().toLowerCase() + "'",
                    "Erro na leitura do endere�o no sistema");// Retorna o codigo do endere�o novo.
            if (codNovoEnd == -1) {//verifica se novo endere�o ja cadastrado
                int proxCodEndereco = insertNovoEndereco(endereco);//adiciona novo endere�o
                updateEnderecoHasBaiCep(proxCodEndereco, endereco.getCodigo());//atualiza tabela com novo endere�o
                deleteEndereco(codAntigoEnd);
            } else {
                updateEnderecoHasBaiCep(codNovoEnd, endereco.getCodigo());
                deleteEndereco(codAntigoEnd);
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do endere�o no sistema");
        }
    }

    private void updateEnderecoHasBaiCep(int codEnderecoNovo, int codEnderecoHasBaiCep) throws SQLException {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBENDERECO_HAS_TBBAI_CEP SET CODENDERECO = ? WHERE COD_ENDERECO_BAI_CEP = ?");
            stm.setInt(1, codEnderecoNovo);
            stm.setInt(2, codEnderecoHasBaiCep);
            stm.execute();// atualiza a tabela TBENDERECO_HAS_TBBAI_CEP
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do endere�o no sistema");
        }
    }

    private void deleteEndereco(int codAntigoEnd) throws SQLException {
        try {
            int qtdade = getCadastrado("SELECT COUNT(*) FROM TBENDERECO_HAS_TBBAI_CEP "
                    + "WHERE CODENDERECO = " + codAntigoEnd, "Erro na leitura do endere�o no sistema");
            if (qtdade == 0) {
                delete("DELETE FROM TBENDERECO WHERE CODENDERECO = "
                        + codAntigoEnd, "Erro ao atualizar os dados do endere�o no sistema");
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do endere�o no sistema");
        }
    }

    private boolean isEnderecoCadastrado(int codEndereco) throws SQLException {
        return isCadastrado("SELECT COD_ENDERECO_BAI_CEP FROM TBENDERECO_HAS_TBBAI_CEP WHERE COD_ENDERECO_BAI_CEP = "
                + codEndereco, "Erro na leitura do endere�o no sistema");
    }

    public int getEnderecoCadastrado(String endereco, int codBairro) throws SQLException {
        return getCadastrado("SELECT E.CODENDERECO FROM TBENDERECO E "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON E.CODENDERECO = EB.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "WHERE LOWER(E.ENDERECO) = '" + endereco.toLowerCase() + "' AND BC.COD_BAIRRO_CEP = "
                + codBairro, "Erro na leitura do endere�o no sistema");
    }

    public boolean isEnderecoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBENDERECO_HAS_TBBAI_CEP",
                "Erro na leitura dos endere�os no sistema");
    }

    public int getProxCodEndereco() throws SQLException {
        return getProxCod("SELECT MAX(COD_ENDERECO_BAI_CEP) FROM TBENDERECO_HAS_TBBAI_CEP",
                "Erro na leitura dos endere�os no sistema");
    }

    public List<Endereco> listEnderecos() throws SQLException {
        return getLista("SELECT EB.COD_ENDERECO_BAI_CEP, EN.ENDERECO, EB.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, "
                + "C.CIDADE, CE.CODESTADO, ES.ESTADO FROM TBENDERECO_HAS_TBBAI_CEP EB "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "ORDER BY EB.COD_ENDERECO_BAI_CEP");
    }

    private List<Endereco> getLista(String sql) throws SQLException {
        try {
            List<Endereco> lista = new ArrayList<Endereco>();
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getEnderecoAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de endere�os no sistema");
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
            CEP cep = new CEP();
            cep.setCodigo(rs.getInt(5));
            cep.setCep(rs.getString(6));
            bairro.setCep(cep);
            Cidade cidade = new Cidade();
            cidade.setCodigo(rs.getInt(7));
            cidade.setCidade(rs.getString(8));
            cep.setCidade(cidade);
            Estado estado = new Estado();
            estado.setCodigo(rs.getInt(9));
            estado.setEstado(rs.getString(10));
            cidade.setEstado(estado);
            endereco.setBairro(bairro);
            return endereco;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do endere�o no sistema");
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
            default:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT EB.COD_ENDERECO_BAI_CEP, EN.ENDERECO, EB.COD_BAIRRO_CEP, B.BAIRRO, BC.CODCEP, CEP.CEP, CEP.COD_CIDADE_ESTADO, "
                + "C.CIDADE, CE.CODESTADO, ES.ESTADO FROM TBENDERECO_HAS_TBBAI_CEP EB "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "WHERE " + condicao + " ORDER BY EB.COD_ENDERECO_BAI_CEP";
    }
}
