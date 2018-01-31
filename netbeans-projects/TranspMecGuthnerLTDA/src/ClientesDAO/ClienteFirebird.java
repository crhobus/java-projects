package ClientesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.MetodosFirebirdGenerico;
import Modelo.Cliente;

public class ClienteFirebird extends MetodosFirebirdGenerico implements ClientesDAO {

    @Override
    public boolean isClieVazio(Connection con) throws Exception {
        return isVazio(con, "SELECT * FROM TBCLIENTE", "clientes");
    }

    @Override
    public Cliente getCliente(String cpfCNPJ, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBCLIENTE WHERE CPFCNPJ = '" + cpfCNPJ + "'").executeQuery();
            if (rs.next()) {
                return leituraCliente(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de cliente do banco de dados");
        }
    }

    private Cliente leituraCliente(ResultSet rs) throws Exception {
        try {
            Cliente cliente = new Cliente();
            cliente.setCodigo(rs.getInt("CODIGO"));
            cliente.setNumero(rs.getInt("NUMERO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setRg(rs.getString("RG"));
            cliente.setCpfCNPJ(rs.getString("CPFCNPJ"));
            cliente.setIe(rs.getString("IE"));
            cliente.setSexo(rs.getString("SEXO"));
            cliente.setEstadoCivil(rs.getString("ESTADOCIVIL"));
            cliente.setTipoPessoa(rs.getString("TIPOPESSOA"));
            cliente.setProfissao(rs.getString("PROFISSAO"));
            cliente.setEmpresa(rs.getString("EMPRESA"));
            cliente.setFoneEmpresa(rs.getString("FONEEMPRESA"));
            cliente.setCep(rs.getString("CEP"));
            cliente.setEndereco(rs.getString("ENDERECO"));
            cliente.setBairro(rs.getString("BAIRRO"));
            cliente.setComplemento(rs.getString("COMPLEMENTO"));
            cliente.setCidade(rs.getString("CIDADE"));
            cliente.setUf(rs.getString("UF"));
            cliente.setReferencia(rs.getString("REFERENCIA"));
            cliente.setFone(rs.getString("FONE"));
            cliente.setCelular1(rs.getString("CELULAR1"));
            cliente.setCelular2(rs.getString("CELULAR2"));
            cliente.seteMail(rs.getString("EMAIL"));
            cliente.setMsn(rs.getString("MSN"));
            cliente.setDescricao(rs.getString("DESCRICAO"));
            cliente.setDataCadastro(rs.getTimestamp("DATACADASTRO"));
            cliente.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            cliente.setDataNascimento(rs.getDate("DATANASCIMENTO"));
            return cliente;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de cliente do banco de dados");
        }
    }

    @Override
    public int getProxCodClie(Connection con) throws Exception {
        return getProxCod(con, "SELECT * FROM TBCLIENTE", "clientes");
    }

    @Override
    public List<Cliente> listClientes(Connection con) throws Exception {
        try {
            List<Cliente> lista = new ArrayList<Cliente>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBCLIENTE").executeQuery();
            while (rs.next()) {
                lista.add(leituraCliente(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de clientes do banco de dados");
        }
    }

    @Override
    public boolean removeCliente(String cpfCNPJ, Connection con) throws Exception {
        try {
            if (getCliente(cpfCNPJ, con) != null) {
                PreparedStatement stm = con.prepareStatement("DELETE FROM TBCLIENTE WHERE CPFCNPJ = ?");
                stm.setString(1, cpfCNPJ);
                stm.execute();
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir um registro de cliente do banco de dados");
        }
    }

    @Override
    public boolean setCliente(Cliente cliente, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBCLIENTE WHERE CODIGO = " + cliente.getCodigo()).executeQuery();
            int cod = 0;
            if (rs.next()) {
                cod = rs.getInt("CODIGO");
            }
            PreparedStatement stm;
            if (cod != 0) {
                stm = con.prepareStatement("UPDATE TBCLIENTE SET NUMERO = ?, NOME = ?, RG = ?, CPFCNPJ = ?, "
                        + "IE = ?, SEXO = ?, ESTADOCIVIL = ?, TIPOPESSOA = ?, PROFISSAO = ?, EMPRESA = ?, "
                        + "FONEEMPRESA = ?, CEP = ?, ENDERECO = ?, BAIRRO = ?, COMPLEMENTO = ?, CIDADE = ?, "
                        + "UF = ?, REFERENCIA = ?, FONE = ?, CELULAR1 = ?, CELULAR2 = ?, EMAIL = ?, MSN = ?, "
                        + "DESCRICAO = ?, DATACADASTRO = ?, ULTALTERACAO = ?, DATANASCIMENTO = ? WHERE CODIGO = ?");
                stm.setInt(1, cliente.getNumero());
                stm.setString(2, cliente.getNome());
                stm.setString(3, cliente.getRg());
                stm.setString(4, cliente.getCpfCNPJ());
                stm.setString(5, cliente.getIe());
                stm.setString(6, cliente.getSexo());
                stm.setString(7, cliente.getEstadoCivil());
                stm.setString(8, cliente.getTipoPessoa());
                stm.setString(9, cliente.getProfissao());
                stm.setString(10, cliente.getEmpresa());
                stm.setString(11, cliente.getFoneEmpresa());
                stm.setString(12, cliente.getCep());
                stm.setString(13, cliente.getEndereco());
                stm.setString(14, cliente.getBairro());
                stm.setString(15, cliente.getComplemento());
                stm.setString(16, cliente.getCidade());
                stm.setString(17, cliente.getUf());
                stm.setString(18, cliente.getReferencia());
                stm.setString(19, cliente.getFone());
                stm.setString(20, cliente.getCelular1());
                stm.setString(21, cliente.getCelular2());
                stm.setString(22, cliente.geteMail());
                stm.setString(23, cliente.getMsn());
                stm.setString(24, cliente.getDescricao());
                stm.setTimestamp(25, new Timestamp(cliente.getDataCadastro().getTime()));
                stm.setTimestamp(26, new Timestamp(cliente.getUltAlteracao().getTime()));
                stm.setDate(27, new Date(cliente.getDataNascimento().getTime()));
                stm.setInt(28, cliente.getCodigo());
                stm.execute();
                return true;
            } else {
                stm = con.prepareStatement("INSERT INTO TBCLIENTE (CODIGO, NUMERO, NOME, RG, CPFCNPJ, IE, SEXO, ESTADOCIVIL, "
                        + "TIPOPESSOA, PROFISSAO, EMPRESA, FONEEMPRESA, CEP, ENDERECO, BAIRRO, COMPLEMENTO, CIDADE, "
                        + "UF, REFERENCIA, FONE, CELULAR1, CELULAR2, EMAIL, MSN, DESCRICAO, DATACADASTRO, ULTALTERACAO, "
                        + "DATANASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                        + "?, ?, ?, ?)");
                stm.setInt(1, cliente.getCodigo());
                stm.setInt(2, cliente.getNumero());
                stm.setString(3, cliente.getNome());
                stm.setString(4, cliente.getRg());
                stm.setString(5, cliente.getCpfCNPJ());
                stm.setString(6, cliente.getIe());
                stm.setString(7, cliente.getSexo());
                stm.setString(8, cliente.getEstadoCivil());
                stm.setString(9, cliente.getTipoPessoa());
                stm.setString(10, cliente.getProfissao());
                stm.setString(11, cliente.getEmpresa());
                stm.setString(12, cliente.getFoneEmpresa());
                stm.setString(13, cliente.getCep());
                stm.setString(14, cliente.getEndereco());
                stm.setString(15, cliente.getBairro());
                stm.setString(16, cliente.getComplemento());
                stm.setString(17, cliente.getCidade());
                stm.setString(18, cliente.getUf());
                stm.setString(19, cliente.getReferencia());
                stm.setString(20, cliente.getFone());
                stm.setString(21, cliente.getCelular1());
                stm.setString(22, cliente.getCelular2());
                stm.setString(23, cliente.geteMail());
                stm.setString(24, cliente.getMsn());
                stm.setString(25, cliente.getDescricao());
                stm.setTimestamp(26, new Timestamp(cliente.getDataCadastro().getTime()));
                stm.setTimestamp(27, new Timestamp(cliente.getUltAlteracao().getTime()));
                stm.setDate(28, new Date(cliente.getDataNascimento().getTime()));
                stm.execute();
                return true;
            }
        } catch (Exception ex) {
            throw new Exception("Erro na gravação de registro de cliente no banco de dados");
        }
    }
}
