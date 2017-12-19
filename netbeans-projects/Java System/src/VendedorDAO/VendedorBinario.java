package VendedorDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Vendedor;

public class VendedorBinario extends LeituraBytes implements VendedorDAO {

    @Override
    public boolean arqVenVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Vendedor.jsy"), "rw");
                String vendedorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 937);
                    vendedorLido = new String(Arrays.copyOf(recuperaTam(arq, 937), getTam()));
                    if (!"".equals(vendedorLido)) {
                        arq.close();
                        return false;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de vendedor");
            }
        }
        return true;
    }

    @Override
    public int getProxCodVen() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Vendedor.jsy"), "rw");
                String vendedorLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 937);
                    vendedorLido = new String(Arrays.copyOf(recuperaTam(arq, 937), getTam()));
                    if ("".equals(vendedorLido)) {
                        arq.close();
                        return i;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de vendedor");
            }
        }
        return 1;
    }

    @Override
    public Vendedor getVendedor(String cpf) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Vendedor.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 937);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if (cpfLido.equals(cpf)) {
                        Vendedor vendedor = new Vendedor();
                        vendedor.setCpf(cpfLido);
                        vendedor.setCodigo(arq.readInt());
                        vendedor.setCelularEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
                        vendedor.setEmailVendedor(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
                        vendedor.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 160), getTam())));
                        vendedor.setHomePage(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
                        vendedor.setOutasConsideracoes(new String(Arrays.copyOf(recuperaTam(arq, 600), getTam())));
                        vendedor.setDataCadas(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
                        vendedor.setUltAlteracao(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
                        vendedor.setComissao(arq.readDouble());
                        vendedor.setSalarioLiquido(arq.readDouble());
                        arq.close();
                        return vendedor;
                    }
                }
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de vendedor");
            }
        }
        return null;
    }

    @Override
    public List<Vendedor> listVendedor() throws Exception {
        List<Vendedor> lista = new ArrayList<Vendedor>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Vendedor.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 937);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if (!"".equals(cpfLido)) {
                        Vendedor vendedor = new Vendedor();
                        vendedor.setCpf(cpfLido);
                        vendedor.setCodigo(arq.readInt());
                        vendedor.setCelularEmpresa(new String(Arrays.copyOf(recuperaTam(arq, 13), getTam())));
                        vendedor.setEmailVendedor(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
                        vendedor.setDescricao(new String(Arrays.copyOf(recuperaTam(arq, 160), getTam())));
                        vendedor.setHomePage(new String(Arrays.copyOf(recuperaTam(arq, 50), getTam())));
                        vendedor.setOutasConsideracoes(new String(Arrays.copyOf(recuperaTam(arq, 600), getTam())));
                        vendedor.setDataCadas(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
                        vendedor.setUltAlteracao(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
                        vendedor.setComissao(arq.readDouble());
                        vendedor.setSalarioLiquido(arq.readDouble());
                        lista.add(vendedor);
                    }
                }
                arq.close();
            } catch (Exception e) {
                throw new Exception("Erro na leitura do arquivo de vendedor");
            }
        }
        return lista;
    }

    @Override
    public boolean removeVendedor(String cpf) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Vendedor.jsy"), "rw");
                String cpfLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 937);
                    cpfLido = new String(Arrays.copyOf(recuperaTam(arq, 14), getTam()));
                    if (cpf.equalsIgnoreCase(cpfLido)) {
                        arq.seek(i * 937);
                        arq.write(Arrays.copyOf("".getBytes(), 937));
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover um vendedor do arquivo");
            }
        }
        return false;
    }

    @Override
    public boolean setVendedor(Vendedor vendedor) throws Exception {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Vendedor.jsy"), "rw");
                arq.seek(vendedor.getCodigo() * 937);
                arq.write(Arrays.copyOf(vendedor.getCpf().getBytes(), 14));
                arq.writeInt(vendedor.getCodigo());
                arq.write(Arrays.copyOf(vendedor.getCelularEmpresa().getBytes(), 13));
                arq.write(Arrays.copyOf(vendedor.getEmailVendedor().getBytes(), 50));
                arq.write(Arrays.copyOf(vendedor.getDescricao().getBytes(), 160));
                arq.write(Arrays.copyOf(vendedor.getHomePage().getBytes(), 50));
                arq.write(Arrays.copyOf(vendedor.getOutasConsideracoes().getBytes(), 600));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(vendedor.getDataCadas()).getBytes(), 15));
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(vendedor.getUltAlteracao()).getBytes(), 15));
                arq.writeDouble(vendedor.getComissao());
                arq.writeDouble(vendedor.getSalarioLiquido());
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de vendedor");
            }
        }
        return false;
    }
}
