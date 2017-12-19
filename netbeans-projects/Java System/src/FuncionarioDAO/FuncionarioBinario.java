package FuncionarioDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Funcionario;

public class FuncionarioBinario extends LeituraBytes implements FuncionarioDAO {

    @Override
    public boolean arqFuncVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FuncionarioCPF.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 14);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if (!"".equals(cpfLido)) {
                        arq.close();
                        return false;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de funcionario");
            }
        }
        return true;
    }

    @Override
    public Funcionario getFuncionario(String cpf) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            int posicao = getFuncionarioCPF(cpf, diretorio);
            if (posicao != 0) {
                try {
                    RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Funcionario.jsy"), "rw");
                    Funcionario funcionario = getFunc(arq, posicao, cpf);
                    arq.close();
                    return funcionario;
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de funcionario");
                }
            }
        }
        return null;
    }

    @Override
    public int getProxCodFunc() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FuncionarioCPF.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 14);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if ("".equals(cpfLido)) {
                        arq.close();
                        return i;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de funcionario");
            }
        }
        return 1;
    }

    @Override
    public List<Funcionario> listFuncionario() throws Exception {
        List<Funcionario> lista = new ArrayList<Funcionario>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arqCPF = new RandomAccessFile(new File(diretorio, "FuncionarioCPF.jsy"), "rw");
                RandomAccessFile arqFunc = new RandomAccessFile(new File(diretorio, "Funcionario.jsy"), "rw");
                String cpfLido;
                Funcionario funcionario;
                for (int i = 1; (i * 14) < arqCPF.length(); i++) {
                    arqCPF.seek(i * 14);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arqCPF, 14), getTam()));
                    if (!"".equals(cpfLido)) {
                        funcionario = getFunc(arqFunc, i, cpfLido);
                        lista.add(funcionario);
                    }
                }
                arqCPF.close();
                arqFunc.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de funcionario");
            }
        }
        return lista;
    }

    private int getFuncionarioCPF(String cpf, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FuncionarioCPF.jsy"), "rw");
            String cpfLido;
            for (int i = 1; i < arq.length(); i++) {
                arq.seek(i * 14);
                cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                if (cpf.equals(cpfLido)) {
                    arq.close();
                    return i;
                }
            }
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do CPF: " + cpf + " no arquivo de funcionario");
        }
        return 0;
    }

    private Funcionario getFunc(RandomAccessFile arq, int posicao, String cpf) throws Exception {
        try {
            arq.seek(posicao * 2153);
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(cpf);
            funcionario.setCodigo(arq.readInt());
            funcionario.setDigitoConta(arq.readInt());
            funcionario.setCentroCusto(arq.readInt());
            funcionario.setCracha(arq.readInt());
            funcionario.setEstabilidade(arq.readInt());
            funcionario.setDescontoINSS(arq.readInt());
            funcionario.setDependentesIR(arq.readInt());
            funcionario.setClasseContribuicaoIR(arq.readInt());
            funcionario.setNumero(arq.readInt());
            funcionario.setIdade(arq.readInt());
            funcionario.setConta(arq.readInt());
            funcionario.setContaFGTS(arq.readInt());
            funcionario.setZona(arq.readInt());
            funcionario.setSecao(arq.readInt());
            funcionario.setNome(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            funcionario.setRg(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            funcionario.setSexo(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            funcionario.setEstadoCivil(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
            funcionario.setTipoPessoa(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            funcionario.setEndereco(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
            funcionario.setBairro(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setComplemento(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam())));
            funcionario.setReferencia(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
            funcionario.setCidade(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            funcionario.setEstado(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            funcionario.setEmail(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setFone(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            funcionario.setCelular1(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            funcionario.setCelular2(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            funcionario.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 160), getTam())));
            funcionario.setEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setFoneEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            funcionario.setFax(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            funcionario.setMsn(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setSkype(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setNaturalidade(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            funcionario.setBanco(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
            funcionario.setAgencia(new String(Arrays.copyOf(recuperaTam(arq, 6), getTam())));
            funcionario.setCarteiraHabilitacao(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            funcionario.setCarteiraTrabalho(new String(Arrays.copyOf(recuperaTam(arq, 17), getTam())));
            funcionario.setCertificadoReservista(new String(Arrays.copyOf(recuperaTam(arq, 6), getTam())));
            funcionario.setTituloEleitor(new String(Arrays.copyOf(recuperaTam(arq, 14), getTam())));
            funcionario.setNomePai(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            funcionario.setNomeMae(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            funcionario.setPais(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
            funcionario.setChefe(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            funcionario.setCargo(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setSetor(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            funcionario.setCep(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            funcionario.setRacaCor(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam())));
            funcionario.setHorasSemanais(new String(Arrays.copyOf(recuperaTam(arq, 5), getTam())));
            funcionario.setHorasMesais(new String(Arrays.copyOf(recuperaTam(arq, 5), getTam())));
            funcionario.setHoraInicial(new String(Arrays.copyOf(recuperaTam(arq, 5), getTam())));
            funcionario.setHoraFinal(new String(Arrays.copyOf(recuperaTam(arq, 5), getTam())));
            funcionario.setInscricaoEstadual(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            funcionario.setCnpj(new String(Arrays.copyOf(recuperaTam(arq, 19), getTam())));
            funcionario.setOutrasConsideracoes(new String(Arrays.copyOf(recuperaTam(arq, 600), getTam())));
            funcionario.setTipoContrato(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            funcionario.setNumeroContrato(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            funcionario.setDocumentoEstrangeiro(new String(Arrays.copyOf(recuperaTam(arq, 25), getTam())));
            funcionario.setInscricaoINSS(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam())));
            funcionario.setPis(new String(Arrays.copyOf(recuperaTam(arq, 14), getTam())));
            funcionario.setInsentoIR(new String(Arrays.copyOf(recuperaTam(arq, 4), getTam())));
            funcionario.setContribuicaoSindical(new String(Arrays.copyOf(recuperaTam(arq, 4), getTam())));
            funcionario.setDataNasc(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            funcionario.setDataCadastro(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            funcionario.setUltAlteracao(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            funcionario.setDataContratacaoDemissao(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            funcionario.setDataPagamento(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            funcionario.setSalarioInicial(arq.readDouble());
            funcionario.setAumento(arq.readDouble());
            funcionario.setSalarioLiquido(arq.readDouble());
            funcionario.setValeAlimentacao(arq.readDouble());
            funcionario.setValeTransporte(arq.readDouble());
            funcionario.setPlanoSaude(arq.readDouble());
            funcionario.setSaldoFGTS(arq.readDouble());
            funcionario.setSeguroVida(arq.readDouble());
            funcionario.setAtivo(arq.readBoolean());
            return funcionario;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do arquivo de funcionario");
        }
    }

    @Override
    public boolean removeFuncionario(String cpf) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FuncionarioCPF.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 14);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if (cpf.equals(cpfLido)) {
                        arq.seek(i * 14);
                        arq.write(Arrays.copyOf("".getBytes(), 14));
                        excluirFuncionario(i, diretorio);
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover um funcionario do arquivo");
            }
        }
        return false;
    }

    private void excluirFuncionario(int posicao, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Funcionario.jsy"), "rw");
            arq.seek(posicao * 2153);
            arq.write(Arrays.copyOf("".getBytes(), 2153));
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro ao remover um funcionario do arquivo");
        }
    }

    @Override
    public boolean setFuncionario(Funcionario funcionario) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.mkdir() || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Funcionario.jsy"), "rw");
                gravarFuncCPF(funcionario.getCodigo(), funcionario.getCpf(), diretorio);
                arq.seek(funcionario.getCodigo() * 2153);
                arq.writeInt(funcionario.getCodigo());
                arq.writeInt(funcionario.getDigitoConta());
                arq.writeInt(funcionario.getCentroCusto());
                arq.writeInt(funcionario.getCracha());
                arq.writeInt(funcionario.getEstabilidade());
                arq.writeInt(funcionario.getDescontoINSS());
                arq.writeInt(funcionario.getDependentesIR());
                arq.writeInt(funcionario.getClasseContribuicaoIR());
                arq.writeInt(funcionario.getNumero());
                arq.writeInt(funcionario.getIdade());
                arq.writeInt(funcionario.getConta());
                arq.writeInt(funcionario.getContaFGTS());
                arq.writeInt(funcionario.getZona());
                arq.writeInt(funcionario.getSecao());
                arq.write(Arrays.copyOf(funcionario.getNome().getBytes(), 40));
                arq.write(Arrays.copyOf(funcionario.getRg().getBytes(), 11));
                arq.write(Arrays.copyOf(funcionario.getSexo().getBytes(), 9));
                arq.write(Arrays.copyOf(funcionario.getEstadoCivil().getBytes(), 8));
                arq.write(Arrays.copyOf(funcionario.getTipoPessoa().getBytes(), 9));
                arq.write(Arrays.copyOf(funcionario.getEndereco().getBytes(), 60));
                arq.write(Arrays.copyOf(funcionario.getBairro().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getComplemento().getBytes(), 20));
                arq.write(Arrays.copyOf(funcionario.getReferencia().getBytes(), 60));
                arq.write(Arrays.copyOf(funcionario.getCidade().getBytes(), 30));
                arq.write(Arrays.copyOf(funcionario.getEstado().getBytes(), 30));
                arq.write(Arrays.copyOf(funcionario.getEmail().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getFone().getBytes(), 13));
                arq.write(Arrays.copyOf(funcionario.getCelular1().getBytes(), 13));
                arq.write(Arrays.copyOf(funcionario.getCelular2().getBytes(), 13));
                arq.write(Arrays.copyOf(funcionario.getDescricao().getBytes(), 160));
                arq.write(Arrays.copyOf(funcionario.getEmpresa().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getFoneEmpresa().getBytes(), 13));
                arq.write(Arrays.copyOf(funcionario.getFax().getBytes(), 13));
                arq.write(Arrays.copyOf(funcionario.getMsn().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getSkype().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getNaturalidade().getBytes(), 30));
                arq.write(Arrays.copyOf(funcionario.getBanco().getBytes(), 45));
                arq.write(Arrays.copyOf(funcionario.getAgencia().getBytes(), 6));
                arq.write(Arrays.copyOf(funcionario.getCarteiraHabilitacao().getBytes(), 11));
                arq.write(Arrays.copyOf(funcionario.getCarteiraTrabalho().getBytes(), 17));
                arq.write(Arrays.copyOf(funcionario.getCertificadoReservista().getBytes(), 6));
                arq.write(Arrays.copyOf(funcionario.getTituloEleitor().getBytes(), 14));
                arq.write(Arrays.copyOf(funcionario.getNomePai().getBytes(), 40));
                arq.write(Arrays.copyOf(funcionario.getNomeMae().getBytes(), 40));
                arq.write(Arrays.copyOf(funcionario.getPais().getBytes(), 45));
                arq.write(Arrays.copyOf(funcionario.getChefe().getBytes(), 40));
                arq.write(Arrays.copyOf(funcionario.getCargo().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getSetor().getBytes(), 50));
                arq.write(Arrays.copyOf(funcionario.getCep().getBytes(), 9));
                arq.write(Arrays.copyOf(funcionario.getRacaCor().getBytes(), 20));
                arq.write(Arrays.copyOf(funcionario.getHorasSemanais().getBytes(), 5));
                arq.write(Arrays.copyOf(funcionario.getHorasMesais().getBytes(), 5));
                arq.write(Arrays.copyOf(funcionario.getHoraInicial().getBytes(), 5));
                arq.write(Arrays.copyOf(funcionario.getHoraFinal().getBytes(), 5));
                arq.write(Arrays.copyOf(funcionario.getInscricaoEstadual().getBytes(), 11));
                arq.write(Arrays.copyOf(funcionario.getCnpj().getBytes(), 19));
                arq.write(Arrays.copyOf(funcionario.getOutrasConsideracoes().getBytes(), 600));
                arq.write(Arrays.copyOf(funcionario.getTipoContrato().getBytes(), 40));
                arq.write(Arrays.copyOf(funcionario.getNumeroContrato().getBytes(), 30));
                arq.write(Arrays.copyOf(funcionario.getDocumentoEstrangeiro().getBytes(), 25));
                arq.write(Arrays.copyOf(funcionario.getInscricaoINSS().getBytes(), 15));
                arq.write(Arrays.copyOf(funcionario.getPis().getBytes(), 14));
                arq.write(Arrays.copyOf(funcionario.getInsentoIR().getBytes(), 4));
                arq.write(Arrays.copyOf(funcionario.getContribuicaoSindical().getBytes(), 4));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(funcionario.getDataNasc()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(funcionario.getDataCadastro()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(funcionario.getUltAlteracao()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(funcionario.getDataContratacaoDemissao()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(funcionario.getDataPagamento()).getBytes(), 15));
                arq.writeDouble(funcionario.getSalarioInicial());
                arq.writeDouble(funcionario.getAumento());
                arq.writeDouble(funcionario.getSalarioLiquido());
                arq.writeDouble(funcionario.getValeAlimentacao());
                arq.writeDouble(funcionario.getValeTransporte());
                arq.writeDouble(funcionario.getPlanoSaude());
                arq.writeDouble(funcionario.getSaldoFGTS());
                arq.writeDouble(funcionario.getSeguroVida());
                arq.writeBoolean(funcionario.isAtivo());
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de funcionario");
            }
        }
        return false;
    }

    private void gravarFuncCPF(int cod, String cpf, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FuncionarioCPF.jsy"), "rw");
            arq.seek(cod * 14);
            arq.write(Arrays.copyOf(cpf.getBytes(), 14));
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do CPF: " + cpf + " no arquivo de funcionario");
        }
    }
}
