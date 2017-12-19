package FuncionarioDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.ArqTextoGenerico;
import Modelo.Funcionario;

public class FuncionarioTexto extends ArqTextoGenerico implements FuncionarioDAO {

    @Override
    public boolean arqFuncVazio() throws Exception {
        return arqVazio("Texto/Funcionario");
    }

    @Override
    public Funcionario getFuncionario(String cpf) throws Exception {
        File diretorio = new File("Texto/Funcionario");
        if (diretorio.exists()) {
            File arq = new File(diretorio, cpf + ".txt");
            if (arq.exists()) {
                try {
                    return leituraFuncionario(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de funcionário\no arquivo " + cpf + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    private Funcionario leituraFuncionario(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo(Integer.parseInt(in.readLine()));
        funcionario.setDigitoConta(Integer.parseInt(in.readLine()));
        funcionario.setCentroCusto(Integer.parseInt(in.readLine()));
        funcionario.setCracha(Integer.parseInt(in.readLine()));
        funcionario.setEstabilidade(Integer.parseInt(in.readLine()));
        funcionario.setDescontoINSS(Integer.parseInt(in.readLine()));
        funcionario.setDependentesIR(Integer.parseInt(in.readLine()));
        funcionario.setClasseContribuicaoIR(Integer.parseInt(in.readLine()));
        funcionario.setNumero(Integer.parseInt(in.readLine()));
        funcionario.setIdade(Integer.parseInt(in.readLine()));
        funcionario.setConta(Integer.parseInt(in.readLine()));
        funcionario.setContaFGTS(Integer.parseInt(in.readLine()));
        funcionario.setZona(Integer.parseInt(in.readLine()));
        funcionario.setSecao(Integer.parseInt(in.readLine()));
        funcionario.setNome(in.readLine());
        funcionario.setRg(in.readLine());
        funcionario.setCpf(in.readLine());
        funcionario.setSexo(in.readLine());
        funcionario.setEstadoCivil(in.readLine());
        funcionario.setTipoPessoa(in.readLine());
        funcionario.setEndereco(in.readLine());
        funcionario.setBairro(in.readLine());
        funcionario.setComplemento(in.readLine());
        funcionario.setReferencia(in.readLine());
        funcionario.setCidade(in.readLine());
        funcionario.setEstado(in.readLine());
        funcionario.setEmail(in.readLine());
        funcionario.setFone(in.readLine());
        funcionario.setCelular1(in.readLine());
        funcionario.setCelular2(in.readLine());
        funcionario.setDescricao(in.readLine());
        funcionario.setEmpresa(in.readLine());
        funcionario.setFoneEmpresa(in.readLine());
        funcionario.setFax(in.readLine());
        funcionario.setMsn(in.readLine());
        funcionario.setSkype(in.readLine());
        funcionario.setNaturalidade(in.readLine());
        funcionario.setBanco(in.readLine());
        funcionario.setAgencia(in.readLine());
        funcionario.setCarteiraHabilitacao(in.readLine());
        funcionario.setCarteiraTrabalho(in.readLine());
        funcionario.setCertificadoReservista(in.readLine());
        funcionario.setTituloEleitor(in.readLine());
        funcionario.setNomePai(in.readLine());
        funcionario.setNomeMae(in.readLine());
        funcionario.setPais(in.readLine());
        funcionario.setChefe(in.readLine());
        funcionario.setCargo(in.readLine());
        funcionario.setSetor(in.readLine());
        funcionario.setCep(in.readLine());
        funcionario.setRacaCor(in.readLine());
        funcionario.setHorasSemanais(in.readLine());
        funcionario.setHorasMesais(in.readLine());
        funcionario.setHoraInicial(in.readLine());
        funcionario.setHoraFinal(in.readLine());
        funcionario.setInscricaoEstadual(in.readLine());
        funcionario.setCnpj(in.readLine());
        funcionario.setTipoContrato(in.readLine());
        funcionario.setNumeroContrato(in.readLine());
        funcionario.setDocumentoEstrangeiro(in.readLine());
        funcionario.setInscricaoINSS(in.readLine());
        funcionario.setPis(in.readLine());
        funcionario.setInsentoIR(in.readLine());
        funcionario.setContribuicaoSindical(in.readLine());
        funcionario.setDataNasc(SimpleDateFormat.getInstance().parse(in.readLine()));
        funcionario.setDataCadastro(SimpleDateFormat.getInstance().parse(in.readLine()));
        funcionario.setUltAlteracao(SimpleDateFormat.getInstance().parse(in.readLine()));
        funcionario.setDataContratacaoDemissao(SimpleDateFormat.getInstance().parse(in.readLine()));
        funcionario.setDataPagamento(SimpleDateFormat.getInstance().parse(in.readLine()));
        funcionario.setSalarioInicial(Double.parseDouble(in.readLine()));
        funcionario.setAumento(Double.parseDouble(in.readLine()));
        funcionario.setSalarioLiquido(Double.parseDouble(in.readLine()));
        funcionario.setValeAlimentacao(Double.parseDouble(in.readLine()));
        funcionario.setValeTransporte(Double.parseDouble(in.readLine()));
        funcionario.setPlanoSaude(Double.parseDouble(in.readLine()));
        funcionario.setSaldoFGTS(Double.parseDouble(in.readLine()));
        funcionario.setSeguroVida(Double.parseDouble(in.readLine()));
        funcionario.setAtivo(Boolean.valueOf(in.readLine()));
        String outConsideracoes = "", linha = "";
        while (linha != null) {
            linha = in.readLine();
            if (linha != null) {
                outConsideracoes += linha + "\n";
            }
        }
        funcionario.setOutrasConsideracoes(outConsideracoes);
        in.close();
        return funcionario;
    }

    @Override
    public int getProxCodFunc() throws Exception {
        return getProxCod("Texto/Funcionario", "funcionário");
    }

    @Override
    public List<Funcionario> listFuncionario() throws Exception {
        List<Funcionario> lista = new ArrayList<Funcionario>();
        File diretorio = new File("Texto/Funcionario");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraFuncionario(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de funcionário\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    @Override
    public boolean removeFuncionario(String cpf) throws Exception {
        return remove("Texto/Funcionario", cpf);
    }

    @Override
    public boolean setFuncionario(Funcionario funcionario) throws Exception {
        List<Funcionario> lista = listFuncionario();
        for (Funcionario func : lista) {
            if (func.getCodigo() == funcionario.getCodigo()) {
                removeFuncionario(func.getCpf());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Funcionario");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, funcionario.getCpf() + ".txt"))));
                    out.println(funcionario.getCodigo());
                    out.println(funcionario.getDigitoConta());
                    out.println(funcionario.getCentroCusto());
                    out.println(funcionario.getCracha());
                    out.println(funcionario.getEstabilidade());
                    out.println(funcionario.getDescontoINSS());
                    out.println(funcionario.getDependentesIR());
                    out.println(funcionario.getClasseContribuicaoIR());
                    out.println(funcionario.getNumero());
                    out.println(funcionario.getIdade());
                    out.println(funcionario.getConta());
                    out.println(funcionario.getContaFGTS());
                    out.println(funcionario.getZona());
                    out.println(funcionario.getSecao());
                    out.println(funcionario.getNome());
                    out.println(funcionario.getRg());
                    out.println(funcionario.getCpf());
                    out.println(funcionario.getSexo());
                    out.println(funcionario.getEstadoCivil());
                    out.println(funcionario.getTipoPessoa());
                    out.println(funcionario.getEndereco());
                    out.println(funcionario.getBairro());
                    out.println(funcionario.getComplemento());
                    out.println(funcionario.getReferencia());
                    out.println(funcionario.getCidade());
                    out.println(funcionario.getEstado());
                    out.println(funcionario.getEmail());
                    out.println(funcionario.getFone());
                    out.println(funcionario.getCelular1());
                    out.println(funcionario.getCelular2());
                    out.println(funcionario.getDescricao());
                    out.println(funcionario.getEmpresa());
                    out.println(funcionario.getFoneEmpresa());
                    out.println(funcionario.getFax());
                    out.println(funcionario.getMsn());
                    out.println(funcionario.getSkype());
                    out.println(funcionario.getNaturalidade());
                    out.println(funcionario.getBanco());
                    out.println(funcionario.getAgencia());
                    out.println(funcionario.getCarteiraHabilitacao());
                    out.println(funcionario.getCarteiraTrabalho());
                    out.println(funcionario.getCertificadoReservista());
                    out.println(funcionario.getTituloEleitor());
                    out.println(funcionario.getNomePai());
                    out.println(funcionario.getNomeMae());
                    out.println(funcionario.getPais());
                    out.println(funcionario.getChefe());
                    out.println(funcionario.getCargo());
                    out.println(funcionario.getSetor());
                    out.println(funcionario.getCep());
                    out.println(funcionario.getRacaCor());
                    out.println(funcionario.getHorasSemanais());
                    out.println(funcionario.getHorasMesais());
                    out.println(funcionario.getHoraInicial());
                    out.println(funcionario.getHoraFinal());
                    out.println(funcionario.getInscricaoEstadual());
                    out.println(funcionario.getCnpj());
                    out.println(funcionario.getTipoContrato());
                    out.println(funcionario.getNumeroContrato());
                    out.println(funcionario.getDocumentoEstrangeiro());
                    out.println(funcionario.getInscricaoINSS());
                    out.println(funcionario.getPis());
                    out.println(funcionario.getInsentoIR());
                    out.println(funcionario.getContribuicaoSindical());
                    out.println(SimpleDateFormat.getInstance().format(funcionario.getDataNasc()));
                    out.println(SimpleDateFormat.getInstance().format(funcionario.getDataCadastro()));
                    out.println(SimpleDateFormat.getInstance().format(funcionario.getUltAlteracao()));
                    out.println(SimpleDateFormat.getInstance().format(funcionario.getDataContratacaoDemissao()));
                    out.println(SimpleDateFormat.getInstance().format(funcionario.getDataPagamento()));
                    out.println(funcionario.getSalarioInicial());
                    out.println(funcionario.getAumento());
                    out.println(funcionario.getSalarioLiquido());
                    out.println(funcionario.getValeAlimentacao());
                    out.println(funcionario.getValeTransporte());
                    out.println(funcionario.getPlanoSaude());
                    out.println(funcionario.getSaldoFGTS());
                    out.println(funcionario.getSeguroVida());
                    out.println(funcionario.isAtivo());
                    String[] outConsideracoes = funcionario.getOutrasConsideracoes().split("\n");
                    for (int i = 0; i < outConsideracoes.length; i++) {
                        out.println(outConsideracoes[i]);
                    }
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de funcionario");
                }
            }
        }
        return false;
    }
}
