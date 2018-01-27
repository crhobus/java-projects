package Cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cidade;
import Modelo.Cliente;
import Principal.DBDAO;

public class ClienteDAO extends DBDAO {

    private Connection con;

    public ClienteDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertClie(Cliente cliente) throws SQLException {
        if (isClieCadastrado(cliente.getCodClie())) {
            return updateCliente(cliente);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(cliente);
            stm = con.prepareStatement("INSERT INTO TBCLIENTE (CODCLIE, CODPESSOA, EMPRESA, "
                    + "FONEEMPRESA, PROFISSAO, RENDA, FAX) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, cliente.getCodClie());
            stm.setInt(2, codPessoa);
            stm.setString(3, cliente.getEmpresa());
            stm.setString(4, cliente.getFoneEmpresa());
            stm.setString(5, cliente.getProfissao());
            stm.setDouble(6, cliente.getRenda());
            stm.setString(7, cliente.getFax());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o cliente no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertPessoa(Cliente cliente) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, "
                    + "NOME, TIPOPESSOA, CPFCNPJ, RGIE, SEXO, ESTADOCIVIL, DATANASC, NUMERO, COMPLEMENTO, REFERENCIA, FONE, CELULAR1, "
                    + "CELULAR2, EMAIL, MSN, DESCRICAO, COD_ENDERECO_BAI_CEP, COD_CIDADE_ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de clientes no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(cliente.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(cliente.getUltAlteracao().getTime()));
            stm.setString(4, cliente.getNome());
            stm.setString(5, cliente.getTipoPessoa().substring(0, 1));
            stm.setString(6, cliente.getCpfCnpj());
            stm.setString(7, cliente.getRgIe());
            stm.setString(8, cliente.getSexo().substring(0, 1));
            stm.setString(9, cliente.getEstadoCivil().substring(0, 3));
            if (cliente.getDataNascimento() != null) {
                stm.setDate(10, new Date(cliente.getDataNascimento().getTime()));
            } else {
                stm.setDate(10, null);
            }
            stm.setInt(11, cliente.getNumero());
            stm.setString(12, cliente.getComplemento());
            stm.setString(13, cliente.getReferencia());
            stm.setString(14, cliente.getFone());
            stm.setString(15, cliente.getCelular1());
            stm.setString(16, cliente.getCelular2());
            stm.setString(17, cliente.getEmail());
            stm.setString(18, cliente.getMsn());
            stm.setString(19, cliente.getDescricao());
            stm.setInt(20, cliente.getCodEndereco());
            if (cliente.getCidad().getCodCidade() == -1) {
                stm.setNull(21, Types.BIGINT);// seta campo como null
            } else {
                stm.setInt(21, cliente.getCidad().getCodCidade());
            }
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o cliente no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateCliente(Cliente cliente) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = updatePessoa(cliente);
            stm = con.prepareStatement("UPDATE TBCLIENTE SET CODPESSOA = ?, EMPRESA = ?, "
                    + "FONEEMPRESA = ?, PROFISSAO = ?, RENDA = ?, FAX = ? WHERE CODCLIE = ?");
            stm.setInt(1, codPessoa);
            stm.setString(2, cliente.getEmpresa());
            stm.setString(3, cliente.getFoneEmpresa());
            stm.setString(4, cliente.getProfissao());
            stm.setDouble(5, cliente.getRenda());
            stm.setString(6, cliente.getFax());
            stm.setInt(7, cliente.getCodClie());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do cliente no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updatePessoa(Cliente cliente) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBCLIENTE WHERE CODCLIE = "
                    + cliente.getCodClie(), "Erro na leitura do cliente no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, "
                    + "NOME = ?, TIPOPESSOA = ?, CPFCNPJ = ?, RGIE = ?, SEXO = ?, ESTADOCIVIL = ?, DATANASC = ?, "
                    + "NUMERO = ?, COMPLEMENTO = ?, REFERENCIA = ?, FONE = ?, CELULAR1 = ?, CELULAR2 = ?, EMAIL = ?, "
                    + "MSN = ?, DESCRICAO = ?, COD_ENDERECO_BAI_CEP = ?, COD_CIDADE_ESTADO = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(cliente.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(cliente.getUltAlteracao().getTime()));
            stm.setString(3, cliente.getNome());
            stm.setString(4, cliente.getTipoPessoa().substring(0, 1));
            stm.setString(5, cliente.getCpfCnpj());
            stm.setString(6, cliente.getRgIe());
            stm.setString(7, cliente.getSexo().substring(0, 1));
            stm.setString(8, cliente.getEstadoCivil().substring(0, 3));
            if (cliente.getDataNascimento() != null) {
                stm.setDate(9, new Date(cliente.getDataNascimento().getTime()));
            } else {
                stm.setDate(9, null);
            }
            stm.setInt(10, cliente.getNumero());
            stm.setString(11, cliente.getComplemento());
            stm.setString(12, cliente.getReferencia());
            stm.setString(13, cliente.getFone());
            stm.setString(14, cliente.getCelular1());
            stm.setString(15, cliente.getCelular2());
            stm.setString(16, cliente.getEmail());
            stm.setString(17, cliente.getMsn());
            stm.setString(18, cliente.getDescricao());
            stm.setInt(19, cliente.getCodEndereco());
            if (cliente.getCidad().getCodCidade() == -1) {
                stm.setNull(20, Types.BIGINT);// seta campo como null
            } else {
                stm.setInt(20, cliente.getCidad().getCodCidade());
            }
            stm.setInt(21, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do cliente no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isClieCadastrado(int codClie) throws SQLException {
        return isCadastrado("SELECT CODCLIE FROM TBCLIENTE WHERE CODCLIE = "
                + codClie, "Erro na leitura do cliente no sistema");
    }

    public int getClieCadastrado(String cpfCnpj, int codClie) throws SQLException {
        return getCadastrado("SELECT CODCLIE FROM TBCLIENTE C "
                + "INNER JOIN TBPESSOA P ON C.CODPESSOA = P.CODPESSOA "
                + "WHERE P.CPFCNPJ = '" + cpfCnpj + "' AND C.CODCLIE = " + codClie,
                "Erro na leitura do cliente no sistema");
    }

    public int getClieCadastrado(String cpfCnpj) throws SQLException {
        return getCadastrado("SELECT CODPESSOA FROM TBPESSOA WHERE CPFCNPJ = '"
                + cpfCnpj + "'", "Erro na leitura do cliente no sistema");
    }

    public boolean isClieVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBCLIENTE",
                "Erro na leitura de clientes no sistema");
    }

    public int getProxCodClie() throws SQLException {
        return getProxCod("SELECT MAX(CODCLIE) FROM TBCLIENTE",
                "Erro na leitura de clientes no sistema");
    }

    public boolean deleteClie(int codClie) throws SQLException {
        int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBCLIENTE WHERE CODCLIE = " + codClie,
                "Erro na leitura do cliente no sistema");// obtém o codigo da pessoa
        delete("DELETE FROM TBCLIENTE WHERE CODCLIE = " + codClie,
                "Não foi possível excluir o cliente do sistema");
        return delete("DELETE FROM TBPESSOA WHERE CODPESSOA = " + codPessoa,
                "Não foi possível excluir o cliente do sistema");
    }

    public List<Cliente> listClie() throws SQLException {
        return getLista("SELECT CL.CODCLIE, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.TIPOPESSOA, P.CPFCNPJ, P.RGIE, P.SEXO, "
                + "P.ESTADOCIVIL, P.COD_CIDADE_ESTADO, C1.CIDADE, P1.PAIS, P.DATANASC, P.COD_ENDERECO_BAI_CEP, "
                + "EN.ENDERECO, B.BAIRRO, P.NUMERO, P.COMPLEMENTO, CEP.CEP, C.CIDADE, ES.ESTADO, R.REGIAO, "
                + "P.REFERENCIA, CL.EMPRESA, CL.FONEEMPRESA, CL.PROFISSAO, CL.RENDA, P.FONE, CL.FAX, "
                + "P.CELULAR1, P.CELULAR2, P.EMAIL, P.MSN, P.DESCRICAO FROM TBCLIENTE CL "
                + "INNER JOIN TBPESSOA P ON CL.CODPESSOA = P.CODPESSOA "
                + "LEFT JOIN TBCIDADE_HAS_TBESTADO CE1 ON P.COD_CIDADE_ESTADO = CE1.COD_CIDADE_ESTADO "
                + "LEFT JOIN TBCIDADE C1 ON CE1.CODCIDADE = C1.CODCIDADE "
                + "LEFT JOIN TBESTADO ES1 ON CE1.CODESTADO = ES1.CODESTADO "
                + "LEFT JOIN TBREGIAO_HAS_TBPAIS RP1 ON ES1.COD_REGIAO_PAIS = RP1.COD_REGIAO_PAIS "
                + "LEFT JOIN TBPAIS P1 ON RP1.CODPAIS = P1.CODPAIS "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON ES.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "ORDER BY CL.CODCLIE");
    }

    private List<Cliente> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Cliente> lista = new ArrayList<Cliente>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getClieAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de clientes no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Cliente getClieAux(ResultSet rs) throws SQLException {
        try {
            Cliente clie = new Cliente();
            clie.setCodClie(rs.getInt(1));
            clie.setDataCadastro(rs.getTimestamp(2));
            clie.setUltAlteracao(rs.getTimestamp(3));
            clie.setNome(rs.getString(4));
            String tipoPessoa = rs.getString(5);
            if (tipoPessoa.equals("F")) {
                clie.setTipoPessoa("Física");
            } else {
                clie.setTipoPessoa("Jurídica");
            }
            clie.setCpfCnpj(rs.getString(6));
            clie.setRgIe(rs.getString(7));
            String sexo = rs.getString(8);
            if (sexo.equals("M")) {
                clie.setSexo("Masculino");
            } else {
                if (sexo.equals("F")) {
                    clie.setSexo("Feminino");
                } else {
                    clie.setSexo("Selecione");
                }
            }
            String estadoCivil = rs.getString(9);
            if (estadoCivil.equals("Sol")) {
                clie.setEstadoCivil("Solteiro");
            } else {
                if (estadoCivil.equals("Cas")) {
                    clie.setEstadoCivil("Casado");
                } else {
                    if (estadoCivil.equals("Sep")) {
                        clie.setEstadoCivil("Separado");
                    } else {
                        if (estadoCivil.equals("Div")) {
                            clie.setEstadoCivil("Divorciado");
                        } else {
                            if (estadoCivil.equals("Vil")) {
                                clie.setEstadoCivil("Viúvo");
                            } else {
                                clie.setEstadoCivil("Selecione");
                            }
                        }
                    }
                }
            }
            String codCidade = rs.getString(10);
            Cidade cidade = new Cidade();
            if (codCidade != null) {
                cidade.setCodCidade(Integer.parseInt(codCidade));
                cidade.setCidade(rs.getString(11));
                cidade.setPais(rs.getString(12));
            } else {
                cidade.setCodCidade(-1);
            }
            clie.setCidad(cidade);
            clie.setDataNascimento(rs.getDate(13));
            clie.setCodEndereco(rs.getInt(14));
            clie.setEndereco(rs.getString(15));
            clie.setBairro(rs.getString(16));
            clie.setNumero(rs.getInt(17));
            clie.setComplemento(rs.getString(18));
            clie.setCep(rs.getString(19));
            clie.setCidade(rs.getString(20));
            clie.setEstado(rs.getString(21));
            clie.setRegiao(rs.getString(22));
            clie.setReferencia(rs.getString(23));
            clie.setEmpresa(rs.getString(24));
            clie.setFoneEmpresa(rs.getString(25));
            clie.setProfissao(rs.getString(26));
            clie.setRenda(rs.getDouble(27));
            clie.setFone(rs.getString(28));
            clie.setFax(rs.getString(29));
            clie.setCelular1(rs.getString(30));
            clie.setCelular2(rs.getString(31));
            clie.setEmail(rs.getString(32));
            clie.setMsn(rs.getString(33));
            clie.setDescricao(rs.getString(34));
            return clie;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do cliente no sistema");
        }
    }

    public List<Cliente> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("CL.CODCLIE = " + n));
            case 1:
                return getLista(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("LOWER(P.TIPOPESSOA) = '" + s.substring(0, 1).toLowerCase() + "'"));
            case 3:
                return getLista(getSql("P.CPFCNPJ = '" + s + "'"));
            case 4:
                return getLista(getSql("P.RGIE = '" + s + "'"));
            case 5:
                return getLista(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
            case 6:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 7:
                return getLista(getSql("P.NUMERO = " + n));
            case 8:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 9:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            case 10:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("P.FONE = '" + s + "'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT CL.CODCLIE, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.TIPOPESSOA, P.CPFCNPJ, P.RGIE, P.SEXO, "
                + "P.ESTADOCIVIL, P.COD_CIDADE_ESTADO, C1.CIDADE, P1.PAIS, P.DATANASC, P.COD_ENDERECO_BAI_CEP, "
                + "EN.ENDERECO, B.BAIRRO, P.NUMERO, P.COMPLEMENTO, CEP.CEP, C.CIDADE, ES.ESTADO, R.REGIAO, "
                + "P.REFERENCIA, CL.EMPRESA, CL.FONEEMPRESA, CL.PROFISSAO, CL.RENDA, P.FONE, CL.FAX, "
                + "P.CELULAR1, P.CELULAR2, P.EMAIL, P.MSN, P.DESCRICAO FROM TBCLIENTE CL "
                + "INNER JOIN TBPESSOA P ON CL.CODPESSOA = P.CODPESSOA "
                + "LEFT JOIN TBCIDADE_HAS_TBESTADO CE1 ON P.COD_CIDADE_ESTADO = CE1.COD_CIDADE_ESTADO "
                + "LEFT JOIN TBCIDADE C1 ON CE1.CODCIDADE = C1.CODCIDADE "
                + "LEFT JOIN TBESTADO ES1 ON CE1.CODESTADO = ES1.CODESTADO "
                + "LEFT JOIN TBREGIAO_HAS_TBPAIS RP1 ON ES1.COD_REGIAO_PAIS = RP1.COD_REGIAO_PAIS "
                + "LEFT JOIN TBPAIS P1 ON RP1.CODPAIS = P1.CODPAIS "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP ON ES.COD_REGIAO_PAIS = RP.COD_REGIAO_PAIS "
                + "INNER JOIN TBREGIAO R ON RP.CODREGIAO = R.CODREGIAO "
                + "WHERE " + condicao + " ORDER BY CL.CODCLIE";
    }
}
