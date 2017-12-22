package dbOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import model.Bairro;
import model.Cep;
import model.Cidade;
import model.Empresa;
import model.Endereco;

public class EmpresaDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Empresa> empresas;

    public EmpresaDAO(Connection con) {
        super(con);
        this.con = con;
        empresas = new HashMap<Integer, Empresa>();
    }

    public boolean insertEmpresa(Empresa empresa) throws SQLException {
        if (isEmpresaCadastrada(empresa.getCodigo())) {
            return updateEmpresa(empresa);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(empresa);
            stm = con.prepareStatement("INSERT INTO TBEMPRESA (CODEMP, EMAIL, CODPESSOA) VALUES (?, ?, ?)");
            stm.setInt(1, empresa.getCodigo());
            stm.setString(2, empresa.getEmail());
            stm.setInt(3, codPessoa);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a empresa no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertPessoa(Empresa empresa) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, NOME, "
                    + "CPFCNPJ, RGIE, NUMERO, CONTATO, COD_ENDERECO_BAI_CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de empresas no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(empresa.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(empresa.getUltAlteracao().getTime()));
            stm.setString(4, empresa.getNome());
            stm.setString(5, empresa.getCpfCnpj());
            stm.setString(6, empresa.getRgIe());
            stm.setInt(7, empresa.getNumero());
            stm.setString(8, empresa.getContato());
            stm.setInt(9, empresa.getEndereco().getCodigo());
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a empresa no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateEmpresa(Empresa empresa) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = updatePessoa(empresa);
            stm = con.prepareStatement("UPDATE TBEMPRESA SET EMAIL = ?, CODPESSOA = ? WHERE CODEMP = ?");
            stm.setString(1, empresa.getEmail());
            stm.setInt(2, codPessoa);
            stm.setInt(3, empresa.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da empresa no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updatePessoa(Empresa empresa) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBEMPRESA WHERE CODEMP = "
                    + empresa.getCodigo(), "Erro na leitura da empresa no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, NOME = ?, "
                    + "CPFCNPJ = ?, RGIE = ?, NUMERO = ?, CONTATO = ?, COD_ENDERECO_BAI_CEP = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(empresa.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(empresa.getUltAlteracao().getTime()));
            stm.setString(3, empresa.getNome());
            stm.setString(4, empresa.getCpfCnpj());
            stm.setString(5, empresa.getRgIe());
            stm.setInt(6, empresa.getNumero());
            stm.setString(7, empresa.getContato());
            stm.setInt(8, empresa.getEndereco().getCodigo());
            stm.setInt(9, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da empresa no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isEmpresaCadastrada(int codEmpresa) throws SQLException {
        return isCadastrado("SELECT CODEMP FROM TBEMPRESA WHERE CODEMP = "
                + codEmpresa, "Erro na leitura da empresa no sistema");
    }

    public int getEmpresaCadastrada(String cnpj, int codEmpresa) throws SQLException {
        return getCadastrado("SELECT CODEMP FROM TBEMPRESA E "
                + "INNER JOIN TBPESSOA P ON E.CODPESSOA = P.CODPESSOA "
                + "WHERE P.CPFCNPJ = '" + cnpj + "' AND E.CODEMP = "
                + codEmpresa, "Erro na leitura da empresa no sistema");
    }

    public int getEmpresaCadastrada(String cnpj) throws SQLException {
        return getCadastrado("SELECT CODPESSOA FROM TBPESSOA WHERE CPFCNPJ = '"
                + cnpj + "'", "Erro na leitura da empresa no sistema");
    }

    public boolean isEmpresaVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBEMPRESA",
                "Erro na leitura de empresas no sistema");
    }

    public int getProxCodEmpresa() throws SQLException {
        return getProxCod("SELECT MAX(CODEMP) FROM TBEMPRESA",
                "Erro na leitura de empresas no sistema");
    }

    public boolean deleteEmpresa(int codEmpresa) throws SQLException {
        int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBEMPRESA WHERE CODEMP = " + codEmpresa,
                "Erro na leitura da empresa no sistema");// obtém o codigo da pessoa
        delete("DELETE FROM TBEMPRESA WHERE CODEMP = " + codEmpresa,
                "Não foi possível excluir a empresa do sistema");
        return delete("DELETE FROM TBPESSOA WHERE CODPESSOA = " + codPessoa,
                "Não foi possível excluir a empresa do sistema");
    }

    public void listEmpresas() throws SQLException {
        addEmpresasMapa("SELECT E.CODEMP, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, "
                + "P.RGIE, P.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, P.NUMERO, "
                + "CEP.CEP, C.CIDADE, P.CONTATO, E.EMAIL FROM TBEMPRESA E "
                + "INNER JOIN TBPESSOA P ON E.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "ORDER BY E.CODEMP");
    }

    public void listEmpresasCondicao(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                addEmpresasMapa(getSql("E.CODEMP = " + n));
                break;
            case 1:
                addEmpresasMapa(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addEmpresasMapa(getSql("P.CPFCNPJ = '" + s + "'"));
                break;
            case 3:
                addEmpresasMapa(getSql("P.RGIE = '" + s + "'"));
                break;
            case 4:
                addEmpresasMapa(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 5:
                addEmpresasMapa(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 6:
                addEmpresasMapa(getSql("P.NUMERO = " + n));
                break;
            case 7:
                addEmpresasMapa(getSql("CEP.CEP = '" + s + "'"));
                break;
            case 8:
                addEmpresasMapa(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
            case 9:
                addEmpresasMapa(getSql("LOWER(E.EMAIL) like '%" + s.toLowerCase() + "%'"));
                break;
            default:
                addEmpresasMapa(getSql("P.CONTATO = '" + s + "'"));
                break;
        }
    }

    private String getSql(String condicao) {
        return "SELECT E.CODEMP, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, "
                + "P.RGIE, P.COD_ENDERECO_BAI_CEP, EN.ENDERECO, B.BAIRRO, P.NUMERO, "
                + "CEP.CEP, C.CIDADE, P.CONTATO, E.EMAIL FROM TBEMPRESA E "
                + "INNER JOIN TBPESSOA P ON E.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "WHERE " + condicao + " ORDER BY E.CODEMP";
    }

    private void addEmpresasMapa(String sql) throws SQLException {
        empresas.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                empresas.put(linha, getEmpresaAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de empresas no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Empresa getEmpresaAux(ResultSet rs) throws SQLException {
        try {
            Empresa empresa = new Empresa();
            empresa.setCodigo(rs.getInt(1));
            empresa.setDataCadastro(rs.getTimestamp(2));
            empresa.setUltAlteracao(rs.getTimestamp(3));
            empresa.setNome(rs.getString(4));
            empresa.setCpfCnpj(rs.getString(5));
            empresa.setRgIe(rs.getString(6));
            Endereco endereco = new Endereco();
            endereco.setCodigo(rs.getInt(7));
            endereco.setEndereco(rs.getString(8));
            Bairro bairro = new Bairro();
            bairro.setBairro(rs.getString(9));
            empresa.setNumero(rs.getInt(10));
            Cep cep = new Cep();
            cep.setCep(rs.getString(11));
            Cidade cidade = new Cidade();
            cidade.setCidade(rs.getString(12));
            cep.setCidade(cidade);
            bairro.setCep(cep);
            endereco.setBairro(bairro);
            empresa.setEndereco(endereco);
            empresa.setContato(rs.getString(13));
            empresa.setEmail(rs.getString(14));
            return empresa;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da empresa no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        empresas.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Empresa empresa = empresas.get(linha);
        switch (coluna) {
            case 0:
                return empresa.getCodigo();
            case 1:
                return empresa.getNome();
            case 2:
                return empresa.getCpfCnpj();
            case 3:
                return empresa.getRgIe();
            case 4:
                return empresa.getEndereco().getEndereco();
            case 5:
                return empresa.getEndereco().getBairro().getBairro();
            case 6:
                return empresa.getNumero();
            case 7:
                return empresa.getEndereco().getBairro().getCep().getCep();
            case 8:
                return empresa.getEndereco().getBairro().getCep().getCidade().getCidade();
            case 9:
                return empresa.getEmail();
            default:
                return empresa.getContato();
        }
    }

    public int getQtdadeEmpresasCadas() {
        return empresas.size();
    }

    public Empresa getEmpresaMapa(int linha) {
        return empresas.get(linha);
    }
}
