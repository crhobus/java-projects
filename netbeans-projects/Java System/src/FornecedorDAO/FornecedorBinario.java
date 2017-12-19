package FornecedorDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Fornecedor;

public class FornecedorBinario extends LeituraBytes implements FornecedorDAO {

    @Override
    public boolean arqFornVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FornecedorNome.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 45);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if (!"".equals(nomeLido)) {
                        arq.close();
                        return false;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de fornecedor");
            }
        }
        return true;
    }

    @Override
    public Fornecedor getFornecedor(String nome) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            int posicao = getFornecedorNome(nome, diretorio);
            if (posicao != 0) {
                try {
                    RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Fornecedor.jsy"), "rw");
                    Fornecedor fornecedor = getForn(arq, posicao, nome);
                    arq.close();
                    return fornecedor;
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de fornecedor");
                }
            }
        }
        return null;
    }

    @Override
    public int getProxCodForn() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FornecedorNome.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 45);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if ("".equals(nomeLido)) {
                        arq.close();
                        return i;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de fornecedor");
            }
        }
        return 1;
    }

    @Override
    public List<Fornecedor> listFornecedor() throws Exception {
        List<Fornecedor> lista = new ArrayList<Fornecedor>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arqNome = new RandomAccessFile(new File(diretorio, "FornecedorNome.jsy"), "rw");
                RandomAccessFile arqForn = new RandomAccessFile(new File(diretorio, "Fornecedor.jsy"), "rw");
                String nomeLido;
                Fornecedor fornecedor;
                for (int i = 1; (i * 45) < arqNome.length(); i++) {
                    arqNome.seek(i * 45);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arqNome, 45), getTam()));
                    if (!"".equals(nomeLido)) {
                        fornecedor = getForn(arqForn, i, nomeLido);
                        lista.add(fornecedor);
                    }
                }
                arqNome.close();
                arqForn.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de fornecedor");
            }
        }
        return lista;
    }

    private int getFornecedorNome(String nome, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FornecedorNome.jsy"), "rw");
            String nomeLido;
            for (int i = 1; i < arq.length(); i++) {
                arq.seek(i * 45);
                nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                if (nome.equalsIgnoreCase(nomeLido)) {
                    arq.close();
                    return i;
                }
            }
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do nome: " + nome + " no arquivo de fornecedor");
        }
        return 0;
    }

    private Fornecedor getForn(RandomAccessFile arq, int posicao, String nome) throws Exception {
        try {
            arq.seek(posicao * 1897);
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(nome);
            fornecedor.setCodigo(arq.readInt());
            fornecedor.setDigitoConta(arq.readInt());
            fornecedor.setNumero(arq.readInt());
            fornecedor.setSigla(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            fornecedor.setNomeFantasia(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
            fornecedor.setInscricaoEstadual(new String(Arrays.copyOf(recuperaTam(arq, 11), getTam())));
            fornecedor.setCnpj(new String(Arrays.copyOf(recuperaTam(arq, 19), getTam())));
            fornecedor.setInscricaoMunicipal(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
            fornecedor.setTipoPessoa(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            fornecedor.setContribuinte(new String(Arrays.copyOf(recuperaTam(arq, 4), getTam())));
            fornecedor.setExportador(new String(Arrays.copyOf(recuperaTam(arq, 4), getTam())));
            fornecedor.setTipoFornecedor(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            fornecedor.setEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
            fornecedor.setFoneEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            fornecedor.setBanco(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
            fornecedor.setAgencia(new String(Arrays.copyOf(recuperaTam(arq, 6), getTam())));
            fornecedor.setConta(new String(Arrays.copyOf(recuperaTam(arq, 7), getTam())));
            fornecedor.setCaixaPostal(new String(Arrays.copyOf(recuperaTam(arq, 6), getTam())));
            fornecedor.setEndereco(new String(Arrays.copyOf(recuperaTam(arq, 70), getTam())));
            fornecedor.setBairro(new String(Arrays.copyOf(recuperaTam(arq, 60), getTam())));
            fornecedor.setCidade(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            fornecedor.setEstado(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            fornecedor.setRegiao(new String(Arrays.copyOf(recuperaTam(arq, 30), getTam())));
            fornecedor.setPais(new String(Arrays.copyOf(recuperaTam(arq, 40), getTam())));
            fornecedor.setCep(new String(Arrays.copyOf(recuperaTam(arq, 9), getTam())));
            fornecedor.setReferencia(new String(Arrays.copyOf(recuperaTam(arq, 70), getTam())));
            fornecedor.setFone(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            fornecedor.setCelular1(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            fornecedor.setCelular2(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            fornecedor.setFax(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
            fornecedor.setEmail(new String(Arrays.copyOf(recuperaTam(arq, 80), getTam())));
            fornecedor.setMsn(new String(Arrays.copyOf(recuperaTam(arq, 80), getTam())));
            fornecedor.setSkype(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
            fornecedor.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 160), getTam())));
            fornecedor.setHomePage(new String(Arrays.copyOf(recuperaTam(arq, 80), getTam())));
            fornecedor.setObservacoes(new String(Arrays.copyOf(recuperaTam(arq, 600), getTam())));
            fornecedor.setDataCadas(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            fornecedor.setUltAlteracao(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
            fornecedor.setComissao(arq.readDouble());
            fornecedor.setCompraMinima(arq.readDouble());
            fornecedor.setCompraMaxima(arq.readDouble());
            fornecedor.setValorFrete(arq.readDouble());
            fornecedor.setIcms(arq.readDouble());
            fornecedor.setCofins(arq.readDouble());
            fornecedor.setIpi(arq.readDouble());
            fornecedor.setJuros(arq.readDouble());
            fornecedor.setDescontos(arq.readDouble());
            return fornecedor;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do arquivo de fornecedor");
        }
    }

    @Override
    public boolean removeFornecedor(String nome) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FornecedorNome.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 45);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if (nome.equalsIgnoreCase(nomeLido)) {
                        arq.seek(i * 45);
                        arq.write(Arrays.copyOf("".getBytes(), 45));
                        excluirFornecedor(i, diretorio);
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover um fornecedor do arquivo");
            }
        }
        return false;
    }

    private void excluirFornecedor(int posicao, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Fornecedor.jsy"), "rw");
            arq.seek(posicao * 1897);
            arq.write(Arrays.copyOf("".getBytes(), 1897));
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro ao remover um fornecedor do arquivo");
        }
    }

    @Override
    public boolean setFornecedor(Fornecedor fornecedor) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.mkdir() || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Fornecedor.jsy"), "rw");
                gravarFornNome(fornecedor.getCodigo(), fornecedor.getNome(), diretorio);
                arq.seek(fornecedor.getCodigo() * 1897);
                arq.writeInt(fornecedor.getCodigo());
                arq.writeInt(fornecedor.getDigitoConta());
                arq.writeInt(fornecedor.getNumero());
                arq.write(Arrays.copyOf(fornecedor.getSigla().getBytes(), 30));
                arq.write(Arrays.copyOf(fornecedor.getNomeFantasia().getBytes(), 45));
                arq.write(Arrays.copyOf(fornecedor.getInscricaoEstadual().getBytes(), 11));
                arq.write(Arrays.copyOf(fornecedor.getCnpj().getBytes(), 19));
                arq.write(Arrays.copyOf(fornecedor.getInscricaoMunicipal().getBytes(), 8));
                arq.write(Arrays.copyOf(fornecedor.getTipoPessoa().getBytes(), 9));
                arq.write(Arrays.copyOf(fornecedor.getContribuinte().getBytes(), 4));
                arq.write(Arrays.copyOf(fornecedor.getExportador().getBytes(), 4));
                arq.write(Arrays.copyOf(fornecedor.getTipoFornecedor().getBytes(), 50));
                arq.write(Arrays.copyOf(fornecedor.getEmpresa().getBytes(), 45));
                arq.write(Arrays.copyOf(fornecedor.getFoneEmpresa().getBytes(), 13));
                arq.write(Arrays.copyOf(fornecedor.getBanco().getBytes(), 45));
                arq.write(Arrays.copyOf(fornecedor.getAgencia().getBytes(), 6));
                arq.write(Arrays.copyOf(fornecedor.getConta().getBytes(), 7));
                arq.write(Arrays.copyOf(fornecedor.getCaixaPostal().getBytes(), 6));
                arq.write(Arrays.copyOf(fornecedor.getEndereco().getBytes(), 70));
                arq.write(Arrays.copyOf(fornecedor.getBairro().getBytes(), 60));
                arq.write(Arrays.copyOf(fornecedor.getCidade().getBytes(), 50));
                arq.write(Arrays.copyOf(fornecedor.getEstado().getBytes(), 50));
                arq.write(Arrays.copyOf(fornecedor.getRegiao().getBytes(), 30));
                arq.write(Arrays.copyOf(fornecedor.getPais().getBytes(), 40));
                arq.write(Arrays.copyOf(fornecedor.getCep().getBytes(), 9));
                arq.write(Arrays.copyOf(fornecedor.getReferencia().getBytes(), 70));
                arq.write(Arrays.copyOf(fornecedor.getFone().getBytes(), 13));
                arq.write(Arrays.copyOf(fornecedor.getCelular1().getBytes(), 13));
                arq.write(Arrays.copyOf(fornecedor.getCelular2().getBytes(), 13));
                arq.write(Arrays.copyOf(fornecedor.getFax().getBytes(), 13));
                arq.write(Arrays.copyOf(fornecedor.getEmail().getBytes(), 80));
                arq.write(Arrays.copyOf(fornecedor.getMsn().getBytes(), 80));
                arq.write(Arrays.copyOf(fornecedor.getSkype().getBytes(), 50));
                arq.write(Arrays.copyOf(fornecedor.getDescricao().getBytes(), 160));
                arq.write(Arrays.copyOf(fornecedor.getHomePage().getBytes(), 80));
                arq.write(Arrays.copyOf(fornecedor.getObservacoes().getBytes(), 600));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(fornecedor.getDataCadas()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(fornecedor.getUltAlteracao()).getBytes(), 15));
                arq.writeDouble(fornecedor.getComissao());
                arq.writeDouble(fornecedor.getCompraMinima());
                arq.writeDouble(fornecedor.getCompraMaxima());
                arq.writeDouble(fornecedor.getValorFrete());
                arq.writeDouble(fornecedor.getIcms());
                arq.writeDouble(fornecedor.getCofins());
                arq.writeDouble(fornecedor.getIpi());
                arq.writeDouble(fornecedor.getJuros());
                arq.writeDouble(fornecedor.getDescontos());
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de fornecedor");
            }
        }
        return false;
    }

    private void gravarFornNome(int cod, String nome, File diretorio) throws Exception {
        try {
            RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "FornecedorNome.jsy"), "rw");
            arq.seek(cod * 45);
            arq.write(Arrays.copyOf(nome.getBytes(), 45));
            arq.close();
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do nome: " + nome + " no arquivo de fornecedor");
        }
    }
}
