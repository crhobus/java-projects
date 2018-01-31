package FuncionarioDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.MetodosFirebirdGenerico;
import Modelo.Funcionario;

public class FuncionarioFirebird extends MetodosFirebirdGenerico implements FuncionarioDAO {

    @Override
    public boolean isFuncVazio(Connection con) throws Exception {
        return isVazio(con, "SELECT * FROM TBFUNCIONARIO", "funcionários");
    }

    @Override
    public Funcionario getFuncionario(String cpf, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFUNCIONARIO WHERE CPF = '" + cpf + "'").executeQuery();
            if (rs.next()) {
                return leituraFuncionario(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de funcionário do banco de dados");
        }
    }

    @Override
    public boolean verificaFuncCadas(String nome, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFUNCIONARIO WHERE NOME = '" + nome + "'").executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de funcionário do banco de dados");
        }
    }

    private Funcionario leituraFuncionario(ResultSet rs) throws Exception {
        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(rs.getInt("CODIGO"));
            funcionario.setDigitoConta(rs.getInt("DIGITOCONTA"));
            funcionario.setCracha(rs.getInt("CRACHA"));
            funcionario.setDescontoINSS(rs.getInt("DESCONTOINSS"));
            funcionario.setNumero(rs.getInt("NUMERO"));
            funcionario.setIdade(rs.getInt("IDADE"));
            funcionario.setConta(rs.getInt("CONTA"));
            funcionario.setContaFGTS(rs.getInt("CONTAFGTS"));
            funcionario.setZona(rs.getInt("ZONA"));
            funcionario.setSecao(rs.getInt("SECAO"));
            funcionario.setNome(rs.getString("NOME"));
            funcionario.setRg(rs.getString("RG"));
            funcionario.setCpf(rs.getString("CPF"));
            funcionario.setSexo(rs.getString("SEXO"));
            funcionario.setEstadoCivil(rs.getString("ESTADOCIVIL"));
            funcionario.setTipoPessoa(rs.getString("TIPOPESSOA"));
            funcionario.setEndereco(rs.getString("ENDERECO"));
            funcionario.setBairro(rs.getString("BAIRRO"));
            funcionario.setComplemento(rs.getString("COMPLEMENTO"));
            funcionario.setReferencia(rs.getString("REFERENCIA"));
            funcionario.setCidade(rs.getString("CIDADE"));
            funcionario.setUf(rs.getString("UF"));
            funcionario.setEmail(rs.getString("EMAIL"));
            funcionario.setFone(rs.getString("FONE"));
            funcionario.setCelular1(rs.getString("CELULAR1"));
            funcionario.setCelular2(rs.getString("CELULAR2"));
            funcionario.setDescricao(rs.getString("DESCRICAO"));
            funcionario.setEmpresa(rs.getString("EMPRESA"));
            funcionario.setFoneEmpresa(rs.getString("FONEEMPRESA"));
            funcionario.setFax(rs.getString("FAX"));
            funcionario.setMsn(rs.getString("MSN"));
            funcionario.setSkype(rs.getString("SKYPE"));
            funcionario.setNaturalidade(rs.getString("NATURALIDADE"));
            funcionario.setBanco(rs.getString("BANCO"));
            funcionario.setAgencia(rs.getString("AGENCIA"));
            funcionario.setCarteiraHabilitacao(rs.getString("CARTEIRAHABILITACAO"));
            funcionario.setCarteiraTrabalho(rs.getString("CARTEIRATRABALHO"));
            funcionario.setCertificadoReservista(rs.getString("CERTIFICADORESERVISTA"));
            funcionario.setTituloEleitor(rs.getString("TITULOELEITOR"));
            funcionario.setNomePai(rs.getString("NOMEPAI"));
            funcionario.setNomeMae(rs.getString("NOMEMAE"));
            funcionario.setPais(rs.getString("PAIS"));
            funcionario.setChefe(rs.getString("CHEFE"));
            funcionario.setCargo(rs.getString("CARGO"));
            funcionario.setSetor(rs.getString("SETOR"));
            funcionario.setCep(rs.getString("CEP"));
            funcionario.setRacaCor(rs.getString("RACACOR"));
            funcionario.setHorasSemanais(rs.getString("HORASSEMANAIS"));
            funcionario.setHorasMesais(rs.getString("HORASMENSAIS"));
            funcionario.setHoraInicial(rs.getString("HORAINICIAL"));
            funcionario.setHoraFinal(rs.getString("HORAFINAL"));
            funcionario.setIe(rs.getString("IE"));
            funcionario.setCnpj(rs.getString("CNPJ"));
            funcionario.setOutrasConsideracoes(rs.getString("OUTRASCONSIDERACOES"));
            funcionario.setTipoContrato(rs.getString("TIPOCONTRATO"));
            funcionario.setNumeroContrato(rs.getString("NUMEROCONTRATO"));
            funcionario.setInscricaoINSS(rs.getString("INSCRICAOINSS"));
            funcionario.setPis(rs.getString("PIS"));
            funcionario.setInsentoIR(rs.getString("ISENTOIR"));
            funcionario.setContribuicaoSindical(rs.getString("CONTRIBUICAOSINDICAL"));
            funcionario.setDataNasc(rs.getDate("DATANASCIMENTO"));
            funcionario.setDataCadastro(rs.getTimestamp("DATACADASTRO"));
            funcionario.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            funcionario.setDataContratacaoDemissao(rs.getDate("DATACONTRDEMI"));
            funcionario.setDataPagamento(rs.getDate("DATAPAGAMENTO"));
            funcionario.setSalarioInicial(rs.getDouble("SALARIOINICIAL"));
            funcionario.setAumento(rs.getDouble("AUMENTO"));
            funcionario.setSalarioLiquido(rs.getDouble("SALARIOLIQUIDO"));
            funcionario.setValeAlimentacao(rs.getDouble("VALEALIMENTACAO"));
            funcionario.setValeTransporte(rs.getDouble("VALETRANSPORTE"));
            funcionario.setPlanoSaude(rs.getDouble("PLANOSAUDE"));
            funcionario.setSaldoFGTS(rs.getDouble("SALDOFGTS"));
            funcionario.setSeguroVida(rs.getDouble("SEGUROVIDA"));
            if (rs.getString("ATIVO").equals("T")) {
                funcionario.setAtivo(true);
            }
            return funcionario;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de funcionário do banco de dados");
        }
    }

    @Override
    public int getProxCodFunc(Connection con) throws Exception {
        return getProxCod(con, "SELECT * FROM TBFUNCIONARIO", "funcionários");
    }

    @Override
    public List<Funcionario> listFuncionarios(Connection con) throws Exception {
        try {
            List<Funcionario> lista = new ArrayList<Funcionario>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFUNCIONARIO").executeQuery();
            while (rs.next()) {
                lista.add(leituraFuncionario(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de funcionários do banco de dados");
        }
    }

    @Override
    public boolean removeFuncionario(String cpf, Connection con) throws Exception {
        try {
            if (getFuncionario(cpf, con) != null) {
                PreparedStatement stm = con.prepareStatement("DELETE FROM TBFUNCIONARIO WHERE CPF = ?");
                stm.setString(1, cpf);
                stm.execute();
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir um registro de funcionário do banco de dados");
        }
    }

    @Override
    public boolean setFuncionario(Funcionario funcionario, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBFUNCIONARIO WHERE CODIGO = " + funcionario.getCodigo()).executeQuery();
            int cod = 0;
            if (rs.next()) {
                cod = rs.getInt("CODIGO");
            }
            PreparedStatement stm;
            if (cod != 0) {
                stm = con.prepareStatement("UPDATE TBFUNCIONARIO SET DIGITOCONTA = ?, CRACHA = ?, DESCONTOINSS = ?, NUMERO = ?, "
                        + "IDADE = ?, CONTA = ?, CONTAFGTS = ?, ZONA = ?, SECAO = ?, NOME = ?, RG = ?, CPF = ?, SEXO = ?, ESTADOCIVIL = ?, TIPOPESSOA = ?, ENDERECO = ?, "
                        + "BAIRRO = ?, COMPLEMENTO = ?, REFERENCIA = ?, CIDADE = ?, UF = ?, EMAIL = ?, FONE = ?, CELULAR1 = ?, CELULAR2 = ?, DESCRICAO = ?, EMPRESA = ?, "
                        + "FONEEMPRESA = ?, FAX = ?, MSN = ?, SKYPE = ?, NATURALIDADE = ?, BANCO = ?, AGENCIA = ?, CARTEIRAHABILITACAO = ?, CARTEIRATRABALHO = ?, "
                        + "CERTIFICADORESERVISTA = ?, TITULOELEITOR = ?, NOMEPAI = ?, NOMEMAE = ?, PAIS = ?, CHEFE = ?, CARGO = ?, SETOR = ?, CEP = ?, RACACOR = ?, "
                        + "HORASSEMANAIS = ?, HORASMENSAIS = ?, HORAINICIAL = ?, HORAFINAL = ?, IE = ?, CNPJ = ?, OUTRASCONSIDERACOES = ?, TIPOCONTRATO = ?, "
                        + "NUMEROCONTRATO = ?, INSCRICAOINSS = ?, PIS = ?, ISENTOIR = ?, CONTRIBUICAOSINDICAL = ?, DATANASCIMENTO = ?, DATACADASTRO = ?, "
                        + "ULTALTERACAO = ?, DATACONTRDEMI = ?, DATAPAGAMENTO = ?, SALARIOINICIAL = ?, AUMENTO = ?, SALARIOLIQUIDO = ?, VALEALIMENTACAO = ?, "
                        + "VALETRANSPORTE = ?, PLANOSAUDE = ?, SALDOFGTS = ?, SEGUROVIDA = ?, ATIVO = ? WHERE CODIGO = ?");
                stm.setInt(1, funcionario.getDigitoConta());
                stm.setInt(2, funcionario.getCracha());
                stm.setInt(3, funcionario.getDescontoINSS());
                stm.setInt(4, funcionario.getNumero());
                stm.setInt(5, funcionario.getIdade());
                stm.setInt(6, funcionario.getConta());
                stm.setInt(7, funcionario.getContaFGTS());
                stm.setInt(8, funcionario.getZona());
                stm.setInt(9, funcionario.getSecao());
                stm.setString(10, funcionario.getNome());
                stm.setString(11, funcionario.getRg());
                stm.setString(12, funcionario.getCpf());
                stm.setString(13, funcionario.getSexo());
                stm.setString(14, funcionario.getEstadoCivil());
                stm.setString(15, funcionario.getTipoPessoa());
                stm.setString(16, funcionario.getEndereco());
                stm.setString(17, funcionario.getBairro());
                stm.setString(18, funcionario.getComplemento());
                stm.setString(19, funcionario.getReferencia());
                stm.setString(20, funcionario.getCidade());
                stm.setString(21, funcionario.getUf());
                stm.setString(22, funcionario.getEmail());
                stm.setString(23, funcionario.getFone());
                stm.setString(24, funcionario.getCelular1());
                stm.setString(25, funcionario.getCelular2());
                stm.setString(26, funcionario.getDescricao());
                stm.setString(27, funcionario.getEmpresa());
                stm.setString(28, funcionario.getFoneEmpresa());
                stm.setString(29, funcionario.getFax());
                stm.setString(30, funcionario.getMsn());
                stm.setString(31, funcionario.getSkype());
                stm.setString(32, funcionario.getNaturalidade());
                stm.setString(33, funcionario.getBanco());
                stm.setString(34, funcionario.getAgencia());
                stm.setString(35, funcionario.getCarteiraHabilitacao());
                stm.setString(36, funcionario.getCarteiraTrabalho());
                stm.setString(37, funcionario.getCertificadoReservista());
                stm.setString(38, funcionario.getTituloEleitor());
                stm.setString(39, funcionario.getNomePai());
                stm.setString(40, funcionario.getNomeMae());
                stm.setString(41, funcionario.getPais());
                stm.setString(42, funcionario.getChefe());
                stm.setString(43, funcionario.getCargo());
                stm.setString(44, funcionario.getSetor());
                stm.setString(45, funcionario.getCep());
                stm.setString(46, funcionario.getRacaCor());
                stm.setString(47, funcionario.getHorasSemanais());
                stm.setString(48, funcionario.getHorasMesais());
                stm.setString(49, funcionario.getHoraInicial());
                stm.setString(50, funcionario.getHoraFinal());
                stm.setString(51, funcionario.getIe());
                stm.setString(52, funcionario.getCep());
                stm.setString(53, funcionario.getOutrasConsideracoes());
                stm.setString(54, funcionario.getTipoContrato());
                stm.setString(55, funcionario.getNumeroContrato());
                stm.setString(56, funcionario.getInscricaoINSS());
                stm.setString(57, funcionario.getPis());
                stm.setString(58, funcionario.getInsentoIR());
                stm.setString(59, funcionario.getContribuicaoSindical());
                stm.setDate(60, new Date(funcionario.getDataNasc().getTime()));
                stm.setTimestamp(61, new Timestamp(funcionario.getDataCadastro().getTime()));
                stm.setTimestamp(62, new Timestamp(funcionario.getUltAlteracao().getTime()));
                stm.setDate(63, new Date(funcionario.getDataContratacaoDemissao().getTime()));
                stm.setDate(64, new Date(funcionario.getDataPagamento().getTime()));
                stm.setDouble(65, funcionario.getSalarioInicial());
                stm.setDouble(66, funcionario.getAumento());
                stm.setDouble(67, funcionario.getSalarioLiquido());
                stm.setDouble(68, funcionario.getValeAlimentacao());
                stm.setDouble(69, funcionario.getValeTransporte());
                stm.setDouble(70, funcionario.getPlanoSaude());
                stm.setDouble(71, funcionario.getSaldoFGTS());
                stm.setDouble(72, funcionario.getSeguroVida());
                if (funcionario.isAtivo()) {
                    stm.setString(73, "T");
                } else {
                    stm.setString(73, "F");
                }
                stm.setInt(74, funcionario.getCodigo());
                stm.execute();
                return true;
            } else {
                stm = con.prepareStatement("INSERT INTO TBFUNCIONARIO (CODIGO, DIGITOCONTA, CRACHA, DESCONTOINSS, NUMERO, "
                        + "IDADE, CONTA, CONTAFGTS, ZONA, SECAO, NOME, RG, CPF, SEXO, ESTADOCIVIL, TIPOPESSOA, ENDERECO, "
                        + "BAIRRO, COMPLEMENTO, REFERENCIA, CIDADE, UF, EMAIL, FONE, CELULAR1, CELULAR2, DESCRICAO, EMPRESA, "
                        + "FONEEMPRESA, FAX, MSN, SKYPE, NATURALIDADE, BANCO, AGENCIA, CARTEIRAHABILITACAO, CARTEIRATRABALHO, "
                        + "CERTIFICADORESERVISTA, TITULOELEITOR, NOMEPAI, NOMEMAE, PAIS, CHEFE, CARGO, SETOR, CEP, RACACOR, "
                        + "HORASSEMANAIS, HORASMENSAIS, HORAINICIAL, HORAFINAL, IE, CNPJ, OUTRASCONSIDERACOES, TIPOCONTRATO, "
                        + "NUMEROCONTRATO, INSCRICAOINSS, PIS, ISENTOIR, CONTRIBUICAOSINDICAL, DATANASCIMENTO, DATACADASTRO, "
                        + "ULTALTERACAO, DATACONTRDEMI, DATAPAGAMENTO, SALARIOINICIAL, AUMENTO, SALARIOLIQUIDO, VALEALIMENTACAO, "
                        + "VALETRANSPORTE, PLANOSAUDE, SALDOFGTS, SEGUROVIDA, ATIVO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                        + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                        + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm.setInt(1, funcionario.getCodigo());
                stm.setInt(2, funcionario.getDigitoConta());
                stm.setInt(3, funcionario.getCracha());
                stm.setInt(4, funcionario.getDescontoINSS());
                stm.setInt(5, funcionario.getNumero());
                stm.setInt(6, funcionario.getIdade());
                stm.setInt(7, funcionario.getConta());
                stm.setInt(8, funcionario.getContaFGTS());
                stm.setInt(9, funcionario.getZona());
                stm.setInt(10, funcionario.getSecao());
                stm.setString(11, funcionario.getNome());
                stm.setString(12, funcionario.getRg());
                stm.setString(13, funcionario.getCpf());
                stm.setString(14, funcionario.getSexo());
                stm.setString(15, funcionario.getEstadoCivil());
                stm.setString(16, funcionario.getTipoPessoa());
                stm.setString(17, funcionario.getEndereco());
                stm.setString(18, funcionario.getBairro());
                stm.setString(19, funcionario.getComplemento());
                stm.setString(20, funcionario.getReferencia());
                stm.setString(21, funcionario.getCidade());
                stm.setString(22, funcionario.getUf());
                stm.setString(23, funcionario.getEmail());
                stm.setString(24, funcionario.getFone());
                stm.setString(25, funcionario.getCelular1());
                stm.setString(26, funcionario.getCelular2());
                stm.setString(27, funcionario.getDescricao());
                stm.setString(28, funcionario.getEmpresa());
                stm.setString(29, funcionario.getFoneEmpresa());
                stm.setString(30, funcionario.getFax());
                stm.setString(31, funcionario.getMsn());
                stm.setString(32, funcionario.getSkype());
                stm.setString(33, funcionario.getNaturalidade());
                stm.setString(34, funcionario.getBanco());
                stm.setString(35, funcionario.getAgencia());
                stm.setString(36, funcionario.getCarteiraHabilitacao());
                stm.setString(37, funcionario.getCarteiraTrabalho());
                stm.setString(38, funcionario.getCertificadoReservista());
                stm.setString(39, funcionario.getTituloEleitor());
                stm.setString(40, funcionario.getNomePai());
                stm.setString(41, funcionario.getNomeMae());
                stm.setString(42, funcionario.getPais());
                stm.setString(43, funcionario.getChefe());
                stm.setString(44, funcionario.getCargo());
                stm.setString(45, funcionario.getSetor());
                stm.setString(46, funcionario.getCep());
                stm.setString(47, funcionario.getRacaCor());
                stm.setString(48, funcionario.getHorasSemanais());
                stm.setString(49, funcionario.getHorasMesais());
                stm.setString(50, funcionario.getHoraInicial());
                stm.setString(51, funcionario.getHoraFinal());
                stm.setString(52, funcionario.getIe());
                stm.setString(53, funcionario.getCep());
                stm.setString(54, funcionario.getOutrasConsideracoes());
                stm.setString(55, funcionario.getTipoContrato());
                stm.setString(56, funcionario.getNumeroContrato());
                stm.setString(57, funcionario.getInscricaoINSS());
                stm.setString(58, funcionario.getPis());
                stm.setString(59, funcionario.getInsentoIR());
                stm.setString(60, funcionario.getContribuicaoSindical());
                stm.setDate(61, new Date(funcionario.getDataNasc().getTime()));
                stm.setTimestamp(62, new Timestamp(funcionario.getDataCadastro().getTime()));
                stm.setTimestamp(63, new Timestamp(funcionario.getUltAlteracao().getTime()));
                stm.setDate(64, new Date(funcionario.getDataContratacaoDemissao().getTime()));
                stm.setDate(65, new Date(funcionario.getDataPagamento().getTime()));
                stm.setDouble(66, funcionario.getSalarioInicial());
                stm.setDouble(67, funcionario.getAumento());
                stm.setDouble(68, funcionario.getSalarioLiquido());
                stm.setDouble(69, funcionario.getValeAlimentacao());
                stm.setDouble(70, funcionario.getValeTransporte());
                stm.setDouble(71, funcionario.getPlanoSaude());
                stm.setDouble(72, funcionario.getSaldoFGTS());
                stm.setDouble(73, funcionario.getSeguroVida());
                if (funcionario.isAtivo()) {
                    stm.setString(74, "T");
                } else {
                    stm.setString(74, "F");
                }
                stm.execute();
                return true;
            }
        } catch (Exception ex) {
            throw new Exception("Erro na gravação de registro de funcionário no banco de dados");
        }
    }
}
