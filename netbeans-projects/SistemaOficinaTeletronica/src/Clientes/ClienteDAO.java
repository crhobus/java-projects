package Clientes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;

public class ClienteDAO {

    private Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    public boolean insertCliente(Cliente cliente) throws Exception {
        try {
            if (con.prepareStatement("SELECT * FROM TBCLIENTE WHERE CODIGO = " + cliente.getCodigo()).executeQuery().next()) {
                return updateCliente(cliente);
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBCLIENTE (CODIGO, NUMERO, NOME, "
                    + "RG, CPFCNPJ, IE, SEXO, ESTADOCIVIL, TIPOPESSOA, PROFISSAO, EMPRESA, FONEEMPRESA, CEP, "
                    + "ENDERECO, BAIRRO, COMPLEMENTO, CIDADE, UF, REFERENCIA, FONE, CELULAR1, CELULAR2, EMAIL, "
                    + "MSN, DESCRICAO, DATACADASTRO, ULTALTERACAO, DATANASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do cliente no banco de dados");
        }
    }

    public boolean updateCliente(Cliente cliente) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBCLIENTE SET NUMERO = ?, NOME = ?, "
                    + "RG = ?, CPFCNPJ = ?, IE = ?, SEXO = ?, ESTADOCIVIL = ?, TIPOPESSOA = ?, PROFISSAO = ?, "
                    + "EMPRESA = ?, FONEEMPRESA = ?, CEP = ?, ENDERECO = ?, BAIRRO = ?, COMPLEMENTO = ?, CIDADE = ?, "
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
        } catch (Exception ex) {
            throw new Exception("Erro ao regravar o cliente no banco de dados");
        }
    }

    public boolean deleteCliente(String cpfCNPJ) throws Exception {
        try {
            return con.prepareStatement("DELETE FROM TBCLIENTE WHERE CPFCNPJ = " + "'" + cpfCNPJ + "'").executeUpdate() == 1;
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir um cliente do banco de dados");
        }
    }

    public Cliente selectCliente(String cpfCNPJ) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBCLIENTE WHERE CPFCNPJ = '" + cpfCNPJ + "'").executeQuery();
            if (rs.next()) {
                return getCliente(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do cliente do banco de dados");
        }
    }

    public List<Cliente> listClientes() throws Exception {
        try {
            List<Cliente> lista = new ArrayList<Cliente>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBCLIENTE ORDER BY CODIGO").executeQuery();
            while (rs.next()) {
                lista.add(getCliente(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de clientes do banco de dados");
        }
    }

    private Cliente getCliente(ResultSet rs) throws Exception {
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
            throw new Exception("Erro na leitura do cliente do banco de dados");
        }
    }

    public boolean isClieVazio() throws Exception {
        try {
            return !con.prepareStatement("SELECT * FROM TBCLIENTE").executeQuery().next();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de clientes do banco de dados");
        }
    }

    public int getProxCodClie() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBCLIENTE ORDER BY CODIGO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("CODIGO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de clientes do banco de dados");
        }
    }
}
