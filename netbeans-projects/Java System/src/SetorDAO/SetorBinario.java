package SetorDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Setor;

public class SetorBinario extends LeituraBytes implements SetorDAO {

    @Override
    public boolean arqSetorVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Setor.jsy"), "rw");
                String setorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 54);
                    setorLido = new String(Arrays.copyOf(recuperaTam(arq, 50), getTam()));
                    if (!"".equals(setorLido)) {
                        arq.close();
                        return false;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de setor");
            }
        }
        return true;
    }

    @Override
    public int getProxCodSetor() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Setor.jsy"), "rw");
                String setorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 54);
                    setorLido = new String(Arrays.copyOf(recuperaTam(arq, 50), getTam()));
                    if ("".equals(setorLido)) {
                        arq.close();
                        return i;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de setor");
            }
        }
        return 1;
    }

    @Override
    public Setor getSetor(String s) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Setor.jsy"), "rw");
                String setorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 54);
                    setorLido = new String(Arrays.copyOf(recuperaTam(arq, 50), getTam()));
                    if (setorLido.equalsIgnoreCase(s)) {
                        Setor setor = new Setor();
                        setor.setNome(setorLido);
                        setor.setCodigo(arq.readInt());
                        arq.close();
                        return setor;
                    }
                }
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de setor");
            }
        }
        return null;
    }

    @Override
    public List<Setor> listSetor() throws Exception {
        List<Setor> lista = new ArrayList<Setor>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Setor.jsy"), "rw");
                String setorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 54);
                    setorLido = new String(Arrays.copyOf(recuperaTam(arq, 50), getTam()));
                    if (!"".equals(setorLido)) {
                        Setor setor = new Setor();
                        setor.setNome(setorLido);
                        setor.setCodigo(arq.readInt());
                        lista.add(setor);
                    }
                }
                arq.close();
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de setor");
            }
        }
        return lista;
    }

    @Override
    public boolean removeSetor(String setor) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Setor.jsy"), "rw");
                String setorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 54);
                    setorLido = new String(Arrays.copyOf(recuperaTam(arq, 50), getTam()));
                    if (setor.equalsIgnoreCase(setorLido)) {
                        arq.seek(i * 54);
                        arq.write(Arrays.copyOf("".getBytes(), 54));
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover um setor do arquivo");
            }
        }
        return false;
    }

    @Override
    public boolean setSetor(Setor setor) throws Exception {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Setor.jsy"), "rw");
                arq.seek(setor.getCodigo() * 54);
                arq.write(Arrays.copyOf(setor.getNome().getBytes(), 50));
                arq.writeInt(setor.getCodigo());
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de setor");
            }
        }
        return false;
    }
}
