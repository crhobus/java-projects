package DBOracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Model.Bairro;
import Model.CEP;
import Model.Cidade;
import Model.Cliente;
import Model.Endereco;
import Model.Estado;

public class ClienteDAO extends DBDAO {

    private Connection con;

    public ClienteDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertClie(Cliente cliente) throws SQLException {
        try {
            if (isClieCadastrado(cliente.getCodigo())) {
                return updateCliente(cliente);
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBCLIENTE (CODCLIE, COD_ENDERECO_BAI_CEP,"
                    + "DATACADASTRO, ULTALTERACAO, NOME, CPF, RG, SEXO, ESTADOCIVIL, DATANASC, NUMERO, COMPLEMENTO, "
                    + "REFERENCIA, FONE, CELULAR1, CELULAR2, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, cliente.getCodigo());
            stm.setInt(2, cliente.getEndereco().getCodigo());
            stm.setTimestamp(3, new Timestamp(cliente.getDataCadastro().getTime()));
            stm.setTimestamp(4, new Timestamp(cliente.getUltAlteracao().getTime()));
            stm.setString(5, cliente.getNome());
            stm.setString(6, cliente.getCpf());
            stm.setString(7, cliente.getRg());
            stm.setString(8, cliente.getSexo().substring(0, 1));
            stm.setString(9, cliente.getEstadoCivil().substring(0, 3));
            stm.setDate(10, new Date(cliente.getDataNascimento().getTime()));
            stm.setInt(11, cliente.getNumero());
            stm.setString(12, cliente.getComplemento());
            stm.setString(13, cliente.getReferencia());
            stm.setString(14, cliente.getFone());
            stm.setString(15, cliente.getCelular1());
            stm.setString(16, cliente.getCelular2());
            stm.setString(17, cliente.getEmail());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o cliente no sistema");
        }
    }

    public boolean updateCliente(Cliente cliente) throws SQLException {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBCLIENTE SET COD_ENDERECO_BAI_CEP = ?,"
                    + "DATACADASTRO = ?, ULTALTERACAO = ?, NOME = ?, CPF = ?, RG = ?, SEXO = ?, ESTADOCIVIL = ?, "
                    + "DATANASC = ?, NUMERO = ?, COMPLEMENTO = ?, REFERENCIA = ?, FONE = ?, CELULAR1 = ?, "
                    + "CELULAR2 = ?, EMAIL = ? WHERE CODCLIE = ?");
            stm.setInt(1, cliente.getEndereco().getCodigo());
            stm.setTimestamp(2, new Timestamp(cliente.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(cliente.getUltAlteracao().getTime()));
            stm.setString(4, cliente.getNome());
            stm.setString(5, cliente.getCpf());
            stm.setString(6, cliente.getRg());
            stm.setString(7, cliente.getSexo().substring(0, 1));
            stm.setString(8, cliente.getEstadoCivil().substring(0, 3));
            stm.setDate(9, new Date(cliente.getDataNascimento().getTime()));
            stm.setInt(10, cliente.getNumero());
            stm.setString(11, cliente.getComplemento());
            stm.setString(12, cliente.getReferencia());
            stm.setString(13, cliente.getFone());
            stm.setString(14, cliente.getCelular1());
            stm.setString(15, cliente.getCelular2());
            stm.setString(16, cliente.getEmail());
            stm.setInt(17, cliente.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do cliente no sistema");
        }
    }

    public boolean isClieCadastrado(int codClie) throws SQLException {
        return isCadastrado("SELECT CODCLIE FROM TBCLIENTE WHERE CODCLIE = "
                + codClie, "Erro na leitura do cliente no sistema");
    }

    public int getClieCadastrado(String cpf, int codClie) throws SQLException {
        return getCadastrado("SELECT CODCLIE FROM TBCLIENTE WHERE CPF = '"
                + cpf + "' AND CODCLIE = " + codClie, "Erro na leitura do cliente no sistema");
    }

    public int getClieCadastrado(String cpf) throws SQLException {
        return getCadastrado("SELECT CODCLIE FROM TBCLIENTE WHERE CPF = '"
                + cpf + "'", "Erro na leitura do cliente no sistema");
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
        return delete("DELETE FROM TBCLIENTE WHERE CODCLIE = " + codClie,
                "Não é possível excluir o cliente do sistema");
    }

    public List<Cliente> listClie() throws SQLException {
        return getLista("SELECT CL.CODCLIE, CL.DATACADASTRO, CL.ULTALTERACAO, CL.NOME, CL.CPF, CL.RG, CL.SEXO, "
                + "CL.ESTADOCIVIL, CL.DATANASC, CL.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, CL.NUMERO, "
                + "CL.COMPLEMENTO, CEP.CEP, C.CIDADE, ES.ESTADO, CL.REFERENCIA, CL.FONE, CL.CELULAR1, "
                + "CL.CELULAR2, CL.EMAIL FROM TBCLIENTE CL "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON CL.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "ORDER BY CL.CODCLIE");
    }

    private List<Cliente> getLista(String sql) throws SQLException {
        try {
            List<Cliente> lista = new ArrayList<Cliente>();
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getClieAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de clientes no sistema");
        }
    }

    private Cliente getClieAux(ResultSet rs) throws SQLException {
        try {
            Cliente clie = new Cliente();
            clie.setCodigo(rs.getInt(1));
            clie.setDataCadastro(rs.getTimestamp(2));
            clie.setUltAlteracao(rs.getTimestamp(3));
            clie.setNome(rs.getString(4));
            clie.setCpf(rs.getString(5));
            clie.setRg(rs.getString(6));
            String sexo = rs.getString(7);
            if (sexo.equals("M")) {
                clie.setSexo("Masculino");
            } else {
                if (sexo.equals("F")) {
                    clie.setSexo("Feminino");
                } else {
                    clie.setSexo("Selecione");
                }
            }
            String estadoCivil = rs.getString(8);
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
            clie.setDataNascimento(rs.getDate(9));
            Endereco endereco = new Endereco();
            endereco.setCodigo(rs.getInt(10));
            endereco.setEndereco(rs.getString(11));
            Bairro bairro = new Bairro();
            bairro.setBairro(rs.getString(12));
            endereco.setBairro(bairro);
            clie.setNumero(rs.getInt(13));
            clie.setComplemento(rs.getString(14));
            CEP cep = new CEP();
            cep.setCep(rs.getString(15));
            bairro.setCep(cep);
            Cidade cidade = new Cidade();
            cidade.setCidade(rs.getString(16));
            cep.setCidade(cidade);
            Estado estado = new Estado();
            estado.setEstado(rs.getString(17));
            cidade.setEstado(estado);
            clie.setReferencia(rs.getString(18));
            clie.setEndereco(endereco);
            clie.setFone(rs.getString(19));
            clie.setCelular1(rs.getString(20));
            clie.setCelular2(rs.getString(21));
            clie.setEmail(rs.getString(22));
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
                return getLista(getSql("LOWER(CL.NOME) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("CL.CPF = '" + s + "'"));
            case 3:
                return getLista(getSql("CL.RG = '" + s + "'"));
            case 4:
                return getLista(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
            case 5:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 6:
                return getLista(getSql("CL.NUMERO = " + n));
            case 7:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 8:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            case 9:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("CL.FONE = '" + s + "'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT CL.CODCLIE, CL.DATACADASTRO, CL.ULTALTERACAO, CL.NOME, CL.CPF, CL.RG, CL.SEXO, "
                + "CL.ESTADOCIVIL, CL.DATANASC, CL.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, CL.NUMERO, "
                + "CL.COMPLEMENTO, CEP.CEP, C.CIDADE, ES.ESTADO, CL.REFERENCIA, CL.FONE, CL.CELULAR1, "
                + "CL.CELULAR2, CL.EMAIL FROM TBCLIENTE CL "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON CL.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C ON CE.CODCIDADE = C.CODCIDADE "
                + "INNER JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "WHERE " + condicao + " ORDER BY CL.CODCLIE";
    }
}
