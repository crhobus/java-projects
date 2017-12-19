package ClienteDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Cliente;

public class ClienteBinario extends LeituraBytes implements ClienteDAO {

    @Override
    public boolean removeCliente(String cpf) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "ClienteCPF.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 14);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if (cpf.equals(cpfLido)) {
                        arq.seek(i * 14);
                        arq.write(Arrays.copyOf("".getBytes(), 14));
                        excluirCliente(i, diretorio);
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover um cliente do arquivo");
            }
        }
        return false;
    }

    private void excluirCliente(int posicao, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cliente.jsy"), "rw");
            arq.seek(posicao * 1414);
            arq.write(Arrays.copyOf("".getBytes(), 1414));
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro ao remover um cliente do arquivo");
        }
    }

    @Override
    public boolean setCliente(Cliente cliente) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.mkdir() || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cliente.jsy"), "rw");
                gravarClieCPF(cliente.getCodigo(), cliente.getCpf(), diretorio);
                arq.seek(cliente.getCodigo() * 1414);
                arq.write(Arrays.copyOf(cliente.getNome().getBytes(), 40));
                arq.write(Arrays.copyOf(cliente.getRg().getBytes(), 11));
                arq.write(Arrays.copyOf(cliente.getSexo().getBytes(), 9));
                arq.write(Arrays.copyOf(cliente.getEstadoCivil().getBytes(), 8));
                arq.write(Arrays.copyOf(cliente.getTipoPessoa().getBytes(), 9));
                arq.write(Arrays.copyOf(cliente.getProfissao().getBytes(), 40));
                arq.write(Arrays.copyOf(cliente.getCep().getBytes(), 9));
                arq.write(Arrays.copyOf(cliente.getEndereco().getBytes(), 60));
                arq.write(Arrays.copyOf(cliente.getBairro().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getComplemento().getBytes(), 20));
                arq.write(Arrays.copyOf(cliente.getReferencia().getBytes(), 60));
                arq.write(Arrays.copyOf(cliente.getCidade().getBytes(), 30));
                arq.write(Arrays.copyOf(cliente.getEstado().getBytes(), 30));
                arq.write(Arrays.copyOf(cliente.getEmail().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getFone().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getCelular1().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getCelular2().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getDescricao().getBytes(), 160));
                arq.write(Arrays.copyOf(cliente.getEmpresa().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getFoneEmpresa().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getFax().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getMsn().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getSkype().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getNaturalidade().getBytes(), 30));
                arq.write(Arrays.copyOf(cliente.getNomePai().getBytes(), 40));
                arq.write(Arrays.copyOf(cliente.getRgPai().getBytes(), 11));
                arq.write(Arrays.copyOf(cliente.getCpfPai().getBytes(), 14));
                arq.write(Arrays.copyOf(cliente.getEstadoCivilPai().getBytes(), 8));
                arq.write(Arrays.copyOf(cliente.getTipoPessoaPai().getBytes(), 9));
                arq.write(Arrays.copyOf(cliente.getProfissaoPai().getBytes(), 40));
                arq.write(Arrays.copyOf(cliente.getContato1Pai().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getContato2Pai().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getEmpresaPai().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getFoneEmpresaPai().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getNomeMae().getBytes(), 40));
                arq.write(Arrays.copyOf(cliente.getRgMae().getBytes(), 11));
                arq.write(Arrays.copyOf(cliente.getCpfMae().getBytes(), 14));
                arq.write(Arrays.copyOf(cliente.getEstadoCivilMae().getBytes(), 8));
                arq.write(Arrays.copyOf(cliente.getTipoPessoaMae().getBytes(), 9));
                arq.write(Arrays.copyOf(cliente.getProfissaoMae().getBytes(), 40));
                arq.write(Arrays.copyOf(cliente.getContato1Mae().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getContato2Mae().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getEmpresaMae().getBytes(), 50));
                arq.write(Arrays.copyOf(cliente.getFoneEmpresaMae().getBytes(), 13));
                arq.write(Arrays.copyOf(cliente.getOpRealizada().getBytes(), 40));
                arq.writeInt(cliente.getCodigo());
                arq.writeInt(cliente.getNumero());
                arq.writeInt(cliente.getIdade());
                arq.writeInt(cliente.getAnoTempoTrab());
                arq.writeInt(cliente.getMesTempoTrab());
                arq.writeInt(cliente.getAnoTempoTrabPai());
                arq.writeInt(cliente.getMesTempoTrabPai());
                arq.writeInt(cliente.getAnoTempoTrabMae());
                arq.writeInt(cliente.getMesTempoTrabMae());
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(cliente.getDataNasc()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(cliente.getDataCadastro()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(cliente.getUltAlteracao()).getBytes(), 15));
                arq.writeDouble(cliente.getRenda());
                arq.writeDouble(cliente.getRendaPai());
                arq.writeDouble(cliente.getRendaMae());
                arq.writeDouble(cliente.getCredito());
                arq.writeDouble(cliente.getDebito());
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de cliente");
            }
        }
        return false;
    }

    private void gravarClieCPF(int cod, String cpf, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "ClienteCPF.jsy"), "rw");
            arq.seek(cod * 14);
            arq.write(Arrays.copyOf(cpf.getBytes(), 14));
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do CPF: " + cpf + " no arquivo de cliente");
        }
    }

    @Override
    public Cliente getCliente(String cpf) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            int posicao = getClienteCPF(cpf, diretorio);
            if (posicao != 0) {
                try {
                    RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cliente.jsy"), "rw");
                    Cliente cliente = getClie(arq, posicao, cpf);
                    arq.close();
                    return cliente;
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de cliente");
                }
            }
        }
        return null;
    }

    private int getClienteCPF(String cpf, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "ClienteCPF.jsy"), "rw");
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
            throw new Exception("Erro na leitura do CPF: " + cpf + " no arquivo de cliente");
        }
        return 0;
    }

    private Cliente getClie(RandomAccessFile arq, int posicao, String cpf) throws Exception {
        try {
            arq.seek(posicao * 1414);
            Cliente cliente = new Cliente();
            cliente.setCpf(cpf);
            cliente.setNome(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setRg(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            cliente.setSexo(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            cliente.setEstadoCivil(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
            cliente.setTipoPessoa(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            cliente.setProfissao(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setCep(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            cliente.setEndereco(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
            cliente.setBairro(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setComplemento(new String(Arrays.copyOf(recuperaTam(arq, 20), getTam())));
            cliente.setReferencia(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
            cliente.setCidade(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            cliente.setEstado(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            cliente.setEmail(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setFone(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setCelular1(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setCelular2(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 160), getTam())));
            cliente.setEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setFoneEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setFax(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setMsn(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setSkype(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setNaturalidade(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            cliente.setNomePai(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setRgPai(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            cliente.setCpfPai(new String(Arrays.copyOf(recuperaTam(arq, 14), getTam())));
            cliente.setEstadoCivilPai(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
            cliente.setTipoPessoaPai(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            cliente.setProfissaoPai(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setContato1Pai(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setContato2Pai(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setEmpresaPai(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setFoneEmpresaPai(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setNomeMae(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setRgMae(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            cliente.setCpfMae(new String(Arrays.copyOf(recuperaTam(arq, 14), getTam())));
            cliente.setEstadoCivilMae(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
            cliente.setTipoPessoaMae(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            cliente.setProfissaoMae(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setContato1Mae(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setContato2Mae(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setEmpresaMae(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            cliente.setFoneEmpresaMae(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            cliente.setOpRealizada(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            cliente.setCodigo(arq.readInt());
            cliente.setNumero(arq.readInt());
            cliente.setIdade(arq.readInt());
            cliente.setAnoTempoTrab(arq.readInt());
            cliente.setMesTempoTrab(arq.readInt());
            cliente.setAnoTempoTrabPai(arq.readInt());
            cliente.setMesTempoTrabPai(arq.readInt());
            cliente.setAnoTempoTrabMae(arq.readInt());
            cliente.setMesTempoTrabMae(arq.readInt());
            cliente.setDataNasc(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            cliente.setDataCadastro(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            cliente.setUltAlteracao(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            cliente.setRenda(arq.readDouble());
            cliente.setRendaPai(arq.readDouble());
            cliente.setRendaMae(arq.readDouble());
            cliente.setCredito(arq.readDouble());
            cliente.setDebito(arq.readDouble());
            return cliente;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do arquivo de cliente");
        }
    }

    @Override
    public List<Cliente> listCliente() throws Exception {
        List<Cliente> lista = new ArrayList<Cliente>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arqCPF = new RandomAccessFile(new File(diretorio, "ClienteCPF.jsy"), "rw");
                RandomAccessFile arqClie = new RandomAccessFile(new File(diretorio, "Cliente.jsy"), "rw");
                String cpfLido;
                Cliente cliente;
                for (int i = 1; (i * 14) < arqCPF.length(); i++) {
                    arqCPF.seek(i * 14);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arqCPF, 14), getTam()));
                    if (!"".equals(cpfLido)) {
                        cliente = getClie(arqClie, i, cpfLido);
                        lista.add(cliente);
                    }
                }
                arqCPF.close();
                arqClie.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de cliente");
            }
        }
        return lista;
    }

    @Override
    public boolean arqClieVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "ClienteCPF.jsy"), "rw");
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
                throw new Exception("Erro na leitura do arquivo de cliente");
            }
        }
        return true;
    }

    @Override
    public int getProxCodClie() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "ClienteCPF.jsy"), "rw");
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
                throw new Exception("Erro na leitura do arquivo de cliente");
            }
        }
        return 1;
    }
}
