package dbOracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import model.Bairro;
import model.Cep;
import model.Cidade;
import model.Endereco;
import model.Funcionario;

public class FuncionarioDAO extends DBDAO {

    private Connection con;
    private Map<Integer, Funcionario> funcionarios;

    public FuncionarioDAO(Connection con) {
        super(con);
        this.con = con;
        funcionarios = new HashMap<Integer, Funcionario>();
    }

    public boolean insertFunc(Funcionario funcionario) throws SQLException {
        if (isFuncCadastrado(funcionario.getCodigo())) {
            return updateFunc(funcionario);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(funcionario);
            stm = con.prepareStatement("INSERT INTO TBFUNCIONARIO (CODFUNC, SEXO, ESTADOCIVIL, DATANASC, "
                    + "COMPLEMENTO, NUMCARTTRAB, CARGO, SALARIO, USUARIO, SENHA, PERMISSAO, DATAADMISSAO, "
                    + "DATADEMISSAO, CODPESSOA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, funcionario.getCodigo());
            stm.setString(2, funcionario.getSexo().substring(0, 1));
            stm.setString(3, funcionario.getEstadoCivil().substring(0, 3));
            stm.setDate(4, new Date(funcionario.getDataNascimento().getTime()));
            stm.setString(5, funcionario.getComplemento());
            stm.setString(6, funcionario.getNumCartTrabalho());
            stm.setString(7, funcionario.getCargo());
            stm.setDouble(8, funcionario.getSalario());
            stm.setString(9, funcionario.getUsuario());
            stm.setString(10, funcionario.getSenha());
            stm.setInt(11, funcionario.getPermissao());
            stm.setDate(12, new Date(funcionario.getDataAdmissao().getTime()));
            if (funcionario.getDataDemissao() != null) {
                stm.setDate(13, new Date(funcionario.getDataDemissao().getTime()));
            } else {
                stm.setDate(13, null);
            }
            stm.setInt(14, codPessoa);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o funcionário no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertPessoa(Funcionario funcionario) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, NOME, "
                    + "CPFCNPJ, RGIE, NUMERO, CONTATO, COD_ENDERECO_BAI_CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de funcionários no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(funcionario.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(funcionario.getUltAlteracao().getTime()));
            stm.setString(4, funcionario.getNome());
            stm.setString(5, funcionario.getCpfCnpj());
            stm.setString(6, funcionario.getRgIe());
            stm.setInt(7, funcionario.getNumero());
            stm.setString(8, funcionario.getContato());
            stm.setInt(9, funcionario.getEndereco().getCodigo());
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o funcionário no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateFunc(Funcionario funcionario) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = updatePessoa(funcionario);
            stm = con.prepareStatement("UPDATE TBFUNCIONARIO SET SEXO = ?, ESTADOCIVIL = ?, DATANASC = ?, "
                    + "COMPLEMENTO = ?, NUMCARTTRAB = ?, CARGO = ?, SALARIO = ?, USUARIO = ?, SENHA = ?, PERMISSAO = ?, "
                    + "DATAADMISSAO = ?, DATADEMISSAO = ?, CODPESSOA = ? WHERE CODFUNC = ?");
            stm.setString(1, funcionario.getSexo().substring(0, 1));
            stm.setString(2, funcionario.getEstadoCivil().substring(0, 3));
            stm.setDate(3, new Date(funcionario.getDataNascimento().getTime()));
            stm.setString(4, funcionario.getComplemento());
            stm.setString(5, funcionario.getNumCartTrabalho());
            stm.setString(6, funcionario.getCargo());
            stm.setDouble(7, funcionario.getSalario());
            stm.setString(8, funcionario.getUsuario());
            stm.setString(9, funcionario.getSenha());
            stm.setInt(10, funcionario.getPermissao());
            stm.setDate(11, new Date(funcionario.getDataAdmissao().getTime()));
            if (funcionario.getDataDemissao() != null) {
                stm.setDate(12, new Date(funcionario.getDataDemissao().getTime()));
            } else {
                stm.setDate(12, null);
            }
            stm.setInt(13, codPessoa);
            stm.setInt(14, funcionario.getCodigo());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do funcionário no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updatePessoa(Funcionario funcionario) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBFUNCIONARIO WHERE CODFUNC = "
                    + funcionario.getCodigo(), "Erro na leitura do funcionário no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, NOME = ?, "
                    + "CPFCNPJ = ?, RGIE = ?, NUMERO = ?, CONTATO = ?, COD_ENDERECO_BAI_CEP = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(funcionario.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(funcionario.getUltAlteracao().getTime()));
            stm.setString(3, funcionario.getNome());
            stm.setString(4, funcionario.getCpfCnpj());
            stm.setString(5, funcionario.getRgIe());
            stm.setInt(6, funcionario.getNumero());
            stm.setString(7, funcionario.getContato());
            stm.setInt(8, funcionario.getEndereco().getCodigo());
            stm.setInt(9, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do funcionário no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isFuncCadastrado(int codFunc) throws SQLException {
        return isCadastrado("SELECT CODFUNC FROM TBFUNCIONARIO WHERE CODFUNC = " + codFunc,
                "Erro na leitura do funcionário no sistema");
    }

    public int getFuncCadastrado(String cpf, int codFunc) throws SQLException {
        return getCadastrado("SELECT CODFUNC FROM TBFUNCIONARIO F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "WHERE P.CPFCNPJ = '" + cpf + "' AND F.CODFUNC = " + codFunc,
                "Erro na leitura do funcionário no sistema");
    }

    public int getFuncCadastrado(String cpf) throws SQLException {
        return getCadastrado("SELECT CODPESSOA FROM TBPESSOA WHERE CPFCNPJ = '"
                + cpf + "'", "Erro na leitura do funcionário no sistema");
    }

    public boolean isUsuarioCadastrado(String usuario, int codFunc) throws SQLException {
        return isCadastrado("SELECT CODFUNC FROM TBFUNCIONARIO WHERE LOWER(USUARIO) = '"
                + usuario.toLowerCase() + "' AND CODFUNC <> " + codFunc, "Erro na leitura do funcionário no sistema");
    }

    public Funcionario getAutenticacao(String usuario) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement("SELECT USUARIO, SENHA, PERMISSAO FROM TBFUNCIONARIO "
                    + "WHERE USUARIO = '" + usuario + "'").executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setUsuario(rs.getString("USUARIO"));
                funcionario.setSenha(rs.getString("SENHA"));
                funcionario.setPermissao(rs.getInt("PERMISSAO"));
                return funcionario;
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do usuário no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isFuncVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBFUNCIONARIO",
                "Erro na leitura de funcionários no sistema");
    }

    public int getProxCodFunc() throws SQLException {
        return getProxCod("SELECT MAX(CODFUNC) FROM TBFUNCIONARIO",
                "Erro na leitura de funcionários no sistema");
    }

    public boolean deleteFunc(int codFunc) throws SQLException {
        int codPessoa = getCadastrado("SELECT CODPESSOA FROM TBFUNCIONARIO WHERE CODFUNC = "
                + codFunc, "Erro na leitura do funcionário no sistema");// obtém o codigo da pessoa
        delete("DELETE FROM TBFUNCIONARIO WHERE CODFUNC = " + codFunc,
                "Não foi possível excluir o funcionário do sistema");
        return delete("DELETE FROM TBPESSOA WHERE CODPESSOA = " + codPessoa,
                "Não foi possível excluir o funcionário do sistema");
    }

    public void listFuncionarios() throws SQLException {
        addFuncionariosMapa("SELECT F.CODFUNC, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, F.SEXO, "
                + "F.ESTADOCIVIL, F.DATANASC, P.COD_ENDERECO_BAI_CEP, E.ENDERECO, B.BAIRRO, P.NUMERO, "
                + "F.COMPLEMENTO, CEP.CEP, C.CIDADE, P.CONTATO, F.NUMCARTTRAB, F.CARGO, F.SALARIO, "
                + "F.USUARIO, F.SENHA, F.PERMISSAO, F.DATAADMISSAO, F.DATADEMISSAO FROM TBFUNCIONARIO F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO E ON EB.CODENDERECO = E.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "ORDER BY F.CODFUNC");
    }

    public void listFuncionariosCondicao(final int coluna, final String s, final int n, final double d) throws SQLException {
        switch (coluna) {
            case 0:
                addFuncionariosMapa(getSql("F.CODFUNC = " + n));
                break;
            case 1:
                addFuncionariosMapa(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
                break;
            case 2:
                addFuncionariosMapa(getSql("P.CPFCNPJ = '" + s + "'"));
                break;
            case 3:
                addFuncionariosMapa(getSql("P.RGIE = '" + s + "'"));
                break;
            case 4:
                addFuncionariosMapa(getSql("LOWER(E.ENDERECO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 5:
                addFuncionariosMapa(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 6:
                addFuncionariosMapa(getSql("P.NUMERO = " + n));
                break;
            case 7:
                addFuncionariosMapa(getSql("CEP.CEP = '" + s + "'"));
                break;
            case 8:
                addFuncionariosMapa(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
                break;
            case 9:
                addFuncionariosMapa(getSql("P.CONTATO = '" + s + "'"));
                break;
            case 10:
                addFuncionariosMapa(getSql("LOWER(F.CARGO) like '%" + s.toLowerCase() + "%'"));
                break;
            case 11:
                addFuncionariosMapa(getSql("F.SALARIO = " + d));
                break;
            default:
                if (s.equalsIgnoreCase("S")) {
                    addFuncionariosMapa(getSql("F.DATADEMISSAO IS NULL"));
                    break;
                } else {
                    if (s.equalsIgnoreCase("N")) {
                        addFuncionariosMapa(getSql("F.DATADEMISSAO IS NOT NULL"));
                        break;
                    }
                }
        }
    }

    private String getSql(String condicao) {
        return "SELECT F.CODFUNC, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, F.SEXO, "
                + "F.ESTADOCIVIL, F.DATANASC, P.COD_ENDERECO_BAI_CEP, E.ENDERECO, B.BAIRRO, P.NUMERO, "
                + "F.COMPLEMENTO, CEP.CEP, C.CIDADE, P.CONTATO, F.NUMCARTTRAB, F.CARGO, F.SALARIO, "
                + "F.USUARIO, F.SENHA, F.PERMISSAO, F.DATAADMISSAO, F.DATADEMISSAO FROM TBFUNCIONARIO F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBENDERECO_HAS_TBBAI_CEP EB ON P.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "INNER JOIN TBENDERECO E ON EB.CODENDERECO = E.CODENDERECO "
                + "INNER JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "INNER JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "INNER JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "INNER JOIN TBCIDADE C ON CEP.CODCIDADE = C.CODCIDADE "
                + "WHERE " + condicao + " ORDER BY F.CODFUNC";
    }

    private void addFuncionariosMapa(String sql) throws SQLException {
        funcionarios.clear();
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            int linha = 0;
            while (rs.next()) {
                funcionarios.put(linha, getFuncionarioAux(rs));
                linha++;
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de funcionários no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Funcionario getFuncionarioAux(ResultSet rs) throws SQLException {
        try {
            Funcionario func = new Funcionario();
            func.setCodigo(rs.getInt(1));
            func.setDataCadastro(rs.getTimestamp(2));
            func.setUltAlteracao(rs.getTimestamp(3));
            func.setNome(rs.getString(4));
            func.setCpfCnpj(rs.getString(5));
            func.setRgIe(rs.getString(6));
            String sexo = rs.getString(7);
            if (sexo.equals("M")) {
                func.setSexo("Masculino");
            } else {
                if (sexo.equals("F")) {
                    func.setSexo("Feminino");
                } else {
                    func.setSexo("Selecione");
                }
            }
            String estadoCivil = rs.getString(8);
            if (estadoCivil.equals("Sol")) {
                func.setEstadoCivil("Solteiro");
            } else {
                if (estadoCivil.equals("Cas")) {
                    func.setEstadoCivil("Casado");
                } else {
                    if (estadoCivil.equals("Sep")) {
                        func.setEstadoCivil("Separado");
                    } else {
                        if (estadoCivil.equals("Div")) {
                            func.setEstadoCivil("Divorciado");
                        } else {
                            if (estadoCivil.equals("Vil")) {
                                func.setEstadoCivil("Viúvo");
                            } else {
                                func.setEstadoCivil("Selecione");
                            }
                        }
                    }
                }
            }
            func.setDataNascimento(rs.getDate(9));
            Endereco endereco = new Endereco();
            endereco.setCodigo(rs.getInt(10));
            endereco.setEndereco(rs.getString(11));
            Bairro bairro = new Bairro();
            bairro.setBairro(rs.getString(12));
            func.setNumero(rs.getInt(13));
            func.setComplemento(rs.getString(14));
            Cep cep = new Cep();
            cep.setCep(rs.getString(15));
            Cidade cidade = new Cidade();
            cidade.setCidade(rs.getString(16));
            cep.setCidade(cidade);
            bairro.setCep(cep);
            endereco.setBairro(bairro);
            func.setEndereco(endereco);
            func.setContato(rs.getString(17));
            func.setNumCartTrabalho(rs.getString(18));
            func.setCargo(rs.getString(19));
            func.setSalario(rs.getDouble(20));
            func.setUsuario(rs.getString(21));
            func.setSenha(rs.getString(22));
            func.setPermissao(rs.getInt(23));
            func.setDataAdmissao(rs.getDate(24));
            func.setDataDemissao(rs.getDate(25));
            return func;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do funcionário no sistema\nProblema interno com o oracle\nCódigo do erro: " + ex.getMessage());
        }
    }

    public void limparMapa() {
        funcionarios.clear();
    }

    public Object conteudoTableModel(int linha, int coluna) throws SQLException {
        Funcionario func = funcionarios.get(linha);
        switch (coluna) {
            case 0:
                return func.getCodigo();
            case 1:
                return func.getNome();
            case 2:
                return func.getCpfCnpj();
            case 3:
                return func.getRgIe();
            case 4:
                return func.getEndereco().getEndereco();
            case 5:
                return func.getEndereco().getBairro().getBairro();
            case 6:
                return func.getNumero();
            case 7:
                return func.getEndereco().getBairro().getCep().getCep();
            case 8:
                return func.getEndereco().getBairro().getCep().getCidade().getCidade();
            case 9:
                return func.getContato();
            case 10:
                return func.getCargo();
            case 11:
                return NumberFormat.getCurrencyInstance().format(func.getSalario());
            default:
                if (func.getDataDemissao() == null) {
                    return "S";
                } else {
                    return "N";
                }
        }
    }

    public int getQtdadeFuncionariosCadas() {
        return funcionarios.size();
    }

    public Funcionario getFuncionarioMapa(int linha) {
        return funcionarios.get(linha);
    }
}
