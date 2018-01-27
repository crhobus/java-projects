package Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cidade;
import Modelo.Departamento;
import Modelo.Funcionario;
import Modelo.Setor;
import Principal.DBDAO;

public class FuncionarioDAO extends DBDAO {

    private Connection con;

    public FuncionarioDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertFunc(Funcionario funcionario) throws SQLException {
        if (isFuncCadastrado(funcionario.getCodFunc())) {
            return updateFunc(funcionario);
        }
        PreparedStatement stm = null;
        try {
            int codPessoa = insertPessoa(funcionario);
            stm = con.prepareStatement("INSERT INTO TBFUNCIONARIO (CODFUNC, CODPESSOA, "
                    + "DATAADMIS, NUMCONTRATO, TIPOCONTRATO, NUMPIS, NUMCARTHABIL, TITULOELEITOR, NUMCARTTRAB, "
                    + "CERTRESERVISTA, CARGO, TURNO, HORAINICIAL, HORAFINAL, DIAPAGTO, BANCO, NUMCONTA, SALARIO, "
                    + "DATADEMIS, ATIVO, CODSETOR, CODDEPARTAMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, funcionario.getCodFunc());
            stm.setInt(2, codPessoa);
            stm.setDate(3, new Date(funcionario.getDataAdmissao().getTime()));
            stm.setString(4, funcionario.getNumContrato());
            stm.setString(5, funcionario.getTipoContrato());
            stm.setString(6, funcionario.getNumPis());
            stm.setString(7, funcionario.getNumCartHabilitacao());
            stm.setString(8, funcionario.getTituloEleitor());
            stm.setString(9, funcionario.getNumCartTrabalho());
            stm.setString(10, funcionario.getCertReservista());
            stm.setString(11, funcionario.getCargo());
            stm.setString(12, funcionario.getTurno().substring(0, 3));
            stm.setString(13, funcionario.getHoraInicial());
            stm.setString(14, funcionario.getHoraFinal());
            stm.setInt(15, funcionario.getDiaPagto());
            stm.setString(16, funcionario.getBanco());
            stm.setString(17, funcionario.getNumConta());
            stm.setDouble(18, funcionario.getSalario());
            if (funcionario.getDataDemissao() != null) {
                stm.setDate(19, new Date(funcionario.getDataDemissao().getTime()));
            } else {
                stm.setDate(19, null);
            }
            if (funcionario.isAtivo()) {
                stm.setString(20, "T");
            } else {
                stm.setString(20, "F");
            }
            stm.setInt(21, funcionario.getSetor().getCodSetor());
            stm.setInt(22, funcionario.getDepartamento().getCodDepartamento());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o funcionário no sistema");
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
            stm = con.prepareStatement("INSERT INTO TBPESSOA (CODPESSOA, DATACADAS, ULTALTERACAO, "
                    + "NOME, CPFCNPJ, RGIE, SEXO, ESTADOCIVIL, DATANASC, NUMERO, COMPLEMENTO, REFERENCIA, FONE, CELULAR1, "
                    + "CELULAR2, EMAIL, MSN, SKYPE, DESCRICAO, COD_ENDERECO_BAI_CEP, NOMEPAI, NOMEMAE, COD_CIDADE_ESTADO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int proxCodPessoa = getProxCod("SELECT MAX(CODPESSOA) FROM TBPESSOA",
                    "Erro na leitura de funcionários no sistema");// obtem próximo codigo de pessoa
            stm.setInt(1, proxCodPessoa);
            stm.setTimestamp(2, new Timestamp(funcionario.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(funcionario.getUltAlteracao().getTime()));
            stm.setString(4, funcionario.getNome());
            stm.setString(5, funcionario.getCpfCnpj());
            stm.setString(6, funcionario.getRgIe());
            stm.setString(7, funcionario.getSexo().substring(0, 1));
            stm.setString(8, funcionario.getEstadoCivil().substring(0, 3));
            stm.setDate(9, new Date(funcionario.getDataNascimento().getTime()));
            stm.setInt(10, funcionario.getNumero());
            stm.setString(11, funcionario.getComplemento());
            stm.setString(12, funcionario.getReferencia());
            stm.setString(13, funcionario.getFone());
            stm.setString(14, funcionario.getCelular1());
            stm.setString(15, funcionario.getCelular2());
            stm.setString(16, funcionario.getEmail());
            stm.setString(17, funcionario.getMsn());
            stm.setString(18, funcionario.getSkype());
            stm.setString(19, funcionario.getDescricao());
            stm.setInt(20, funcionario.getCodEndereco());
            stm.setString(21, funcionario.getNomePai());
            stm.setString(22, funcionario.getNomeMae());
            stm.setInt(23, funcionario.getCidad().getCodCidade());
            stm.execute();
            return proxCodPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o funcionário no sistema");
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
            stm = con.prepareStatement("UPDATE TBFUNCIONARIO SET CODPESSOA = ?, "
                    + "DATAADMIS = ?, NUMCONTRATO = ?, TIPOCONTRATO = ?, NUMPIS = ?, NUMCARTHABIL = ?, "
                    + "TITULOELEITOR = ?, NUMCARTTRAB = ?, CERTRESERVISTA = ?, CARGO = ?, TURNO = ?, "
                    + "HORAINICIAL = ?, HORAFINAL = ?, DIAPAGTO = ?, BANCO = ?, NUMCONTA = ?, SALARIO = ?, "
                    + "DATADEMIS = ?, ATIVO = ?, CODSETOR = ?, CODDEPARTAMENTO = ? WHERE CODFUNC = ?");
            stm.setInt(1, codPessoa);
            stm.setDate(2, new Date(funcionario.getDataAdmissao().getTime()));
            stm.setString(3, funcionario.getNumContrato());
            stm.setString(4, funcionario.getTipoContrato());
            stm.setString(5, funcionario.getNumPis());
            stm.setString(6, funcionario.getNumCartHabilitacao());
            stm.setString(7, funcionario.getTituloEleitor());
            stm.setString(8, funcionario.getNumCartTrabalho());
            stm.setString(9, funcionario.getCertReservista());
            stm.setString(10, funcionario.getCargo());
            stm.setString(11, funcionario.getTurno().substring(0, 3));
            stm.setString(12, funcionario.getHoraInicial());
            stm.setString(13, funcionario.getHoraFinal());
            stm.setInt(14, funcionario.getDiaPagto());
            stm.setString(15, funcionario.getBanco());
            stm.setString(16, funcionario.getNumConta());
            stm.setDouble(17, funcionario.getSalario());
            if (funcionario.getDataDemissao() != null) {
                stm.setDate(18, new Date(funcionario.getDataDemissao().getTime()));
            } else {
                stm.setDate(18, null);
            }
            if (funcionario.isAtivo()) {
                stm.setString(19, "T");
            } else {
                stm.setString(19, "F");
            }
            stm.setInt(20, funcionario.getSetor().getCodSetor());
            stm.setInt(21, funcionario.getDepartamento().getCodDepartamento());
            stm.setInt(22, funcionario.getCodFunc());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do funcionario no sistema");
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
                    + funcionario.getCodFunc(), "Erro na leitura do funcionário no sistema");// obtém o codigo da pessoa
            stm = con.prepareStatement("UPDATE TBPESSOA SET DATACADAS = ?, ULTALTERACAO = ?, "
                    + "NOME = ?, CPFCNPJ = ?, RGIE = ?, SEXO = ?, ESTADOCIVIL = ?, DATANASC = ?, NUMERO = ?, COMPLEMENTO = ?, "
                    + "REFERENCIA = ?, FONE = ?, CELULAR1 = ?, CELULAR2 = ?, EMAIL = ?, MSN = ?, SKYPE = ?, DESCRICAO = ?, "
                    + "COD_ENDERECO_BAI_CEP = ?, NOMEPAI = ?, NOMEMAE = ?, COD_CIDADE_ESTADO = ? WHERE CODPESSOA = ?");
            stm.setTimestamp(1, new Timestamp(funcionario.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(funcionario.getUltAlteracao().getTime()));
            stm.setString(3, funcionario.getNome());
            stm.setString(4, funcionario.getCpfCnpj());
            stm.setString(5, funcionario.getRgIe());
            stm.setString(6, funcionario.getSexo().substring(0, 1));
            stm.setString(7, funcionario.getEstadoCivil().substring(0, 3));
            stm.setDate(8, new Date(funcionario.getDataNascimento().getTime()));
            stm.setInt(9, funcionario.getNumero());
            stm.setString(10, funcionario.getComplemento());
            stm.setString(11, funcionario.getReferencia());
            stm.setString(12, funcionario.getFone());
            stm.setString(13, funcionario.getCelular1());
            stm.setString(14, funcionario.getCelular2());
            stm.setString(15, funcionario.getEmail());
            stm.setString(16, funcionario.getMsn());
            stm.setString(17, funcionario.getSkype());
            stm.setString(18, funcionario.getDescricao());
            stm.setInt(19, funcionario.getCodEndereco());
            stm.setString(20, funcionario.getNomePai());
            stm.setString(21, funcionario.getNomeMae());
            stm.setInt(22, funcionario.getCidad().getCodCidade());
            stm.setInt(23, codPessoa);
            stm.execute();
            return codPessoa;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do funcionario no sistema");
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

    public List<Funcionario> listFunc() throws SQLException {
        return getLista("SELECT F.CODFUNC, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, P.SEXO, "
                + "P.ESTADOCIVIL, P.DATANASC, P.COD_CIDADE_ESTADO, C1.CIDADE, P1.PAIS, P.COD_ENDERECO_BAI_CEP, P.NOMEPAI, "
                + "P.NOMEMAE, EN.ENDERECO, B.BAIRRO, P.NUMERO, P.COMPLEMENTO, CEP.CEP, C.CIDADE, "
                + "ES.ESTADO, R.REGIAO, P.REFERENCIA, P.FONE, P.CELULAR1, P.CELULAR2, P.EMAIL, P.MSN, "
                + "P.SKYPE, F.DATAADMIS, F.NUMCONTRATO, F.TIPOCONTRATO, F.NUMPIS, F.NUMCARTHABIL, "
                + "F.TITULOELEITOR, F.NUMCARTTRAB, F.CERTRESERVISTA, F.CODSETOR, S.SETOR, F.CODDEPARTAMENTO, "
                + "D.DEPARTAMENTO, F.CARGO, F.TURNO, F.HORAINICIAL, F.HORAFINAL, F.DIAPAGTO, F.BANCO, "
                + "F.NUMCONTA, F.SALARIO, P.DESCRICAO, F.DATADEMIS, F.ATIVO FROM TBFUNCIONARIO F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE1 ON P.COD_CIDADE_ESTADO = CE1.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C1 ON CE1.CODCIDADE = C1.CODCIDADE "
                + "INNER JOIN TBESTADO ES1 ON CE1.CODESTADO = ES1.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP1 ON ES1.COD_REGIAO_PAIS = RP1.COD_REGIAO_PAIS "
                + "INNER JOIN TBPAIS P1 ON RP1.CODPAIS = P1.CODPAIS "
                + "INNER JOIN TBSETOR S ON F.CODSETOR = S.CODSETOR "
                + "INNER JOIN TBDEPARTAMENTO D ON F.CODDEPARTAMENTO = D.CODDEPARTAMENTO "
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
                + "ORDER BY F.CODFUNC");
    }

    private List<Funcionario> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Funcionario> lista = new ArrayList<Funcionario>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getFuncAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de funcionários no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Funcionario getFuncAux(ResultSet rs) throws SQLException {
        try {
            Funcionario func = new Funcionario();
            func.setCodFunc(rs.getInt(1));
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
            Cidade cidade = new Cidade();
            cidade.setCodCidade(rs.getInt(10));
            cidade.setCidade(rs.getString(11));
            cidade.setPais(rs.getString(12));
            func.setCidad(cidade);
            func.setCodEndereco(rs.getInt(13));
            func.setNomePai(rs.getString(14));
            func.setNomeMae(rs.getString(15));
            func.setEndereco(rs.getString(16));
            func.setBairro(rs.getString(17));
            func.setNumero(rs.getInt(18));
            func.setComplemento(rs.getString(19));
            func.setCep(rs.getString(20));
            func.setCidade(rs.getString(21));
            func.setEstado(rs.getString(22));
            func.setRegiao(rs.getString(23));
            func.setReferencia(rs.getString(24));
            func.setFone(rs.getString(25));
            func.setCelular1(rs.getString(26));
            func.setCelular2(rs.getString(27));
            func.setEmail(rs.getString(28));
            func.setMsn(rs.getString(29));
            func.setSkype(rs.getString(30));
            func.setDataAdmissao(rs.getDate(31));
            func.setNumContrato(rs.getString(32));
            func.setTipoContrato(rs.getString(33));
            func.setNumPis(rs.getString(34));
            func.setNumCartHabilitacao(rs.getString(35));
            func.setTituloEleitor(rs.getString(36));
            func.setNumCartTrabalho(rs.getString(37));
            func.setCertReservista(rs.getString(38));
            Setor setor = new Setor();
            setor.setCodSetor(rs.getInt(39));
            setor.setSetor(rs.getString(40));
            func.setSetor(setor);
            Departamento departamento = new Departamento();
            departamento.setCodDepartamento(rs.getInt(41));
            departamento.setDepartamento(rs.getBytes(42));
            func.setDepartamento(departamento);
            func.setCargo(rs.getString(43));
            String turno = rs.getString(44);
            if (turno.equals("Ger")) {
                func.setTurno("Geral");
            } else {
                if (turno.equals("Pri")) {
                    func.setTurno("Primeiro");
                } else {
                    if (turno.equals("Seg")) {
                        func.setTurno("Segundo");
                    } else {
                        if (turno.equals("Ter")) {
                            func.setTurno("Terceiro");
                        } else {
                            if (turno.equals("Qua")) {
                                func.setTurno("Quarto");
                            } else {
                                if (turno.equals("Qui")) {
                                    func.setTurno("Quinto");
                                } else {
                                    func.setTurno("Selecione");
                                }
                            }
                        }
                    }
                }
            }
            func.setHoraInicial(rs.getString(45));
            func.setHoraFinal(rs.getString(46));
            func.setDiaPagto(rs.getInt(47));
            func.setBanco(rs.getString(48));
            func.setNumConta(rs.getString(49));
            func.setSalario(rs.getDouble(50));
            func.setDescricao(rs.getString(51));
            func.setDataDemissao(rs.getDate(52));
            String ativo = rs.getString(53);
            if (ativo.equals("T")) {
                func.setAtivo(true);
            } else {
                func.setAtivo(false);
            }
            return func;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do funcionário no sistema");
        }
    }

    public List<Funcionario> getLista(final int coluna, final String s, final int n, final double d) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("F.CODFUNC = " + n));
            case 1:
                return getLista(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("P.CPFCNPJ = '" + s + "'"));
            case 3:
                return getLista(getSql("P.RGIE = '" + s + "'"));
            case 4:
                return getLista(getSql("LOWER(EN.ENDERECO) like '%" + s.toLowerCase() + "%'"));
            case 5:
                return getLista(getSql("LOWER(B.BAIRRO) like '%" + s.toLowerCase() + "%'"));
            case 6:
                return getLista(getSql("P.NUMERO = " + n));
            case 7:
                return getLista(getSql("CEP.CEP = '" + s + "'"));
            case 8:
                return getLista(getSql("LOWER(C.CIDADE) like '%" + s.toLowerCase() + "%'"));
            case 9:
                return getLista(getSql("LOWER(ES.ESTADO) like '%" + s.toLowerCase() + "%'"));
            case 10:
                return getLista(getSql("P.FONE = '" + s + "'"));
            case 11:
                return getLista(getSql("LOWER(F.CARGO) like '%" + s.toLowerCase() + "%'"));
            case 12:
                return getLista(getSql("F.SALARIO = " + d));
            default:
                return getLista(getSql("LOWER(F.ATIVO) like '%" + s.toLowerCase() + "%'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT F.CODFUNC, P.DATACADAS, P.ULTALTERACAO, P.NOME, P.CPFCNPJ, P.RGIE, P.SEXO, "
                + "P.ESTADOCIVIL, P.DATANASC, P.COD_CIDADE_ESTADO, C1.CIDADE, P1.PAIS, P.COD_ENDERECO_BAI_CEP, P.NOMEPAI, "
                + "P.NOMEMAE, EN.ENDERECO, B.BAIRRO, P.NUMERO, P.COMPLEMENTO, CEP.CEP, C.CIDADE, "
                + "ES.ESTADO, R.REGIAO, P.REFERENCIA, P.FONE, P.CELULAR1, P.CELULAR2, P.EMAIL, P.MSN, "
                + "P.SKYPE, F.DATAADMIS, F.NUMCONTRATO, F.TIPOCONTRATO, F.NUMPIS, F.NUMCARTHABIL, "
                + "F.TITULOELEITOR, F.NUMCARTTRAB, F.CERTRESERVISTA, F.CODSETOR, S.SETOR, F.CODDEPARTAMENTO, "
                + "D.DEPARTAMENTO, F.CARGO, F.TURNO, F.HORAINICIAL, F.HORAFINAL, F.DIAPAGTO, F.BANCO, "
                + "F.NUMCONTA, F.SALARIO, P.DESCRICAO, F.DATADEMIS, F.ATIVO FROM TBFUNCIONARIO F "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBCIDADE_HAS_TBESTADO CE1 ON P.COD_CIDADE_ESTADO = CE1.COD_CIDADE_ESTADO "
                + "INNER JOIN TBCIDADE C1 ON CE1.CODCIDADE = C1.CODCIDADE "
                + "INNER JOIN TBESTADO ES1 ON CE1.CODESTADO = ES1.CODESTADO "
                + "INNER JOIN TBREGIAO_HAS_TBPAIS RP1 ON ES1.COD_REGIAO_PAIS = RP1.COD_REGIAO_PAIS "
                + "INNER JOIN TBPAIS P1 ON RP1.CODPAIS = P1.CODPAIS "
                + "INNER JOIN TBSETOR S ON F.CODSETOR = S.CODSETOR "
                + "INNER JOIN TBDEPARTAMENTO D ON F.CODDEPARTAMENTO = D.CODDEPARTAMENTO "
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
                + "WHERE " + condicao + " ORDER BY F.CODFUNC";
    }
}
