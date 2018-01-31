package Fornecedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Fornecedor;

public class FornecedorDAO {

    private Connection con;

    public FornecedorDAO(Connection con) {
        this.con = con;
    }

    public boolean insertFornecedor(Fornecedor fornecedor) throws Exception {
        try {
            if (con.prepareStatement("SELECT * FROM TBFORNECEDOR WHERE CODIGO = " + fornecedor.getCodigo()).executeQuery().next()) {
                return updateFornecedor(fornecedor);
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBFORNECEDOR (CODIGO, DIGITOCONTA, NUMERO, NOME, "
                    + "SIGLA, NOMEFANTASIA, IE, CNPJ, INSCRICAOMUNICIPAL, TIPOPESSOA, CONTRIBUINTE, EXPORTADOR, "
                    + "TIPOFORNECEDOR, EMPRESA, FONEEMPRESA, BANCO, AGENCIA, CONTA, CAIXAPOSTAL, ENDERECO, BAIRRO, CIDADE, "
                    + "UF, REGIAO, PAIS, CEP, REFERENCIA, FONE, CELULAR1, CELULAR2, FAX, EMAIL, MSN, SKYPE, DESCRICAO, HOMEPAGE, "
                    + "OBSERVACOES, DATACADASTRO, ULTALTERACAO, COMISSAO, COMPRAMINIMA, COMPRAMAXIMA, VALORFRETE, ICMS, COFINS, "
                    + "IPI, JUROS, DESCONTOS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, fornecedor.getCodigo());
            stm.setInt(2, fornecedor.getDigitoConta());
            stm.setInt(3, fornecedor.getNumero());
            stm.setString(4, fornecedor.getNome());
            stm.setString(5, fornecedor.getSigla());
            stm.setString(6, fornecedor.getNomeFantasia());
            stm.setString(7, fornecedor.getIe());
            stm.setString(8, fornecedor.getCnpj());
            stm.setString(9, fornecedor.getInscricaoMunicipal());
            stm.setString(10, fornecedor.getTipoPessoa());
            stm.setString(11, fornecedor.getContribuinte());
            stm.setString(12, fornecedor.getExportador());
            stm.setString(13, fornecedor.getTipoFornecedor());
            stm.setString(14, fornecedor.getEmpresa());
            stm.setString(15, fornecedor.getFoneEmpresa());
            stm.setString(16, fornecedor.getBanco());
            stm.setString(17, fornecedor.getAgencia());
            stm.setString(18, fornecedor.getConta());
            stm.setString(19, fornecedor.getCaixaPostal());
            stm.setString(20, fornecedor.getEndereco());
            stm.setString(21, fornecedor.getBairro());
            stm.setString(22, fornecedor.getCidade());
            stm.setString(23, fornecedor.getUf());
            stm.setString(24, fornecedor.getRegiao());
            stm.setString(25, fornecedor.getPais());
            stm.setString(26, fornecedor.getCep());
            stm.setString(27, fornecedor.getReferencia());
            stm.setString(28, fornecedor.getFone());
            stm.setString(29, fornecedor.getCelular1());
            stm.setString(30, fornecedor.getCelular2());
            stm.setString(31, fornecedor.getFax());
            stm.setString(32, fornecedor.getEmail());
            stm.setString(33, fornecedor.getMsn());
            stm.setString(34, fornecedor.getSkype());
            stm.setString(35, fornecedor.getDescricao());
            stm.setString(36, fornecedor.getHomePage());
            stm.setString(37, fornecedor.getObservacoes());
            stm.setTimestamp(38, new Timestamp(fornecedor.getDataCadas().getTime()));
            stm.setTimestamp(39, new Timestamp(fornecedor.getUltAlteracao().getTime()));
            stm.setDouble(40, fornecedor.getComissao());
            stm.setDouble(41, fornecedor.getCompraMinima());
            stm.setDouble(42, fornecedor.getCompraMaxima());
            stm.setDouble(43, fornecedor.getValorFrete());
            stm.setDouble(44, fornecedor.getIcms());
            stm.setDouble(45, fornecedor.getCofins());
            stm.setDouble(46, fornecedor.getIpi());
            stm.setDouble(47, fornecedor.getJuros());
            stm.setDouble(48, fornecedor.getDescontos());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do fornecedor no banco de dados");
        }
    }

    public boolean updateFornecedor(Fornecedor fornecedor) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBFORNECEDOR SET DIGITOCONTA = ?, NUMERO = ?, NOME = ?, "
                    + "SIGLA = ?, NOMEFANTASIA = ?, IE = ?, CNPJ = ?, INSCRICAOMUNICIPAL = ?, TIPOPESSOA = ?, CONTRIBUINTE = ?, "
                    + "EXPORTADOR = ?, TIPOFORNECEDOR = ?, EMPRESA = ?, FONEEMPRESA = ?, BANCO = ?, AGENCIA = ?, CONTA = ?, CAIXAPOSTAL = ?, "
                    + "ENDERECO = ?, BAIRRO = ?, CIDADE = ?, UF = ?, REGIAO = ?, PAIS = ?, CEP = ?, REFERENCIA = ?, FONE = ?, CELULAR1 = ?, "
                    + "CELULAR2 = ?, FAX = ?, EMAIL = ?, MSN = ?, SKYPE = ?, DESCRICAO = ?, HOMEPAGE = ?, OBSERVACOES = ?, DATACADASTRO = ?, "
                    + "ULTALTERACAO = ?, COMISSAO = ?, COMPRAMINIMA = ?, COMPRAMAXIMA = ?, VALORFRETE = ?, ICMS = ?, COFINS = ?, IPI = ?, "
                    + "JUROS = ?, DESCONTOS = ? WHERE CODIGO = ?");
            stm.setInt(1, fornecedor.getDigitoConta());
            stm.setInt(2, fornecedor.getNumero());
            stm.setString(3, fornecedor.getNome());
            stm.setString(4, fornecedor.getSigla());
            stm.setString(5, fornecedor.getNomeFantasia());
            stm.setString(6, fornecedor.getIe());
            stm.setString(7, fornecedor.getCnpj());
            stm.setString(8, fornecedor.getInscricaoMunicipal());
            stm.setString(9, fornecedor.getTipoPessoa());
            stm.setString(10, fornecedor.getContribuinte());
            stm.setString(11, fornecedor.getExportador());
            stm.setString(12, fornecedor.getTipoFornecedor());
            stm.setString(13, fornecedor.getEmpresa());
            stm.setString(14, fornecedor.getFoneEmpresa());
            stm.setString(15, fornecedor.getBanco());
            stm.setString(16, fornecedor.getAgencia());
            stm.setString(17, fornecedor.getConta());
            stm.setString(18, fornecedor.getCaixaPostal());
            stm.setString(19, fornecedor.getEndereco());
            stm.setString(20, fornecedor.getBairro());
            stm.setString(21, fornecedor.getCidade());
            stm.setString(22, fornecedor.getUf());
            stm.setString(23, fornecedor.getRegiao());
            stm.setString(24, fornecedor.getPais());
            stm.setString(25, fornecedor.getCep());
            stm.setString(26, fornecedor.getReferencia());
            stm.setString(27, fornecedor.getFone());
            stm.setString(28, fornecedor.getCelular1());
            stm.setString(29, fornecedor.getCelular2());
            stm.setString(30, fornecedor.getFax());
            stm.setString(31, fornecedor.getEmail());
            stm.setString(32, fornecedor.getMsn());
            stm.setString(33, fornecedor.getSkype());
            stm.setString(34, fornecedor.getDescricao());
            stm.setString(35, fornecedor.getHomePage());
            stm.setString(36, fornecedor.getObservacoes());
            stm.setTimestamp(37, new Timestamp(fornecedor.getDataCadas().getTime()));
            stm.setTimestamp(38, new Timestamp(fornecedor.getUltAlteracao().getTime()));
            stm.setDouble(39, fornecedor.getComissao());
            stm.setDouble(40, fornecedor.getCompraMinima());
            stm.setDouble(41, fornecedor.getCompraMaxima());
            stm.setDouble(42, fornecedor.getValorFrete());
            stm.setDouble(43, fornecedor.getIcms());
            stm.setDouble(44, fornecedor.getCofins());
            stm.setDouble(45, fornecedor.getIpi());
            stm.setDouble(46, fornecedor.getJuros());
            stm.setDouble(47, fornecedor.getDescontos());
            stm.setInt(48, fornecedor.getCodigo());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao regravar o fornecedor no banco de dados");
        }
    }

    public boolean deleteFornecedor(String cnpj) throws Exception {
        try {
            return con.prepareStatement("DELETE FROM TBFORNECEDOR WHERE CNPJ = " + "'" + cnpj + "'").executeUpdate() == 1;
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir um fornecedor do banco de dados");
        }
    }

    public Fornecedor selectFornecedor(String cnpj) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFORNECEDOR WHERE CNPJ = '" + cnpj + "'").executeQuery();
            if (rs.next()) {
                return getFornecedor(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do fornecedor do banco de dados");
        }
    }

    public List<Fornecedor> listFornecedores() throws Exception {
        try {
            List<Fornecedor> lista = new ArrayList<Fornecedor>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFORNECEDOR ORDER BY CODIGO").executeQuery();
            while (rs.next()) {
                lista.add(getFornecedor(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de fornecedores do banco de dados");
        }
    }

    private Fornecedor getFornecedor(ResultSet rs) throws Exception {
        try {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCodigo(rs.getInt("CODIGO"));
            fornecedor.setDigitoConta(rs.getInt("DIGITOCONTA"));
            fornecedor.setNumero(rs.getInt("NUMERO"));
            fornecedor.setNome(rs.getString("NOME"));
            fornecedor.setSigla(rs.getString("SIGLA"));
            fornecedor.setNomeFantasia(rs.getString("NOMEFANTASIA"));
            fornecedor.setIe(rs.getString("IE"));
            fornecedor.setCnpj(rs.getString("CNPJ"));
            fornecedor.setInscricaoMunicipal(rs.getString("INSCRICAOMUNICIPAL"));
            fornecedor.setTipoPessoa(rs.getString("TIPOPESSOA"));
            fornecedor.setContribuinte(rs.getString("CONTRIBUINTE"));
            fornecedor.setExportador(rs.getString("EXPORTADOR"));
            fornecedor.setTipoFornecedor(rs.getString("TIPOFORNECEDOR"));
            fornecedor.setEmpresa(rs.getString("EMPRESA"));
            fornecedor.setFoneEmpresa(rs.getString("FONEEMPRESA"));
            fornecedor.setBanco(rs.getString("BANCO"));
            fornecedor.setAgencia(rs.getString("AGENCIA"));
            fornecedor.setConta(rs.getString("CONTA"));
            fornecedor.setCaixaPostal(rs.getString("CAIXAPOSTAL"));
            fornecedor.setEndereco(rs.getString("ENDERECO"));
            fornecedor.setBairro(rs.getString("BAIRRO"));
            fornecedor.setCidade(rs.getString("CIDADE"));
            fornecedor.setUf(rs.getString("UF"));
            fornecedor.setRegiao(rs.getString("REGIAO"));
            fornecedor.setPais(rs.getString("PAIS"));
            fornecedor.setCep(rs.getString("CEP"));
            fornecedor.setReferencia(rs.getString("REFERENCIA"));
            fornecedor.setFone(rs.getString("FONE"));
            fornecedor.setCelular1(rs.getString("CELULAR1"));
            fornecedor.setCelular2(rs.getString("CELULAR2"));
            fornecedor.setFax(rs.getString("FAX"));
            fornecedor.setEmail(rs.getString("EMAIL"));
            fornecedor.setMsn(rs.getString("MSN"));
            fornecedor.setSkype(rs.getString("SKYPE"));
            fornecedor.setDescricao(rs.getString("DESCRICAO"));
            fornecedor.setHomePage(rs.getString("HOMEPAGE"));
            fornecedor.setObservacoes(rs.getString("OBSERVACOES"));
            fornecedor.setDataCadas(rs.getTimestamp("DATACADASTRO"));
            fornecedor.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            fornecedor.setComissao(rs.getDouble("COMISSAO"));
            fornecedor.setCompraMinima(rs.getDouble("COMPRAMINIMA"));
            fornecedor.setCompraMaxima(rs.getDouble("COMPRAMAXIMA"));
            fornecedor.setValorFrete(rs.getDouble("VALORFRETE"));
            fornecedor.setIcms(rs.getDouble("ICMS"));
            fornecedor.setCofins(rs.getDouble("COFINS"));
            fornecedor.setIpi(rs.getDouble("IPI"));
            fornecedor.setJuros(rs.getDouble("JUROS"));
            fornecedor.setDescontos(rs.getDouble("DESCONTOS"));
            return fornecedor;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do fornecedor do banco de dados");
        }
    }

    public boolean isFornVazio() throws Exception {
        try {
            return !con.prepareStatement("SELECT * FROM TBFORNECEDOR").executeQuery().next();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de fornecedores do banco de dados");
        }
    }

    public int getProxCodForn() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFORNECEDOR ORDER BY CODIGO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("CODIGO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de fornecedores do banco de dados");
        }
    }
}
