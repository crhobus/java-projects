package CidadeDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Cidade;

public class CidadeBinario extends LeituraBytes implements CidadeDAO {

    @Override
    public int getProxCodCid() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cidade.jsy"), "rw");
                String cidadeLida;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 94);
                    cidadeLida = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if ("".equals(cidadeLida)) {
                        arq.close();
                        return i;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de cidade");
            }
        }
        return 1;
    }

    @Override
    public boolean removeCid(String cid) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cidade.jsy"), "rw");
                String cidadeLida;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 94);
                    cidadeLida = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if (cid.equalsIgnoreCase(cidadeLida)) {
                        arq.seek(i * 94);
                        arq.write(Arrays.copyOf("".getBytes(), 94));
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover uma cidade do arquivo");
            }
        }
        return false;
    }

    @Override
    public boolean setCidade(Cidade cidade) throws Exception {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cidade.jsy"), "rw");
                arq.seek(cidade.getCodigo() * 94);
                arq.write(Arrays.copyOf(cidade.getCidade().getBytes(), 45));
                arq.writeInt(cidade.getCodigo());
                arq.write(Arrays.copyOf(cidade.getEstado().getBytes(), 45));
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de cidade");
            }
        }
        return false;
    }

    @Override
    public Cidade getCidade(String cid) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cidade.jsy"), "rw");
                String cidadeLida;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 94);
                    cidadeLida = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if (cidadeLida.equalsIgnoreCase(cid)) {
                        Cidade cidade = new Cidade();
                        cidade.setCidade(cidadeLida);
                        cidade.setCodigo(arq.readInt());
                        cidade.setEstado(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
                        arq.close();
                        return cidade;
                    }
                }
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de cidade");
            }
        }
        return null;
    }

    @Override
    public List<Cidade> listCidade() throws Exception {
        List<Cidade> lista = new ArrayList<Cidade>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cidade.jsy"), "rw");
                String cidadeLida;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 94);
                    cidadeLida = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if (!"".equals(cidadeLida)) {
                        Cidade cidade = new Cidade();
                        cidade.setCidade(cidadeLida);
                        cidade.setCodigo(arq.readInt());
                        cidade.setEstado(new String(Arrays.copyOf(recuperaTam(arq, 45), getTam())));
                        lista.add(cidade);
                    }
                }
                arq.close();
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de cidade");
            }
        }
        return lista;
    }

    @Override
    public boolean arqCidVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Cidade.jsy"), "rw");
                String cidadeLida;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 94);
                    cidadeLida = new String(Arrays.copyOf(recuperaTam(arq, 45), getTam()));
                    if (!"".equals(cidadeLida)) {
                        arq.close();
                        return false;
                    }
                }
                arq.close();
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de cidade");
            }
        }
        return true;
    }
}
